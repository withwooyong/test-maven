[toc]
### 설치파일 다운로드
*tvingadmin* 계정으로 서버설치 공통규약에 의하여 */home/tvingadmin/src* 경로 하위에 다운로드 한다. 2.6.x 이상의 버전에서는 툴 및 일괄 업데이트 등이 오작동함에 따라 2.4.x 버전을 사용한다.
~~~~
wget http://fastdl.mongodb.org/linux/mongodb-linux-x86_64-2.4.13.tgz
~~~~

### 압축해제
*tvingadmin* 계정으로 압축 해제 후 */home/tvingadmin/server/ 경로 하위로 이동한다.
~~~~
tar xvfz mongodb-linux-x86_64-2.4.13.tgz  
cp -R mongodb-linux-x86_64-2.4.13 /home/tvingadmin/server/
~~~~

### 링크 설정
로그관리 및 쉘상의 경로를 위해 심볼릭 링크를 *mongodb*로 걸어준다.
~~~~
ln -s mongodb-linux-x86_64-2.4.13 mongodb
~~~~

### 실행 및 확인
*/home/tvingadmin/server/mongodb/bin/mongod* 의 쉘을 구동한다. 기본 포트는 27017로 구동이 되므로 아래의 명령어를 이용하여, 포트가 Listen 상태임을 확인한다.
~~~~
/home/tvingadmin/server/mongodb/bin/mongod
ps -ef|grep mongod
netstat -tnlp
~~~~

### 관리자 계정 설정
*/home/tvingadmin/server/mongodb/bin/mongo*의 쉘을 이용하여 mongodb를 접속한다. 접속 후 아래의 명령어를 이용하여 관리자 계정 및 권한 설정을 한다. 현재 설정되어 있는 관리자 계정은 *root*이며, im-db01번의 root 계정 비밀번호로 설정이 되어 있다.

~~~~
/home/tvingadmin/server/mongodb/bin/mongo  
> use admin
> db.addUser("관리계정", "비밀번호");
> db.auth("관리계정", "비밀번호");
~~~~

### 서비스 데이터 베이스 및 계정설정
관리자 계정 설정과 동일한 방식으로 접속 후 아래의 명령어를 이용하여 데이터 베이스 및 계정을 생성한다. 해당 데이터 베이스와 계정이 정상적으로 생성이 되었는지 확인한다.

~~~~
*/home/tvingadmin/server/mongodb/bin/mongo*  
> use im_service
> db.addUser("service" , "service@2013");
> db.auth("service" , "service@2013");
> show collections;
> show users;
~~~~
이와 같은 방식으로 im_manager, im_community, im_operator, im_micro의 데이터 베이스를 생성한다. master가 대상이 될 서버에서만 실행을 하며, 추후 replication 연결 시 slave 서버에서 접속 후 확인을 한다.


### 설정파일 추가
구동 중인 mongodb를 */home/tvingadmin/server/mongodb --shutdown* 명령어로 mongodb를 중지후,  
*/home/tvingadmin/server/mongodb* 하위에 conf 디력토리 및 logs 디렉토리를 생성한다. mongodb.conf 파일을 conf 경로 하위에 생성한다.
~~~~
mkdir conf
mkdir logs
~~~~

### replication key 파일 생성
replication 설정 시 아래의 명령어를 이용하여 *mongodb/conf/* 하위에 key 파일을 생성한다. key 데이터에는 특수키 값이 들어가면 replication 시에 에러가 발생하니 영문, 숫자로만 데이터를 구성한다.
~~~~
echo "[key데이터]" > [key파일명]
chmod 600 [key 파일명]
~~~~

##### 실서버 replication mongodb.conf
mongodb 구동시 옵션을 정의한 파일로 replication 정보 및 포트, 프로파일 등을 정의한다.
~~~~
replSet=ironCluster3
dbpath=/dbdata
logpath=/home/tvingadmin/server/mongodb/logs/mongodb.log
logappend=true
verbose=true

port=25017
fork=true

rest=true
auth=true
oplogSize=136314
quiet=true
slowms=100
profile=1
~~~~

### replication 구동
위의 설정파일 추가, replication key 생성후 replication 상태에서 mongodb를 구동한다. 구동 방법은 아래의 명령어로 구동을 한다.
~~~~
/home/tvingadmin/server/mongodb/bin/mongod --keyFile=/home/tvingadmin/server/mongodb/conf/[key 파일명] --config=/home/tvingadmin/server/conf/mongodb.conf
~~~~
위의 구동방식을 이용하여 mongodb 모든 대상의 서버에서 구동한다.

### replication 설정
위와 같이 모든 mongodb 구동이 끝났으면 master 설정 및 slave , arbiter 를 연결한다.연결 후 정상적으로 연결이 되어 있는지 rs.status()로 확인을 한다.

~~~~
/home/tvingadmin/server/mongodb/bin/mongo -u root -p [비밀번호] --port 25017 admin  
> rs.initiate()
> rs.add("172.18.147.13:25017");
> rs.add("172.18.147.14:25017");
> rs.add("172.18.147.15:25017");
> rs.add("172.18.147.16:25017");
> rs.add("172.18.147.17:25017");
> rs.add("172.18.147.9:25017", {arbiterOnly:true});
~~~~


### MongoDB Log 관리
mongodb는 Log파일 용량이 커지면 속도 자체가 느려지는 현상이 있다. 이에 따라 mongodb 한시간 단위로 log를 rotate 한다. 이에 따라 CentOS의 */etc/cron.hourly/* 하위에 mongodb-log를 *root*의 계정으로 쉘을 생성한다. mongodb log rotate는 mongodb의 명령어를 이용하므로 /home/tvingadmin/server/mongodb/bin/ 하위에 log_rotate.js를 생성한다. mongodb-log와 log_rotate 내용을 각각 아래와 같다.  
  
mongodb-log 내용
~~~~
#!/bin/bash  
MONGODB_LOCATION=/home/tvingadmin/server/mongodb  
$MONGODB_LOCATION/bin/mongo --port 25017 -uroot -p[비밀번호] admin $MONGODB_LOCATION/bin/log_rotate.js  
~~~~
log-rotate.js 내용  
~~~~
db.runCommand('logRotate');
~~~~

### 실서버 쉘 유틸, 백업
*/home/tvingadmin/bin/* 하위에 위치한다. 각 내용을 아래와 같다. 백업 172.18.147.12에서만 구동중
  
7days_ago_mongodb_log_delete.sh 내용 - 7일 이전의 로그 파일을 제거  
~~~~
#!/bin/sh  
cd /home/tvingadmin/server/mongodb/logs  
seven_days_ago_date=`date -d '7 days ago ' +"%Y-%m-%d"`  
#echo $seven_days_ago_date  
rm -f mongodb.log.$seven_days_ago_date*  
~~~~

mongodb-conn.sh 내용 - mongo client 접속  
~~~~
#!/bin/bash  
$MONGO_HOME/bin/mongo -uroot -p[비밀번호] --port 25017 admin
~~~~

mongodb-down-standalone.sh 내용 - standalone 중지 시 사용  
~~~~
#!/bin/bash  
numactl --interleave=all $MONGO_HOME/bin/mongod --config $MONGO_HOME/conf/mongodb-standalone.conf --shutdown
~~~~

mongodb-down.sh 내용 - replication 구동 상태상에서 중지 시 사용  
~~~~
#!/bin/bash  
numactl --interleave=all $MONGO_HOME/bin/mongod --keyFile $MONGO_HOME/conf/ironKey --config $MONGO_HOME/conf/mongodb.conf --shutdown  
~~~~

mongodb-startup-standalone.sh 내용 - standalone 구동  
~~~~
#!/bin/bash  
numactl --interleave=all $MONGO_HOME/bin/mongod --config $MONGO_HOME/conf/mongodb-standalone.conf  
sleep 3  
ps -ef|grep mongod|grep conf  
~~~~

mongodb-startup.sh 내용 - replication 구동 시 사용  
~~~~
#!/bin/bash  
numactl --interleave=all $MONGO_HOME/bin/mongod --keyFile $MONGO_HOME/conf/ironKey --config $MONGO_HOME/conf/mongodb.conf  
sleep 3  
ps -ef|grep mongod|grep conf
~~~~

mongodb-stat.sh 내용 - mongoDB 상태 확인  
~~~~
#!/bin/bash  
$MONGO_HOME/bin/mongostat --port 25017 -uroot -p*didcjsim_12 -n 10 --all
~~~~

mongodb-top.sh 내용 - mongoDB colleciton별 질의 시간 확인  
~~~~
#!/bin/bash  
$MONGO_HOME/bin/mongotop --port 25017 -uroot -p*didcjsim_12 -vvv
~~~~

mongodb_backup.sh 내용 - crontab 에 새벽 05시에 백업 후 개발서버로 전송.
~~~~
#!/bin/sh
cd /home/tvingadmin/dbdump
seven_days_ago_date=`date -d '7 days ago ' +"%Y%m%d"`
today_date=`/bin/date +%Y%m%d`

rm -rf $seven_days_ago_date
rm -rf $today_date
mkdir $today_date
cd $today_date

# mongodb dump
/home/tvingadmin/server/mongodb/bin/mongodump -dim_service -uservice -pservice@2013 -o /home/tvingadmin/dbdump/$today_date/ --port 25017
/home/tvingadmin/server/mongodb/bin/mongodump -dim_manager -umanager -pmanager@2013 -o /home/tvingadmin/dbdump/$today_date/  --port 25017
/home/tvingadmin/server/mongodb/bin/mongodump -dim_operator -uoperator -poperator@2013 -o /home/tvingadmin/dbdump/$today_date/  --port 25017
/home/tvingadmin/server/mongodb/bin/mongodump -dim_community -ucommunity -pcommunity@2013 -o /home/tvingadmin/dbdump/$today_date/  --port 25017
/home/tvingadmin/server/mongodb/bin/mongodump -dim_logs -ulogs -plogs@2013 -o /home/tvingadmin/dbdump/$today_date/  --port 25017
/home/tvingadmin/server/mongodb/bin/mongodump -dim_micro -umicro -pmicro@2013 -o /home/tvingadmin/dbdump/$today_date/  --port 25017

# tar 압축
tar cvfz im_service_$today_date.tar.gz im_service
tar cvfz im_manager_$today_date.tar.gz im_manager
tar cvfz im_operator_$today_date.tar.gz im_operator
tar cvfz im_community_$today_date.tar.gz im_community
tar cvfz im_logs_$today_date.tar.gz im_logs
tar cvfz im_micro_$today_date.tar.gz im_micro

# 덤프 경로 remove
rm -rf im_service
rm -rf im_manager
rm -rf im_operator
rm -rf im_community
rm -rf im_logs
rm -rf im_micro

# dump 파일 전송
scp im_service_$today_date.tar.gz tvingadmin@180.182.43.33:/home/tvingadmin/dbdump/im-db/ 
scp im_manager_$today_date.tar.gz tvingadmin@180.182.43.33:/home/tvingadmin/dbdump/im-db/ 
scp im_operator_$today_date.tar.gz tvingadmin@180.182.43.33:/home/tvingadmin/dbdump/im-db/ 
scp im_community_$today_date.tar.gz tvingadmin@180.182.43.33:/home/tvingadmin/dbdump/im-db/ 
scp im_logs_$today_date.tar.gz tvingadmin@180.182.43.33:/home/tvingadmin/dbdump/im-db/ 
scp im_micro_$today_date.tar.gz tvingadmin@180.182.43.33:/home/tvingadmin/dbdump/im-db/ 
~~~~

