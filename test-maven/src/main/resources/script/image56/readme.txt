# yum update
# yum install epel-release
# rpm -Uvh https://mirror.webtatic.com/yum/el7/webtatic-release.rpm
# php -v
# yum install php70w-xml php70w-xmlrpc php70w-soap php70w-mbstring php70w-json php70w-gd php70w-mcrypt php70w-mysql
# systemctl restart httpd

yum --enablerepo=remi-php71 install -y php php-fpm php-mysql php-gd php-common php-cli php-json php-opcache php-devel php-imagick php-mbstring php-mcrypt php-mysqlnd php-pear php-xml php-xmlrpc php-soap php-dba php-bcmath php-pdo php-ldap

/bin/ld: ext/facedetect/.libs/facedetect.o: undefined reference to symbol 'cvEqualizeHist'
/usr/local/lib/libopencv_imgproc.so.3.2: error adding symbols: DSO missing from command line
collect2: error: ld returned 1 exit status
make: *** [sapi/cli/php] 오류 1


http://techieroop.com/install-opencv-in-centos/
https://github.com/opencv/opencv/archive/2.4.13.2.tar.gz

sudo apt-get install build-essential

[compiler] sudo apt-get install build-essential
[required] sudo apt-get install cmake git libgtk2.0-dev pkg-config libavcodec-dev libavformat-dev libswscale-dev
[optional] sudo apt-get install python-dev python-numpy libtbb2 libtbb-dev libjpeg-dev libpng-dev libtiff-dev libjasper-dev libdc1394-22-dev

# http://techieroop.com/install-opencv-in-centos/
## 2) Installing Opencv from Source:
To install opencv from we need to install require and optional dependencies.

CMake : For configure the opencv installation.
Python-devel and Numpy : For Creating python extension.
GCC: For compilation.
$ yum install cmake
$ yum install python-devel numpy
$ yum install gcc gcc-c++

## Install GTK To support GUI features, Camera support (libdc1394, libv4l), Media Support (ffmpeg, gstreamer) etc.
$ yum install gtk2-devel
$ yum install libdc1394-devel
$ yum install libv4l-devel
$ yum install ffmpeg-devel
$ yum install gstreamer-plugins-base-devel

# apt-get install pkg-config python libjpeg62-dev libpng12-dev libtiff4-dev
sudo apt-get install libtbb2 libtbb-dev libjpeg-dev libpng-dev libtiff-dev libjasper-dev libdc1394-22-dev

## Optional Dependencies:
Install below pakcages if you need latest libraries of PNG, JPEG, JPEG2000, TIFF, WebP.
$ yum install libpng-devel libjpeg-turbo-devel jasper-devel openexr-devel libtiff-devel libwebp-devel

## Now we have finish the first step of opencv installation. In next step download the opencv source from https://github.com/opencv/opencv/archive/3.2.0.zip
wget https://github.com/opencv/opencv/archive/3.2.0.zip
unzip 3.2.0.zip
cd opencv-3.2.0

## Create a new directory build to compile opencv from source.
$ mkdir build
-- $ cd build
$ cmake -DCMAKE_BUILD_TYPE=RELEASE -DCMAKE_INSTALL_PREFIX=/usr/local ..
$ make
$ sudo make install


cmake -D CMAKE_BUILD_TYPE=RELEASE \
    -D CMAKE_INSTALL_PREFIX=/usr/local \
    -D OPENCV_EXTRA_MODULES_PATH=/home/manager/opencv_contrib/modules \
    -D INSTALL_C_EXAMPLES=OFF \
    -D INSTALL_PYTHON_EXAMPLES=ON \
    -D BUILD_EXAMPLES=ON \
    -D BUILD_OPENCV_PYTHON2=ON ..


yum --enablerepo=remi-php71 install -y php php-fpm php-mysql php-gd php-common php-cli php-json php-opcache php-devel php-imagick php-mbstring php-mcrypt php-mysqlnd php-pear php-xml php-xmlrpc php-soap php-dba php-bcmath php-pdo php-ldap

./configure \
--prefix=/home/manager/server/php-7.1.5 \
--with-config-file-path=/home/manager/server/php-7.1.5/etc \
--with-config-file-scan-dir=/home/manager/server/php-7.1.5/etc/conf.d \
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
--with-mcrypt \
--with-mhash \
--with-mysqli \
--with-xmlrpc \
--with-freetype-dir=/usr/include/freetype2 \
--with-jpeg-dir=/usr/lib \
--with-libxml-dir=/usr/lib \
--with-png-dir=/usr/lib \
--with-zlib-dir=/usr/lib \
--with-fpm-user=manager \
--with-fpm-group=manager


Wrote PEAR system config file at: /home/manager/server/php-7.1.5/etc/pear.conf
You may want to add: /home/manager/server/php-7.1.5/lib/php to your php.ini include_path
/home/manager/php-7.1.5/build/shtool install -c ext/phar/phar.phar /home/manager/server/php-7.1.5/bin
ln -s -f phar.phar /home/manager/server/php-7.1.5/bin/phar
Installing PDO headers:           /home/manager/server/php-7.1.5/include/php/ext/pdo/

cd /home/manager/server/
wget http://kr1.php.net/distributions/php-7.1.5.tar.gz
tar xvfz php-7.1.5.tar.gz
cd php-7.1.5
./configure \
--prefix=/home/manager/server/php-7.1.5 \
--with-config-file-path=/home/manager/server/php-7.1.5/etc \
--with-config-file-scan-dir=/home/manager/server/php-7.1.5/etc/conf.d \
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
--with-mcrypt \
--with-mhash \
--with-mysqli \
--with-xmlrpc \
--with-freetype-dir=/usr/include/freetype2 \
--with-jpeg-dir=/usr/lib \
--with-libxml-dir=/usr/lib \
--with-png-dir=/usr/lib \
--with-zlib-dir=/usr/lib \
--with-fpm-user=manager \
--with-fpm-group=manager \
--with-facedetect

sudo yum install jasper-devel-1.900.1-29.el7 instead of sudo yum install jasper-devel should do the trick

https://zetawiki.com/wiki/CentOS_yum_%ED%8C%A8%ED%82%A4%EC%A7%80_%EC%82%AD%EC%A0%9C
yum list installed java*jdk
yum remove java-1.7.0-openjdk.x86_64
yum -y downgrade jasper*


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

http://1.255.144.54/index.html
http://1.255.144.54/index2.html

http://1.255.144.53/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg&w=300

http://1.255.144.54/resize/300/stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg

php --ini | grep php.ini

./configure --prefix=/home/manager/server/php-7.1.5 --with-config-file-path=/home/manager/server/php-7.1.5/etc --with-config-file-scan-dir=/home/manager/server/php-7.1.5/etc/conf.d
 --disable-debug --enable-fpm --enable-bcmath --enable-exif --enable-ftp --enable-gd-native-ttf --enable-inline-optimization --enable-intl --enable-mbregex --enable-mbstring --enab
le-sigchild --enable-soap --enable-sockets --enable-sysvsem=yes --enable-sysvshm=yes --enable-xml --enable-zip --with-facedetect --with-bz2 --with-iconv --with-curl --with-zlib --with-gd --with-gett
ext --with-mcrypt --with-mhash --with-mysqli --with-openssl --with-openssl-dir=/usr/local/bin --with-xmlrpc --with-freetype-dir=/usr/include/freetype2 --with-jpeg-dir=/usr/lib --wi
th-libxml-dir=/usr/lib --with-png-dir=/usr/lib --with-zlib-dir=/usr/lib --with-fpm-user=manager --with-fpm-group=manager
make
make install

/etc/php.ini
[facedetect]
extension=/usr/lib64/php/modules/facedetect.so


cp php-fpm.conf.default php-fpm.conf
cd php-fpm.d
ls -ltr
cp www.conf.default www.conf
cd ../sbin
service php-fpm restart
./php-fpm


http://stimage.hanafostv.com:8080/ontv/10250/T30222_100x100.jpg
http://stimage.hanafostv.com:8080/zero/11000/Z329544.jpg
/ontv/10550/T11549.jpg


cp /usr/lib64/php/modules/facedetect.so /home/manager/server/php-7.1.5/lib/php/extensions/no-debug-non-zts-20160303
cp /home/manager/PHP-Facedetect-master/modules /home/manager/server/php-7.1.5/lib/php/extensions/no-debug-non-zts-20160303
tail -f /home/manager/server/nginx_master/logs/*.log

cp /home/manager/server/OpenCV/opencv-3.2.0/data/haarcascades/haarcascade_frontalface_alt.xml /home/manager/server/app/
phpize && ./configure && make && make install

- opencv 설치
http://techieroop.com/install-opencv-in-centos/

- 원본 
./configure --prefix=/home/manager/server/nginx_cache --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module  

- 이미지 라이브러리 추가
./configure --prefix=/home/manager/server/nginx_master --user=manager --group=manager
./configure --prefix=/home/manager/server/nginx_slave01 --user=manager --group=manager
./configure --prefix=/home/manager/server/nginx_slave02 --user=manager --group=manager

./configure --prefix=/home/manager/server/nginx_master --user=manager --group=manager --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module --with-http_image_filter_module --with-http_secure_link_module
./configure --prefix=/home/manager/server/nginx_slave01 --user=manager --group=manager --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module --with-http_image_filter_module --with-http_secure_link_module
./configure --prefix=/home/manager/server/nginx_slave02 --user=manager --group=manager --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module --with-http_image_filter_module --with-http_secure_link_module
./configure --prefix=/home/manager/server/nginx_cache --user=manager --group=manager --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module --with-http_image_filter_module --with-http_secure_link_module

# echo -n "http://1.255.144.55/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg&w=200" | md5sum
0c9aa652815494e45573c077c9015c60  -

/home/manager/server/nginx_cache/sbin/nginx -s stop
/home/manager/server/nginx_image/sbin/nginx -s stop

/home/manager/server/nginx_image/sbin/nginx -c /home/manager/server/nginx_image/conf/nginx.conf

/home/manager/server/nginx_master/sbin/nginx -s stop
/home/manager/server/nginx_master/sbin/nginx -c /home/manager/server/nginx_master/conf/nginx.conf
/home/manager/server/nginx_slave01/sbin/nginx -s stop
/home/manager/server/nginx_slave01/sbin/nginx -c /home/manager/server/nginx_slave01/conf/nginx.conf
/home/manager/server/nginx_slave02/sbin/nginx -s stop
/home/manager/server/nginx_slave02/sbin/nginx -c /home/manager/server/nginx_slave02/conf/nginx.conf
ps -ef | grep nginx


/home/manager/server/nginx_cache/sbin/nginx -s stop
/home/manager/server/nginx_cache/sbin/nginx -c /home/manager/server/nginx_cache/conf/nginx.conf

tail -f /home/manager/server/nginx_master/logs/*.log
tail -f /home/manager/server/nginx_slave01/logs/*.log
tail -f /home/manager/server/nginx_slave02/logs/*.log

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
