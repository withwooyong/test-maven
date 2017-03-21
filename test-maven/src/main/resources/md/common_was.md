# [공통] WAS 설치, 설정

[toc]
### 설치파일 다운로드
*tvingadmin* 계정으로 서버설치 공통규약에 의하여 */home/tvingadmin/src* 경로 하위에 다운로드 한다.
~~~~
wget http://mirror.apache-kr.org/tomcat/tomcat-7/v7.0.59/bin/apache-tomcat-7.0.59.tar.gz
~~~~
### 압축해제
~~~~
tar xvfz apache-tomcat-7.0.59.tar.gz
~~~~
### 기동쉘 수정
Tomcat Container가 여러개인 경우 kill시 각각의 pid 기준으로 프로세스를 종료 처리하기 위하여 apache-tomcat-7.0.59/bin/catalina.sh 240라인 부근 하단주석에 *CATALINA_PID* 추가한다. 
~~~~
# ----- Execute The Requested Command -----------------------------------------
CATALINA_PID=$CATALINA_HOME/logs/.tomcat_pid
~~~~
### 심볼릭 링크 추가
*tvingadmin* 계정 홈 하단의 서버설치 공통 규약에 의해 압축해제 된 파일을 복사 후, _번호 형식으로 기동할 was수 만큼 복사한다. 컨테이너 성격에 따라 tomcat_[라벨명]_[컨텐이너 순번] 형식으로 심볼릭 링크를 설정한다.
~~~~
cp -R apache-tomcat-7.0.59 apache-tomcat-7.0.59_01  
ln -s apache-tomcat-7.0.59_01 apache_api_01
~~~~
### alias 추가
*tvingadmin* 계정 홈 경로의 .bashrc 파일에 *JAVA_OPTS*, was 기동/중지, 로그 alias를 추가한다.
~~~~
export JAVA_OPTS="$JAVA_OPTS -server -Xms2g -Xmx2g -XX:MaxPermSize=256m -Dfile.encoding=UTF-8 -Dbil-env=real_jndi"  

alias api1boot='/home/tvingadmin/server/tomcat_api_01/bin/startup.sh'  
alias api1down='/home/tvingadmin/server/tomcat_api_01/bin/shutdown.sh -force'  

alias api1log='tail -f /home/tvingadmin/server/tomcat_api_01/logs/catalina.out'
~~~~
### 로그관리 - catalina.out
*root* 계정으로 */etc/logrotate.d/* tomcat 명칭으로 아래의 스크립트를 추가한다. was의 로그관리 주기는 7일로 설정한다.
~~~~
/home/tvingadmin/server/tomcat_api_*/logs/catalina.out {
        copytruncate
        daily
        rotate 7
        compress
        missingok
        size 20M
}
~~~~
### 그 외의 was 로그관리
was 상의 access.log, host-manager.log의 로그는 *tvingadmin* 계정의 크론탭에 아래의 쉘을 등록 후 별도 관리한다.
~~~~
#!/bin/sh
seven_days_ago_date=`date -d '7 days ago ' +"%Y-%m-%d"`
#echo $seven_days_ago_date
rm -f  /home/tvingadmin/server/tomcat_*/logs/*access_log.$seven_days_ago_date.txt
rm -f  /home/tvingadmin/server/tomcat_*/logs/*.$seven_days_ago_date.log*
~~~~
