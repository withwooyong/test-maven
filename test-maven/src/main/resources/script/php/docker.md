> http://blueyikim.tistory.com/471
> http://brownbears.tistory.com/26
> http://blog.kalkin7.com/2014/02/05/wordpress-markdown-quick-reference-for-koreans/

## nGrinder 설치 - docker

* Controller 설치
아래 명령어로 controller의 이미지를 받습니다.
docker pull ngrinder/controller:3.4
docker pull ngrinder/agent:3.4
다음 아래의 명령어로 컨테이너를 생성합니다.
docker run -d -v ~/.ngrinder:/root/.ngrinder -p 8080:80 -p 16001:16001 -p 12000-12009:12000-12009 ngrinder/controller:3.4

docker run -d -e 'CONTROLLER_ADDR=ip주소:8080' --memory="1g" --memory-swap -1 --cpu-period=50000 --cpu-quota=25000 --name agent_minimum ngrinder/agent:3.4

> docker run -d -e 'CONTROLLER_ADDR=1.255.144.53:8080' --memory="1g" --memory-swap -1 --cpu-period=50000 --cpu-quota=25000 --name agent01 ngrinder/agent:3.4

> docker run -d -e 'CONTROLLER_ADDR=1.255.144.53:8080' --memory="1g" --memory-swap -1 --cpu-period=50000 --cpu-quota=25000 --name agent02 ngrinder/agent:3.4

--memory  : 해당 컨테이너에 물리적으로 메모리를 얼마나 할당할 지 설정하여 줍니다. 할당받은 메모리를 전부 사용했을 시, host의 메모리를 빌려서 사용합니다.
--memory-swap  : 컨테이너에게 얼만큼 메모리를 빌려줄 지 결정합니다. 만약 --memory="300m" --memory-swap="200m"일 경우, 해당 컨테이너에서 사용할 수 있는 메모리의 양은 500m입니다. 여기서는 정확하게 1gb/2gb/4gb만 사용하기 위해 -1로 메모리를 고정합니다.
--cpu-period, --cpu-quota  :  cpu-priod는 cpu-quota와 함께 사용합니다. cpu-priod의 기본 값은 100000(100ms)로, cpu-quota를 이용해서 100ms 동안 어느정도의 cpu 할당할 것 인지를 설정할 수 있습니다. 만약 cpu-quota가 25000이라면, 1/4만큼의 cpu자원을 사용할 수 있습니다. 여기서 agent vm을 2core로 설정하였기 때문에 1/2만큼의 cpu자원을 사용하도록 하기 위해 --cpu-period는 50000, --cpu-quota는 25000을 정했습니다.

이러한 방식으로 agent를 각기 다른 자원을 할당해 생성합니다.

controller_web_port는 사용자가 원하는 포트 번호를 입력하면 됩니다. (ex. 8080)
10.101.30.24:8080/login 
default id와 pw는 admin입니다.

* 포트
Agent : Any ==> Controller : 16001 
Agent : Any ==> Controller : 12000 ~ 1200x(the number of concurrent tests allowed) 
==>은 단방향 통신을 뜻합니다.
16001 포트는 테스트를 하지 않은 에이전트가 컨트롤러에게 "할 일없으니 테스트 가능" 이란 메세지를 알려주는 포트입니다. 
또한 컨트롤러는 "테스트가 실행하는데 해당 테스트는 1200x에서 발생하니, 해당 포트에 접속해서 테스트 실행 준비"라는 메세지를 에이전트에게 지시를 합니다.
12000~1200x 포트는 "테스트 실행, 테스트 종료"와 같은 컨트롤러 명령어와 에이전트별 테스트 실행 통계를 초별로 수집하는 포트입니다.

* Agent 설치
아래 명령어로 agent의 이미지를 받습니다.
$ docker pull ngrinder/agent:3.3
다음 명령어로 agent 컨테이너를 생성합니다.
$ docker run -d -e 'CONTROLLER_ADDR=controller_ip:controller_web_port' ngrinder/agent:3.3
controller_ip: controller의 아이피
controller_web_port: 앞서 controller의 컨테이너르 생성할 때, 정한 포트