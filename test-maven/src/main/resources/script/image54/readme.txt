Apache Bench (https://httpd.apache.org/docs/current/programs/ab.html)
100 requests using 5 concurrent connections:
ab -n 100 -c 5 http://1.255.144.54/media/768/T30222_src.jpg
http://ohgyun.com/455
` -n 100 -c 5`는, 
모두 100번의 요청을 보내는데, '동시에 처리하는 요청'의 수를 5개로 유지한다는 의미이다.
크기가 5인 요청 풀이 있고, 100개의 요청을 순서대로 보낸다고 생각하면 된다.
###############################################################################
    server {
        listen       80;
        server_name  localhost;

        charset UTF-8;

        #access_log  logs/host.access.log  main;

        location ~ .html {
            root /home/manager/server/app;
            index index.html index.htm;
        }

        location = /favicon.ico {
            return 204;
            access_log     off;
            log_not_found  off;
        }


        location ~ "^/media/(?<width>\d+)/(?<image>.+)$" {
            alias /home/manager/server/images/$image;
            image_filter resize $width -;
            image_filter_jpeg_quality 75;
            image_filter_buffer 8M;
        }

        #error_page  404              /404.html;

        # redirect server error pages to the static page /50x.html
        #
        error_page   500 502 503 504  /50x.html;
        location = /50x.html {
            root   html;
        }
    }
###############################################################################
server {
    # Internal image resizing server.
    server_name localhost;
    listen 8888;

    location ~ "^/media/(?<width>\d+)/(?<image>.+)$" {
        alias /var/www/images/$image;
        image_filter resize $width -;
        image_filter_jpeg_quality 75;
        image_filter_buffer 8M;
    }
}

proxy_cache_path /tmp/nginx-images-cache/ levels=1:2 keys_zone=images:10m inactive=24h max_size=100m;

server {
    # Public-facing cache server.
    server_name example.com;

    # Only serve widths of 768 or 1920 so we can cache effectively.
    location ~ "^/media/(?<width>(768|1920))/(?<image>.+)$" {
        # Proxy to internal image resizing server.
        proxy_pass http://localhost:8888/media/$width/$image;
        proxy_cache images;
        proxy_cache_valid 200 24h;
    }

    location /media {
        # Nginx needs you to manually define DNS resolution when using
        # variables in proxy_pass. Creating this dummy location avoids that.
        # The error is: "no resolver defined to resolve localhost".
        proxy_pass http://localhost:8888/;
    }
}
###############################################################################


http://1.255.144.54/resize/300/stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg

/home/manager/server/nginx_master/sbin/nginx -s stop
/home/manager/server/nginx_master/sbin/nginx -c /home/manager/server/nginx_master/conf/nginx.conf
/home/manager/server/nginx_slave01/sbin/nginx -s stop
/home/manager/server/nginx_slave01/sbin/nginx -c /home/manager/server/nginx_slave01/conf/nginx.conf
ps -ef | grep nginx

tail -f /home/manager/server/nginx_master/logs/*.log
tail -f /home/manager/server/nginx_slave01/logs/*.log

http://1.255.144.54/media/768/T30222_src.jpg
http://1.255.144.54/media/1920/T30222_src.jpg
http://1.255.144.54/resize/300/http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg

http://myproxydomain.com/resize/300/http://example.com/image.jpg
merge_slashes off;

    location ~ /(?<r>http://.*) {
        resolver 127.0.0.1;
        proxy_pass $r;
    }

http://1.255.144.53/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg&w=328
http://1.255.144.53/resize/300/http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg

location ~ "^/resize/(?<width>)/(?<image>.+)$" {
            proxy_pass  http://$image;
            proxy_set_header Host      $host;
            proxy_set_header X-Real-IP $remote_addr;
            proxy_set_header X-Forwarded-Proto $scheme;
            proxy_buffer_size 512k;
            proxy_buffers 16 512k;
            image_filter resize $width -;
            image_filter_jpeg_quality 75;
            image_filter_buffer 8M;
        }



location ~^/resize/(\d+)/(.+)$   {
    proxy_pass  $2;
    proxy_set_header Host      $host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-Proto $scheme;
    proxy_buffer_size 512k;
    proxy_buffers 16 512k;
    image_filter resize $1 $2;
}

location ~^/photo/thumb/(\d+)x(\d+)/(.+)$   {
    proxy_pass  http://stimage.hanafostv.com:8080/photo/original/$3;
    proxy_set_header Host      $host;
    proxy_set_header X-Real-IP $remote_addr;
    proxy_set_header X-Forwarded-Proto $scheme;
    proxy_buffer_size 512k;
    proxy_buffers 16 512k;
    image_filter resize $1 $2;
}


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


출처: http://czar.tistory.com/1017 [너는 개발자냐?]
centos - nginx 설치 후 서비스 등록
분류없음 2016.01.05 13:18
Knowledge Center Home
Cloud Hosting
Cloud Servers
CentOS - Adding an Nginx init script
Have Feedback?
We love customer feedback. Help us improve our products and service by leaving yours.
Rackspace Product Feedback
CentOS - Adding an Nginx init script
Article ID: 271
Last updated on August 22, 2014
Authored by: Rackspace Support
If you decided to install Nginx via source (see the previous article) you would have the latest and greatest version.
However, one disadvantage of installing from source is that init scripts are not created. No problem, let's go ahead and create one for easy control of Nginx and to ensure it restarts on a reboot.
Contents
1 Assumption
2 Stop
3 Init script
4 Execute
5 Chkconfig
6 Start, Stop and Restart
7 Summary
Assumption
I am assuming you have followed the previous article and installed Nginx from source.
If you have used other options or have placed the Nginx binary in a directory other than /usr/local/sbin/ then you will need to adjust the script shown below to match your installation.
Stop
If you have Nginx running then stop the process using:
sudo kill `cat /usr/local/nginx/logs/nginx.pid`
Init script
The script I use below is from a CentOS yum install and has been adapted to take into account our custom install of Nginx.
Let's go ahead and create the script:
sudo nano /etc/init.d/nginx
Inside the blank file place the following:
#!/bin/sh
#
# nginx - this script starts and stops the nginx daemin
#
# chkconfig:   - 85 15 
# description:  Nginx is an HTTP(S) server, HTTP(S) reverse \
#               proxy and IMAP/POP3 proxy server
# processname: nginx
# config:      /usr/local/nginx/conf/nginx.conf
# pidfile:     /usr/local/nginx/logs/nginx.pid
 
# Source function library.
. /etc/rc.d/init.d/functions
 
# Source networking configuration.
. /etc/sysconfig/network
 
# Check that networking is up.
[ "$NETWORKING" = "no" ] && exit 0
 
nginx="/usr/local/nginx/sbin/nginx"
prog=$(basename $nginx)
 
NGINX_CONF_FILE="/usr/local/nginx/conf/nginx.conf"
 
lockfile=/var/lock/subsys/nginx
 
start() {
    [ -x $nginx ] || exit 5
    [ -f $NGINX_CONF_FILE ] || exit 6
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
There's not really the space to go into the workings of the script but suffice to say, it defines where the main Nginx binary and pid files are located so Nginx can be started correctly.
Execute
As the init file is a shell script, it needs to have executable permissions.
We set them like so:
sudo chmod +x /etc/init.d/nginx
Chkconfig
Now we have the base script prepared, we need to add it to the default run levels:
sudo /sbin/chkconfig nginx on
Let's check our work to confirm:
sudo /sbin/chkconfig --list nginx
nginx           0:off   1:off   2:on    3:on    4:on    5:on    6:off
Done.
The script will now be called on a reboot so Nginx will automatically start.
Start, Stop and Restart
Now we can start, stop, restart, and reload Nginx using these commands:
sudo /etc/init.d/nginx start
...
sudo /etc/init.d/nginx stop
...
sudo /etc/init.d/nginx restart
...
sudo /etc/init.d/nginx reload
You can also check the current status as well as the configuration syntax:
sudo /etc/init.d/nginx status
...
sudo /etc/init.d/nginx configtest
Summary
Adding a process to the run levels like this saves a lot of frustration and effort, not only in manually starting and stopping the process, but it having it automatically start on a reboot.
