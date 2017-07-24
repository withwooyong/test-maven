<?php
// *** Include the class
include ("resize-class.php");
$image_url = $_REQUEST ["u"];

$resize_image = str_replace ( "http://", '/home/manager/server/images/', $image_url );
$image_file_path = substr ( $resize_image, 0, strrpos ( $resize_image, '/' ) ) . '/';
$image_file_name = str_replace ( $image_file_path, "", $resize_image );
$image_file_name = substr ( $image_file_name, 0, strrpos ( $image_file_name, '.' ) );
$width_height = strrchr ( $image_file_name, "_" );
$width = "";
$height = "";
$is_width_height = true;
$remote_image = "";
$extension = strtolower ( substr ( strrchr ( $image_url, "." ), 1 ) );

if ($extension == 'jpg') {
	$extension = 'jpeg';
}
// $extension = .jpg
$save_image = $resize_image;
$option = "auto"; // crop

if (file_exists ( $resize_image )) { // 파일이 존재하면
	$resizeObj = new resize ( $resize_image );
	header ( 'Content-type: image/' . $extension );
	if ($extension == 'png') {		
		imagepng ( $resizeObj->getImage () );
	} else {
		imagejpeg ( $resizeObj->getImage (), NULL, 100 );
	}
} else { // 파일이 없으면
	umask ( 0002 );
	// mount 되면 삭제
	$path = pathinfo ( $resize_image );
	if (! file_exists ( $path ['dirname'] )) {
		mkdir ( $path ['dirname'], 0777, true );
	}
	
	// $resize_image
	if ($width_height == NULL) { // src 원본 이미지
	    $remote_image = file_get_contents ( $image_url );
		file_put_contents ( $resize_image, $remote_image );
		$is_width_height = false;
	} else { // resize 이미지
		$width = substr ( $width_height, 1, strrpos ( $width_height, 'x' ) - 1 ); // 300
		$height = substr ( $width_height, strrpos ( $width_height, 'x' ) + 1, strlen ( $width_height ) ); // 429
		if (is_numeric ( $width ) && is_numeric ( $height )) { // resize 정보가 있으면
			$remote_image = @file_get_contents ( str_replace ( $width_height, "_src", $image_url ) );
			if ($remote_image === false) {
				$remote_image = file_get_contents ( $image_url );
				$is_width_height = false;
			} else {
				$resize_image = str_replace ( $width_height, "_src", $resize_image );
				$option = "auto";
			}
			file_put_contents ( $resize_image, $remote_image );
		} else { // resize 정보가 없으면
		    $remote_image = file_get_contents ( $image_url );
			file_put_contents ( $resize_image, $remote_image );
			$is_width_height = false;
		}
	}
	
	if ($is_width_height == true) {
		$resizeObj = new resize ( $resize_image );
		$resizeObj->resizeImage ( $width, $height, $option );
		$resizeObj->viewImage ( $extension );
		$resizeObj->saveImage ( $save_image ); // resize image save
	} else {
		$resizeObj = new resize ( $resize_image );
		header ( 'Content-type: image/' . $extension );		
		if ($extension == 'png') {
			imagepng ( $resizeObj->getImage () );
		} else {
			imagejpeg ( $resizeObj->getImage (), NULL, 100 );
		}
	}
}
?>