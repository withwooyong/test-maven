<?php
        // *** Include the class
        include("resize-class.php");

        // http://1.255.144.55/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_300x429.jpg
        // http://1.255.144.55/resize.php?u=http://1.255.144.56/movie/33600/M335638_src.jpg
        // http://1.255.144.55/resize.php?u=http://1.255.144.56/movie/33600/M335638_src_300x429.jpg
        $image_url = $_REQUEST["u"];
        // http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_300x429.jpg
        //if (strpos($image_url, 'stimage.hanafostv.com:8080') > 0) {
        if (strpos($image_url, '1.255.144.') > 0) {
        	
        	// http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_300x429.jpg
        	$stimage_full_path = str_replace("http://", '/home/manager/server/images/', $image_url);
        	
        	// /home/manager/server/images/ontv/10250/T30222_src_300x429.jpg
        	$stimage_file_path = substr($stimage_full_path, 0, strrpos($stimage_full_path, '/')).'/'; 
        	
        	// T30222_src_300x429.jpg
        	$stimage_file_name = str_replace($stimage_file_path, "", $stimage_full_path); 
        	
        	// T30222_src_300x429
        	$stimage_file_name = substr($stimage_file_name, 0, strrpos($stimage_file_name, '.')); 
        	
        	$is_width_height = true;
        	if ($width_height == NULL) {
        		$http_image = file_get_contents($image_url); // http://1.255.144.56/movie/33600/M335635_src.jpg
        		$is_width_height = false;
        		$resize_image = $stimage_full_path;
        	} else {
        		$width = substr($width_height, 1, strrpos($width_height, 'x')-1); // 300
        		$height = substr($width_height, strrpos($width_height, 'x')+1, strlen($width_height)); // 429
        		if (is_numeric($width) && is_numeric($height)) {
        			$origin_image = str_replace($width_height, "", $image_url);
        			$http_image = file_get_contents($origin_image); // http://1.255.144.56/movie/33600/M335635_src.jpg
        			$resize_image = str_replace("http://", '/home/manager/server/images/', $origin_image);
        		} else {
        			$http_image = file_get_contents($image_url); // http://1.255.144.56/movie/33600/M335635_src.jpg
        			$is_width_height = false;
        			$resize_image = $stimage_full_path;
        		}
        	}
        	//
        	if ($width_height != NULL) {
        		$width = substr($width_height, 1, strrpos($width_height, 'x')-1); // 300
        		$height = substr($width_height, strrpos($width_height, 'x')+1, strlen($width_height)); // 429
        	}
        	
        	// .jpg
        	$stimage_extension = strtolower(strrchr($image_url, "."));
        	
        	// /home/manager/server/images/stimage.hanafostv.com:8080/ontv/10250/T30222_src_300x429.jpg
			$resize_image = str_replace("http://", '/home/manager/server/images/', $image_url);
	
			if (file_exists($resize_image)) { // 파일이 존재하면
				$resizeObj = new resize($resize_image);
				header('Content-type: image/jpeg');
				header("Cache-Control: private, max-age=300, pre-check=300");
				header("Pragma: private");
				header("Expires: " . date(DATE_RFC822, strtotime(" 2 day")));
				imagejpeg($resizeObj->getImage());
			} else { // 파일이 없으면
				umask(0002);
// 				if (!file_exists(dirname($file_path))) {
// 					mkdir(dirname($file_path), 0777, true);
// 				}

				// http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_300x429.jpg
				// http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg
				$image = file_get_contents(str_replace($width_height, "", $image_url)); // 원본파일 가져오기
				
				echo "<br>1. image : ". $image. "<br>";
				echo "<br>1. stimage_file_path : ". $stimage_file_path. "<br>";
				
				if ($image !== false) {
					file_put_contents($stimage_file_path, $image);
					$resizeObj = new resize($stimage_file_path);					
					$resizeObj->resizeImage($width, $height, 'auto');
					$resizeObj->viewImage();
					$resizeObj->saveImage($resize_image, 100);
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