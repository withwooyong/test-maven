<?php
	// *** Include the class
	include("resize-class.php");
	
	$image_url = $_REQUEST["u"];
	$resize_w = $_REQUEST["w"];
	$resize_w = $resize_w + 50;
	
	// http://image.btv.com/resize.php?u=http://image.btv.com/upload/cms/caip/CAIP0400/P000372113.jpg&w=228
	// http://image.btv.com/crop.php?u=http://image.btv.com/upload/cms/caip/CAIP0500/P000311900.jpg&w=320
	
	var_dump($_REQUEST);	
	
	$image_url = str_replace("image-origin.btv.com", "image.btv.com", $image_url);
	
	echo "<br>1. image_url : ". $image_url . "<br>";
	
	if (strpos($image_url, "image.btv.com") > 0) {
		$saveFileName = "/images/upload/btv/crop/".$image_url;
		$saveFilePath = str_replace("http://image.btv.com/", "/images", $image_url);
		$extension = strtolower(strrchr($image_url, "."));
		
		echo "2. saveFileName : " . $saveFileName . "<br>saveFilePath : " . $saveFilePath . "<br>extension : " . $extension . "<br>";
		
		$saveFileName = str_replace("http://", "", $saveFileName);
		$saveFileName = str_replace("https://", "", $saveFileName);
		
		if ($extension == '.jpg' || $extension == '.jpeg' || $extension == '.gif') {
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
			$resizeObj = new resize($saveFilePath);
			
			echo "4. resizeObj->getHeight : " . $resizeObj->getHeight() . ", resizeObj->getWidth : ".$resizeObj->getWidth() . "<br>";
			
			$resizeObj->cropImageBy16x9($resize_w, 'top');
			$resizeObj->viewImage();
			$resizeObj->saveImage($saveFileName, 100);
		}
	} else {
		header("Location: $image_url");
		exit();
	}
?>
