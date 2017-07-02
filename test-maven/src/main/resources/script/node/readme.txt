sudo yum install gcc

which gcc
which libgcc
sudo yum group install "Development Tools"
sudo yum install gcc-c++
sudo yum groupinstall 'Development Tools' && sudo yum install curl git irb python-setuptools ruby
yum install rsync
yum install opencv

sudo rm -rf /usr/local/lib/node /usr/local/lib/node_modules /var/db/receipts/org.nodejs.*
sudo npm uninstall npm -g
sudo rm -rf /usr/local/include/node /Users/$USER/.npm
sudo rm /usr/local/bin/node
sudo rm /usr/local/share/man/man1/node.1
sudo rm /usr/local/lib/dtrace/node.d
node --version
which node
sudo yum groupinstall "Development Tools" 
sudo yum install gcc
sudo yum install cmake
sudo yum install git
sudo yum install gtk2-devel
sudo yum install pkgconfig
sudo yum install numpy
sudo yum install ffmpeg
sudo mkdir /opt/working
cd /opt/working
sudo git clone https://github.com/Itseez/opencv.git
cd opencv/
git checkout tags/2.4.8.2
sudo git checkout tags/2.4.8.2
mkdir release
sudo mkdir release
cd release/
cmake -D CMAKE_BUILD_TYPE=RELEASE -D CMAKE_INSTALL_PREFIX=/usr/local ..
sudo cmake -D CMAKE_BUILD_TYPE=RELEASE -D CMAKE_INSTALL_PREFIX=/usr/local ..
pwd
make
sudo make
sudo make install
cd ~/shell
exit
wget http://people.centos.org/tru/devtools-2/devtools-2.repo -O /etc/yum.repos.d/devtools-2.repo
sudo wget http://people.centos.org/tru/devtools-2/devtools-2.repo -O /etc/yum.repos.d/devtools-2.repo
sudo yum install devtoolset-2-gcc devtoolset-2-binutils devtoolset-2-gcc-c++
scl enable devtoolset-2 bash

npm install opencv --build-from-source
yum install libopencv-dev
node-pre-gyp ERR! Tried to download(403): https://node-opencv.s3.amazonaws.com/opencv/v6.0.0/Release/node-v48-linux-x64.tar.gz 
node-pre-gyp ERR! Pre-built binaries not found for opencv@6.0.0 and node@6.10.3 (node-v48 ABI) (falling back to source compile with node-gyp) 
gyp WARN EACCES user "root" does not have permission to access the dev dir "/root/.node-gyp/6.10.3"
gyp WARN EACCES attempting to reinstall using temporary dev dir "/home/manager/node_modules/opencv/.node-gyp"


501      13324 13317  0 Feb03 ?        03:42:20 /home/supporter/.meteor/packages/meteor-tool/.1.3.1.o8t0ss++os.linux.x86_64+web.browser+web.cordova/mt-os.linux.x86_64/dev_bundle/bin/node /home/supporter/.meteor/packages/meteor-tool/.1.3.1.o8t0ss++os.linux.x86_64+web.browser+web.cordova/mt-os.linux.x86_64/tools/index.js --settings settings.json
501      13369 13324  0 Feb03 ?        01:25:12 /home/supporter/.meteor/packages/meteor-tool/.1.3.1.o8t0ss++os.linux.x86_64+web.browser+web.cordova/mt-os.linux.x86_64/dev_bundle/bin/node /home/supporter/mcs/.meteor/local/build/main.js
501      13843     1  0 Feb03 ?        00:00:01 /home/supporter/node/bin/node /home/supporter/node/lib/node_modules/forever/bin/monitor server.js
501      13910 13843  0 Feb03 ?        00:22:20 /home/supporter/node/bin/node /home/supporter/mcs_img_viewer/server.js
501      24279 24143  0 10:36 pts/1    00:00:00 grep node

- 실행
node /home/manager/server/app/app.js
/home/manager/server/node-v6.11.0/node /home/manager/server/app/app.js
/home/manager/server/node-v6.11.0/node /home/manager/server/app/server.js

find . -name "*.*" | xargs grep -n crop
find . -name "mcs.mongo" | xargs grep -n crop


이미지경로

이미지구분 6-13 시스템관리->시스템파라미터관리

C_PSTR_RES  C영역 포스터 이미지 해상도
    C_PSTR_RES  C영역 포스터 이미지 해상도 180x258 C영역 포스터 이미지 해상도 NXUHD   Y

D_PSTR_RES  D영역 포스터 이미지 해상도
    D_PSTR_RES  D영역 포스터 이미지 해상도 315x452 D영역 포스터 이미지 해상도 NXUHD   Y
    
HPRMRESLTN  NextUI 홈전체메뉴 프로모션 이미지해상도
    HPRMRESLTN  NextUI 홈전체메뉴 프로모션 이미지해상도    185x128 185x128 (레거시)   NXNEWUI Y
    HPRMRESLTN  NextUI 홈전체메뉴 프로모션 이미지해상도    250x172 250x172 (SSTB)  NXSSTB  Y
    HPRMRESLTN  NextUI 홈전체메뉴 프로모션 이미지해상도    374x258 374x258 (UHD)   NXUHDSTB    Y
    
IMGDEFDIR   이미지 디폴트 디렉토리명
    IMGDEFDIR   이미지 디폴트 디렉토리명   01  change  01
    IMGDEFDIR   이미지 디폴트 디렉토리명   02  update  01
    IMGDEFDIR   이미지 디폴트 디렉토리명   03  default 01
    IMGDEFDIR   이미지 디폴트 디렉토리명   04  kids    01
    IMGDEFDIR   이미지 디폴트 디렉토리명   05  style   01
    IMGDEFDIR   이미지 디폴트 디렉토리명   06  style/blue  01
    IMGDEFDIR   이미지 디폴트 디렉토리명   07  style/green 01
    IMGDEFDIR   이미지 디폴트 디렉토리명   08  style/orange    01
    IMGDEFDIR   이미지 디폴트 디렉토리명   09  style/purple    01
    IMGDEFDIR   이미지 디폴트 디렉토리명   10  style/yellow    01
    IMGDEFDIR   이미지 디폴트 디렉토리명   11  widget  01
    IMGDEFDIR   이미지 디폴트 디렉토리명   12  widget/default  01
    IMGDEFDIR   이미지 디폴트 디렉토리명   13  widget/style    01
    IMGDEFDIR   이미지 디폴트 디렉토리명   14  widget/style/blue   01
    IMGDEFDIR   이미지 디폴트 디렉토리명   15  widget/style/green  01
    IMGDEFDIR   이미지 디폴트 디렉토리명   16  widget/style/orange 01
    IMGDEFDIR   이미지 디폴트 디렉토리명   17  widget/style/purple 01
    IMGDEFDIR   이미지 디폴트 디렉토리명   18  widget/style/yellow 01
    
IMGFTPINFO  이미지 FTP 서버정보
    IMGFTPINFO  이미지 FTP 서버정보    220 ftp://imageup:image_media@1.226.200.38:2401 COPY
    IMGFTPINFO  이미지 FTP 서버정보    CDN ftp://ftpuser01:stbuser01@cdn.hanafostv.com:21  
    IMGFTPINFO  이미지 FTP 서버정보    TAR ftp://epgrenew:dlvlwlrodtls@121.125.28.55:21    
    
MENUIMGINF  메뉴이미지업로드정보
    MENUIMGINF  메뉴이미지업로드정보  CMS_WEBSVR_FTP_INFO 이미지 WEBSERVER FTP전송정보(P1:접속정보,P2:업로드경로)-was로변경150129    jeus:hmsysdev@123.215.199.157:22    /home/DATA/exbillhanaro/webapps/menu_image/kids/
    MENUIMGINF  메뉴이미지업로드정보  IMG_HIST_LNKD_INFO  이미지 이력 I/F정보(P1:URL, P2:ENCODING)   http://cms.hanafostv.com/img/IMG_IMAGE_HIST_MULTI_P_6.jsp   UTF-8
    MENUIMGINF  메뉴이미지업로드정보  IMG_WAS_ULD_PATH    이미지 WAS 업로드 경로정보(P1:업로드경로정보)    /home/DATA/menu_image_xpg_v7/menu_image/kids/   
    
MIMG_BRICK  메뉴이미지 1,2,3,5,9단 그룹
    MIMG_BRICK  메뉴이미지 1,2,3,5,9단 그룹 34  1단메뉴 이미지속성  371*62|248*40|198*34    Y
    MIMG_BRICK  메뉴이미지 1,2,3,5,9단 그룹 35  2단메뉴 이미지속성  371*130|248*84|198*72   Y
    MIMG_BRICK  메뉴이미지 1,2,3,5,9단 그룹 36  3단메뉴 이미지속성  371*196|248*128|198*110 Y
    MIMG_BRICK  메뉴이미지 1,2,3,5,9단 그룹 41  5단메뉴 이미지속성  371*326|248*216|198*186 Y
    MIMG_BRICK  메뉴이미지 1,2,3,5,9단 그룹 42  9단메뉴 이미지속성  371*590|248*392|198*338 Y
    
MYPMRESLTN  MYPPM이미지 해상도
    MYPMRESLTN  MYPPM이미지 해상도    305x60  305x60 (레거시)    NXNEWUI Y
    MYPMRESLTN  MYPPM이미지 해상도    374x258 374x258(UHD)    NXSSTB  Y
    MYPMRESLTN  MYPPM이미지 해상도    382x66  382x66 (SSTB)   NXUHDSTB    Y    


find . -name "mcs.log.2017-06-08" | xargs grep -n crop
find . -name "rsync.log" | xargs grep -n crop

http://1.255.144.56/crop/src=/home/manager/server/images/kobis_still_20163324_0.jpg&dst=/home/manager/server/images/c_kobis_still_20163324_0.jpg

-- nginx 설치
wget http://nginx.org/download/nginx-1.12.0.tar.gz
tar -xvzf nginx-1.12.0.tar.gz
cd nginx-1.12.0
./configure --prefix=/home/manager/server/nginx_image --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module
make && make install  


./configure --prefix=/home/manager/server/nginx_node --with-http_ssl_module --with-http_gzip_static_module --with-http_stub_status_module

/home/manager/server/nginx_node/sbin/nginx -s stop
/home/manager/server/nginx_node/sbin/nginx -c /home/manager/server/nginx_node/conf/nginx.conf



/home/manager/server/nginx_master/sbin/nginx -s stop
/home/manager/server/nginx_master/sbin/nginx -c /home/manager/server/nginx_master/conf/nginx.conf

/home/manager/server/nginx_slave01/sbin/nginx -s stop
/home/manager/server/nginx_slave01/sbin/nginx -c /home/manager/server/nginx_slave01/conf/nginx.conf

/home/manager/server/nginx_slave02/sbin/nginx -s stop
/home/manager/server/nginx_slave02/sbin/nginx -c /home/manager/server/nginx_slave02/conf/nginx.conf
ps -ef | grep nginx


출처: http://terrorjang.tistory.com/61 [개발자 공간]
pkg-config --modversion opencv

http://techieroop.com/install-opencv-in-centos/
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

https://zetawiki.com/wiki/CentOS_yum_%ED%8C%A8%ED%82%A4%EC%A7%80_%EC%82%AD%EC%A0%9C
-- yum remove
- 리스트 
yum list installed java*jdk
- 삭제
yum remove java-1.7.0-openjdk.x86_64
- 확인
yum list installed java*jdk

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

