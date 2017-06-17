<?php
        // *** Include the class
        include("resize-class.php");

        $image_url = $_REQUEST["u"];
        
        $is_width_height = true;

        if (strpos($image_url, 'stimage.hanafostv.com:8080') > 0 || strpos($image_url, '1.255.144.') > 0) {
                $stimage_full_path = str_replace("http://", '/home/manager/server/images/', $image_url);
                $stimage_file_path = substr($stimage_full_path, 0, strrpos($stimage_full_path, '/')).'/';

                // T30222_src_300x429.jpg
                $stimage_file_name = str_replace($stimage_file_path, "", $stimage_full_path);
                $stimage_file_name = substr($stimage_file_name, 0, strrpos($stimage_file_name, '.'));

                // T30222_src_300x429
                // width_height _300x429
                $width_height = strrchr($stimage_file_name, "_");
                $width = "";
                $height = "";
                $http_image = ""; 
                $origin_image = ""; 
                $resize_image = "";
                
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
                // .jpg
                $stimage_extension = strtolower(strrchr($image_url, "."));
                // /home/manager/server/images/stimage.hanafostv.com:8080/ontv/10250/T30222_src.165.jpg
                
                if ($http_image !== false) {
                    umask(0002);
                    if (!file_exists(dirname($resize_image))) {
                        mkdir(dirname($resize_image), 0777, true);
                    }
                    file_put_contents($resize_image, $http_image);
                    
                    $resizeObj = new resize($resize_image);
                    if ($is_width_height == true) {
                        $resizeObj->resizeImage($width, $height, 'auto');
                        $resizeObj->viewImage();
                    } else {
                        header('Content-type: image/jpeg');
                        imagejpeg($resizeObj->getImage());
                    }
                } else {
                    // error 발생
                    header("Location: $image_url");
                    exit();
                }
        } else {
                header("Location: $image_url");
                exit();
        }
?>