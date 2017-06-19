<?php
        // *** Include the class
        include("resize-class.php");

        $image_url = $_REQUEST["u"];
        
        $is_width_height = true;

        if (strpos($image_url, '1.255.144.56') > 0) {        
        	$resize_image = str_replace("http://", '/home/manager/server/images/', $image_url);
        	
        	if (file_exists($resize_image)) { // 파일이 존재하면
               	$resizeObj = new resize($resize_image);
               	header('Content-type: image/jpeg');
               	header("Cache-Control: private, max-age=300, pre-check=300");
               	header("Pragma: private");
               	header("Expires: " . date(DATE_RFC822, strtotime(" 2 day")));
               	imagejpeg($resizeObj->getImage());
            } else {
            	$stimage_full_path = $resize_image;
            	$stimage_file_path = substr($resize_image, 0, strrpos($resize_image, '/')).'/';
              	
            	echo "<br>1. stimage_full_path : ". $stimage_full_path. "<br>";
            	echo "<br>1. stimage_file_path : ". $stimage_file_path. "<br>";
            	echo "<br>1. resize_image : ". $resize_image. "<br>";
            	
               	// T30222_src_300x429.jpg
            	$stimage_file_name = str_replace($stimage_file_path, "", $resize_image);
               	$stimage_file_name = substr($stimage_file_name, 0, strrpos($stimage_file_name, '.'));
               	
               	// T30222_src_300x429
               	// width_height _300x429
               	$width_height = strrchr($stimage_file_name, "_");
               	$width = "";
               	$height = "";
               	$http_image = "";
               	$save_image = "";
               	// $resize_image
               	
               	umask(0002);
               	
               	$path = pathinfo($stimage_full_path);
               	if (!file_exists($path['dirname'])) {
               		mkdir($path['dirname'], 0777, true);
               	}
               	
               	if ($width_height == NULL) { // src 원본 이미지
               		$save_image = $stimage_full_path;
               		copy($image_url, $save_image);
//                		$http_image = file_get_contents($image_url); // http://1.255.144.56/movie/33600/M335635_src.jpg
//                		file_put_contents($stimage_full_path, $http_image);
               		$is_width_height = false;
               		
               	} else { // resize 이미지
               		$width = substr($width_height, 1, strrpos($width_height, 'x')-1); // 300
               		$height = substr($width_height, strrpos($width_height, 'x')+1, strlen($width_height)); // 429
               		if (is_numeric($width) && is_numeric($height)) { // resize 정보가 있으면
               			$save_image = str_replace($width_height, "", $stimage_full_path);
               			copy(str_replace($width_height, "", $image_url), $save_image);
//                			$http_image = file_get_contents($src_image); // http://1.255.144.56/movie/33600/M335635_src.jpg
//                			file_put_contents($stimage_full_path, $http_image);
               			$is_width_height = true;
               			//$resize_image = str_replace("http://", '/home/manager/server/images/', $save_image);
               			
               		} else { // resize 정보가 없으면
               			$save_image = $stimage_full_path;
               			copy($image_url, $save_image);               			
//                			$http_image = file_get_contents($image_url); // http://1.255.144.56/movie/33600/M335635_src.jpg
//                			file_put_contents($stimage_full_path, $http_image);
               			$is_width_height = false;
               			//$resize_image = $stimage_full_path;
               		}
               	}
               	// .jpg
               	//$stimage_extension = strtolower(strrchr($image_url, "."));
               	// /home/manager/server/images/stimage.hanafostv.com:8080/ontv/10250/T30222_src.165.jpg
               	
               	if ($http_image !== false) {
               		if ($is_width_height == true) {
               			umask(0002);
               			if (!file_exists(dirname($save_image))) {
               				mkdir(dirname($save_image), 0777, true);
               			}
               			$resizeObj = new resize($save_image);
               			$resizeObj->resizeImage($width, $height, 'auto');
               			$resizeObj->viewImage();
               			$resizeObj->saveImage($resize_image, 100);
               		} else {
               			
               		}              		
               	} else {
               		// error 발생
               		header("Location: $image_url");
               		exit();
               	}
            }               
        } else {
        	header("Location: $image_url");
            exit();
        }
?>