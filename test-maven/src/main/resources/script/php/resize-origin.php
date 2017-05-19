<?php
	// *** Include the class
	include("resize-class.php");
	
	$image_url = $_REQUEST["u"];
	$resize_w = $_REQUEST["w"];
	$resize_w = $resize_w + 50;
	
	// http://image.btv.com/resize.php?u=http://image.btv.com/upload/cms/caip/CAIP0400/P000372113.jpg&w=228
	// http://image.btv.com/crop.php?u=http://image.btv.com/upload/cms/caip/CAIP0500/P000311900.jpg&w=320
	
	$image_url = str_replace("image-origin.btv.com", "image.btv.com", $image_url);
	
	if (strpos($image_url, "image.btv.com") > 0) {
		$saveFileName = "/images/upload/btv/resize/".$image_url;
		$saveFilePath = str_replace("http://stimage.hanafostv.com:8080/", "/images", $image_url);
		$extension = strtolower(strrchr($image_url, "."));
		
		$saveFileName = str_replace("http://", "", $saveFileName);
		$saveFileName = str_replace("https://", "", $saveFileName);
		
		if ($extension == '.jpg' || $extension == '.jpeg' || $extension == '.gif') {
			$saveFileName = substr($saveFileName, 0, strrpos($saveFileName, '.')).'.'.$resize_w.strrchr($saveFileName, '.');
		} else {
			$saveFileName = substr($saveFileName, 0, strrpos($saveFileName, '/')).strrchr($saveFileName, '/').'_'.$resize_w.'.jpg';
		}

		if (file_exists($saveFileName)) {
			$resizeObj = new resize($saveFileName);			
			
			header('Content-type: image/jpeg');
			header("Cache-Control: private, max-age=300, pre-check=300");
			header("Pragma: private");
			header("Expires: " . date(DATE_RFC822, strtotime(" 2 day")));
			
			imagejpeg($resizeObj->getImage());
		} else {
			umask(0002);
			$resizeObj = new resize($saveFilePath);
			$resize_h = round($resizeObj->getHeight() * ($resize_w/$resizeObj->getWidth()));
			
			// *** 2) Resize image (options: exact, portrait, landscape, auto, crop)
			$resizeObj->resizeImage($resize_w, $resize_h, 'auto');
			$resizeObj->viewImage();
			$resizeObj->saveImage($saveFileName, 100);
		}
	} else {
		header("Location: $image_url");
		exit();
	}
?>
