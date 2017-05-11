[toc]
## 이미지 서버군 구성
### Application version (2017년 5월 기준)
 - nginx master : nginx-1.12.0 (Reverse Proxy Server)
 - nginx slave01 : nginx-1.12.0 (Web Application Server)
 - nginx slave02 : nginx-1.12.0 (Web Application Server)
 - php : php-7.1.4 (이미지 리사이즈)

### 서버구성
서버당 4개의 인스턴스
각 서버 인스턴스별 사용은 아래와 같다.

|no|ip|port|인스턴스 명|기능|
|------|------|------|------|------|
|01|127.0.0.1|80|nginx-master|로드밸런싱|
|02|127.0.0.1|8001|nginx-slave01|요청처리#1|
|03|127.0.0.1|8002|nginx-slave02|요청처리#2|
|04|127.0.0.1|9000|php-fpm|이미지 리사이즈|

### 서버설치
*[공통]Web Server 설치, 설정 – nginx*와 비슷하나 서버 기동스크립트 등이 약간 차이가 있고, 이미지 리사이즈 처리를 위해 php를 설치한다.
설치 과정은 아래와 같다.

#### 설치설정
~~~~

 - nginx download & copy
cd /home/image/server/
wget http://nginx.org/download/nginx-1.12.0.tar.gz
cp -R nginx-[version] nginx-1.12.0_master  
cp -R nginx-[version] nginx-1.12.0_slave01  
cp -R nginx-[version] nginx-1.12.0_slave02  

 - nginx configure
/home/image/server/nginx-[version]_master/configure --prefix=/home/image/server/nginx-1.12.0_master --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module  

/home/image/server/nginx-[version]_slave01/configure --prefix=/home/image/server/nginx-1.12.0_slave01 --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module  

/home/image/server/nginx-[version]_slave02/configure --prefix=/home/image/server/nginx-1.12.0_slave02 --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module  

 - nginx make install
cd /home/image/server/nginx-1.12.0_master/  
make && make installl  

cd /home/image/server/nginx-1.12.0_slave01/  
make && make install  

cd /home/image/server/nginx-1.12.0_slave02/  
make && make install  

 - resize server  
cd /home/image/server/
wget http://kr1.php.net/get/php-7.1.4.tar.gz/from/this/mirror  
tar xvfz php-7.1.4.tar.gz  

cd php-[version]  
./configure \
--prefix=/home/image/server/php-7.1.4 \
--with-config-file-path=/home/image/server/php-7.1.4/etc \
--with-config-file-scan-dir=/home/image/server/php-7.1.4/etc/conf.d \
--disable-debug \
--enable-fpm \
--enable-bcmath \
--enable-exif \
--enable-ftp \
--enable-gd-native-ttf \
--enable-inline-optimization \
--enable-intl \
--enable-mbregex \
--enable-mbstring \
--enable-mod-charset \
--enable-sigchild \
--enable-soap \
--enable-sockets \
--enable-sysvsem=yes \
--enable-sysvshm=yes \
--enable-xml \
--enable-zip \
--with-bz2 \
--with-iconv \
--with-curl \
--with-zlib \
--with-gd \
--with-gettext \
--with-ldap \
--with-mcrypt \
--with-mhash \
--with-mysql \
--with-mysqli \
--with-openssl \
--with-xmlrpc \
--with-freetype-dir=/usr/include/freetype2 \
--with-jpeg-dir=/usr/lib \
--with-libxml-dir=/usr/lib \
--with-png-dir=/usr/lib \
--with-zlib-dir=/usr/lib \
--with-fpm-user=image \
--with-fpm-group=image

make && make install
~~~~

#### 심볼릭 링크 설정 및 실행쉘명칭 변경
service 등록 시 각각의 고유명칭으로 기동/중단/재기동을 하기 위하여 아래와 같이 각각의 경로 하위의 */sbin/nginx*를 변경한다.
~~~~
cd /home/image/server
ln -s nginx-1.12.0_master nginx_image_master
ln -s nginx-1.12.0_slave01 nginx_image_slave01
ln -s nginx-1.12.0_slave02 nginx_image_slave02

각 경로의 /sbin/nginx
mv nginx nginx_master
mv nginx nginx_slave01
mv nginx nginx_slave02
~~~~

#### 서비스 등록
각 개별 서비스 등록을 위해 */etc/init.d* 하위에 *root*의 계정으로 nginx_master, nginx_slave01, nginx_slave02 를 아래의 스크립트로 만든다. 주의점은 위와 같이 쉘명을 변경하였기 때문에 각 쉘명을 기입한다.


[nignx_master]
~~~~
#!/bin/sh
#
# nginx - this script starts and stops the nginx daemon
#
# chkconfig:   - 85 15 
# description:  Nginx is an HTTP(S) server, HTTP(S) reverse \
#               proxy and IMAP/POP3 proxy server
# processname: nginx
# config:      /etc/nginx/nginx.conf
# config:      /etc/sysconfig/nginx
# pidfile:     /var/run/nginx.pid

# Source function library.
. /etc/rc.d/init.d/functions

# Source networking configuration.
. /etc/sysconfig/network

# Check that networking is up.
[ "$NETWORKING" = "no" ] && exit 0

nginx="/home/image/server/nginx_image_master/sbin/nginx_master" 
prog=$(basename $nginx)

NGINX_CONF_FILE="/home/image/server/nginx_image_master/conf/nginx.conf" 

[ -f /etc/sysconfig/nginx ] && . /etc/sysconfig/nginx

lockfile=/var/lock/subsys/nginx_master

make_dirs() {
   # make required directories
   user=`nginx -V 2>&1 | grep "configure arguments:" | sed 's/[^*]*--user=\([^ ]*\).*/\1/g' -`
   options=`$nginx -V 2>&1 | grep 'configure arguments:'`
   for opt in $options; do
       if [ `echo $opt | grep '.*-temp-path'` ]; then
           value=`echo $opt | cut -d "=" -f 2`
           if [ ! -d "$value" ]; then
               # echo "creating" $value
               mkdir -p $value && chown -R $user $value
           fi
       fi
   done
}

start() {
    [ -x $nginx ] || exit 5
    [ -f $NGINX_CONF_FILE ] || exit 6
    make_dirs
    echo -n $"Starting $prog: " 
    daemon $nginx -c $NGINX_CONF_FILE
    retval=$?
    echo
    [ $retval -eq 0 ] && touch $lockfile
    return $retval
}

stop() {
    echo -n $"Stopping $prog: " 
    killproc $prog -QUIT
    retval=$?
    echo
    [ $retval -eq 0 ] && rm -f $lockfile
    return $retval
}

restart() {
    configtest || return $?
    stop
    sleep 1
    start
}

reload() {
    configtest || return $?
    echo -n $"Reloading $prog: " 
    killproc $nginx -HUP
    RETVAL=$?
    echo
}

force_reload() {
    restart
}

configtest() {
  $nginx -t -c $NGINX_CONF_FILE
}

rh_status() {
    status $prog
}

rh_status_q() {
    rh_status >/dev/null 2>&1
}

case "$1" in
    start)
        rh_status_q && exit 0
        $1
        ;;
    stop)
        rh_status_q || exit 0
        $1
        ;;
    restart|configtest)
        $1
        ;;
    reload)
        rh_status_q || exit 7
        $1
        ;;
    force-reload)
        force_reload
        ;;
    status)
        rh_status
        ;;
    condrestart|try-restart)
        rh_status_q || exit 0
            ;;
    *)
        echo $"Usage: $0 {start|stop|status|restart|condrestart|try-restart|reload|force-reload|configtest}" 
        exit 2
esac

~~~~

[nginx_slave01]
~~~~
#!/bin/sh
#
# nginx - this script starts and stops the nginx daemon
#
# chkconfig:   - 85 15 
# description:  Nginx is an HTTP(S) server, HTTP(S) reverse \
#               proxy and IMAP/POP3 proxy server
# processname: nginx
# config:      /etc/nginx/nginx.conf
# config:      /etc/sysconfig/nginx
# pidfile:     /var/run/nginx.pid

# Source function library.
. /etc/rc.d/init.d/functions

# Source networking configuration.
. /etc/sysconfig/network

# Check that networking is up.
[ "$NETWORKING" = "no" ] && exit 0

nginx="/home/image/server/nginx_image_slave01/sbin/nginx_slave01" 
prog=$(basename $nginx)

NGINX_CONF_FILE="/home/image/server/nginx_image_slave01/conf/nginx.conf" 

[ -f /etc/sysconfig/nginx ] && . /etc/sysconfig/nginx

lockfile=/var/lock/subsys/nginx_slave01

make_dirs() {
   # make required directories
   user=`nginx -V 2>&1 | grep "configure arguments:" | sed 's/[^*]*--user=\([^ ]*\).*/\1/g' -`
   options=`$nginx -V 2>&1 | grep 'configure arguments:'`
   for opt in $options; do
       if [ `echo $opt | grep '.*-temp-path'` ]; then
           value=`echo $opt | cut -d "=" -f 2`
           if [ ! -d "$value" ]; then
               # echo "creating" $value
               mkdir -p $value && chown -R $user $value
           fi
       fi
   done
}

start() {
    [ -x $nginx ] || exit 5
    [ -f $NGINX_CONF_FILE ] || exit 6
    make_dirs
    echo -n $"Starting $prog: " 
    daemon $nginx -c $NGINX_CONF_FILE
    retval=$?
    echo
    [ $retval -eq 0 ] && touch $lockfile
    return $retval
}

stop() {
    echo -n $"Stopping $prog: " 
    killproc $prog -QUIT
    retval=$?
    echo
    [ $retval -eq 0 ] && rm -f $lockfile
    return $retval
}

restart() {
    configtest || return $?
    stop
    sleep 1
    start
}

reload() {
    configtest || return $?
    echo -n $"Reloading $prog: " 
    killproc $nginx -HUP
    RETVAL=$?
    echo
}

force_reload() {
    restart
}

configtest() {
  $nginx -t -c $NGINX_CONF_FILE
}

rh_status() {
    status $prog
}

rh_status_q() {
    rh_status >/dev/null 2>&1
}

case "$1" in
    start)
        rh_status_q && exit 0
        $1
        ;;
    stop)
        rh_status_q || exit 0
        $1
        ;;
    restart|configtest)
        $1
        ;;
    reload)
        rh_status_q || exit 7
        $1
        ;;
    force-reload)
        force_reload
        ;;
    status)
        rh_status
        ;;
    condrestart|try-restart)
        rh_status_q || exit 0
            ;;
    *)
        echo $"Usage: $0 {start|stop|status|restart|condrestart|try-restart|reload|force-reload|configtest}" 
        exit 2
esac
~~~~

[php-fpm]
~~~~
### BEGIN INIT INFO
# Provides:          php-fpm
# Required-Start:    $remote_fs $network
# Required-Stop:     $remote_fs $network
# Default-Start:     2 3 4 5
# Default-Stop:      0 1 6
# Short-Description: starts php-fpm
# Description:       starts the PHP FastCGI Process Manager daemon
### END INIT INFO

prefix=/home/image/server/php-7.1.4
exec_prefix=${prefix}

php_fpm_BIN=${exec_prefix}/sbin/php-fpm
php_fpm_CONF=${prefix}/etc/php-fpm.conf
php_fpm_PID=${prefix}/var/run/php-fpm.pid

php_opts="--fpm-config $php_fpm_CONF --pid $php_fpm_PID"

wait_for_pid () {
        try=0
        while test $try -lt 35 ; do
                case "$1" in
                        'created')
                        if [ -f "$2" ] ; then
                                try=''
                                break
                        fi
                        ;;
                        'removed')
                        if [ ! -f "$2" ] ; then
                                try=''
                                break
                        fi
                        ;;
                esac

                echo -n .
                try=`expr $try + 1`
                sleep 1
        done
}

case "$1" in
        start)
                echo -n "Starting php-fpm "
                $php_fpm_BIN --daemonize $php_opts

                if [ "$?" != 0 ] ; then
                        echo " failed"
                        exit 1
                fi

                wait_for_pid created $php_fpm_PID
                if [ -n "$try" ] ; then
                        echo " failed"
                        exit 1
                else
                        echo " done"
                fi
        ;;

        stop)
                echo -n "Gracefully shutting down php-fpm "

                if [ ! -r $php_fpm_PID ] ; then
                        echo "warning, no pid file found - php-fpm is not running ?"
                        exit 1
                fi
                kill -QUIT `cat $php_fpm_PID`
                wait_for_pid removed $php_fpm_PID
                if [ -n "$try" ] ; then
                        echo " failed. Use force-quit"
                        exit 1
                else
                        echo " done"
                fi
        ;;

        status)
                if [ ! -r $php_fpm_PID ] ; then
                        echo "php-fpm is stopped"
                        exit 0
                fi

                PID=`cat $php_fpm_PID`
                if ps -p $PID | grep -q $PID; then
                        echo "php-fpm (pid $PID) is running..."
                else
                        echo "php-fpm dead but pid file exists"
                fi
        ;;

        force-quit)
                echo -n "Terminating php-fpm "

                if [ ! -r $php_fpm_PID ] ; then
                        echo "warning, no pid file found - php-fpm is not running ?"
                        exit 1
                fi

                kill -TERM `cat $php_fpm_PID`

                wait_for_pid removed $php_fpm_PID

                if [ -n "$try" ] ; then
                        echo " failed"
                        exit 1
                else
                        echo " done"
                fi
        ;;

        restart)
                $0 stop
                $0 start
        ;;

        reload)
                echo -n "Reload service php-fpm "
                if [ ! -r $php_fpm_PID ] ; then
                        echo "warning, no pid file found - php-fpm is not running ?"
                        exit 1
                fi
                kill -USR2 `cat $php_fpm_PID`
                echo " done"
        ;;
        *)
                echo "Usage: $0 {start|stop|force-quit|restart|reload|status}"
                exit 1
        ;;

esac

~~~~
[서비스 등록]
~~~~
chkconfig --add nginx_master
chkconfig --add nginx_slave01
chkconfig --add nginx_slave02
chkconfig --add php-fpm
~~~~
[서버 재기동시 자동 스타트 업]
~~~~
chkconfig --level 2345 nginx_master on
chkconfig --level 2345 nginx_slave01 on
chkconfig --level 2345 nginx_slave02 on

~~~~

#### 설정파일
이미지 서버는 4개의 nginx 인스턴스로 구성이 되어 있어 각 설정이 기존의 web 서버설정 파일과는 다르다. 아래와 같이 로드밸러싱 역할을 하는 nginx_master와 실제 요청에 대한 처리를 담당하는 nginx_slave로 크게 나뉘며, nginx_slave는 포트번호를 제외하고 내용이 동일하다.  stillshot-origin의 요청의 경우 404에 대한 에러처리 대신에 400 에러를 리턴해 준다.(차호상님 요청사항) 또한 이미지 리사이징 요청의 경우는 proxy-cache를 적용하여 동일 요청의 경우 cache 처리함.


[nginx_master/conf/nginx.conf]
~~~~
user  image;
worker_processes  8;

error_log  logs/error.log;

pid        logs/nginx_master.pid;

worker_rlimit_nofile 40960;
events {
    worker_connections 5120;
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
    #tcp_nopush     on;

    keepalive_timeout  5 5;
    keepalive_requests 5; 
    #keepalive_timeout  65;
    gzip  on;
    gzip_min_length 1k;
    gzip_buffers 4 8k;
    gzip_http_version 1.1;
    gzip_comp_level 2;
    gzip_types  text/plain application/x-javascript text/css application/xml;
    #gzip_types  text/plain text/css image/png image/gif image/jpeg application/x-javascript text/xml application/xml application/xml+rss text/javascript;
    gzip_vary on;
    gzip_disable "MSIE [1-6]\.(?!.*SV1)";

    upstream tving_image{
                #ip_hash;
                server 127.0.0.1:8081;
                server 127.0.0.1:8082;
                keepalive 10;
    }

    #fastcgi cache path define
    fastcgi_cache_path /home/image/server/nginx_image_master/cache levels=1:2 keys_zone=one:100m inactive=60m max_size=300M;

    server {
        listen       80;
        server_name  localhost;

        #       root    /home/image/www;
        charset UTF-8;
        rewrite_log on;

        # file size
        client_max_body_size 100M;

        location = /favicon.ico {
            return 204;
            access_log     off;
            log_not_found  off;
        }

#        location ~* ^.+\.(jpg|jpeg|gif|png|ico|css|zip|tgz|gz|rar|bz2|doc|xls|exe|pdf|ppt|txt|tar|mid|midi|wav|bmp|rtf|js|mov)$ {
#               proxy_pass              http://tving_image;
#                proxy_redirect          off;
#                proxy_set_header        Host    $host;
#                proxy_set_header        X-Real-IP       $remote_addr;
#                proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
#        }

        location ~^/thumbnail/{
                root /images;
                expires      5s;
                try_files $uri @notfound;
        }

        location @notfound {
                return 404;
        }

        location ~ \.php$ {
            root           /home/image/app;
            include        fastcgi.conf;

            fastcgi_cache_key "$scheme$host$request_uri$cookie_user";
            fastcgi_cache one;
            fastcgi_cache_valid 200 302 1m;
            fastcgi_cache_min_uses 1;

            fastcgi_pass   127.0.0.1:9000;
            fastcgi_index  index.php;
            #fastcgi_param  SCRIPT_FILENAME  /scripts$fastcgi_script_name;
            include        fastcgi_params;
        }

        location ~* ^ {
                proxy_pass              http://btv_image;
                proxy_redirect          off;
                proxy_set_header        Host    $host;
                proxy_set_header        X-Real-IP       $remote_addr;
                proxy_set_header        X-Forwarded-For $proxy_add_x_forwarded_for;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
}

~~~~
[nginx_slave/conf/nginx.conf]
~~~~
user  image;
worker_processes  8;

error_log  logs/error.log;

pid        logs/nginx_slave01.pid;

worker_rlimit_nofile 20480;

events {
    #worker_connections  1024;
    worker_connections 2560;
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

    keepalive_timeout  5 5;
    keepalive_requests 5;

    gzip  on;
    gzip_min_length 1k;
    gzip_buffers 4 8k;
    gzip_http_version 1.1;
    gzip_comp_level 2;
    gzip_types  text/plain application/x-javascript text/css application/xml;
    #gzip_types  text/plain text/css image/png image/gif image/jpeg application/x-javascript text/xml application/xml application/xml+rss text/javascript;
    #gzip_vary on;
    gzip_disable "MSIE [1-6]\.(?!.*SV1)";

    server {
        listen       8081;
        server_name  localhost;
        charset UTF-8;
        
        root    /images;

        # file size
        client_max_body_size 100M;
 
        #access_log  logs/host.access.log  main;
        rewrite_log on;

        location ~ ^/$ {
                if (-f /index.html){
                        rewrite (.*) /index.html last;
                }
        }

        location ~ .*\.(gif|jpg|jpeg|png|bmp)$ {
                  expires      10s;
        }

        location ~ .*\.(js|css|swf)?$ {
                  expires      5m;
        }

        location ~ .html {
                root /images;
        }

        #location ~* ^.+\.(jpg|jpeg|gif|png|ico|css|zip|tgz|gz|rar|bz2|doc|xls|exe|pdf|ppt|txt|tar|mid|midi|wav|bmp|rtf|js|mov)$ {
        #        root /home/image/www;
        #}

        location ^~ /upload/fe/profile/ {
                try_files $uri /theme/micro/common/img/player/profile_thmb_default.gif;
        }

        location ~* ^ {
                root /images;
        }

        error_page 400 403 404        /40x.html;
        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
}
~~~~

### 운영상 주의점
image 서버군의 VIP는 180.182.43.12번으로 172대역으로 시작하는 사설IP 대역으로 묶여 있다. L4의 알고리즘은 round robin 방식이며, 80/443에 대해 health check가 되고 있다. 이에 따라 서버인스턴스 다운 시 nginx_master, nginx_slave01/02 순으로 다운하고 기동시에는 반대로 진행을 해야 한다.

 - 기동 : *root*의 계정으로 실행
~~~~
service nginx_slave01 start
service nginx_slave02 start
service nginx_master start
~~~~

 - 중단 : *root*의 계정으로 실행
~~~~
service nginx_master stop
service nginx_slave01 stop
service nginx_slave02 stop
~~~~

 - 재기동 : *root*의 계정으로 실행
~~~~
service nginx_slave01 restart
service nginx_slave02 restart
service nginx_master restart
~~~~

