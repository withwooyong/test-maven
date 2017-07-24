http://www.haproxy.org/


http://d2.naver.com/helloworld/284659

https://medium.com/@jinro4/%EA%B0%9C%EB%B0%9C-haproxy-%EC%84%A4%EC%B9%98-%EB%B0%8F-%EA%B8%B0%EB%B3%B8-%EC%84%A4%EC%A0%95-f4623815622
http://cyuu.tistory.com/entry/Haproxy-%EA%B8%B0%EB%B3%B8-%EC%84%A4%EC%B9%98-%EB%B0%8F-%EC%84%A4%EC%A0%95-%EB%B0%A9%EB%B2%95

-- 설명 잘 되어 있음
$ wget http://haproxy.1wt.eu/download/1.4/src/haproxy-1.4.22.tar.gz
$ tar xvfz haproxy-1.4.22.tar.gz
$ cd harproxy-1.4.22
$ make TARGET=linux26 ARCH=x86_64
$ make install
$ cd examples
$ cp haproxy.init /etc/rc.d/init.d/haproxy
$ chmod 755 /etc/rc.d/init.d/haproxy
$ mkdir -p /etc/haproxy/
$ cp /생성위치/haproxy-1.4.22/examples/haproxy.cfg /etc/haproxy/
$ mkdir -p /etc/haproxy/errors/
$ cp /생성위치/haproxy-1.4.22/examples/errorfiles/* /etc/haproxy/errors/
$ cd /usr/sbin
$ ln -s /usr/local/haproxy/sbin/haproxy haproxy
$ vi /etc/haproxy/haproxy.cfg

wget http://www.haproxy.org/download/1.7/src/haproxy-1.7.8.tar.gz
tar -xvzf haproxy-1.7.8.tar.gz
cd haproxy-1.7.8
make TARGET=linux2628 USE_PCRE=1 USE_OPENSSL=1 USE_ZLIB=1
make install
cd examples
cp haproxy.init /etc/rc.d/init.d/haproxy
chmod 755 /etc/rc.d/init.d/haproxy
mkdir -p /etc/haproxy/
cp /home/manager/server/haproxy-1.7.8/examples/haproxy.cfg /etc/haproxy/
mkdir -p /etc/haproxy/errors/
cp /home/manager/server/haproxy-1.7.8/examples/errorfiles/* /etc/haproxy/errors/
cd /usr/sbin
#ln -s /usr/local/haproxy/sbin/haproxy haproxy
ln -s /home/manager/server/haproxy-1.7.8/haproxy haproxy

-- haproxy.cfg
#서버 정보
#LB ip    10.101.22.33
#server-1 10.101.27.49
#server-2 10.101.26.50

global  
        log 127.0.0.1   local0
        log 127.0.0.1   local1 notice
        maxconn 4096
        uid 99
        gid 99
        daemon
        log-send-hostname
        #debug
        #quiet

defaults  
        log     global

listen  webfarm 10.101.22.33:80  
        mode http
        option httpchk GET /l7check.html HTTP/1.0
        option log-health-checks
        option forwardfor
        option httpclose
        cookie SERVERID rewrite
        cookie JSESSIONID prefix
        balance roundrobin
        stats enable
        stats uri /admin
        server  xvadm01.ncli 10.101.27.49:80 cookie admin_portal_1 check inter 1000 rise 2 fall 5
        server  xvadm02.ncli 10.101.26.50:80 cookie admin_portal_2 check inter 1000 rise 2 fall 5
$ /etc/init.d/haproxy start


=============
defaults 
 # 기본 설정 
 mode http
 option httplog
 option dontlognull
 option redispatch
 option forwardfor
 option http-server-close
 retries 3
 maxconn 20480
 timeout connect 5s
 timeout server 50s
 timeout client 50s
 timeout http-keep-alive 3000
 # HAProxy 상태 확인 URI 설정
 stats enable
 stats auth id:password
 stats refresh 10s
 stats uri /stats
frontend http_frontend
 bind *:80
 reqadd X-Forwarded-Proto:\ http
 
 # 설정한 backend(web_server) 쪽으로 프록시
 default_backend web_server
 
backend web_server
 mode http 
 # 로드 밸런싱 알고리즘 선택
 balance roundrobin
 cookie SERVERID insert indirect nocache
 server s1 1.255.144.55:8081 cookie s1 check inter 5000 fastinter 1000 rise 1 fall 1 weight 1
 server s2 1.255.144.56:8081 cookie s2 check inter 5000 fastinter 1000 rise 1 fall 1 weight 1


=========================================
global
    daemon
    maxconn 4096

defaults
    mode http
    timeout connect 5000ms
    timeout client 50000ms
    timeout server 50000ms

frontend http-in
    bind *:80
    default_backend servers

backend servers
    mode http
    balance roundrobin
    server server1 1.255.144.54:8081
    server server1 1.255.144.55:8081
    server server1 1.255.144.56:8081
    
    