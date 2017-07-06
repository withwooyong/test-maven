<?php
	//error_reporting(0);
    // *** Include the class
    include("resize-class.php");
    $image_url = $_REQUEST["u"];
    $is_width_height = true;

    // $image_url = http://stimage.hanafostv.com:8080/smartdelivery/20168261/kobis_still_20168261_1_142x142.jpg
    $resize_image = str_replace("http://", '/home/manager/server/images/', $image_url);
    // $resize_image = /home/manager/server/images/stimage.hanafostv.com:8080/smartdelivery/20168261/kobis_still_20168261_1_142x142.jpg
	$image_file_path = substr($resize_image, 0, strrpos($resize_image, '/')).'/';
    // $image_file_path = /home/manager/server/images/stimage.hanafostv.com:8080/smartdelivery/20168261/kobis_still_20168261_1_142x142/
    $image_file_name = str_replace($image_file_path, "", $resize_image);
    // $image_file_name = kobis_still_20168261_1_142x142.jpg
    $image_file_name = substr($image_file_name, 0, strrpos($image_file_name, '.'));
    // $image_file_name = kobis_still_20168261_1_142x142
    // 141394_car3new2_menu_pro_v1_0630_374x258.png
    $width_height = strrchr($image_file_name, "_");
    // $width_height = _300x429
    $width = "";
    $height = "";
    $remote_image = "";       
    $extension = strtolower(strrchr($image_url, "."));
    // $extension = .jpg
    $save_image = $resize_image;
    $option = "auto"; // crop
    
    if (file_exists($resize_image)) { // 파일이 존재하면
    	$resizeObj = new resize($resize_image);
    	header('Content-type: image/jpeg');
    	header("Cache-Control: private, max-age=300, pre-check=300");
    	header("Pragma: private");
    	header("Expires: " . date(DATE_RFC822, strtotime(" 2 day")));
    	imagejpeg($resizeObj->getImage());
    } else { // 파일이 없으면
    	umask(0002);
    	
    	// mount 되면 삭제
    	$path = pathinfo($resize_image);
    	if (!file_exists($path['dirname'])) {
    		mkdir($path['dirname'], 0777, true);
    	}
    	// $image = file_get_contents(str_replace($width_height, "", $image_url));
    	// $size = getimagesize("http://www.example.com/gifs/logo.gif");
    	// $resize_image
    	if ($width_height == NULL) { // src 원본 이미지
    		//copy($image_url, $resize_image);
    		$remote_image = file_get_contents($image_url);
    		file_put_contents($resize_image, $remote_image);
    		$is_width_height = false;
    	} else { // resize 이미지
    		
    		$width = substr($width_height, 1, strrpos($width_height, 'x')-1); // 300
    		$height = substr($width_height, strrpos($width_height, 'x')+1, strlen($width_height)); // 429
    		if (is_numeric($width) && is_numeric($height)) { // resize 정보가 있으면
    			// kobis_still
    			if (strpos($image_url, 'kobis_still') !== false) { // still 이미지는 원본이미지에 _src 와 확장자 .jpg 없음
    				$remote_image = @file_get_contents(str_replace($width_height.".jpg", "", $image_url));
    				if ($remote_image === false) {
    					$remote_image = file_get_contents($image_url);
    					$is_width_height = false;
    				} else {
    					$resize_image = str_replace($width_height.".jpg", "", $resize_image);
    					$option = "crop";
    				}
    			} else {
    				$remote_image = @file_get_contents(str_replace($width_height, "_src", $image_url));
    				if ($remote_image === false) {
    					$remote_image = file_get_contents($image_url);
    					$is_width_height = false;
    				} else {
    					$resize_image = str_replace($width_height, "_src", $resize_image);
    					$option = "auto";
    				}
    			}
    			// $resize_image
    			file_put_contents($resize_image, $remote_image);
    		} else { // resize 정보가 없으면
    			//copy($image_url, $resize_image);
    			$remote_image = file_get_contents($image_url);
    			file_put_contents($resize_image, $remote_image);
    			$is_width_height = false;
    		}    		
    	}
    	
    	if ($is_width_height == true) {
    		$resizeObj = new resize($resize_image);
    		$resizeObj->resizeImage($width, $height, $option);
    		$resizeObj->viewImage();
    		$resizeObj->saveImage($save_image); // resize image save
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