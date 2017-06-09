<?php
        // *** Include the class
        include("resize-class.php");

        $image_url = $_REQUEST["u"];
        //$resize_w = $_REQUEST["w"];
        //$resize_w = $resize_w + 50;

        // http://1.255.144.53/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg&w=228

        //http://1.255.144.54/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_100x143.jpg
        //http://1.255.144.54/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_200x286.jpg
        //http://1.255.144.54/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_300x429.jpg
        //http://1.255.144.54/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_400x572.jpg
        //http://1.255.144.54/resize.php?u=http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_500x715.jpg
        //$http = "http://";
        //$stimage_domain = 'stimage.hanafostv.com:8080';
        //stimage_domain = 'image.tving.com';
        //$file_path = '/home/manager/server/images/';

        if (strpos($image_url, 'stimage.hanafostv.com:8080') > 0) {
        	    // http://stimage.hanafostv.com:8080/ontv/10250/T30222_src_300x429.jpg
                // /home/manager/server/images/stimage.hanafostv.com:8080/ontv/10250/T30222_src.jpg
        	    $stimage_full_path = str_replace("http://", '/home/manager/server/images/', $image_url);

                // /home/manager/server/images/stimage.hanafostv.com:8080/ontv/10250/
                $stimage_file_path = substr($stimage_full_path, 0, strrpos($stimage_full_path, '/')).'/';

                // T30222_src_300x429.jpg
                $stimage_file_name = str_replace($stimage_file_path, "", $stimage_full_path);
                $stimage_file_name = substr($stimage_file_name, 0, strrpos($stimage_file_name, '.'));
                
                // T30222_src_300x429
                // width_height _300x429
                $width_height = strrchr($stimage_file_name, "_");
                $width = substr($width_height, 1, strrpos($width_height, 'x')-1); // 300
                $height = substr($width_height, strrpos($width_height, 'x')+1, strlen($width_height)); // 429
                
                // .jpg
                $stimage_extension = strtolower(strrchr($image_url, "."));
                // /home/manager/server/images/stimage.hanafostv.com:8080/ontv/10250/T30222_src.165.jpg
                
//                 if (is_null($width) || is_null($height)) {
//                 	$resize_image = $stimage_full_path;
//                 } else {
//                 	$resize_image = $stimage_file_path.$stimage_file_name.'_'.$width.'_'.$height.'_'.$stimage_extension;
//                 }
                $resize_image = $stimage_full_path;

//                 if (is_null($resize_w)) {
//                         $resize_image = $stimage_full_path;
//                 } else {
//                         $resize_image = $stimage_file_path.$stimage_file_name.'.'.$resize_w.$stimage_extension;
//                 }

//              $resize_image = $stimage_file_path.$stimage_file_name.'.'.$resize_w.$stimage_extension;
//              echo "<br>1. resize_image : ". $resize_image. "<br>";

                if (file_exists($resize_image)) {
                        $resizeObj = new resize($resize_image);

                        header('Content-type: image/jpeg');
//                         header("Cache-Control: private, max-age=300, pre-check=300");
//                         header("Pragma: private");
//                         header("Expires: " . date(DATE_RFC822, strtotime(" 2 day")));

                        imagejpeg($resizeObj->getImage());
                } else {
                        umask(0002);

                        if (!file_exists(dirname($stimage_full_path))) {
                        	mkdir(dirname($stimage_full_path), 0777, true);
                        }
                                                
                        $image = file_get_contents(str_replace($width_height, "", $image_url));
                        
                        if ($image !== false) {
                        	file_put_contents($stimage_full_path, $image);
                        	
                        	$resizeObj = new resize($stimage_full_path);
                        	//$resize_h = round($resizeObj->getHeight() * ($resize_w/$resizeObj->getWidth()));
                        	
                        	// *** 2) Resize image (options: exact, portrait, landscape, auto, crop)
                        	//$resizeObj->resizeImage($resize_w, $resize_h, 'auto');
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