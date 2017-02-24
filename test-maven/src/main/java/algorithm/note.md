1. [한시간에 만드는 대용량 로그 수집 시스템](http://bcho.tistory.com/1158)
https://cloud.google.com/logging/quota-policy 를 보면 스택드라이버 로깅에 쿼타 제한이 초당 500건/계정으로 잡혀있어서. 일반적인 경우는 최대 500 TPS의 성능을 낼 수 있습니다. 그 이상의 성능이 필요하면, 여러 계정을 사용해야 합니다 또는 구글에 별도의 쿼타 증설 요청을 해야 합니다.
하루에, 최대 2천5백만건의 로그를 하나의 프로젝트를 통해서 수집이 가능합니다.

 또한 프리티어의 경우에는 한달에 로그를 5GB  까지 수집이 가능한데, 이게 넘으면 로그가 더이상 수집되지 않습니다. 그래서 아래 내용 처럼 빅쿼리로 Export를 해서 로그가 5GB 이상 스택드라이버에 저장되지 않도록 해야 합니다. (차기전에 데이타를 퍼나르는)
애플리케이션 로그 이외에도, VM 로그등도 이 5GB의 용량을 공유하기 때문에, VM 로그등도 차기전에 GCS로 퍼 나르거나 또는 구글 Support 티켓을 통하여 애플리케이션 로그 이외의 로그를 수집하지 않도록 별도 요청해야 합니다. (로그 저장 용량에 대해서 비용을 지불하면, 이런 제약은 없음)

 백앤드 시스템에서 중요한 컴포넌트중의 하나가, 클라이언트로 부터 로그를 수집 및 분석하는 시스템이다. 
오늘 설명할 내용은 500 TPS (Transaction Per Sec)가 넘는 대용량 로그 수집 및 분석 시스템을  managed 서비스를 이용하여, 쉽고 빠르게 구축할 수 있는 방법에 대해서 소개하고자한다.

 일반적인 로그 수집 및 분석 시스템 아키텍쳐
일반적으로 클라이언트에서 로그를 수집하여 분석 및 리포팅 하는 시스템의 구조는 다음과 같다.
![일반적인 로그 수집 및 분석 시스템 아키텍쳐](http://cfile24.uf.tistory.com/image/2510314D5886F3942F3F82)

 앞단의  API 서버가 로그를 클라이언트로 부터 수집하고 데이타를 정재한다.
로그 저장소가 순간적으로 많은 트래픽을 감당할 수 없는 경우가 많기 때문에, 중간에 Message Q를 넣어서, 들어오는 로그를 Message Q에 저장하여 완충을 한다.
이 Message Q로 부터 로그를 Message Consumer가 순차적으로 읽어서 Log Storage에 저장한다.
저장된 로그는 Reporting 툴을 이용하여 시각화 한다.

 이런 구조 이외에도 API 서버에서 파일로 로그를 저장한 후, Fluentd나, LogStash 등의 로그 수집기를 이용하는 방법등 다양한 아키텍쳐가 존재한다. 

 이런 시스템을 구축하기 위한 일반적인 솔루션들은 다음과 같다.

 | 컴포넌트 | 솔루션 ||
 |--------|--------|--------|
 | API 서버 | node.js, ruby, php 등 일반적인 웹서버 ||
 | Message Q | Rabbit MQ와 같은 일반적인 큐<br> Kafaka 와 같은 대량 큐<br>AWS SQS나 구글 Pub/Sub 같은 클라우드 큐 ||
 | Message Consumer | Multi Thread(or Process) + Timer를 조합하여 메세지를 폴링 방식으로 읽어오는 애플리케이션 개발 ||
 | Log Storage | Hadoop, HBase 와 같은 하둡 제품<br>Drill,Druid와 같은 SQL 기반 빅데이타 플랫폼<br>Elastic Search ||
 | Reporting | Zeppeline, Jupyter 와 같은 노트북류<br>Kibana ||

 구조나 개념상으로는 그리 복잡한 시스템은 아니지만, 저러한 솔루션을 모두 배우고, 설치하고 운영하는데 시간이 들고, 각각의 컴포넌트를 구현해야하기 때문에 꽤나 시간이 걸리는 작업이다. 

 그러면 이러한 로그 수집 및 분석 작업을 클라우드 서비스를 이용하여 단순화 할 수 없을까?

- 스택 드라이버
스택 드라이버는 구글 클라우드의 모니터링, 로깅 및 애플리케이션 성능 분석등 모니터링 분야에서 다양한 기능을 제공하는 서비스 이다. 
그중에서 스택드라이버 로깅은 구글 클라우드나 아마존 또는 기타 인프라에 대한 모니터링과, Apache, MySQL과 같은 써드 파티 미들웨어에 대한 로그 수집 및 모니터링을 지원하는데, 이 외에도, 사용자가 애플리케이션에서 로깅한 데이타를 수집하여 모니터링할 수 있다.
_ _ _

1. [Log Aggregator 비교 - Scribe, Flume, Fluentd, logstash](http://blog.seulgi.kim/2014/04/log-aggregator-scribe-flume-fluentd.html)
 https://docs.google.com/presentation/d/12A5RlMCVDN6tA_zUnLrZ0TN5v08gz2GhlRdzxy3T3mU/edit?usp=sharing
 회사에서 최근에 Log aggregator system으로 무엇을 사용해야 할지 조사해본 자료다.
 우선 log aggregator가 무엇인지 한 문장으로 설명하면, 여러 머신에서 쌓인 로그들을 한 번에 분석할 수 있도록 수집하여 주는 시스템을 말한다.
 요새는 특히나 클라우드 시스템이 유행하면서 같은 일을 하는 시스템임에도 다른 머신에서 돌아가는 일이 많아지면서 필요성이 크게 증가하였다.
이번 조사로 알게 된 것들을 적어보도록 하겠다.

- Scribe
 우선 scribe는 Facebook에서 제작하고 사용하던 log aggregator system이다.
 scribe: http://www.cnblogs.com/brucewoo/archive/2011/12/13/2285482.html
 후에 다른 로그 수집 시스템들을 보면 알겠지만, Scribe는 다른 시스템보다 간단한 구조로 되어 있다.
 Scribe는 일종의 message queue와 message queue에 쌓인 message를 DB에 저장해 주거나, DB가 실패하였으면 local에 저장하였다가 DB가 복구되었을 때 다시 DB에 저장해 주는 것만을 책임진다. 다시 말하면, message queue에 실제로 메시지를 보내는 부분은 사용자가 직접 작성하여야 한다는 것이다.
 흔히들 말하는 scribe의 장점은 c++로 만든 만큼 다른 시스템들의 3~5배 정도의 성능을 보여준다는 것이다. 하지만 실제 scribe 사용자들은 무엇보다도 Facebook이 실제로 사용하였던 솔루션인 만큼 성능과 안정성에서 신뢰도가 있다는 것을 장점으로 뽑는다.

 하지만 나는 scribe를 사용하는 것을 추천하지는 않는다.
 일단 가장 큰 문제는 더이상 Facebook이 Scribe를 사용하지 않는다는 것이다. Facebook은 이미 Java로 작성한 Calligraphus를 사용하기 시작했다. scribe는 open source이고 개발이 완전히 멈춘 것은 아니다. 하지만 Facebook이 중심적으로 만들던 시절에 비하면 거의 발전이 없는 상태라고 해도 될 정도이다.
 앞에서 말했듯이 Scribe는 message를 보내는 부분을 완전히 새롭게 작성해야 한다. 또한, 바이너리 배포를 안하기 때문에 사용하려면 Scribe 자체도 빌드하여야 하는데 Scribe 자체가 여러 라이브러리에 의존성이 걸려있기 때문에 빌드하는 것도 쉽지 않다.

 남은 장점은 성능밖에 없는 관계로 일단 다른 솔루션을 사용해보고, 그것으로 감당이 되지 않을 정도로 많은 부하가 걸리는 게 아니라면 굳이 Scribe를 사용할 이유는 없어 보인다.

- Flume
 다음으로 소개할 Flume은 과거 Cloudera에서 제작하여 지금은 apache의 top level project가 되었다.
 Flume의 가작 큰 특징(?)은 중간에 설계가 크게 바뀌었다는 것이다.

 Flume OG: http://archive.cloudera.com/cdh/3/flume/UserGuide/
 Flume NG: http://flume.apache.org/
 원래의 Flume OG는 master가 존재하여 agent나 collector를 master를 통해서 제어해야 했다. 하지만 Flume NG에서는 각각의 Agent가 독립적으로 어디로 data를 보낼지 결정할 수 있다.
 Flume은 deb package로 배포하고 있기 때문에 사용하기 쉽다는 장점이 있지만, plugin을 만들기 쉬운 구조가 아니므로 주어진 조건에 맞게 사용하는 것 외에는 힘들다는 단점이 있다. 아직은 Flume을 검색하면 Flume OG 시절의 문서와 사용 방법이 나오는 경우가 있다는 것도 단점이다.

- Fluentd
 Fluentd는 ruby와 c로 짜여진 log aggregator 시스템이다.
 fluentd: http://blog.treasure-data.com/post/13047440992/fluentd-the-missing-log-collector-software
 기본적인 구조는 Flume NG와 비슷한 구조로 되어 있다. Flume의 Source, Channel, Sink가 각각 Input, Buffer, Output이 되었다고 보면 된다.
 Fluentd의 가장 큰 특징이자 장점은 각 파트별로 plugin을 만들기 쉽다는 것이다. 직접 plugin을 만들지 않더라도, ruby로 짜여 있으며 plugin을 gem으로 배포하기 때문에 plugin을 쉽게 붙일 수 있다는 것도 큰 장점이다.
 그러면서도 성능이 필요한 부분은 c로 작성하여 ruby로 wrapping을 하였기 때문에 성능이 크게 떨어지지도 않는다.

 물론 Fluentd도 단점이 있다.
 중요한 부분은 c로 짜여 있다고 해도 대부분의 부분이 scribe보다는 느리다.
 대부분이 ruby로 짜여 만큼 ruby의 고질적인 문제인 memory fragmentation을 피할 수 없다.
 로그에 시간을 반드시 남기게 되어 있기 때문에 agent들 사이에 시간이 맞지 않으면 log가 이상하게 쌓이기도 한다.

 하지만 이런 문제들은 전부 어렵지 않게 해결할 방법이 있다.
 scribe보다 느리다고 했지만, 대부분 시스템에서 사용하기에는 충분하다. 그래도 느리다면 멀티코어를 이용하는 플러그인을 사용하면 된다.
 memory fragmentation은 jemalloc을 사용하면 된다.
 시간이 맞지 않는 문제는 클라이언트들에서 ntpd를 돌리면 해결된다.

 사용하기 쉽고, 단점들을 해결하기도 쉽기 때문에 아마 Fluentd를 사용하게 될 것 같다.

- logstash
 logstash는 다른 것들과는 약간 다른 관점에서 조사하다가 나온 것이다.
 로그를 수집하였다고 하더라도 수집된 로그를 분석하여 보기좋게 표현할 방법이 없으면 그건 그저 information이 되지 못한 단순한 data일 뿐이다.
 logstash는 elsaticsearch family의 하나가 되면서 쌓인 로그를 웹으로 보여주는데 좋은 툴인 kibana와 함께 쓸 수 있어서 손쉽게 로그를 보고 분석할 수 있는 기능을 제공해준다.
 하지만 fluentd도 kibana를 붙일 수 있고, logstash자체의 기능이 fleuntd보다 못하기 때문에 굳이 logstash를 쓸 일은 없어 보인다.

_ _ _

[빅데이터를 친구로 만드는 첫 걸음, 바라보는 관점 바꾸기 ①](http://blog.lgcns.com/1132)
1. 인터넷 서비스가 고도화됨에 따라 사용자가 생성하는 정보의 저장과 처리가 필요하고, 이를 위한 Big Cache System이 요구되고 있습니다. 

 DB의 장애와 증설 비용을 고민하던 800만 사용자를 가진 B2C 서비스 시스템에 Open Source Software인 Redis와 HBase기반의 Cache System을 구축하여, 목표한 수준으로 CPU 사용률을 낮출 수 있었죠. 
 
- 알파고와 빅데이터 
 알파고와 이세돌의 바둑 대결은 현재를 살아가는 사람들에게 많은 충격을 주었고, 새로운 세상에 대한 상상력을 자극했습니다. 이러한 알파고의 뒤에는 Deep Learning이란 기술이 숨어있고, 그 뒤로는 분산 병렬 컴퓨팅이라는 든든한 형님이 버티고 있는데요. 무려 1,202개의 CPU와 176 개의 GPU로 동시에 연산을 수행하는 것입니다. 
 ![](http://cfile23.uf.tistory.com/image/2611DC3C576CCF4817C2F2)
 빅데이터(Big Data)란 말이 나온 지는 오래되었지만, 아직 실체가 없이 추상적인 느낌이고 외국에서나 제대로 활용할 것만 같은 느낌이 드는 것도 사실입니다. 매체나 일반인들 사이에서 빅데이터는 ‘분석’의 뉘앙스가 강하고 또 ‘빅 브라더’의 이미지도 겹쳐있어 그리 좋은 느낌을 주지 못하고 있는데요. 이러한 빅데이터의 뒤에도 알파고와 마찬가지로 분산 병렬 컴퓨팅이 버티고 있습니다. 

 빅데이터를 적용할 사용처를 찾고 있는 현업의 입장에서는 빅데이터를 분산 병렬 컴퓨팅 기술 그 자체로 보는 것이 멀게만 느껴지는 빅데이터를 친구로 만들 수 있는 첫걸음이라고 할 수 있습니다. 일단 '분석'이란 단어는 잊고 분산 처리를 적용할 곳을 찾는 것이죠. 찾다 보면 적용하게 되고 적용하게 되면 빅데이터가 모이게 될 것입니다. 그 때 가서 분석을 논하면 되는 것이죠.

 지금부터 소개해드릴 사례는 분산 병렬 컴퓨팅 기술을 이용해 DB의 부하를 덜어주는 아키텍처입니다. 분석과는 무관하지만 이어지는 프로젝트들은 이 아키텍처를 기반으로 큰 데이터를 모을 수 있게 되었습니다.
 
- RDB만으로 B2C 서비스를? 
 인터넷 기업에서는 흔한 분산 병렬 아키텍처이지만, 우리나라의 공공기관과 민간기업은 아직도 상용 소프트웨어로 구성된 전통 아키텍처를 고수하고 있습니다. 이런 아키텍처에서 가장 문제가 되는 부분은 DB입니다. 

 RDBMS(Relational Database Management System)가 상용이기 때문에 유지와 증설에 많은 비용이 드는데요. 증설을 한다고 해도 다시 증설이 필요해집니다. 장애도 이어지죠. 가장 큰 문제점은 RDBMS의 한계입니다. 개인화 추천과 같은 고급서비스가 사업 측면에서 구상되더라도 이전의 아키텍처로는 RDB 증설만으로는 해결할 수 없는 한계 때문에 불가능 합니다.

 서비스와 사용자가 급증하면서 DB에서의 장애와 증설 비용으로 고민하는 한 IPTV 서비스 업체의 의뢰로 OSS(Open Source Software)인 Redis와 HBase를 기반으로 하는 Big Cache System과 Big Table System을 2013년 초 제안했습니다. 이 중 Data Caching System을 가장 트랜젝션이 많은 휴대폰 서비스에 구축하기로 하고 2013년 11월부터 시작하여 2014년 6월 25일 800만 가입자에게 오픈했습니다.
 
- 빅데이터를 처방하기 전
 ① 문제가 되었던 RDB만의 아키텍처
 사용자는 휴대폰이나 STB(Set Top Box), PC 등의 단말을 통해 응용 서버에 데이터를 요청합니다. 응용 서버는 요청 중 일부를 Local File System에 위치한 File Cache로 서비스하지만 대부분 ORACLE에서 데이터를 읽어 서비스합니다.
![](http://cfile22.uf.tistory.com/image/230B773C576CCF481DCE81)
l Fig.1 기존 아키텍처

 ② 발생하는 문제 현상들
 서비스의 특성상 짧은 시간에 사용자의 이용이 집중될 수 있습니다. 응용 서버는 서비스와 사용자별로 여러 대를 운영하고 있어 이러한 집중에 대응할 수 있지만 DB는 사정이 다릅니다. 모든 사용이 집중되죠. 사용자가 800만 명이나 되기 때문에 대비해야 할 Transaction 밀도는 예측하기 어렵고 또 크기는 RDBMS가 감당할 수 있는 수준을 상회합니다. 이런 집중 상황에서 RDBMS의 한계가 나타납니다. Read 또는 Write에 따라 다른 형태의 문제점들이 나타나는 것이죠.

 [Read 집중]
 안정적인 운영과 증설 시점 판단에 있어서 DB 서버의 CPU 사용률은 가장 중요한 지표입니다. 이 시스템의 CPU 사용률 추이는 LRB1 추이와 일치하는데요. Fig.1에서 전반적으로 아래에 위치한 선은 LRB의 추이이고 위는 CPU 사용률의 추이입니다. PRB2 추이는 일치하지 않습니다. LRB에 비교했을 때 PRB는 거의 없는 수준입니다. 

 Fig.3을 보면 LRB와 PRB는 3,510배나 차이가 납니다. 일반적인 시스템의 CPU사용률 증가는 PRB를 동반하는 데 여기서는 전혀 다른 형태를 보입니다. 모든 조회 데이터는 메모리 상의 ORACLE의 SGA에 위치하고 있다는 의미입니다. 

 수행 횟수가 너무 많아 논리적인 읽기만으로 CPU 부하가 올라가는 것입니다. ORACLE은 이미 메모리 DB인 셈입니다. 이런 사실로 혹자의 메모리 DB 도입 의견을 무시할 수 있었습니다. 여기 필요한 것은 Distributed Architecture입니다. 이를 가진 Data Cache와 Big Table System이 필요한 것이죠.
![](http://cfile1.uf.tistory.com/image/211C123C576CCF490D3DC5)
l Fig.2 휴일의 CPU 사용률 및 LRB
 24시간 CPU와 LRB 변화를 겹쳐보았다. 전구간에서 일치한다. 상자 안 23시는 최번시로 CPU 사용률이 평상시보다 월등히 높다.
![](http://cfile23.uf.tistory.com/image/2573EA3C576CCF4933215D)
l Fig.3 ORACLE AWR3 보고서 일부
2013년 3월, RAC 2 Node 중 한 Node의 1주일 간의 통계. 초당 Logical reads가 Physical reads를 매우 높이 상회한다.
 
 최번시(最繁時, busy hour)와 그렇지 않은 시간대의 CPU 사용률의 편차가 크지만 운영 안정성 때문에 최번시 기준으로 보수적인 증설 계획을 세울 수 밖에 없습니다. 따라서 대부분의 시간대에서는 자원이 놀게 됩니다. 이런 어쩔 수 없는 잉여 자원도 사업자에게 부담이 됩니다.

 Fig.2는 예측 가능한 증가이지만 Fig.4는 예측할 수 없는 증가입니다. 가입자 수가 크기 때문에 프로그램 인기도, 이벤트, 연휴, 올림픽과 같은 변수로 인해 사용률이 크게 바뀔 수 있어 최번시 통계만을 가지고 증설하는 것도 용량 부족의 위험을 안고 있습니다.
![](http://cfile21.uf.tistory.com/image/2216753C576CCF4A129923)
l Fig.4 이슈 프로그램 방영 직후 사용률 증가

 [Write 집중]
 시청과 구매 Table에는 많은 쓰기가 발생합니다. Table 별로 일 200만 건 정도 발생하는데요. 성능과 비용 문제로 모든 데이터를 유지할 수 없어 일주일치만 남기고 삭제합니다. 고급 서비스를 위해서는 사용자가 생성하는 정보에 대한 쓰기와 장기간 보관이 필요하지만 DB의 한계로 못하고 있는 것이죠.

 Read 집중보다 정도는 심하지 않지만 사용률 증가 시 Read에 의한 CPU 사용률 증가와 함께 Write도 급격히 증가합니다. 이 때 ORACLE의 Wait Event인 ‘TX: index contention’과 ‘gc buffer busy’가 과도하게 발생하고 쓰기 지연 장애가 발생합니다.

 쓰기 경합이 발생하는 ORACLE의 Object는 이력 Table에 위치한 Timestamp를 갖는 Index들 입니다. 동시에 여러 Process들이 한 Block에 INSERT하는 Right Hand Index입니다. 최근 이력 조회를 서비스가 요구하기 때문에 조회에 필요한 Timestamp열에 대한 Index 생성을 안할 수 없습니다. Fig.4는 Right Hand Index의 실 목록입니다.
![](http://cfile8.uf.tistory.com/image/260CD23C576CCF4A1CF7B5)
l Fig.5 Right Hand Index 목록
 최근 시간 데이터 색인이 위치하는 가장 오른쪽 Leaf Block에 부하가 집중되고(Fig.6) 여러 RAC Node에서 Process들에 의해 번갈아 불려지면서 ‘gc buffer busy’가 대량 발생됩니다. 이 문제를 해결하기 위해서는 Timestamp열에 대한 Index를 포기하거나 Hash Partitioning을 해야 합니다.
![](http://cfile4.uf.tistory.com/image/230F923C576CCF4B197A94)
l Fig.6 Right Hand Index의 Leaf Block 경합
 Partitioning을 Hash로 하게 되면 Right Hand Index 문제가 해결되나 TRUNCATE를 못해 대량의 데이터를 Delete해야 합니다. 이 또한 자원 소모가 많아 서비스 장애로 이어질 수 있는 요인이 됩니다. Range로 하게 되면 TRUNCATE는 가능하나 Right Hand Index 문제가 계속됩니다. 

 지역과 같이 사용자를 나눌 수 있는 열을 전치시켜 Timestamp열과 묶어 Composite Partitioning을 하면 완화시킬 수 있지만, 큰 쓰기 집중 상황에서는 근본적인 해결책이 되지 않습니다.

 이 문제에 대해 제시한 대안인 HBase 기반의 Big Table System에 대해서는 다음 시간에 함께 살펴보도록 하겠습니다.

_ _ _

1. [빅데이터를 친구로 만드는 첫 걸음, 바라보는 관점 바꾸기 ②](http://blog.lgcns.com/1136)
지난 글에서 짚어본 문제점에 대한 해결책으로 HBase 기반의 Big Table System을 제시했는데요. 지금부터는 그 처방과 효과에 대해 살펴보겠습니다.

- 조치된 빅데이터 처방

 Redis와 HBase로 구성된 Data Caching System으로 Read 집중 문제를 해결하고 HBase만으로 구성된 Big Table로 Write 집중 문제를 해결합니다. ORACLE을 대치하지 않고 NoSQL과 RDBMS 각각의 장점을 융합한 Hybrid 구조입니다. Big Table은 구현되지 않았기 때문에 본 글에서 이야기 하지 않겠습니다.

 ![](http://cfile25.uf.tistory.com/image/21529C35577083C23A06EC)
 l Fig. 1 개선 후 아키텍처

 ① 데이터의 특성

 시스템의 데이터는 공통 정보와 사용자 개인화 정보 그리고 개인화 이력 정보로 나눌 수 있습니다. 공통 정보는 Master Data입니다. 개인화 정보는 즐겨찾기와 같은 개인 입장에서의 Master Data이고, 개인화 이력 정보는 구매나 시청과 같이 Time-Series Data입니다.

 Read 집중 문제는 공통 정보와 개인화 정보에서 일어나고 Write 집중 문제는 개인화 이력 정보에서 일어납니다. 공통 정보는 읽기 비율이 쓰기에 비해 월등히 높지만 크기는 작습니다. 관리자가 만들고 사용자가 읽기 때문입니다. 개인화 정보와 개인화 이력 정보는 사용자가 만들기 때문에 읽기와 쓰기가 많고 크기도 큽니다(Tab. 1).

 ![](http://cfile6.uf.tistory.com/image/216D063D57708306207854)
 l Tab. 1 데이터의 특징

 ② Redis

 작지만 읽기가 쓰기보다 많은 공통 정보를 담을 수 있는 NoSQL로 Redis를 선택했습니다. Redis는 메모리 기반의 Key-Value Store입니다. 다양한 특징 중 빠른 Replication 성능을 주목했습니다.

 Redis Slave 10개에 초당 30만 읽기 부하 상황에서 Master에 1만번 쓰기를 하고 매번 모든 Slave에 복제가 이뤄졌는지 확인하고 동기화가 완료되지 않았을 때 1ms 후 다시 확인하는 프로그램으로 시험한 결과는 평균 1.2ms에 복제가 완료되었습니다. 

 1만건 중 9,988건이 첫회 동기화되었고 12건도 1ms 후 확인했을 때 정상 복제되었습니다. 복제 시간이 튀는 것도 있었으나 5ms 이내이고 그래프에서 최초 10ms 소요는 접속 시도 때문입니다. (Fig. 1)

 ![](http://cfile6.uf.tistory.com/image/27798A3D57708306151116)
 l Fig. 2 Redis 복제 시험 결과

 여러 개의 Redis를 Coherent Hashing 기술을 바탕으로 Ling 형태로 구성하는 것이 일반적이지만 대규모 Caching을 목적으로 하지 않고 작은 공통 정보를 N개로 복제하여 읽기 부하를 수평적으로 분산시키는 것이 목적이기 때문에 일반적인 구성 대신 1개 Master에 N개의 Slave를 구성했습니다(Fig. 3).

 ![](http://cfile8.uf.tistory.com/image/2206833D577083070A7045)
 l Fig. 3 Redis Cluster 구성

 ③ HBase

 사용자가 생성하고 조회하는 개인화 정보를 담을 수 있는 NoSQL로 HBase를 선택했습니다. HBase는 Redis와 달리 Permanent Storage를 가지며 Table-like 데이터 구조로 Column을 갖는 NoSQL입니다. Block Cache1 기능과 Sharding2에 주목했습니다.

 Block Cache는 Disk I/O를 줄일 목적으로 한번 읽은 블록 전체를 설정한 크기의 Memory 안에서 유지시키는 기능입니다. 메모리를 충분히 확보하고 사용하면 Memory DB와 같은 성능을 낼 수 있습니다. 

 Result Set의 크기가 0.8KB인 Light SQL과 300KB인 Heavy SQL의 Result Set의 결과를 Redis와 HBase에 각각 Caching하여 100회 수행시켜 소요된 시간은 ORACLE과 비교했습니다. 무거운 SQL일수록 ORACLE보다 Redis와 HBase를 읽는 게 빠르다는 것을 보여줍니다. 

 더불어 Memory DB 중 가장 빠른 Redis와 속도를 비교하면 Light한 경우에 6배 정도 차이가 나지만 Heavy한 경우 HBase와 거의 비슷하게 나옴을 알 수 있습니다.

 ![](http://cfile22.uf.tistory.com/image/23717F3D577083071B904D)
 l Tab. 2 Light SQL 100회 수행 시간

 ![](http://cfile28.uf.tistory.com/image/25743C3D5770830819911B)
 l Tab. 3 Heavy SQL 100회 수행 시간

 HBase는 Row Key3를 기준으로 Sharding을 합니다. 논리적으로 Sharding은 분산 환경에서 수평적으로 데이터를 분할하는 것입니다. 앞에서 이야기했듯이 Redis도 Coherent Hashing으로 분산 캐시를 구현해 대량의 데이터를 Sharding해 처리4할 수 있습니다. 그러나 두 가지 단점이 있는데요. 

 첫째, Redis Cluster가 데이터의 위치를 결정해주지 않습니다. 데이터가 분산환경 어디에 위치할 지는 Client에서 구현해줘야 합니다. 반면 HBase는 Cluster-Side 설정으로 해결합니다. 분할 기준을 설계해 Table을 생성해두면 Client는 연결하고 읽고 쓰기만 하면 됩니다. 

 둘째, HBase는 CP5 계열의 NoSQL로 Data Consistency를 보장합니다. 사용자가 구매 정보와 같은 주요 정보를 일관되게 볼 수 있도록 해줍니다. 반면에 Redis Cluster는 Cassandra6와 같은 구조를 가지고 있어 AP  계열로 볼 수 있습니다. AP 계열의 NoSQL은 Consistency를 보장해주지 않습니다.

 ④ 질의 결과 Caching

 질의 결과를 Caching하면 SQL 기반인 응용 프로그램을 수정하지 않아도 됩니다. 또한 Tab. 2와 Tab. 3이 보여주듯이 경중(輕重)에 상관없이 일정한 속도의 서비스를 제공할 수 있는 장점도 있습니다.

 하지만 SQL의 조회 조건 별로 Cache가 만들어지기 때문에 대용량의 저장소를 필요로 하는 단점이 있습니다. 이런 면에서 저렴하면서 대용량이 가능한 NoSQL이 유리합니다. 또한 ORACLE과 Cache의 동기화를 Row 수준으로 할 수 없습니다. 

 SQL의 특성상 Bind Variable Input 없이 Join된 Table들의 경우 실행 시 어떤 Row들을 처리하는 지를 RDBMS가 아니면 알 수 없기 때문입니다. 그러한 이유로 아래 예시(Fig. 4)에서 A, D Table은 Row 단위로 동기 제어가 가능하지만 B와 C는 Table 단위로만 가능합니다.

 ![](http://cfile5.uf.tistory.com/image/226C513D577083081F2500)
 l Fig. 4 Cache가 적용될 SQL 예

 Table 단위로 동기화할 경우 SQL의 결과와 무관한 Row때문에 Cache가 갱신되어 Cache의 수명이 짧아지는 문제가 있습니다. 그러나 짧아져도 Table 단위 동기화는 효과적입니다. 읽기가 쓰기보다 극도로 많은 시스템에서 Cache 수명이 어느 정도 짧은 것은 Cache 자체의 효율을 크게 떨어뜨리진 않습니다(Fig. 5).

 ![](http://cfile10.uf.tistory.com/image/25021840577083092D5C45)
 l Fig. 5 가상Cache 의 Life

 적용 후 한 Node에서 관측된 Redis Cache의 초당 읽기가 6,000인 사실을 바탕으로 하나의 가상 Cache가 1시간 간격으로 갱신되는 것과 시간당 다섯 번 갱신 되는 것을 비교한 것입니다. Y축의 Scale이 X축보다 매우 크기 때문에 Cache의 효율은 큰 차이가 없습니다.

 Row 단위 동기 제어는 유지보수가 어렵기 때문에 일반화해 쓰기 어렵습니다. 실제 운영되는 시스템의 데이터를 변경하는 주체는 수작업 변경을 포함해 다양합니다. 변경 주체들을 관리하는 인원과 조직도 다르기 때문입니다(Fig. 14). 그러나 주요 Data에 한해서(여기서는 사용자 ID와 방송 대상 항목들) Row 단위로 제어하도록 했습니다.

 ⑤ Cache 갱신의 주체는 사용자

 관리자가 Cache를 직접 갱신하지 않고 사용자 Transaction이 갱신의 주체가 되도록 했습니다. 작은 Dataset을 Caching하는 경우 전자의 방법을 큰 문제 없이 쓸 수 있지만 Dataset의 크기가 큰 경우에는 적합하지 않은 방법입니다. 

 기존 시스템에도 일부 공통 정보에 대해서 File 형태의 Cache를 운영 중인데 별도의 서버에서 장시간 Dataset을 만들고 있습니다. Database와 Cache의 실시간 동기화가 어려운 구조입니다.

 실시간에 가까운 동기화를 위해 Dataset을 이동시키는 대신 변경 사실만 이동시키는 구조로 설계했습니다. 변경 사실은 Dataset의 크기가 작아 관리가 쉽고 변경 주체로부터 Cache System까지 정보 전달의 시간을 최소화할 수 있습니다.

 사용자 Transaction이 Cache System에 접속하면 자체 Logic으로 변경 사실을 확인하고 연관된 Cache를 갱신합니다. 다음 변경 사실이 전달될 때까지 Cache는 이후 사용자들에게 공유됩니다.

 Redis Cluster에서 변경 정보는 Master에 써지고 여러 Slave들로 자동 복제됩니다. 사용자는 Load Balancing Logic에 의해서 어떤 Slave에서 접속하고 복제된 변경 정보를 참조해 Cache를 읽거나 생성합니다(Fig. 6).

 ![](http://cfile26.uf.tistory.com/image/2415A040577083091FDDFA)
 l Fig. 6 Redis Cluster의 Cache 생성

 HBase Cluster에서 변경 주체는 사용자 자신의 Update, Insert, Delete 처리입니다. 변경 사실을 자신의 Transaction 안에서 HBase로 씁니다. 이것이 개인화 정보의 변경 정보입니다. 공통 정보의 변경은 Redis에서 읽습니다. 두 정보를 참조해 Cache를 만들거나 읽습니다(Fig. 7).

 ![](http://cfile30.uf.tistory.com/image/2536A9405770830A0460D8)
 l Fig. 7 HBase Cluster의 Cache 생성

 ⑥ 동기화 Queue Batch

 Database에서 일어나는 변경 사실을 Cache System에 전달하기 위해 비동기적인 방식을 선택했습니다. Queue Table과 그것을 일정한 주기로 읽어 Cache System에 쓰는 Batch 프로그램 입니다. 

 변경 주체가 다양하고 개발된 프로그램 언어도 다르기 때문에 NoSQL인 Cache System을 직접 갱신할 수 있도록 각자에 맞는 환경을 제공하는 것이 만만치 않기 때문입니다. 변경 주체는 SQL을 이용해 Queue Table에 Insert하면 되기 때문에 적용이 간편해집니다.

 비동기식이지만 대상이 매우 작기 때문에, 시간 단위로 최대 2만여 건, 변경 사실은 수초 이내로 Cache에 반영됩니다(Tab. 4).
 
 ![](http://cfile27.uf.tistory.com/image/23734033577084EA06B6D4)
 l Fig. 8 Redis Cluster의 Cache 생성

 ![](http://cfile25.uf.tistory.com/image/2777B7405770830A363FAE)
 l Tab. 4 1개월 Redis Queue Batch 처리 통계
 (2015년 1월 한달 동안의 Redis 쪽에 대한 통계. HBase 통계는 EAI로부터 들어오는 사용자 기본 정보이기 때문에 양이 적어 제외)

 ⑦ 공통 모듈

 크게 세 부분이 있습니다. NoSQL에 대한 연결과 Read, Write을 담당하는 HBase와 Redis core가 각각 있습니다. 그리고 Interface를 담당하는 부분이 있습니다. 기존 응용 프로그램들이 Middleware인 TMAX사의 TP Monitor 위에서 Pro*C 기반으로 구축되어 있기 때문에 공통 모듈은 C/C++ 기반의 API 형태로 제공해야 했습니다.

 ![](http://cfile9.uf.tistory.com/image/2274D6405770830B39901C)
 l Fig. 9 S/W 구성도

 공통 모듈의 기본 기능은 사용자가 SQL을 수행했을 때 Cache의 내용이 최신인지 확인하고 최신이면 돌려주고 그렇지 않으면 ORACLE에서 읽어와 Cache를 새로 만들고 결과를 주는 것입니다.

 Load Balancing 기능이 있습니다. NoSQL Cluster의 Redis Slave들과 Thrift Server들에 대해 부하가 고르게 분산되도록 합니다. 장애 감지를 위해 Cluster 내부와 소통이 필요하기 때문에 Hardware인 L4를 사용하지 않았습니다.

 Runtime Blocking 기능이 있습니다. Cluster 내 N개의 Node들에게 부하를 분산하여 처리하는 과정에서 한 Node의 장애를 감지하면 그쪽을 폐쇄하고 정상 Node로 우회할 수 있도록 합니다. HBase의 경우 HBase RegionServer 장애와 Thrift 장애를 구분하여 대응해야 합니다. 후자는 연결 장애지만 전자는 데이터 장애이기 때문입니다.

 Full Blocking 기능이 있습니다. Redis와 HBase Cluster를 각각 또는 모두 사용하지 않도록 하는 기능입니다. NoSQL 전체 장애 대응과 유지보수 차원에서 필요한 기능입니다.

 주기적으로 정합성을 점검하는 기능이 있습니다. Cache와 DB의 동기화가 깨질 수 있는 위험이 항상 존재하기 때문에 깨진 채 방치되는 것을 막기 위해서입니다. 어떤 Cache의 읽기가 설정된 횟수에 도달하면 RDB와 Cache의 데이터 정합성을 강제 확인합니다. 

 확인 결과가 다르면 해당 Cache를 더 이상 사용하지 못하도록 하고 관리자에게 Reporting합니다. 점검 간격과 횟수를 2단계로 설정할 수 있습니다. 따라서 초반을 짧게 하고 후반을 길게 설정하여 개발자 실수에 의한 부정합을 초반에 대응할 수 있습니다.

 HBase 쓰기 실패에 대한 대응 기능이 있습니다. HBase가 부분 및 전체 장애 시 RDB와 Cache의 정합이 깨질 수 있습니다. 이에 대한 대비로 RDB에 Queue 개념의 Table을 만들어 Batch를 통해 장애 복구 후 동기화되도록 했습니다.

 Long Running SQL에 대한 대응 기능이 있습니다. 실행에 오래 걸리는 SQL은 Cache 생성에 오래 걸리기 때문에 만드는 동안 응답속도를 위해 Dirty Cache가 조회되도록 했습니다.

- 빅데이터 처방의 효과

 ① 시험 환경 및 시나리오

 ORACLE Database를 공유하는 7개 서비스 중 D 시스템의 28개 프로그램에 Cache를 사용하도록 적용했습니다. D 서비스는 RAC의 한쪽 Node에서 서비스되고 있었습니다.

 ![](http://cfile30.uf.tistory.com/image/267A86405770830C34478E)
l Tab. 4 전체 프로그램 중 18% (D) 적용

 운영 Database 서버는 RAC 2 Node이고 사양은 아래와 같습니다.

 ● HP Superdom 32 core (3,910,767 tpmc)
 ● Memory 256GB
 ● Storage VNX5300
 ● Oracle 10.2.04

 Redis Cluster는 하기 사양의 서버 8대로 구성된 LG CNS SBP Appliance 를 씁니다. 이중 2대는 Master, 6 대는 Slave입니다.

 ● HP DL360p Gen8
 ● CPU 1P/8 Core 2.6 GHz
 ● Memory 64GB
 ● Redis 2.8.0

 HBase Cluster는 하기 사양의 서버 8대로 구성된 SBP Appliance를 씁니다. 이중 2대는 Hadoop Namenode이고 6대는 Datanode입니다.

 ● HP DL380p Gen8
 ● CPU 2P/16 Core 2.6 GHz
 ● Memory 64GB
 ● Apache HDFS 1.1.2
 ● Apache HBase 0.94.5
 ● Thrift 0.8.0

 사용률이 가장 높은 시간대 직후인 23시 29분에 Cache를 사용하지 않게 Blocking하고 ORACLE만 사용하도록 했습니다. Blocking 시점 전후로 3분간 사용률이 같다고 가정하고 서버 지표인 CPU 사용률과 Database 지표인 초당 LRB 수, 초당Executions 수를 비교했습니다. Blocking은 26분간 유지했습니다.

 ② 시험 결과

 Cache 없었을 때와 비교해 CPU 사용률은 약 32% 감소하였고 초당 LRB는 56%, 초당 Executions 는 36% 감소하는 결과를 얻었습니다. 사용자의 이용이 많을수록 Cache 효과가 높아지는 아키텍처이기 때문에 최번시에 더 높은 효과를 보였을 것으로 예상됩니다.

 ![](http://cfile26.uf.tistory.com/image/27321B405770830C08B425)
 l Tab. 5 Cache 효과 수치

 ![](http://cfile27.uf.tistory.com/image/2609443D5770830D08E962)
 l Fig. 10 Cache 효과 Graph

- 빅데이터 처방 후기
 주변에 많은 우려가 있었지만 OSS인 HBase와 Redis 그리고 Commodity Linux Server로 구성된 LG CNS SBP Appliance를 B2C 성격의 1등급 시스템에 적용하여 안정적으로 운영하는 성과를 얻었습니다. 2014년 6월 25일 오픈부터 글을 쓰고 있는 2015년 2월 12일까지 6개월 이상 무 장애로 운영되고 있습니다.

 Big Cache System의 관점에서는 Big Data를 OSS로 Caching할 수 있는 Architecture와 기술을 성립했습니다. 그리고 SQL 결과를 Caching하는 구조는 기존 응용 시스템의 수정이 크게 요구되지 않기 때문에 다양한 목적의 시스템에 적용할 수 있을 것입니다. Big Cache System은 Big Table System과 더불어 RDB 증설의 악순환을 끊을 수 있을 것입니다.

 Big Cache System의 한 부분으로 운영된 HBase가 성능과 안정성을 고객으로부터 인정받은 것도 성과입니다. Hbase가 중요한 이유는 영구 저장소를 가지고 있기 때문인데요. 향후 다양한 Data를 저장하는 용도로 사용될 것입니다.

 Big Cache System의 Cache Monitoring을 통계 기반으로 강화하고, 수집된 정보의 분석을 통해 개발자 오류 등을 찾아 Cache의 수명을 늘리는 것이 앞으로의 과제가 될 것입니다.

_ _ _

[RDB NoSQL CAP이론](http://blog.naver.com/2evergr/220788616093)

- RDB (Relational Database)
 ●  관계형 데이터베이스. 테이블가 테이블 사이의 관계가 정의됨.
 ● 서버 자체 성능 증강(Scale up) 을 통한 성능 향상이 중요하다.
 ● consistency 가 유지되어야 하는 Business Data 처리에 적합하다. (고객, 상품 계좌, 주문, 입금)

- NoSQL (Not noly SQL)
 ● Table-Column 과 같은 스키마 개념이 없다. 즉, 데이터 관계와 정해진 규격이 없다.
 ● 단순 검색 및 저장만을 위한 작업에 매우 최적화 됨.
 ● 서로의 관계를 정의할 수 없어 join 이 불가능하다.
 ● 서버 대수 증가를 통한(Scale out) 을 통한 단순처리를 동시 병행적으로 처리해야하는 분산처리에 용이하다.
 ● 개개의 데이터가 비지니스 적으로 중요하지 않지만 로그성이나 히스토리성, 대량으로 모으면 가치가 있는 정보를 발견할 가능성이 있는 데이터 집합을 모으는데 적합하다. (고객방문로그, SNS 를 통한 VOC, 생산 설비의 상태, 포탈의 주요 검색 키워드)

- CAP 이론
  분산 컴퓨팅이 보장해야 할 3가지 특징
  3가지 특성중 2가지만 보장 가능 하며 3가지를 모두 충족할 수 없다는 이론
 ● Consistency(일관성): 모든 노드가 같은 시간에 같은 데이터를 본다.
 ● Availability(가용성): 일부 노드가 다운되어도 다른 노드에 영향이 없다.
 ● Partition Tolerance(부분결함 허용): 일부 메세지 손실보다 시스템의 정상 작동이 더 중요하다.
![](http://postfiles2.naver.net/20160816_145/2evergr_1471311353815U6vFH_PNG/%BD%BA%C5%A9%B8%B0%BC%A6_2016-08-16_%BF%C0%C0%FC_10.29.00.png?type=w2)

_ _ _

1. 트랜잭션(Transaction)의 4가지 특성 (ACID)

- 원자성(Atomicity)
 트랜잭션에서의 원자성이란 더 이상 쪼개질 수 없는 작업 단위로서,  이 작업 단위는 정상적으로 함께 수행되거나 전혀 수행되지 않음을 보장해야 한다.  'All or Nothing' 으로 많이 표현되곤 한다.
예를 들어, 한 번의 송금으로 두 사람에게 각각 50원씩 보내도록 했을 때 둘 모두가 동시에 처리되거나 모두 입금되지 않도록 하는 것을 말한다.

- 일관성 (Consistency)
 트랜잭션의 수행이 DB의 일관성을 보존해야 한다. 즉, 트랜잭션이 성공했을 경우 기존의 기본키/외래키 같은 무결성 제약조건이나 데이터간의 정합성이 일치하는 상태가 유지되어야 한다.
예를 들어, 50원을 어떤 사람에게 2번 송금 했는데 상대방에게는 입금이 안 되었거나 1번만 입금되는 등의 데이터 불일치가 생겨선 안 된다.

- 고립성, 독립성(Isolation)
 여러 트랜잭션이 동시에 실행되더라도 각 트랜잭션은 다른 트랜잭션과 독립적으로 수행되어야 한다.
고립성이 유지되는 가장 좋은 방법은 트랜잭션이 순차적으로 수행되는 것이지만, 병렬적으로 수행되는 상황에서도 사용자에게는 순차적으로 수행되는 것처럼 보이며 결과도 이와 같아야 한다.
이를 위해 한 트랜잭션의 중간 과정이 다른 트랜잭션에게는 숨겨지고 영향을 주지 않아야 한다. 다른 트랜잭션의 영향을 받게 되면 rollback 이 되어도 원래의 상태로 돌려지지 않을 수 있다.

 단, DBMS 의 차이나 버전에 따라 고립성 수준은 변경될 수 있으며 사용자가 설정할 수도 있다.
대표적인 고립성 수준의 구분은 4가지 (Read Uncommitted, Read Committed, Repeatable Read, Serializable Read) 가 있고 고립성 수준이 높을 수록 동시성이 떨어진다.

- 지속성(Durability)
 성공적으로 수행된 트랜잭션의 결과는 지속성이 있어야 한다. 
즉, 트랜잭션이 성공적으로 완료된 후에는 다른 트랜잭션에 의해 변경되기 전까지 소프트웨어 혹은 하드웨어적인 장애가 발생되는 한이 있어도 변하지 않고 보존된다.

_ _ _ 

1. [OAuth란?](http://interconnection.tistory.com/76) 
"웹, 앱 서비스에서 제한적으로 권한을 요청해 사용 할 수 있는 키를 발급해주는 것입니다."
사용하는 이유는 엄청나게 많은 각 서비스별 ID와 Password를 다 기억하고 로그인시 입력을 해야하는 경우가 생깁니다.
그런데 이 OAuth API를 사용하는 사이트는 그렇지 않습니다. 제한된 설정으로 연결하여 인증하면 그만이죠.

- 예를 한번 들어보겠습니다.
자동차에는 발렛키라는게 있다고 합니다. 중저가의 차만 타본 저는 잘 이해가 안가지만,
이 발렛키는 사용할 수 있는 권한이 제한적입니다.
차량을 발렛을 하는 사람으로 가장해서 탈취하려는 시도를 막기 위해서,
트렁크를 열 수 없고, 주행거리는 1km이하로 주행되고 그 이상은 안된다고 합니다.
발렛키의 예에서 보는 것처럼 어떤 제한적으로 인증을 해서 사용이 가능하게끔 하는 역할을 하는 것이 OAuth입니다.
OAuth를 이용하면 특정 기능과 리소스에 접근 할 수 있는 인증 토큰(Access Token = 발렛키)를 발급 받을 수 있습니다.
인증 토큰으로 서비스로 접근하는데에 사용을 합니다.

- OAuth의 원리와 과정
1) Resource Owner(사용자)
2) Authorization Server(인증 서버)
3) Resource Server(REST API)
4) Client
이렇게 OAuth가 네 가지의 구성으로 이루어집니다.

 ![](http://cfile29.uf.tistory.com/image/2348E54558353FD917BDFD)

 이제 OAuth를 사용해서 구성들이 어떠한 방식으로 작동하는 지에 대해서 이야기를 해보겠습니다.
위에서 언급했지만 다음 네가지의 구성이 있습니다.
1) Resource Owner(DB를 장악하고 있는 OAuth를 사용하는 사람)
2) Authorization Server(OAuth 인증 서버)
3) Resource Server(REST API Server)
4) Client(Resource를 사용하는 직접 사용자)

 ![](http://cfile10.uf.tistory.com/image/25238637583547EC0A3023)

_ _ _
1. [쿠키와 세션](http://interconnection.tistory.com/74)

 ![](http://cfile6.uf.tistory.com/image/241EE049582DA37607A255)

- 일단 쿠키와 세션이 왜 사용되는가? 그 질문이 앞서 설명되어야합니다.
 쿠키와 세션은 HTTP 프로토콜의 약점을 커버하기 위해서 존재합니다.
HTTP 프로토콜은 Connectionless하고 Stateless하다라고 이야기합니다.
무슨 이야기인가 하면,
** Connectionless : 클라이언트가 request를 서버에 보내면, 서버는 클라이언트에게 response를 하면 서로 접속을 끊는 특성입니다.
 Stateless : 접속을 끝는 순간 클라이언트와 서버의 통신은 끝나고 상태 정보는 유지하지 않는 특성입니다.**

 이 두가지 특성이 우리에겐 장점이자 약점이 됩니다.
장점은 통신를 유지하고 있으면 드는 모든 리소스 낭비를 줄인다는 것에 아주아주 큰 장점이지만,
동시에 약점이 존재하게 됩니다.
약점은 통신을 할때마다 계속 내가 누구라는 인증을 계속해야합니다.
예를 들면, 네이버 로그인을 하시고 많은 서비스 이용하는데, 서비스를 클릭해서 이동할때마다 전부 로그인을 다시 해야합니다.
이때 바로 HTTP 프로토콜을 사용하여 Server가 Client를 식별할 수 있도록 쿠키와 세션을 사용해서 해결합니다.

쿠키와 세션에 대해서 하나하나 이야기 해보겠습니다.

* 쿠키(Cookie)
- 쿠키는 클라이언트에 저장되는 키와 값이 들어있는 작은 데이터 파일입니다.
- 쿠키는 이름, 값, 만료 날짜(쿠키 저장기간), 경로 정보가 들어있습니다.
- 쿠키는 일정 시간동안 데이터를 저장할 수 있어서 로그인 상태를 유지합니다.
- 쿠키는 클라이언트의 상태 정보를 본인 하드 디스크에 저장하였다가 필요할 때 참조, 재사용합니다.

* 쿠키의 원리
1) 클리이언트가 브라우저로 웹페이지 접속
2) 클라이언트가 요청한 웹페이지를 전송받으면서 쿠키(클라이언트의 상태정보) 하드에 저장
3) 클라이언트 재 방문시 웹페이지 요청과 함께 쿠키값도 전송
4) 지속적으로 로그인 정보를 가지고 있는 것 처럼 사용

* 쿠키의 사용 예
- 방문 사이트에서 아이디와 비밀번호를 저장하시겠습니까? 라고 나타나는 것은 쿠키로 저장하겠냐 라는 뜻이다.
- 팝업이 나타날때 "오늘 이 창을 보지 않음"을 누르면 오늘 그 창이 뜨지 않는 것.
- 쇼핑몰에서 장바구니같은 기능을 사용하는 경우 저장형태가 쿠키이다.

* 쿠키의 제약 조건
- 클라이언트에 총 300개까지 쿠키를 저장할 수 있습니다.
- 하나의 도메인 당 20개의 값만 가질 수 있습니다. (20개를 초과하면 가정 적게 참조된 쿠키가 지워짐.)
- 하나의 쿠키 값은 4MB까지 저장이 가능합니다.

Response Header의 Set-Cookie 속성을 사용하면 클라이언트에 쿠키를 만들 수 있다.

쿠키는 사용자가 요청하지 않아도 브라우저는 Request시에 Request Header에 넣어서 알아서 서버에 전송합니다.

---

* 세션(Session)
- 세션은 클라이언트와 웹서버 간 네트워크 연결이 지속 유지되고 있는 상태를 말합니다.
- 즉, 사용자가 브라우저를 열어 서버에 접속한 뒤 접속을 종료할 때 시점 까지를 말합니다.
- HTTP 프로토콜은 비접속형 프로토콜이므로, 매 접속시마다 새로운 네트워크 연결이 이루어지는데, 세션이 연결 유지를 가능하게 해줍니다.
- 클라이언트가 웹서버에 Request를 보내면, 해당 서버의 엔진이 클라이언트에게 유일한 ID를 부여하는데, 이 ID를 세션이라고 부릅니다.
- 세션 ID는 임시로 저장하여 페이지 이동 시 이용하거나, 클라이언트가 재 접속 했을 때 클라이언트를 유일하게 구분하는 수단이 됩니다.

* 세션의 원리
1) 세션 ID를 서버에서 클라이언트가 자시 웹사이트에 접속시 발급해준다.
2) 서버에서 클라이언트로 발급해준 세션 ID를 쿠키를 사용해서 저장합니다. (쿠키 이름은 JSESSIONID)
3) 클라이언트는 다시 접속시, 이 쿠키(JSESSIONID)를 이용해서 세션 ID 값을 서버에 전달합니다.

* 세션의 장점
- 각 클라이언트에게 고유 ID를 부여합니다.( 정확한 구분)
- 세션 ID로 클라이언트를 구분해서 클라이언트의 요구에 맞는 서비스를 제공할 수 있습니다.
- 사용했던 보았던 정보들을 서버에 저장하기때문에 보안 면에서 쿠키보다 우수합니다.

* 세션의 단점
- 서버에 저장되는 세션때문에 서버에 처리를 요구하는 부하와 저장 공간을 필요로 한다.
-

* 세션의 사용 예
- 로그인과 같이 보안상 중요한 작업은 세션을 통해서 작동합니다. 읽어버리면 안되는 부분이기 때문입니다.

* 쿠키와 세션의 차이점
 쿠키와 세션은 비슷한 역할을 합니다. 동작원리 또한 비슷합니다. 왜냐하면 세션도 결국 쿠키로 저장해서 사용하기 때문입니다.
그러나 큰 차이점은 저장되는 위치입니다.
쿠키는 클라이언트에 저장되어서 보내는 역할을 하고,
세션은 서버에 저장되어서 클라이언트에게 알려줘서 사용합니다.
쿠키는 서버의 자원을 전혀 사용하지 않습니다. 클라이언트 단에서 저장을 해서 사용하기 때문입니다.
세션은 서버에 저장되어서 그 고유한 ID를 가지고 있습니다. 그래서 서버의 자원을 사용합니다.
쿠키와 세션은 서버의 자원을 사용할 수가 있습니다. 쿠키와 세션의 만료되는 기간도 다릅니다.
쿠키는 클라이언트에 저장되어서 서버에 요청시 빠른 속도를 냅니다.
세션은 서버에 정보가 있기때문에 서버의 처리가 필요해서 쿠키보다 느린 속도를 냅니다.
_ _ _

1. [네트워크 전송 GET, POST 비교](http://interconnection.tistory.com/72)
 개발을 하면서 서버에 요청을 하면서 굉장히 당연스럽게 POST, GET을 겸해서 사용해왔습니다.
GET방식과 POST방식은 도대체 무슨 차이점이 있을까요?
일단은 왜 사용하는 지에 대해서 일단 정리를 하겠습니다.
"GET, POST 방식의 request는 서버로 본인이 가지고 있는 data를 전달하기 위해서 사용합니다."
Data를 전달한다라는 본질적인 기능적 측면에서는 똑같다고 볼 수 있습니다.
그런데 세부적으로 보안, 전달형식, 전달할 수 있는 data의 량 적인 측면에서 봤을 때 차이점이 발생합니다.
이제 GET과 POST 방식이 가지고 있는 특징을 살펴보겠습니다.

- GET 방식
 클라이언트로가 입력한 query의 이름과 값이 결합되어 스트링 형태로 서버에 전달됩니다.
 <FORM> TAG의 METHOD 속성의 값으로 GET을 입력합니다.
 ex) `<FORM NAME="form1" ACTION="index.jsp" METHOD="GET">`
 한번 요청시 전송 데이터 양은 주소값+파라미터로 255자(HTTP/1.1인 경우 2048자)로 제한됩니다.
 DB에 추가로 정보를 처리하지않고, 저장된 Data를 단순 요청하는 정도로 사용합니다.
 Dependancy : POST < GET
 URL에 그대로 query의 이름과 값이 같이 연결되어 표현됩니다.
ex) http://www.naver.com/news?title=하야

![](http://cfile28.uf.tistory.com/image/255F47335825C6A1139CA2)

*POST 방식
- 클라이언트와 서버 간에 인코딩하여 서버로 전송합니다.
- 헤더를 통해 요청이 전송되는 방식입니다.
- <FORM> TAG의 METHOD 속성의 값으로 POST을 입력합니다.
ex) `<FORM NAME="form1" ACTION="index.jsp" METHOD="POST">`
- 한번 요청시 데이터 양은 제한이 없습니다.
- DB에 추가로 서버에서 갱신 작업을 할때, 서버에서 정보가 가공되어 응답하는 경우에 사용합니다.
- Dependancy : POST > GET
- POST 방식 : 클라이언트에서 데이터를 인코딩 -> 서버측에서 디코딩 해서 사용합니다.
- Query는 body 안에 들어가 있어서 보안에 조금 유리함이 있습니다.

![](http://cfile10.uf.tistory.com/image/253A4F395825C6B82FE89A)

- POST 방식이 나온 이유
 GET 방식으로 넘길 수 있는 데이터의 양은 한계가 있기 때문이 있고,
 사용자들에게 쉽게 URL path와 query를 노출되지 않게 body에 넣어서 노출되지 않게 하기 위해서 입니다.
(다만, GET, POST 둘 다 보안은 취약합니다.)
그리고 여기서 주목해야하는 부분은 어디에 GET과 POST를 사용하는가?

 GET은 가져온다는 개념이고, POST는 수행한다는 개념으로 받아들이면 됩니다.
조금 더 자세히 사용하는 차이점에 대해서 이야기를 하자면,
GET은 Select와 비슷한 성향을 가지고 있습니다.
서버에서 어떤 데이터를 가져와서 보여줄 때 사용합니다. 즉, 서버의 어떤 값이나 내용, 상태 등을 바꾸지 않는 경우에 사용합니다.
예를 들면, 게시판에서 글의 내용에 대한 목록을 보여주는 경우나 글의 내용을 보는 경우입니다.

 POST는 서버의 값이나 상태를 바꾸기 위해서 사용합니다.
글쓰기를 하면 글의 내용이 DB에 저장/수정시에 DB의 값이 변경되게하는 경우에 POST를 사용합니다.
예를 들면, 게시판의 글을 써서 올리거나, 수정하는 경우입니다.

- GET과 POST를 잘 못 사용하는 경우에 대해서는 Google "Accelerator" 사건이 있습니다.
 Google은 웹페이지를 더 빠른 속도로 제공하기 위해서 현재 웹페이지에 URL로 Link가 쓰여있으면 미리 해당 페이지의 내용을 가져와서,
Link의 내용을 가져옵니다. 그래서 웹페이지를 전환하는 경우 빠르게 전환될 수 있게 합니다.
마치 한 번 방문했던 사이트에 다시 접근할 때 빠르게 기존의 내용을 불러와 빨리 보여주는 것과 같습니다.
그런데 문제는 구글러라는 구글의 개발자들이 GET, POST의 개념이 없이 개발을 시작하면서 발생했습니다.
WEB RESTful API의 CRUD의 지식에서 생각을 해보면,
GET은 Read를 호출하는 경우에 사용을 합니다. 그리고 나머지 Create, Update, Delete는 POST를 이용하는 게 맞습니다.
그런데 마음대로 혼용해서 구글에서 개발을 해서 Delete 같은 곳에도 GET을 이용해서 개발하여,
Accelerator가 웹페이지의 URL을 가져오는 경우 GET으로 당연히 가져오는 것으로 인식하였지만,
중간에 Delete를 GET으로 개발해놓은 부분과 충돌하여 링크의 메일이나 게시글이 마구 지워지는 사태가 발생했습니다.
허나 GET도 정말로 필요한 부분이 있습니다.
URL안에 해당 사이트가 어떻게 구성되었고, 어떤 Query를 담아서 요청하는 지가 보이는 문제로 보안에 위협이 되었습니다.
그런데 그 부분이 또 하나의 장점이 되는 경우가 있습니다. 요새 흔히 사용하는 "카카오톡의 공유하기"와 같은 기능입니다.
공유하기 기능은 URL을 보내줌으로써 공유를 하는 것인데, POST에는 URL에 보안때문에 담고 있지 않아서 공유하기가 힘듦니다.
그래서 여기서 GET의 보안적 문제점이지만 링크를 공유할 때 장점이 되는 URL에 모든 내용을 담고 있는 부분입니다.
마지막으로 결론은 GET과 POST 둘 다 어떤 부분에 사용을 하는 지 설계하고, 그다음에 사용을 해야한다는 것입니다.

_ _ _

1. [NoSQL의 필요성과 주요 제품 비교](http://jacking.tistory.com/1332)
 데이터를 둘러싼 환경의 변화
데이터 볼륨 확대화 → Google이나 Facebook의 보유 데이터가 페타 바이트 급으로
데이터 처리 응답 속도가 중요 → Web 사이트의 방문자 수가 초당 10 만 액세스
데이터의 다양성 → 비 구조 데이터가 많아지고 있기 때문에 RDBMS으로 저장이 곤란
 
- RDBMS의 현황
 확장 성 한계에 도달
 비정형 메타 데이터 관리가 어려워
 빅 데이터화
 데이터베이스의 변경에 중지가 따른다
 처리에 많은 시간이 걸렸다(예 : EC 사이트에서 제품 카탈로그 업데이트 12 시간)
 상용 RDBMS는 스케일 업이 너무 비싸다
 스키마 재정의에 공수가 너무 많이 소요된다
 
- NoSQL 등장 NoSQL의 특징은 다음과 같다.
 비 구조 데이터의 취급이 용이하다
 수평 분산이 용이하다(결과 무결성)
 ※ RDBMS는 "강한 일관성'을 유지하기 위해 수평 분산이 머리 아프다.

- Hadoop의 차이 Hadoop의 특징은 다음과 같다.
 배치 분산 처리가 용이하다
 사전에 데이터를 넣을 수 있다
 응답 속도보다는 전체의 처리를 우선한다
 
- NoSQL 분류
 KVS
 키 밸류 형 ... redis, memcached, Oracle Coherence,
 열 지향 와이드 컬럼 스토어 ... Cassandra, HBASE, Cloud Datastore
 
 문서 형
 MongoDB, Couchbase, MarkLogic, PostgreSQL, MySQL, DynamoDB MS-DocumentDB
 
 그래프 형
 Neo4j
 
 유행했던 Hadoop은 이미 철이 지났다. 문서 형, 키 밸류 형이 현재이고, 그래프 형은 앞으로.
 
- KVS
 redis
 장점
 redis3에서 샤딩이 가능
 배열에 PUSH, POP 가능
 PUB, SUB 가능
 트랜잭션 가능
 
 단점
 메모리 크기 이상 사용할 수 없다
 디스크에 미리 쓰기는 할 수 없다
 자동로드 밸런싱을 할수 없다
 
 Cloud Datastore
 장점
 데이터 구조에서 엔티티의 부모-자식 관계를 가질 수 있다
 부분적인 트랜잭션을 할 수 있다 → 같은 엔티티 그룹 내에서 가능 (강한 무결성) → 다른 엔티티 그룹과도 결과 무결성
 
 단점
 Not 하나 밖에 할 수 없다
 정렬을 먼저 해야 한다
 
 HBASE
 장점
 HDFS가 필수 (등장 인물이 많다)
 Hadoop과 궁합이 좋다
 강한 일관성을 가진다
 데이터 압축이 가능하다
 
 단점
 5 대 미만에서는 사용할 수 없다 (대규모 전용​​)
 단독으로 전개 할 수 없다(Hadoop과 Zookeeper 등)
 
 cassandra
 특징
 DynamoDB와 같은 수평 분산 방식
 CQL을 SQL스럽게 다룰 수 있다
 
 문서 유형
 mongoDB
 ※ 소규모 용
 장점
 SQL 이상의 집계 쿼리(MS-DocumentDB와 더불어 최강)
 역할 기반 액세스 관리
 대량 데이터 처리
 실시간으로 질의 생성

 단점
 트랜잭션을 할 수 없다
 다중 마스터 업데이트

 couchbase
 ※ 대형 전용
 장점
 다중 마스터
 CRUD와 MapReduce(사전에 정한 쿼리)
 GUI 콘솔이 제공된다
 
 단점
 임시 쿼리
 
 DynamoDB
 장점
 최근에는 JSON을 지원(드라이버가 흡수)
 다른 AWS 서비스와의 연계
 
 단점
 집계 정렬, JSON 검색을 할 수 없다
 인덱스가 비동기, 수에 제한
 
 Microsoft DocumentDB
 장점
 SQL과 흡사한 쿼리
 자동 인덱싱
 트랜잭션 지원
 형 검사
 
 단점
 집계, 정렬을 할 수 없다
 복수 인덱스를 할 수 없다
 Azure에서만 사용할 수 있다
 
 MarkLogic
 특징
 원래는 XML, RDF 트리플, 최근 JSON
 XQuery 가능
 분류 표시, 드릴 다운 가능
 트랜잭션 가능
 액세스 관리, 감사, 경고가 가능
 
 그래프 형
 Neo4j
 장점
 노드 → 관련 → 노드 (JSON으로 표현)
 Cypher
 GUI가 멋지다
 
 단점
 수평 분산을 할 수 없다
 커뮤니티 버전은 GPL 라이센스
 
 NoSQL 판별 방법
 흔히있는 캐치 프레이즈는 패스해도 OK
 
 차별화되는 요소
- 데이터 구조
- 응용 프로그램의 인터페이스
- 트랜잭션
- 보조 인덱스 복합 인덱스
-쿼리 (정렬, 한계, 데이터의 부분 업데이트 집계)
- 다중 마스터 복제
- 액세스 권한 관리
- 학습 비용 (문서의 양 노하우가 많음)
 
성능의 단순 비교는 할 수 없다
→ 원래 일관성과 인터페이스가 통일되어 있지 않다
→ NoSQL 성능 비교 보고서는 그다지 맞지 않다(쿼리에서는 MySQL 쪽이 빠를 수 있다)

![](http://revieweasyhomemadecookies.com/wp-content/uploads/2017/01/What-is-Hadoop-Ecosystem-768x529.png)

_ _ _

1. [REST API](http://hyunalee.tistory.com/1)
 API란
 REST API에 대해 설명하기 전, 기본적으로 API는 무엇인지에 대해 알아야 한다.

   API
 API(Application Programming Interface, 응용 프로그램 프로그래밍 인터페이스)는 응용 프로그램에서 사용할 수 있도록, 운영 체제나 프로그래밍 언어가 제공하는 기능을 제어할 수 있게 만든 인터페이스를 뜻한다. 주로 파일 제어, 창 제어, 화상 처리, 문자 제어 등을 위한 인터페이스를 제공한다.

 예를 들어 회사에서 사용하려고 파일 제어(관리)하는 응용프로그램을 만들었다고 생각해보자.
회사 내부에서는 문제가 되지 않지만 외부 사용자가 해당 프로그램을 사용하려고 할 때, 외부 사용자가 해당 소스 및 데이터베이스에 접근하면 안된다.
이런 문제를 해결하기 위해서 API가 사용하는 것이다.

 API를 통해 소스 및 데이터베이스는 접근하지 못하게하고 해당 프로그램을 사용할 수 있도록 기능을 제공하는 것이다.
보통 웹에서의 API는 데이터를 요청하고 응답하는게 전부이며, Wiki에서는 웹API를 아래와 같이 정의해 놓았다.
"웹 API는 웹 애플리케이션 개발에서 다른 서비스에 요청을 보내고 응답을 받기 위해 정의된 명세를 일컫는다."

![](http://cfile22.uf.tistory.com/image/2431164557C6C7BA33E202)
https://blogs.msdn.microsoft.com/martinkearn/2015/01/05/introduction-to-rest-and-net-web-api/

SOAP
http://www.slideshare.net/seunghochoi4/soap-restful
REST API란
REST API
Representational State Transfer (표현 상태 전이, REST)
REST API는 크게 리소스, 메서드, 메세지로 이루어져 있다. 
예, "이름이 Terry인 사용자를 생성한다."라는 호출이 있을 때, 
- 리소스: "사용자" -> http://myweb/users라는 형태의 URI
- 메서드: "생성한다"라는 행위 -> HTTP POST 메서드
- 메세지: "이름이 Terry인 사용자" -> 생성하고자 하는 사용자의 디테일한 내용은 JSON 문서를 이용해서 표현

HTTP POST, http://myweb/users/
{
    "users": {
        "name": "terry"
    }
}

REST Architecture 6가지 원칙 (RESTful)
- 균일한 인터페이스 Uniform interface
- 상태없음 Stateless
- 캐시 Cache
- 클라이언트/서버 Client/Server
- 계층 시스템 Layered system
- (조건부) Code on demand
URI
REST API는 리소스를 나타낼 때 URI(Uniform Resource Identifier)를 사용한다. 
URI는 "http://api.example.restapi.com/kfootball/news"처럼 분명하게 표현된 것들부터 사람이 이해하기 힘든 "http://api.example.restapi.com/23444-122dds-dfsee"와 같은 것까지 다양하게 사용된다.
URI 형태
1. 슬래시(/)는 계층 관계를 나타내는 데 사용된다.

포워드 슬래시(/)는 경로내에서 리소스 간의 계층 관계를 나타내는 데 사용된다.

http://api.example.restapi.com/kfootball/news
2. URI 마지막 문자로 슬래시(/)를 포함하지 않는다.

URI 경로 마지막에 있는 슬래시는 아무런 의미가 없지만, 혼란을 방지하기 위해 REST API의 마지막 글자에 슬래시를 포함해서는 안된다.
많은 웹 컴포넌트와 프레임워크에서는 다음 두 URI를 같은 것으로 취급한다.

http://api.example.restapi.com/kfootball/
http://api.example.restapi.com/kfootball

하지만 URI는 유일한 리소스 식별자로 사용되기 때문에 REST API는 분명한 URI를 만들어 통신해야 한다. 규칙을 엄격하게 적용하지 않은 API들은 클라이언트가 보낸 URI를 마지막에 슬래시(/)가 없는 URI로 리다이렉트하기도 한다.
3. 하이픈(-)은 URI 가독성을 높이는데 사용된다.

URI를 쉽게 이해하기 위해, 긴 URI에 하이픈(-)을 사용해서 가독성을 높일 수 있다.

http://api.example.restapi.com/blogs/my/first-post
4. 밑줄(_)은 사용하지 않는다.

텍스트 뷰어 애플리케이션은 클릭할 수 있다는 표시로 URI에 밑줄을 긋는다. 또한, 글꼴에 따라 밑줄은 보기 어렵거나 문자가 완전히 가져지기도 한다.
밑줄로 URI를 표현하는 대신 하이픈(-)을 사용해야 한다.
5. URI는 소문자로 구성하는게 적합하다.

RFC 3986은 URI 스키마와 호스트를 제외하고는 대소문자를 구별하도록 규정한다.

http://api.example.restapi.com/blogs/my/first-post
HTTP://API.EXAMPLE.RESTAPI.COM/blogb/my/first-post
http://api.example.restapi.com/blogs/My/First-Post
URI 스펙(RFC 3986)에 따라 1번과 2번은 같은 것으로 간주되며 3번은 1,2와 다른 URI를 나타낸다. 이러한 혼란을 피하기 위해 기본적으로 URI는 소문자로 구성한다.
6. 파일확장자는 URI에 포함하지 않는다.

웹에서 점(.)은 URI에서 파일 이름과 확장자를 구분하는데 사용된다.하지만 REST API는 미디어 타입에 의존하여 Content-Type 헤더를 통해 전달되고 메세지 바디의 내용을 어떻게 처리할지 결정한다.
URI 권한 설계
1. API에 있어서 서브 도메인은 일관성있게 사용돼야 한다.

API 최상위 도메인과 1차 서브 도메인 이름(예, map.daum.net)으로 서비스를 제공해야 한다. API의 전체 도메인 이름에 api라는 서브 도메인을 붙여야 한다.
http://apis.map.daum.net/
2. 클라이언트 개발자 포탈 서브 도메인은 일관성있게 사용돼야 한다.

보통 REST API는 개발자 포탈이라는 개발자들을 위한 웹 사이트를 제공한다. 이 포탈은 웹 사이트의 문서 등을 포탈에서 직접 제공하는 API를 이용하여 새로운 클라이언트를 개발할 수 있도록 도와준다. 관습적으로 이러한 웹 사이트의 URI는 developers라는 서브 도메인을 사용한다.
https://developers.daum.net/services

메서드
REST에서는 HTTP 메서드를 그대로 사용한다. HTTP에서 여러 메서드가 있지만 REST API에서는  CRUD(Create Read Update Delete)에 해당하는 네가지 메서드를 사용한다.
POST: 등록 (Create)
GET: 조회 (Select/Read)
PUT: 수정 (Update)
DELETE: 삭제 (DELETE)
CRUD 네가지 상황 외에 컨트롤러를 실행하는데 POST 메서드가 사용된다. POST 요청 메시지는 파라미터 정보를 헤더나 바디에 포함시킬 수 있다.
또한, 최근에는 PATCH라는 메서드를 많이 사용하고 있다. PUT이 해당 자원의 전체를 수정하는 의미하며, PATCH는 해당 자원의 일부를 수정한다는 의미로 인해 update 이벤트에서 PUT보다는 PATCH가 더 적합하다고 평가받고 있다.


_ _ _

[IPTV의 전체적인 시스템](https://www.slideshare.net/yhjrookie/ss-29149718)
콘텐츠
헤드앤드(플랫폼)
네트워크
셋톱박스(STB)

[3. SAN, NAS, DAS의 비교](http://www.pims.kr/wp/?p=2398)

파일관리
수급(영상/이미지)->인제스트->인코딩->DRM(영상)->동기화(CDN)
콘텐츠



@cv00400004945-1:~$ ps -ef | grep tomcat
   9281  9005  0 16:47 pts/4    00:00:00 grep tomcat
@cv00400004945-1:~$ ps -ef | grep java
   9900  9005  0 16:47 pts/4    00:00:00 grep java
  15501     1  2  2016 ?        2-01:44:49 java -Dserver.port=9991 -jar /home/hellotv/app/api_tcp-1.0.jar
  15533     1  2  2016 ?        2-01:34:16 java -Dserver.port=9992 -jar /home/hellotv/app/api_tcp-1.0.jar
  15568     1  2  2016 ?        2-01:24:05 java -Dserver.port=9993 -jar /home/hellotv/app/api_tcp-1.0.jar
  16246     1  5  2016 ?        4-07:24:00 /home/hellotv/src/jdk1.8.0_45/bin/java -Djava.util.logging.config.file=/home/hellotv/src/vert.x-2.1.6/conf/logging.properties -Dvertx.home=/home/hellotv/src/vert.x-2.1.6 -Dvertx.clusterManagerFactory=org.vertx.java.spi.cluster.impl.hazelcast.HazelcastClusterManagerFactory -classpath :/home/hellotv/src/vert.x-2.1.6/conf:/home/hellotv/src/vert.x-2.1.6/lib/* org.vertx.java.platform.impl.cli.Starter run com.hellotv.udp.app.ServerStart -cp /home/hellotv/app/api_udp-1.0.jar -instances 1
push     28506     1  0 02:00 ?        00:02:01 java -jar -Xms5120m -Xmx5120m /home/push/pushsvr/pushSvr.jar

빌더패턴
스트레티지패턴
템플릿메서드패턴
테커레이터패턴
플라이웨이트패턴
싱글턴패턴
