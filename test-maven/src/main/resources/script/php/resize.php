<?php
	// *** Include the class
	include("resize-class.php");
	
	$image_url = $_REQUEST["u"];
	$resize_w = $_REQUEST["w"];
	$resize_h = $_REQUEST["h"];
	
	// http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg
	// http://1.255.144.53/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg&w=228
	// http://image.tving.com/upload/cms/caip/CAIP0400/P000372113.jpg
	$http = "http://";
	$stimage_domain = 'stimage.hanafostv.com:8080/';
	$resize_file_path = '/home/manager/server/images/resize/';
	// http://image.btv.com/resize.php?u=http://image.btv.com/upload/cms/caip/CAIP0400/P000372113.jpg&w=228
	// http://image.btv.com/crop.php?u=http://image.btv.com/upload/cms/caip/CAIP0500/P000311900.jpg&w=320
	
	var_dump($_REQUEST);
	
	echo "<br>1. image_url : ". $image_url . "<br>";
	
	if (strpos($image_url, $stimage_domain) > 0) {
		// http://1.255.144.53/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg&w=228
		$saveFilePath = str_replace($http.$stimage_domain, $resize_file_path, $image_url);
		$saveFileName = $resize_file_path.$image_url;
		$extension = strtolower(strrchr($image_url, ".")); // .jpg
		
		echo "2. saveFilePath : " . saveFilePath. "<br>saveFileName : " . saveFileName. "<br>extension : " . $extension . "<br>";
		
		$saveFileName = str_replace("http://", "", $saveFileName);
		//$saveFileName = str_replace("https://", "", $saveFileName);
		
		//3. saveFileName : /home/manager/server/images/resize/stimage.hanafostv.com:8080/ontv/10250/T30222_src.200.jpg
		//44. saveFilePath : /ontv/10250/T30222_src.jpg
		
		if ($extension == '.jpg' || $extension == '.jpeg' || $extension == '.gif') {
			//$saveFileName = substr($saveFileName, 0, strrpos($saveFileName, '.')).'.'.$resize_w.strrchr($saveFileName, '.');
			$saveFileName = substr($saveFileName, 0, strrpos($saveFileName, '.')).'.'.$resize_w.strrchr($saveFileName, '.');
		} else {
			$saveFileName = substr($saveFileName, 0, strrpos($saveFileName, '/')).strrchr($saveFileName, '/').'_'.$resize_w.'.jpg';
		}

		echo "3. saveFileName : " . $saveFileName . "<br>";
		
		if (file_exists($saveFileName)) {
			$resizeObj = new resize($saveFileName);			
			
			header('Content-type: image/jpeg');
			header("Cache-Control: private, max-age=300, pre-check=300");
			header("Pragma: private");
			header("Expires: " . date(DATE_RFC822, strtotime(" 2 day")));
			
			imagejpeg($resizeObj->getImage());
		} else {
			umask(0002);
			echo "44. saveFilePath : " . $saveFilePath. "<br>";
			$resizeObj = new resize($saveFilePath);
			//$resize_h = round($resizeObj->getHeight() * ($resize_w/$resizeObj->getWidth()));
			//echo "4. resizeObj->getWidth : " . $resizeObj->getWidth() . ", resizeObj->getHeight : ".$resizeObj->getHeight() . "<br>";
			echo "4. resize_w : " . $resize_w. ", resize_h : ".$resize_h. "<br>";
			// *** 2) Resize image (options: exact, portrait, landscape, auto, crop)
			$resizeObj->resizeImage($resize_w, $resize_h, 'auto');
			//$resizeObj->viewImage();
			echo "5. saveFileName : " . $saveFileName. "<br>";
			$resizeObj->saveImage($saveFileName, 100);
		}
	} else {
		header("Location: $image_url");
		exit();
	}
?>
