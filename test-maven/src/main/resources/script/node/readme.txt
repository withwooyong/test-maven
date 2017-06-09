501      13324 13317  0 Feb03 ?        03:42:20 /home/supporter/.meteor/packages/meteor-tool/.1.3.1.o8t0ss++os.linux.x86_64+web.browser+web.cordova/mt-os.linux.x86_64/dev_bundle/bin/node /home/supporter/.meteor/packages/meteor-tool/.1.3.1.o8t0ss++os.linux.x86_64+web.browser+web.cordova/mt-os.linux.x86_64/tools/index.js --settings settings.json
501      13369 13324  0 Feb03 ?        01:25:12 /home/supporter/.meteor/packages/meteor-tool/.1.3.1.o8t0ss++os.linux.x86_64+web.browser+web.cordova/mt-os.linux.x86_64/dev_bundle/bin/node /home/supporter/mcs/.meteor/local/build/main.js
501      13843     1  0 Feb03 ?        00:00:01 /home/supporter/node/bin/node /home/supporter/node/lib/node_modules/forever/bin/monitor server.js
501      13910 13843  0 Feb03 ?        00:22:20 /home/supporter/node/bin/node /home/supporter/mcs_img_viewer/server.js
501      24279 24143  0 10:36 pts/1    00:00:00 grep node

/home/manager/server/node-v6.11.0/node /home/manager/server/app/app.js
/home/manager/server/node-v6.11.0/node /home/manager/server/app/server.js

find . -name "*.*" | xargs grep -n crop
find . -name "mcs.mongo" | xargs grep -n crop


find . -name "mcs.log.2017-06-08" | xargs grep -n crop
find . -name "rsync.log" | xargs grep -n crop

http://1.255.144.56/crop/src=/home/manager/server/images/kobis_still_20163324_0.jpg&dst=/home/manager/server/images/c_kobis_still_20163324_0.jpg

-- nginx 설치
wget http://nginx.org/download/nginx-1.12.0.tar.gz
tar -xvzf nginx-1.12.0.tar.gz
cd nginx-1.12.0
./configure --prefix=/home/manager/server/nginx_image --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module
make && make install  

/home/manager/server/nginx_master/sbin/nginx -s stop
/home/manager/server/nginx_master/sbin/nginx -c /home/manager/server/nginx_master/conf/nginx.conf

/home/manager/server/nginx_slave01/sbin/nginx -s stop
/home/manager/server/nginx_slave01/sbin/nginx -c /home/manager/server/nginx_slave01/conf/nginx.conf

/home/manager/server/nginx_slave02/sbin/nginx -s stop
/home/manager/server/nginx_slave02/sbin/nginx -c /home/manager/server/nginx_slave02/conf/nginx.conf
ps -ef | grep nginx

-- opencv 설치
$ yum install cmake
$ yum install python-devel numpy
$ yum install gcc gcc-c++
$ yum install gtk2-devel
$ yum install libdc1394-devel
$ yum install libv4l-devel
$ yum install ffmpeg-devel
$ yum install gstreamer-plugins-base-devel
$ yum install libpng-devel libjpeg-turbo-devel jasper-devel-1.900.1-29.el7 openexr-devel libtiff-devel libwebp-devel
$ yum install git
$ mkdir opencv-build
$ cd opencv-build
#$ git clone https://github.com/Itseez/opencv.git
#$ cd opencv
#$ git checkout tags/2.4.8.2

wget https://github.com/opencv/opencv/archive/3.2.0.zip
wget https://github.com/opencv/opencv/archive/2.4.13.2.tar.gz 
unzip 3.2.0.zip
cd opencv-3.2.0

1.255.144.28
1.255.144.53
1.255.144.54
1.255.144.55
1.255.144.56
devm1219
devs1219

https://bugs.centos.org/view.php?id=13276
make[2]: *** [modules/imgcodecs/CMakeFiles/opencv_imgcodecs.dir/src/grfmt_jpeg2000.cpp.o] 오류 1
make[1]: *** [modules/imgcodecs/CMakeFiles/opencv_imgcodecs.dir/all] 오류 2
yum -y downgrade jasper*
sudo yum install jasper-devel-1.900.1-29.el7 instead of sudo yum install jasper-devel should do the trick

$ mkdir build
$ cd build
$ cmake -D CMAKE_BUILD_TYPE=RELEASE -D CMAKE_INSTALL_PREFIX=/usr/local ..
$ make
$ sudo make install
$ cp /usr/local/lib/python2.7/site-packages/cv2.so /usr/lib/python2.7/site-packages
$ python
>>> import cv2
>>> print cv2.__version__
ㅡ
삭제
make uninstall


-- nodejs 설치
yum install gcc gcc-c++ python
yum install nodejs npm --enablerepo=epel
#npm install -g express
npm install -g express-generator
express /home/manager/server/app/nodejs && cd /home/manager/server/app/nodejs
npm install -d

mcs_img_viewer

wget https://nodejs.org/dist/v6.11.0/node-v6.11.0.tar.gz
tar -xvzf node-v6.11.0.tar.gz
cd node-v6.11.0
./configure --prefix=/home/manager/server/node-v6.11.0
make && make install
node --version

안녕하세요. 전재오입니다.

표세민 팀장님~
MCS에서 사용하였던 인물중심 크롭 기능 관련 자료 및 테스트/상용 서버 정보 부탁 드립니다.
(인물중심 크롭 기능 개발 시 사용했던 라이브러리 정보 등)

죄송하지만 금일(06/07)까지 회신 부탁 드립니다.

감사합니다. 멋진 하루 되세요 

From: 표세민 [mailto:tpals@zinnaworks.com] 
Sent: Wednesday, June 7, 2017 5:40 PM
To: 전재오(JEON JAE O)/플랫폼개발팀/SKB
Cc: 김현(KIM HYUN)/플랫폼개발팀/SKB; 허우용(HEO WOO YONG)/플랫폼개발팀/SKB; 문명호(MOON MYOUNG HO)/플랫폼개발팀/SKB
Subject: RE: [MCS] 서버 접속 정보 및 인물 중심 스틸컷 관련 자료 요청 드립니다.

안녕하세요 표세민입니다

인물중심 크롭 관련하여 사용하고 있는 라이브러리는 opencv 이며,
그냥 구글링을 통해 작업한것으로 공유 드릴만한 별도의 자료는 존재하지 않습니다.

서버 정보 공유드립니다.
테스트 서버 : 1.255.144.28:3005
상용 서버 : 221.140.123.237:8080

감사합니다.


4.  통합이미지 배포 시스템 구축 (양정우M님)
- 기존 계획 수립 또는 현재 진행 상황 체크 부탁드리며, 전체 구조 변경에 대한 최종 확인 부탁드립니다.
 기존 oksusu nsigs 모듈 탑재에서  B tv 전용기능(예:인물인식기능)등을 고려하여 B tv 자체적 개발 하는게 효율적일 것 같아 계획 변경했습니다.(이병덕 본부장님과 이상범본부장님께 기보고 완료)
  목표 일정은 : ~7/31, 상세 내용은 별첨 참조

