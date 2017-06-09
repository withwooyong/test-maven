grep -rnw '찾고자하는 디렉토리 위치' -e "찾는 텍스트명"
grep -rnw '/home/manager/server/nginx_image/cache' -e "T30222_src"
출처: http://jojoldu.tistory.com/60 [기억보단 기록을]

http://1.255.144.54/resize/300/stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg

/home/manager/server/nginx_image/sbin/nginx -s stop
/home/manager/server/nginx_image/sbin/nginx -c /home/manager/server/nginx_image/conf/nginx.conf
tail -f /home/manager/server/nginx_image/logs/*.log

grep -rnw '/home/manager/server/nginx_image/cache' -e "T30222_src"


Apache Bench (https://httpd.apache.org/docs/current/programs/ab.html)
100 requests using 5 concurrent connections:
ab -n 100 -c 5 http://1.255.144.54/media/768/T30222_src.jpg
http://ohgyun.com/455
` -n 100 -c 5`는, 
모두 100번의 요청을 보내는데, '동시에 처리하는 요청'의 수를 5개로 유지한다는 의미이다.
크기가 5인 요청 풀이 있고, 100개의 요청을 순서대로 보낸다고 생각하면 된다.

http://1.255.144.53/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg&w=300

http://1.255.144.54/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_100x143.jpg
http://1.255.144.54/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_200x286.jpg
http://1.255.144.54/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_300x429.jpg
http://1.255.144.54/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_400x572.jpg
http://1.255.144.54/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_500x715.jpg
http://1.255.144.54/resize/300/stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg

/home/manager/server/nginx_image/sbin/nginx -s stop
/home/manager/server/nginx_image/sbin/nginx -c /home/manager/server/nginx_image/conf/nginx.conf

/home/manager/server/nginx_master/sbin/nginx -s stop
/home/manager/server/nginx_master/sbin/nginx -c /home/manager/server/nginx_master/conf/nginx.conf
/home/manager/server/nginx_slave01/sbin/nginx -s stop
/home/manager/server/nginx_slave01/sbin/nginx -c /home/manager/server/nginx_slave01/conf/nginx.conf
ps -ef | grep nginx

tail -f /home/manager/server/nginx_image/logs/*.log
tail -f /home/manager/server/nginx_master/logs/*.log
tail -f /home/manager/server/nginx_slave01/logs/*.log

http://1.255.144.54/media/768/T30222_src.jpg
http://1.255.144.54/media/1920/T30222_src.jpg
http://1.255.144.54/resize/300/http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg
