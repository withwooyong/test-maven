<?php
	// *** Include the class
	include("resize-class.php");
	
	$image_url = $_REQUEST["u"];
	$resize_w = $_REQUEST["w"];
	//$resize_w = $resize_w + 50;
	
	// http://1.255.144.53/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg&w=228
	
	$http = "http://";
	$stimage_domain = 'stimage.hanafostv.com:8080/';
	$file_path = '/home/manager/server/images/';
	
	if (strpos($image_url, $stimage_domain) > 0) {
		// /home/manager/server/images/stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg
		$stimage_full_path = str_replace($http, $file_path, $image_url);
		
		// /home/manager/server/images/stimage.hanafostv.com:8080/ontv/10250/		
		$stimage_file_path = substr($stimage_full_path, 0, strrpos($stimage_full_path, '/')).'/';
		
		// T30222_src.jpg
		$stimage_file_name = str_replace($stimage_file_path, "", $stimage_full_path);		
		$stimage_file_name= substr($stimage_file_name, 0, strrpos($stimage_file_name, '.'));
		
		// .jpg
		$stimage_extension = strtolower(strrchr($image_url, "."));
		// /home/manager/server/images/stimage.hanafostv.com:8080/ontv/10250/T30222_src.165.jpg
		$resize_image = $stimage_file_path.$stimage_file_name.'.'.$resize_w.$stimage_extension;
// 		echo "<br>1. image_url : ". $image_url. "<br>";
// 		echo "<br>1. stimage_full_path : ". $stimage_full_path. "<br>";
// 		echo "<br>1. stimage_file_path : ". $stimage_file_path. "<br>";
// 		echo "<br>1. stimage_file_name : ". $stimage_file_name. "<br>";
// 		echo "<br>1. stimage_extension : ". $stimage_extension. "<br>";
// 		echo "<br>1. resize_image : ". $resize_image. "<br>";
		
		if (file_exists($resize_image)) {
			$resizeObj = new resize($resize_image);			
			
			header('Content-type: image/jpeg');
			header("Cache-Control: private, max-age=300, pre-check=300");
			header("Pragma: private");
			header("Expires: " . date(DATE_RFC822, strtotime(" 2 day")));
			
			imagejpeg($resizeObj->getImage());
		} else {
			umask(0002);
			
			if (! file_exists ( dirname ( $stimage_full_path ) )) {
				$imageString = file_get_contents($image_url);
				mkdir ( dirname ( $stimage_full_path ), 0777, true );
				file_put_contents($stimage_full_path, $imageString);
			}
			
			$resizeObj = new resize($stimage_full_path);
			$resize_h = round($resizeObj->getHeight() * ($resize_w/$resizeObj->getWidth()));
			
			// *** 2) Resize image (options: exact, portrait, landscape, auto, crop)
			$resizeObj->resizeImage($resize_w, $resize_h, 'auto');
			$resizeObj->viewImage();
			$resizeObj->saveImage($resize_image, 100);
		}
	} else {
		header("Location: $image_url");
		exit();
	}
?>
