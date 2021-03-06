# [JDK 설치 스크립트]

~~~~
#! /bin/sh
#
# JAVA SDK 을 자동으로 설치 합니다.
#

CURRENT_JDK_HOME=`echo $JAVA_HOME`
INSTALL_JDK_VERSION=1.6
if [ -z $CURRENT_JDK_HOME ]; then
        echo "\$JAVA_HOME 이 설정되지 않았습니다."
        echo "이미 JDK가 설치되어 있다면 \$JAVA_HOME을 설정한 후 주시기 바랍니다.\n"

        read -p "JDK를 새로 설치하시겠습니까? (y|n): " IMSI
        IMSI=`echo $IMSI | tr 'A-Z' 'a-z'`
        if [ $IMSI = "y" ]; then
                echo "\n========== Install JDK Configuration ==========\n"

                CURRENT_JDK_HOME="/home/tvingadmin/jdk"
                read -p "JDK를 설치할 절대경로를 입력해 주세요. (default: /home/tvingadmin/jdk) : " IMSI
                if [ ! -z "$IMSI" ]; then
                        CURRENT_JDK_HOME=$IMSI
                fi

                if [ ! -e $CURRENT_JDK_HOME ]; then
                        mkdir -p $CURRENT_JDK_HOME
                fi

                read -p "JDK를 설치할 버전을 입력해 주세요. (1.6 or 1.7) : " IMSI
                if [ ! -z "$IMSI" ]; then
                        if [ $IMSI = "1.6" -o $IMSI = "1.7" ]; then
                                INSTALL_JDK_VERSION=$IMSI
                        else
                                echo "JDK 버전은 1.6 이거나 1.7만 설치가능 합니다."
                                echo "설치 스크립트를 다시 실행해주세요."
                        fi
                fi

                cd $CURRENT_JDK_HOME
                echo "\n========== Download & Install JDK ==========\n"
                if [ $INSTALL_JDK_VERSION = "1.6" ]; then
                        wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2F; oraclelicense=accept-securebackup-cookie" "http://download.oracl
e.com/otn-pub/java/jdk/6u45-b06/jdk-6u45-linux-x64.bin"
                        mv jdk-6u45-linux-x64.bin* jdk-6u45-linux-x64.bin
                        chmod 755 jdk-6u45-linux-x64.bin
                        ./jdk-6u45-linux-x64.bin
                        rm -rf jdk-6u45-linux-x64.bin
                        echo "" >> ~/.bashrc
                        echo "## JAVA_HOME" >> ~/.bashrc
                        echo "export JAVA_HOME=\"$CURRENT_JDK_HOME/jdk1.6.0_45\"" >> ~/.bashrc
                        echo "export PATH=\"\$PATH:\$JAVA_HOME/bin\"" >> ~/.bashrc
                else
                        wget --no-check-certificate --no-cookies --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2Ftechnetwork%2Fjava%2Fjavase%2Fdownloads%2Fjdk7u9-downloads-1859576
.html;" http://download.oracle.com/otn-pub/java/jdk/7u9-b05/jdk-7u9-linux-x64.tar.gz
                        mv jdk-7u9-linux-x64.tar.gz* jdk-7u9-linux-x64.tar.gz
                        tar xvzf jdk-7u9-linux-x64.tar.gz
                        rm -rf jdk--7u9-linux-x64.tar.gz
                        echo "" >> ~/.bashrc
                        echo "## JAVA_HOME" >> ~/.bashrc
                        echo "export JAVA_HOME=\"$CURRENT_JDK_HOME/jdk1.7.0_09\"" >> ~/.bashrc
                        echo "export PATH=\"\$PATH:\$JAVA_HOME/bin\"" >> ~/.bashrc
                fi
                echo "설치가 완료되었습니다. 환경변수를 위하여 source ~/.bashrc 를 실행해주세요"
        else
                exit;
        fi
else
        echo "이미 JDK가 설치되어 있습니다. 설치된 경로는 $JAVA_HOME 입니다."
fi
~~~~