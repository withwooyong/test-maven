[toc]

### 라이브러리 설치
root 계정으로 아래의 명령을 실행한다.
~~~~
yum install gcc gcc-c++ make autoconf wget libxml2-devel perl perl-devel perl-ExtUtils-* 
yum install pcre-devel openssl-devel zlib library cpan
~~~~

### 설치파일 다운로드
 tvingadmin 계정으로 실행하며, 서버설치 공통 규약에 의하여 */home/tvingadmin/src* 에 다운로드 한다. 현재 stable version은 1.6.2 (2015.03.10)
~~~~
wget http://nginx.org/download/nginx-1.6.2.tar.gz
tar xvfz nginx-1.6.2.tar.gz
~~~~

### 설치옵션
 nginx는 기본설치시 지정하지 않는 모듈을 설치하지 않으므로, 아래의 옵션을 주고 설치를  tvingadmin계정으로 진행해여야 함.
~~~~
cd  /home/tvingadmin/src/nginx-1.6.2
./configure --prefix=/home/tvingadmin/server/nginx-1.6.2 --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module
make
make install
~~~~

### 심볼릭 링크 설정
 설치 옵션상에 prefix로 주어진 위치에 설치를 확인 한다. 추후 로그관리, 서비스 등록시 쉘스크립트 재사용을 위해 심볼릭 링크를 설정한다. 
~~~~
cd /home/tvingadmin/server
ln -s nginx-1.6.2 nginx
~~~~

### 서비스 등록
root의 계정으로 서비스 등록쉘을 */etc/init.d/nginx* 로 생성후, 권한을 755로 변경한다.  
* cd /etc/init.d  
* chmod 755 nginx  
* 서비스 등록  chkconfig --add nginx  
* 서비스 등록 확인  chkconfig --list nginx  

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

nginx="/home/tvingadmin/server/nginx/sbin/nginx" 
prog=$(basename $nginx)

NGINX_CONF_FILE="/home/tvingadmin/server/nginx/conf/nginx.conf" 

[ -f /etc/sysconfig/nginx ] && . /etc/sysconfig/nginx

lockfile=/var/lock/subsys/nginx

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
### 웹서버 기동/재기동/중지/상태
웹서버는 80 포트를 사용하므로, root의 계정으로 실행한다.
* service nginx start
* service nginx restart
* service nginx stop
* service nginx status

##### 로그관리 스크립트
root의 계정으로 CentOS의 */etc/logrotate.d/* 하위에 해당 쉘을 생성하면 새벽04시에 동작이 된다.  
쉘 설정상 주의사항은 심볼릭 링크, 웹서버 설치 위치, 로테이션 주기와 로그 디렉토리 권한 등이다.
~~~~
#####################################
########## nginx logrotate ##########
#####################################

/home/tvingadmin/server/nginx/logs/*.log {
    #rotate the logfile(s) daily
    daily
    # adds extension like YYYYMMDD instead of simply adding a number
    dateext
    # If log file is missing, go on to next one without issuing an error msg
    missingok
    # Save logfiles for the last 49 days
    rotate 180
    # Old versions of log files are compressed with gzip
    compress
    # Postpone compression of the previous log file to the next rotation cycle
    delaycompress
    # Do not rotate the log if it is empty
    notifempty
    # create mode owner group
    create 644 tvingadmin tvingadmin
    #after logfile is rotated and nginx.pid exists, send the USR1 signal
    postrotate
       [ ! -f /home/tvingadmin/server/nginx/logs/nginx.pid ] || kill -USR1 `cat /home/tvingadmin/server/nginx/logs/nginx.pid`
    endscript
}
~~~~
### 소켓수 조정
root의 계정으로 */etc/security/limits.conf* 하위에 아래 코드를 추가하며, 시스템별 특성에 따라 DMC 문의 후 수치는 조정한다.
~~~~
tvingadmin      soft    nofile          65536
tvingadmin      hard    nofile          65536
root            soft    nofile          65536
root            hard    nofile          65536
~~~~
### tcp 튜닝
root 계정으로 */etc/sysctl.conf* 파일을 수정, OS담당인 DMC 설정해 놓은 값이다. 어플리케이션 특성에 따라 조정이 필요함.  
sysctl -p 옵션으로 설정 값을 확인할 수 있다.
~~~~
# DSR setting
net.ipv4.conf.lo.arp_ignore = 1
net.ipv4.conf.lo.arp_announce = 2
net.ipv4.conf.all.arp_ignore = 1
net.ipv4.conf.all.arp_announce = 2


# TCP Tuning
net.ipv4.ip_local_port_range    = 1024 65000

net.ipv4.tcp_fin_timeout        = 15
net.ipv4.tcp_rfc1337            = 1
net.ipv4.tcp_tw_recycle         = 1
net.ipv4.tcp_tw_reuse           = 1

net.ipv4.tcp_max_tw_buckets     = 2000000
net.core.netdev_max_backlog     = 300000

net.ipv4.tcp_keepalive_time     = 30
net.ipv4.tcp_keepalive_probes   = 3
net.ipv4.tcp_keepalive_intvl    = 10
net.ipv4.tcp_orphan_retries     = 2
~~~~

