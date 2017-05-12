<?php

define('IMG_PATH','./asset/img/');
ini_set('memory_limit',-1);

/*
define('IMG_PATH', PATH) : 이미지 파일이 있는 디렉터리를 정의한다. 필자의 경우 모든 경로는 루트에 상수로 정의하고 있기에 define() 메쏘드를 사용했지만, 취향과 경우에 따라 일반 variable선언을 하여도 된다.
ini_set('memory_limit',-1) : PHP상의 가용메모리를 무제한으로 설정해주는 함수이다. 이 옵션을 지정해주지 않으면 1M가 넘는 사진들은 이 스크립트에서 사용할 수 없게 된다.
이미지는 위의 경로상에 저장하되, 루트에위치한 핸들러로 이미지를 뿌려주려고 한다. 불러올 내용은 get메쏘드로 입력받는다.
*/

$file = IMG_PATH . $_GET['f'];
if( file_exists($file) ){
    //...
}

/*
$file = IMG_PATH . $_GET['f']; : 파일을 실제 위치하는 경로상으로 표현한다.
file_exists( $file ) : 해당 파일이 존재하는지 확인하여 불린값을 반환하는 함수이다.
위 코드를 통하여 파일이 실제 존재함이 확인되면 이미지 반환 스크립트를 실행한다.
*/

header( 'content-type', image_type_to_mime_type( $file ) );
list($width, $height) = getimagesize($file);

$source = imagecreatefromjpeg($file);
imagejpeg($file, NULL, 90);
imagedestroy($file);

/*
header( 'content-type', image_type_to_mime_type( $file ) ); : 출력할 문서의 헤더를 설정한다. 이 구문을 통하여 클라이언트측 웹브라우저는 확장자가 어떻건 간에 이 구문에서 지정한 포맷으로 문서를 인식한다.
image_type_to_mime_type( $file ); : 이미지의 포맷을 밈타입으로 반환한다.
imagecreatefromjpeg($file); : jpg이미지를 불러온다. png나 다른 확장자의경우 imagecreatefrompng(); 등 으로 불러와야 한다.
위 코드를 통하여 경로내의 jpg파일을 php스크립팅을 거쳐서 클라이언트로 전송하는데에 성공하였다. 이제 여기에 리사이징 기능을 덧붙이면 그럴싸한 반응형 이미지 스크립트가 완성 될 것 이다. 리사이징명령은 파일이름과 똑같이 get으로 받되, 문자열형, 정수형 어느것으로 받든 상관없을 것 이다. 위의 헤더 정의와 가로세로 변수를 지정해준 것 이후 공백부터의 코드이다.
*/

if(isset($_GET['width']))
{
    $newWidth = $_GET['width'];
    $newHeight = $height * ($_GET['width'] / $width);
}
else
{
    $newWidth = $width;
    $newHeight = $height;
}

$result = imagecreateruecolor($newWidth, $newHeight);
$source = imagecreatefromjpeg($file);

imagecopyresized($result, $source, 0, 0, 0, 0, $newwidth, $newheight, $width, $height);

imagejpeg($result, NULL, 90);
imagedestroy($result);

/*
if문 : 가로값을 새로 설정 할 변수가 들어오면 가로값이 줄어든 비율에따라 세로값도 줄어드는 것을 정의한다.
imagecreatetruecolor(); : 새로운 트루컬러 이미지를 생성한다.
imagecopyresized(); : 이미지를 복사한 후 리사이징 한다.
imagejpeg(); : 이미지를 출력한다. png의 경우 imagepng(); 로 출력한다.
위와같이 스크립팅을 하면 반응형 이미지의 골자가 완성된 것 이다. 아래에서 본 필자의 서버의 이미지를 가로 200픽셀로 불러와보는 시연을 해 보겠다.

이 대로 실제 서버에 적용시켜 사용하여도 무방하나, 역시 전 세계 개발자의 변태같은 취미중 하나는 숨김의 미학이라고, 이 것을 디렉터리에서 호출하는 것 처럼 위장(?)하여 볼 것 이다. 필자는 apache2를 사용하였다. 루트상의 .htaccess파일을 열어서 아래의 내용을 추가하도록 한다.

RewriteEngine On
RewriteBase /
RewriteRule image/(.*)/?$ /imageHandler.php?f=$1 [L]
이 명령은 url으로 //example.com/image/... 식으로 리퀘스트가 올 경우 example.com/imageHandler.php?f=... 로 자동으로 rewrite해주는 명령이다. apache 모듈로 nginx를 사용하는경우는 해당 엔진 사양을 참조하여 비슷하게 구성해주면 될 것이다.

이러한 설정을 마치면 이미지가 php스크립트를 거치지않고 실존하는 폴더에서 불러와지는 것 처럼 보이게 할 수 있다. 이것은 기본적인 서버보안의 일종이기도 하며, 사용자친화적 url구성의 한 부분이기도 하다.

이제 웹사이트에서 다량의 이미지를 불러 올 경우 속도 테스트를 해볼 것 이다. 브라우저 캐시를 끄고 내부망으로 테스팅하였다.
*/