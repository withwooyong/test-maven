[toc]
## 통합 API 서버군 구성
### Application Version
- jdk : Java(TM) SE Runtime Environment (build 1.6.0_35-b10)
- web server : nginx-1.4.1
- was : apache-tomcat-7.0.42

### 서버구성
통합 API 서버군은 총 14대의 서버로 구성이 되어 있으며, api01 ~ api08에는 각 서버당 nginx + tomcat(cms:6ea, infra:2ea)이 설치 되어 있고, api09 ~ api14에는 tomcat(cms:6ea - community, operator 요소)으로 구성되고 있다. 각 서버의 proxy 구성은 아래의 표와 같다.  
cms was port는 8081 ~ 8086, infra was port는 9091 ~ 9092를 사용하고 있다.

|host|ip|web server|infra|cms(contents)|cms(operator/community)|jennifer|기타|
|-----|-----|-----|-----|-----|-----|-----|-----|
|api01|119.149.188.197|true|true|true|true|true|-|
|api02|119.149.188.198|true|true|true|true|true|-|
|api03|119.149.188.199|true|true|true|false|false|-|
|api04|119.149.188.200|true|true|true|false|false|-|
|api05|119.149.188.228|true|true|true|false|false|-|
|api06|119.149.188.206|true|true|true|false|false|-|
|api07|119.149.188.207|true|true|true|false|false|-|
|api08|119.149.188.208|true|true|true|false|false|-|
|api09|180.182.43.93|false|false|false|true|false|api03 연결|
|api10|180.182.43.94|false|false|false|true|false|api04 연결|
|api11|180.182.43.95|false|false|false|true|false|api05 연결|
|api12|180.182.43.96|false|false|false|true|false|api06 연결|
|api13|180.182.43.97|false|false|false|true|false|api07 연결|
|api14|180.182.43.98|false|false|false|true|false|api08 연결|

### Web Server
서버 설치 및 로그 관리 설명은 *[공통]Web Server 설치, 설정 – nginx*을 참조하면 되므로, 생략하고 서버설정 파일(nginx.conf)를 바탕으로 기술한다. 통합 API Web Sever 설정 파일은 아래와 같다. 
~~~~
user  tvingadmin tvingadmin;
worker_processes  24;

error_log  logs/error.log   crit;
pid        logs/nginx.pid;

worker_rlimit_nofile 20480;

events {
    worker_connections 2048;
    use epoll;
}

http {
    include       mime.types;
    default_type  application/octet-stream;

    log_format  main  '$remote_addr - $remote_user [$time_local] "$request" '
                      '$status $body_bytes_sent "$http_referer" '
                      '"$http_user_agent" "$http_x_forwarded_for"';

    access_log  logs/access.log  main;

    sendfile        on;
    tcp_nopush     on;

    #keepalive_timeout  0;
    #keepalive_timeout  65;
    keepalive_timeout  10 10;
    send_timeout 5;
    tcp_nodelay     on;

    gzip  on;
    gzip_min_length 1k;
    gzip_http_version 1.1;
    gzip_vary on;
    gzip_comp_level 4;
    gzip_proxied any;
    gzip_types text/plain text/css application/json application/x-javascript text/xml application/xml application/xml+rss text/javascript application/javascript text/x-js;
    gzip_buffers 16 8k;
    gzip_disable "MSIE [1-6]\.(?!.*SV1)";

    upstream cmsapi {
        #ip_hash;
        server 127.0.0.1:8081;
        server 127.0.0.1:8082;
        server 127.0.0.1:8083;
        server 127.0.0.1:8084;
        server 127.0.0.1:8085;
        server 127.0.0.1:8086;
        keepalive 10;
    }

    upstream cmsapi2 {
        server 180.182.43.93:8081;
        server 180.182.43.93:8082;
        server 180.182.43.93:8083;
        server 180.182.43.93:8084;
        server 180.182.43.93:8085;
        server 180.182.43.93:8086;
        keepalive 10;
    }

    upstream infraapi{
        server 127.0.0.1:9091;
        server 127.0.0.1:9092;
        keepalive 10;
    }

    server {
        listen       80;
        server_name  localhost;
        #server_name     api.tving.com;
        root    /home/tvingadmin/app/cms_api;

        charset UTF-8;
        rewrite_log on;

        client_max_body_size 10M;

        location ~ ^/$ {
            if (-f /index.html){
                rewrite (.*) /index.html last;
            }
            proxy_pass      http://cmsapi;
        }

        location ~ .*\.(gif|jpg|jpeg|png|bmp|swf)$ {
            expires      30d;
        }

        location ~ .*\.(js|css)?$ {
            expires      1h;
        }

        location ~ .html {
            root /home/tvingadmin/app/cms_api;
        }

        location ~* ^.+\.(jpg|jpeg|gif|png|ico|css|zip|tgz|gz|rar|bz2|doc|xls|exe|pdf|ppt|txt|tar|mid|midi|wav|bmp|rtf|js|mov)$ {
            root /home/tvingadmin/app/cms_api;
        }

        location ~^/v1/(operator|community)/ {
                proxy_pass              http://cmsapi;
                proxy_redirect          off;
                proxy_set_header        Host    $host;
                proxy_set_header        X-Real-IP       $remote_addr;
                proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_set_header        X-Forwarded-Proto  http;
        }

        location ~^/v1/(media|api|micro|lol|member|asp)/ {
                proxy_pass              http://cmsapi;
                proxy_redirect          off;
                proxy_set_header        Host    $host;
                proxy_set_header        X-Real-IP       $remote_addr;
                proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
        }

        location ~^/v1/(user|bill) {
                proxy_pass              http://infraapi;
                proxy_redirect          off;
                proxy_set_header        Host    $host;
                proxy_set_header        X-Real-IP       $remote_addr;
                proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
        }

        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }


  server {
        listen       443;
        server_name api.tving.com;

        ssl                  on;
        ssl_certificate      /home/tvingadmin/server/nginx/ssl/star.tving.com.pem;
        ssl_certificate_key  /home/tvingadmin/server/nginx/ssl/star.tving.com.key;

        ssl_session_timeout  5m;

        ssl_protocols  SSLv2 SSLv3 TLSv1;
       #ssl_ciphers  HIGH:!aNULL:!MD5;
        ssl_ciphers ALL:!ADH:!EXPORT56:RC4+RSA:+HIGH:+MEDIUM:+LOW:+SSLv2:+EXP;
        ssl_prefer_server_ciphers   on;

        root    /home/tvingadmin/app/cms_api;
        charset UTF-8;
 
        rewrite_log on;
        client_max_body_size 10M;

        location ~ ^/$ {
                if (-f /index.html){
                        rewrite (.*) /index.html last;
                }
                proxy_pass      http://cmsapi;
        }
        location ~ .html {
                root /home/tvingadmin/app/cms_api;
        }

        location ~ .*\.(gif|jpg|jpeg|png|bmp|swf)$ {
              expires      30d;
        }

        location ~ .*\.(js|css)?$ {
              expires      1h;
        }
        location ~* ^.+\.(jpg|jpeg|gif|png|ico|css|zip|tgz|gz|rar|bz2|doc|xls|exe|pdf|ppt|txt|tar|mid|midi|wav|bmp|rtf|js|mov)$ {
          root /home/tvingadmin/app/cms_api;
        }
        location ~^/v1/(operator|community)/ {
                proxy_pass              http://cmsapi;
                proxy_redirect          off;
                proxy_set_header        Host    $host;
                proxy_set_header        X-Real-IP       $remote_addr;
                proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
                proxy_set_header        X-Forwarded-Proto  http;
        }
        location ~^/v1/(media|api|micro|lol|member|asp)/ {
                proxy_pass              http://cmsapi;
                proxy_redirect          off;
                proxy_set_header        Host    $host;
                proxy_set_header        X-Real-IP       $remote_addr;
                proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
        }
        location ~^/v1/(user|bill) {
                proxy_pass              http://infraapi;
                proxy_redirect          off;
                proxy_set_header        Host    $host;
                proxy_set_header        X-Real-IP       $remote_addr;
                proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
        }
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
}
~~~~
통합 API의 Web Server는 주요 사용이 WAS에 대한 load balancing 처리와 URI 패턴에 대한 분배처리이다. 각 WAS의 기동/중지 시 *tvingadmin*으로 동작이 됨에 따라 서버 프로세스의 사용자/그룹은 *tvingadmin*으로 기동을 한다. 이에 대한 설정은 nginx.conf 최상단에 선언되어 있다. 최초 기동시 nginx 서버하위 경로에 *_temp와 logs 경로의 사용자/그룹 권한 또한 변경을 한다. 특히 *proxy_temp*의 경우 response 결과가 많을 경우 일부가 누락이 되는 현상이 발견 됨에 따라 권한 변경을 권고한다.
~~~~
cd /home/tvingadmin/server/nginx  
chown -R *_temp tvnigadmin:tvingadmin  
chown -R logs tvnigadmin:tvingadmin
~~~~

### WAS
cms was와 infra was의 설정은 사용 port(server, connection), oracle jndi 수치, deploy 옵션 등이 차이가 있으며, tomcat_api/conf/server.xml, tomcat_infra/conf/server.xml은 아래와 같다. 튜닝 포인트는 executor, connnector (protocol, acceptCount 등), compress와 log pattern 의 remoteIp 변경이다.

~~~~
<?xml version='1.0' encoding='utf-8'?>
<Server port="8015" shutdown="SHUTDOWN">

    <Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="on" />
    <Listener className="org.apache.catalina.core.JasperListener" />
    <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" />
    <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener" />
    <Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener" />

    <GlobalNamingResources>
        <Resource name="ds_ufrt" auth="Container" type="javax.sql.DataSource" 
                  factory="org.apache.tomcat.jdbc.pool.DataSourceFactory" 
                  maxActive="35" minIdle="30" initialSize="25" maxWait="10000"  
                  validationQuery="SELECT 1 FROM DUAL" driverClassName="oracle.jdbc.driver.OracleDriver" defaultAutoCommit="false"
                  username="U_FRT" password="*****" 
                  url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=172.18.144.12)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=172.18.144.22)(PORT=1521))(LOAD_BALANCE = Y
ES)(FAILOVER=ON)(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=TVING)))" />
    </GlobalNamingResources>

    <Service name="Catalina">
        <Executor name="tomcatThreadPool" namePrefix="catalina-exec-" maxThreads="1500" minSpareThreads="100"/>
        <Connector executor="tomcatThreadPool" port="8081" protocol="org.apache.coyote.http11.Http11NioProtocol"
                acceptCount="10" enableLookups="false"
                connectionTimeout="10000" maxConnections="10000"
                maxKeepAliveRequests="1"  maxThreads="500" tcpNoDelay="true"                
                redirectPort="8443"  URIEncoding="UTF-8"
                compression="on" compressionMinSize="1024"
                compressableMimeType="text/html,text/xml,text/plain,application/javascript,application/json,text/javascript" />

        <Engine name="Catalina" defaultHost="localhost" jvmRoute="api1">

            <Host name="localhost"  appBase="/home/tvingadmin/app/cms_api" unpackWARs="false" autoDeploy="false">
                <Context path="" distributable="true" docBase="/home/tvingadmin/app/cms_api" privileged="true" reloadable="false">
                        <ResourceLink global="ds_ufrt" name="ds_ufrt" type="javax.sql.DataSource" />
                </Context>
                    <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs" prefix="api1_access_log." suffix=".txt" pattern="%{X-Forwarded-For}i %l %u %t &quot
;%r&quot; %s %b &quot;%{User-agent}i&quot;" />
                    <Alias>api.tving.com</Alias>
            </Host>
        </Engine>
    </Service>
</Server>


<?xml version='1.0' encoding='utf-8'?>
<Server port="9015" shutdown="SHUTDOWN">
    <Listener className="org.apache.catalina.core.AprLifecycleListener" SSLEngine="on" />
    <Listener className="org.apache.catalina.core.JasperListener" />
    <Listener className="org.apache.catalina.core.JreMemoryLeakPreventionListener" />
    <Listener className="org.apache.catalina.mbeans.GlobalResourcesLifecycleListener" />
    <Listener className="org.apache.catalina.core.ThreadLocalLeakPreventionListener" />
    <GlobalNamingResources>

        <Resource name="ds_ufrt" auth="Container" type="javax.sql.DataSource" 
                  factory="org.apache.tomcat.jdbc.pool.DataSourceFactory" 
                  maxActive="75" minIdle="55" initialSize="50" maxWait="10000"  
                  validationQuery="SELECT 1 FROM DUAL" driverClassName="oracle.jdbc.driver.OracleDriver" defaultAutoCommit="false"
                  testWhileIdle="true"
                  username="U_FRT" password="******" 
                  url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=172.18.144.12)(PORT=1521))(ADDRESS=(PROTOCOL=TCP)(HOST=172.18.144.22)(PORT=1521))(LOAD_BALANCE = Y
ES)(FAILOVER=ON)(CONNECT_DATA=(SERVER=DEDICATED)(SERVICE_NAME=TVING)))" />

    </GlobalNamingResources>

    <Service name="Catalina">
        <Executor name="tomcatThreadPool" namePrefix="catalina-exec-" maxThreads="1500" minSpareThreads="100"/>
        <Connector executor="tomcatThreadPool" port="9091" protocol="org.apache.coyote.http11.Http11NioProtocol"
                acceptCount="10" enableLookups="false"
                connectionTimeout="10000" maxConnections="10000"
                maxKeepAliveRequests="1"  maxThreads="500" tcpNoDelay="true"
                redirectPort="8443"  URIEncoding="UTF-8"
                compression="on" compressionMinSize="1024" 
                compressableMimeType="text/html,text/xml,text/plain,application/javascript,application/json,text/javascript" />


        <Engine name="Catalina" defaultHost="localhost" jvmRoute="infra1">
            <Host name="localhost"  appBase="/home/tvingadmin/app/infra_api" unpackWARs="false" autoDeploy="false"  deployIgnore=".*">
                <Context path="" distributable="true" docBase="im_infra_api" privileged="true" reloadable="false">
                        <ResourceLink global="ds_ufrt" name="ds_ufrt" type="javax.sql.DataSource" />
                </Context>
                <Valve className="org.apache.catalina.valves.AccessLogValve" directory="logs" prefix="infra1_access_log." suffix=".txt" pattern="%{X-Forwarded-For}i %l %u %t &quot;%
r&quot; %s %b &quot;%{User-agent}i&quot;" />
                <Alias>api.tving.com</Alias>
            </Host>
        </Engine>
    </Service>
</Server>

~~~~


### 로그관리
*[공통]Web Server 설치, 설정 – nginx*와 *[공통] Was 설치/설정* 부분의 로그관리를 참조함.