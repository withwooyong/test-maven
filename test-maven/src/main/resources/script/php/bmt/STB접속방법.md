매니저님 1,2,3,9, OK는 STB 인증 초기화 하는거구요,

IP보안해제는 재생 -> 1 -> 2-> 3-> 1-> OK 입니다. 


[ STB IP보안해제 ]
	STB에 기기정보 들어가서 재생->1->2->3->1->OK 

root@BHX-UH400:/data/btv_home/config # logcat | grep http

STB 셋탑 IP보안해제
재생 1231 OK
server-list.conf
/btv_home/config
busybox vi server-list.conf

logcat 사용법
https://developer.android.com/studio/command-line/logcat.html?hl=ko

logcat -v time | grep http

https://www.joinc.co.kr/w/man/12/nginx/static
6.4. 캐시 상태
앞서 X-Proxy-Cache를 이용해서 캐시의 상태를 반환하고 있다. MISS, HIT, BYPASS외에 몇 가지 상태들이 더 있다.
MISS    캐시를 찾을 수 없다. 요청은 WAS 까지 전달된다. WAS 응답이 끝나면 캐시가 만들어질 것이다.
BYPASS  캐시서버의 캐시를 무시하고, WAS까지 요청을 전달한다. 캐시서버는 아마도 캐시를 가지고 있을 것이다.
EXPIRED 캐시 만료시간이 초과했다. 캐시서버는 WAS로 요청을 전달해서 캐시를 업데이트 한다.
STALE   서버가 제대로 응답하지 않아서, 낡은(stale) 캐시를 서비스하고 있다.
UPDATING    정확한 목적을 모르겠다. 좀 더 살펴봐야 할 듯
REVALIDATED 캐시가 여전히 유효하다. 클라이언트가 if-modified-since를 설정했을 때 리턴한다.
HIT 성공적으로 캐시를 서비스 했다.

동작TB 서버 정보 입니다.

시스템	IP	PORT
CMS	1.255.98.16	8080
MeTV	1.255.86.24	8080
SMDXPG	1.255.86.34	8080
SCS	1.255.98.66	8443
검색	116.126.69.98	29990
SCS	1.255.98.66	8443
WSCS	1.255.98.66	8443
LGS	1.255.98.67	8443
EPS	1.255.98.57	8443
KidsXPG	1.255.86.32	8080
RPS	1.255.98.43	8080


셋탑 접근 방법
가온 BOX PW : Bko-100
UHD1 : relax&think
UHD2 : bentley.conti.GT4-R#2015 입니다.


가온 : LegacyStb_Bko-100@DeviceSK
삼성 : LegacyStb_Smt-E5030@DeviceSK


root@BHX-UH400:/ # cd data/btv_home/config
root@BHX-UH400:/data/btv_home/config # ls
IPTV_license.dat
RCU
adv.v161208.ok
ams.conf
devid.dat
dscp46_server_list
iCAS
icas.conf
ip_accept_list
ip_accept_list.v0903.ok
isqmsAgent.xml
isqmsAgent.xml.bak
isqms_init.info
kids.conf
kids_meta_data.conf
kidszone-isqms-properties.xml
logAgent.conf
media.conf
metv.conf
optisvc.prop
partialPatchNum.dat
pss.conf
qi_agent
rtspbuffer.conf
rws.conf
server-list.conf
server-list.conf.20170407
server-list.conf.back
server-list.v170105.ok
smtdv.conf
station-list.conf
station-list.v0221.ok
trs.conf
tvsdb.conf
tvsdb.v150721.ok
tvservice-setting-properties.xml
tvservice-system-properties.xml
ui_thres.conf
upgradesvc.prop
version.txt
xpg.conf
zconfig.conf
root@BHX-UH400:/data/btv_home/config # cd data/btv_home/config
root@BHX-UH400:/data/btv_home/config # busybox vi server-list.conf
root@BHX-UH400:/data/btv_home/config # busybox cat server-list.conf                                                                                                                                        
<server-list>
        <!-- PSS -->
        <server id="pss" address="https://pss.hanafostv.com" port="8443" format="xml" multi="9"/>
        <!-- XPG -->
        <server id="xpg" address="http://smdxpg.hanafostv.com" port="8080" format="xml|json|bson" multi="7"/>
        <server id="xpg-common" address="http://uixpg.hanafostv.com" port="8080" format="xml|json|bson" multi="7"/>
        <!-- MeTV -->
        <server id="metv" address="http://metv.hanafostv.com" port="8080" format="xml|json|bson"/>
        <!-- WSCS -->
        <server id="wscs" address="https://1.255.115.51" port="8443" format="xml" multi="2"/>
        <!-- License -->
        <server id="license" address="https://license.hanafostv.com" port="8443" format="xml|json|bson"/>
        <!-- WLGS -->
        <server id="wlgs" address="https://lgs.hanafostv.com" port="8443" format="xml|json|bson" multi="6"/>
        <!-- QSCH -->
        <server id="search" address="http://search2.hanafostv.com" port="9990" format="xml|json|bson"/>
        <!-- NSRVS -->
        <server id="nsrvs" address="http://nsrvs.hanafostv.com" port="8080" format="xml"/>
        <!-- AMS -->
        <server id="ams" address="http://vswu.hanafostv.com" port="8080" format="xml|bson"/>
        <!-- MMS -->
        <server id="mms" address="http://mms.hanafostv.com" port="8080" format="xml|bson"/>
        <!-- ADV -->
        <server id="adv" address="http://adv.hanafostv.com" port="8088" format="xml|json|bson"/>
        <!-- ADV log -->
        <server id="advlog" address=" http://adv.hanafostv.com" port="8089" format="xml|json|bson"/>
        <!-- Hotbill (On Screen) -->
        <server id="hotbill" address="https://hotbill.hanafostv.com" port="8080" format="xml|json|bson"/>
        <!-- PNS -->
        <server id="pns" address="http://popnotice.hanafostv.com" port="8090" format="xml|json|bson"/>
        <!-- IPTV -->
        <server id="iptv" address="https://iptv.hanafostv.com" port="8443" format="xml"/>
        <!-- image -->
        <server id="image" address="http://stimage.hanafostv.com" port="8080"/>
        <!-- nsigs -->
        <server id="nsigs" address="http://nsigs.hanafostv.com" port="8080"/>
        <!-- cancel PPM -->
        <server id="cancel_ppm" address="https://promotion.hanafostv.com" port="8443"/>
        <!-- PPM -->
        <server id="ppm" address="https://promotion.hanafostv.com" port="8443"/>
        <!-- swu -->
        <server id="swu" address="https://swu.hanafostv.com" port="8443" format="xml|json|bson" multi="14"/>
        <!-- USS -->
        <server id="uss" address="http://uss.hanafostv.com" port="8080" format="bson"/>
        <!-- STACM -->
        <server id="stacm" address="https://stacm.hanafostv.com" port="8443" format="xml"/>
        <!-- STLOG -->
        <server id="stlog" address="http://stlog.hanafostv.com" port="8080"/>
        <!-- nsepg -->
        <server id="nsepg" address="http://nsepg.hanafostv.com" port="9080" format="json"/>
        <!-- NPS -->
        <server id="nps" address="nps.hanafostv.com" port="8080" format="xml"/>
        <!-- SMTDV -->
        <server id="smtdv" address="http://smd.hanafostv.com" port="8080" format="json"/>
        <!-- MNLOG -->
        <server id="mnlog" address="navilog.hanafostv.com" port="8080" format="xml|json|bson"/>
        <!-- MVS -->
        <server id="mvs" address="http://mvs.hanafostv.com" port="8080" format="xml|json|bson"/>
        <!-- DSM -->
        <server id="dsm" address="http://dsm.hanafostv.com" port="8080" format="xml|json|bson"/>
        <!-- Kids zone XPG -->
        <server id="kidsxpg" address="http://kidsxpg.hanafostv.com" port="8080" format="xml|json|bson" multi="7"/>
        <!-- IIS -->
        <server id="iis" address="http://sbxiis.hanafostv.com" port="8080" format="xml|json|bson" multi="7"/>
        <!-- RWS -->
        <server id="rws" address="http://rws.hanafostv.com" port="8080" format="xml|json|bson" multi="7"/>
        <!-- Voice Search Server -->
        <server id="voice_search" address="STB_SKB" format="xml"/>
        <!-- TRS -->
        <server id="trs" address="1.255.69.25" port="9000" format="xml|json|bson"/>
        <!-- RPS -->
        <server id="rps" address="http://rps.hanafostv.com" port="8080" format="xml|json|bson" multi="7"/>
        <!-- PUSHUPMC -->
        <server id="upmc" address="https://rpushupmc.hanafostv.com" port="8033" format="xml|json|bson" multi="7"/>
        <!-- PUSHUPNS -->
        <server id="upns" address="tcp://rpushupns.hanafostv.com" port="1788" format="xml|json|bson" multi="7"/>
        <!-- EPS -->
        <server id="eps" address="https://eps.hanafostv.com" port="8443" format="xml|json|bson" multi="7"/>
        <!-- notice -->
        <server id="notice" address="239.192.60.11" port="49210" format="xml|json|bson" multi="7"/>
</server-list>root@BHX-UH400:/data/btv_home/config # 
SK BROADBAND
정 아 름 Manager 플랫폼개발팀 
서울시 중구 퇴계로 24 SK남산그린빌딩 6층
02.6266.3262 | 010.3870.7647 | arjeong@sk.com


--- 1.226.200.167 ping statistics ---
3 packets transmitted, 3 received, 0% packet loss, time 2003ms
rtt min/avg/max/mdev = 1.895/2.002/2.120/0.105 ms
[manager@DEV-VM-IRCS-03 ~]$ nslookup 1.226.200.167
Server:         210.220.163.82
Address:        210.220.163.82#53

** server can't find 167.200.226.1.in-addr.arpa.: NXDOMAIN

[manager@DEV-VM-IRCS-03 ~]$ 
[manager@DEV-VM-IRCS-03 ~]$ 
[manager@DEV-VM-IRCS-03 ~]$ 
[manager@DEV-VM-IRCS-03 ~]$ 
[manager@DEV-VM-IRCS-03 ~]$ 
[manager@DEV-VM-IRCS-03 ~]$ nslookup 1.226.200.167
Server:         210.220.163.82
Address:        210.220.163.82#53

** server can't find 167.200.226.1.in-addr.arpa.: NXDOMAIN

[manager@DEV-VM-IRCS-03 ~]$ traceroute 1.226.200.167
traceroute to 1.226.200.167 (1.226.200.167), 30 hops max, 60 byte packets
 1  1.255.144.2 (1.255.144.2)  0.130 ms  0.085 ms  0.093 ms
 2  1.255.84.50 (1.255.84.50)  1.229 ms  0.891 ms  0.995 ms
 3  172.21.254.17 (172.21.254.17)  6.250 ms  6.262 ms  6.222 ms
 4  175.124.54.8 (175.124.54.8)  1.009 ms  1.029 ms  1.058 ms
 5  10.222.13.4 (10.222.13.4)  2.123 ms  2.055 ms  2.116 ms
 6  * * *
 7  222.233.107.190 (222.233.107.190)  1.715 ms 222.233.107.186 (222.233.107.186)  1.731 ms 219.240.70.154 (219.240.70.154)  1.950 ms
 8  1.226.200.167 (1.226.200.167)  1.874 ms  1.845 ms  1.835 ms
[manager@DEV-VM-IRCS-03 ~]$ nslookup stimage.hanafostv.com
Server:         210.220.163.82
Address:        210.220.163.82#53

Non-authoritative answer:
Name:   stimage.hanafostv.com
Address: 116.126.70.233
Name:   stimage.hanafostv.com
Address: 1.226.200.167
Name:   stimage.hanafostv.com
Address: 1.226.200.169
Name:   stimage.hanafostv.com
Address: 1.226.200.41
Name:   stimage.hanafostv.com
Address: 116.126.70.234
Name:   stimage.hanafostv.com
Address: 1.226.200.40
Name:   stimage.hanafostv.com
Address: 1.226.200.166
Name:   stimage.hanafostv.com
Address: 1.226.200.168
Name:   stimage.hanafostv.com
Address: 116.126.70.232
Name:   stimage.hanafostv.com
Address: 116.126.70.231

[manager@DEV-VM-IRCS-03 ~]$

logcat | grep -E "stimage|1.226.200.168"

adb shell logcat | grep -E "tag1|tag2"
Or you could just use logcat's built-in filters:

adb logcat -s tag1:* tag2:*