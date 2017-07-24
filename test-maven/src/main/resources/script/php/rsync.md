CentOS 7.x LSYNCD 서버 파일 동기화 시키기

출처: http://blog.ivps.kr/150 [iVPS 가상서버호스팅]
http://blog.ivps.kr/150
yum install lsyncd lua rsync

http://kkamagistory.tistory.com/112 
http://newstars.tistory.com/105
http://bloodguy.tistory.com/entry/rsync-%EC%84%A0%ED%83%9D%EC%A0%81-rsync-exclude

리눅스 백업 - rsync

rsync
- rsync [option] [service ip] [backup_directory]

option
-a : 심볼릭 링크, 속성, 퍼미션, 소유권등 보존
-v : 진행상황을 상세하게 보여줌
-z : 전송시 압축을 함
-u : 새로운 파일을 덮어쓰지 않음
--delete : 서버쪽에 없고 클라이언트 쪽에만 있는 파일을 백업시 지움
--progress : sync내용을 퍼센트로 보여줌

------------------------------------------------------------------------------------------------------------------------

rsync 사용을 위한 설정

# rpm -qi rsync
Name : rsync Relocations: /usr 
Version : 2.6.8 Vendor: CentOS
Release : 3.1 Build Date: 
Install Date: Build Host: builder5.centos.org
Group : Applications/Internet Source RPM: rsync-2.6.8-3.1.src.rpm
Size : 401189 License: GPL
Signature : DSA/SHA1, 2007년 04월 04일 (수) 오전 09시 27분 24초, Key ID a8a447dce8562897
Summary : 파일을 네트워크 상에서 동기화하는 프로그램.
Description :
Rsync는 신뢰할 수 있는 알고리즘을 사용하여 매우 빠르게 리모트와
호스트 파일을 가져와서 싱크합니다. Rsync는 네트웍을 통해 파일을
모두 전송하지 않고 단지 파일의 차이점만 전송하기 때문에 빠릅니다.
Rsync는 종종 매우 강력한 밀러링 처리나 더욱 유능한 rcp 명령의
대체로서 사용됩니다. rsync 알고리즘에 대해 설명한 기술 문서는
이 패키지에 포함되어 있습니다.
- 우선 시스템에 rsync가 있는지 확인한다.


서버쪽 설정

# vi /etc/xinetd.d/rsync
------------------ /etc/xinetd.d/rsync 파일 설정 -----------------------
# default: off
# description: The rsync server is a good addition to an ftp server, as it \
# allows crc checksumming etc.
service rsync
{
disable = no - rsync 데몬 사용을 위해 yes -> no 로 바꿔준다
socket_type = stream
wait = no
user = root
server = /usr/bin/rsync
server_args = --daemon
log_on_failure += USERID
}
----------------------------------------------------------------------

# vi /etc/rsyncd.conf
--------------------- /etc/rsyncd.conf 파일 설정-----------------------
[server3] - 서버이름
path=/ - 클라이언트에게 허용할 경로를 지정
hosts allow=10.10.33.1/10.10.33.2/10.10.33.100 - 허용할 ip입력
hosts deny=10.10.33.0/255.255.0.0 - 10.10.33 번대 ip 모두 차단
uid=0 - 루트 권한을 줌
gid=0 - 루트 권한을 줌
----------------------------------------------------------------------
- /etc.rsyncd.conf 파일이 없으면 생성한다.

# service xinetd restart

-- client setup
rsync -avz --progress          1.255.144.55:/home/manager/server/app /home/manager/server/
rsync -avz --progress --delete 1.255.144.55:/home/manager/server/app /home/manager/server/

ssh 패스워드 자동 설정
http://dev-mumu.tistory.com/79

http://redcap.egloos.com/v/1662926

ssh -v 1.255.144.55
-- 55번 서버에서 권한 수정 해야 함.
chmod 600 /home/manager/.ssh/authorized_keys
> chmod g-w /home/your_user
> chmod 700 /home/your_user/.ssh
> chmod 600 /home/your_user/.ssh/authorized_keys

#####################################
ssh-keygen -t dsa
cat ~/.ssh/id_dsa.pub | ssh manager@1.255.144.55 "cat >> .ssh/authorized_keys"

vi rsync.sh

#!/bin/bash

date=`date +%Y-%m-%d`
date_delete=`date --date "15 day ago" +%Y-%m-%d`

backup_dir="/home/manager/rsynclog/"

rm -Rf ${backup_dir}${date_delete}
mkdir ${backup_dir}${date}

/usr/bin/rsync -avz --progress --delete --log-file=${backup_dir}${date}/rsync.log 1.255.144.55:/home/manager/server/app /home/manager/server/

[root@client ~]# chmod +x rsync.sh
[root@client ~]# crontab -e
*/1 * * * * su - manager -c '/home/manager/rsync.sh'
:wq
######################################
vi /etc/ssh/ssh_config 
Host *
        GSSAPIAuthentication yes -> no

클라이언트쪽 설정
rsync -avz --progress 1.255.144.55::
        
# rsync -avz --progress 10.10.33.2::server2/boot /root/backup
- 10.10.33.2 (=server2)의 /boot 디렉토리를 내 컴퓨터의 /root/backup 폴더에 sync 시킨다.

여기서 

# rsync -avz --progress --delete 10.10.33.2::server2/boot /root/backup
- --delete 를 써주고 10.10.33.3 서버에 파일이 없는 것이 있다면 차후 sync 시 내 컴퓨터에 있는것도 삭제된다.

