<?php
	error_reporting(0);
    // *** Include the class
    include("resize-class.php");
    $image_url = $_REQUEST["u"];
    $is_width_height = true;

	$resize_image = str_replace("http://", '/home/manager/server/images/', $image_url);
	$stimage_file_path = substr($resize_image, 0, strrpos($resize_image, '/')).'/';
      	
    // T30222_src_300x429.jpg
    $stimage_file_name = str_replace($stimage_file_path, "", $resize_image);
    $stimage_file_name = substr($stimage_file_name, 0, strrpos($stimage_file_name, '.'));

    // kids_new/10750/K307160_33_162x91.jpg
    // T30222_src_300x429
    // width_height _300x429
    $width_height = strrchr($stimage_file_name, "_");
    $width = "";
    $height = "";
    $http_image = "";
    $extension = strtolower(strrchr($image_url, ".")); // .jpg
    
    umask(0002);
    
    if (file_exists($saveFileName)) { // 파일이 존재하면
    	$resizeObj = new resize($saveFileName);    	
    	header('Content-type: image/jpeg');
    	header("Cache-Control: private, max-age=300, pre-check=300");
    	header("Pragma: private");
    	header("Expires: " . date(DATE_RFC822, strtotime(" 2 day")));    	
    	imagejpeg($resizeObj->getImage());
    } else { // 파일이 없으면
    	// $image = file_get_contents(str_replace($width_height, "", $image_url));
    	// $size = getimagesize("http://www.example.com/gifs/logo.gif");
    	// $resize_image
    	if ($width_height == NULL || $extension != '.jpg') { // src 원본 이미지
    		copy($image_url, $resize_image);
    		
    		// 원격 파일을 사용하기 전에 성공적으로 open 되었는지 확인
    		if ($fp = fopen($image_url, 'r')) {
    			$content = '';
    			// 전부 읽을때까지 계속 읽음
    			while ($line = fread($fp, 1024)) {
    				$content .= $line;
    			}
    			
    			// content 사용
    			$resizeObj->saveImage($resize_image, 100);
    			// ...
    		} else {
    			// 파일 open시 에러 발생
    		}
    		
    		
    		$is_width_height = false;
    	} else { // resize 이미지
    		$width = substr($width_height, 1, strrpos($width_height, 'x')-1); // 300
    		$height = substr($width_height, strrpos($width_height, 'x')+1, strlen($width_height)); // 429
    		if (is_numeric($width) && is_numeric($height)) { // resize 정보가 있으면
    			//$resize_image = str_replace($width_height, "", $resize_image);
    			//copy(str_replace($width_height, "_src", $image_url), $resize_image);
    			//     		if (copy(str_replace($width_height, "_src", $image_url), str_replace($width_height, "", $resize_image))) {
    			//     			$is_width_height = true;
    			//     			echo "Copy success!";
    			//     		} else {
    			//     			copy($image_url, $resize_image);
    			//     			$is_width_height = false;
    			//     			echo "Copy fail!";
    			//     		}
    			
    		} else { // resize 정보가 없으면
    			copy($image_url, $resize_image);
    			$is_width_height = false;
    		}
    	}
    	// .jpg
    	//$stimage_extension = strtolower(strrchr($image_url, "."));
    	// /home/manager/server/images/stimage.hanafostv.com:8080/ontv/10250/T30222_src.165.jpg
    	if ($is_width_height == true) {
    		$resizeObj = new resize(str_replace($width_height, "", $resize_image));
    		$resizeObj->resizeImage($width, $height, 'auto');
    		$resizeObj->viewImage();
    		$resizeObj->saveImage($resize_image, 100);
    	} else {
    		$resizeObj = new resize($resize_image);
    		header('Content-type: image/jpeg');
    		header("Cache-Control: private, max-age=300, pre-check=300");
    		header("Pragma: private");
    		header("Expires: " . date(DATE_RFC822, strtotime(" 2 day")));
    		imagejpeg($resizeObj->getImage());
    	}
    }
    
?>