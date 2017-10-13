<?php
    include("resize-class.php");
        
    // $array_width_height = array('_100x143', '_110x158', '_127x182', '_144x206', '_154x220', '_180x258', '_190x272', '_206x296', '_216x309', '_224x329', '_230x324', '_254x364', '_260x372', '_315x452', '_38x54', '_62x92', '_95x136', '_180x101', '_320x180', '_480x270');
    
    $image_url = $_REQUEST["u"]; // /ontv/11000/T98976_315x452.jpg    
    $image_url = str_replace("//", "/", $image_url);

    $image_root = "/home/manager/server/images/contents";
    $resize_image = $image_root.$image_url; // /home/manager/server/images/contents/ontv/11000/T98976_315x452.jpg
    
    $extension = strtolower(substr(strrchr($image_url, "."), 1));    
    if ($extension == 'jpg') {
        $extension = 'jpeg';
    }
    
    error_log (date("Y-m-d H:i:s")." 01 : ".$resize_image."\n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
    if (file_exists($resize_image)) { // file exist
        error_log (date("Y-m-d H:i:s")." 02 : file exist \n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
        $resizeObj = new resize($resize_image);
        header('Content-type: image/'.$extension);
        
        if ($extension == 'png') {
            error_log (date("Y-m-d H:i:s")." 03 : ".$extension."\n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
            imagepng($resizeObj->getImage());
        } else {
            error_log (date("Y-m-d H:i:s")." 04 : ".$extension."\n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
            imagejpeg($resizeObj->getImage(), NULL, 100);
        }
        ////////////////////////////////////////////////////////////////////////
    } else { // file not exist
        error_log (date("Y-m-d H:i:s")." 05 : file not exist\n", 3, "/home/manager/server/php-7.1.5/log/debug.log");

        umask(0002);            
        $path = pathinfo($resize_image);

        if (!file_exists($path['dirname'])) {
            mkdir($path['dirname'], 0777, true);
        }
        
        $remote_url = "/home/manager/server/images/contents"; // LIVE        
        
        $image_origin1 = "_src";
        $image_origin2 = "_315x452";            
        $is_width_height = true;
        
        $image_file_path = substr($resize_image, 0, strrpos($resize_image, '/')).'/'; // /home/manager/server/images/contents/ontv/11000/    
        $image_file_name = str_replace($image_file_path, "", $resize_image); // T98976_315x452.jpg    
        $image_file_name = substr($image_file_name, 0, strrpos($image_file_name, '.'));  // T98976_315x452
        $width_height = strrchr($image_file_name, "_"); // _315x452
        
        $width = "";
        $height = "";
        $remote_image = "";           
        
        $save_image = $resize_image;
        $option = "exact"; // auto crop exact 
        
        if ($width_height == NULL || $extension == 'png') { // error 원본 이미지              
            $is_width_height = false;
        } else { // resize 이미지
            $width = substr($width_height, 1, strrpos($width_height, 'x')-1); // 315
            $height = substr($width_height, strrpos($width_height, 'x')+1, strlen($width_height)); // 452
            
            if (is_numeric($width) && is_numeric($height)) { // resize 정보가 있으면
                $remote_image = @file_get_contents(str_replace($width_height, $image_origin1, $image_root.$image_url)); // 로컬 원본 _src
                if ($remote_image === false) {
                    error_log (date("Y-m-d H:i:s")." 08 : local origin _src not found ".$image_root.$image_url."\n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
                    $remote_image = @file_get_contents(str_replace($width_height, $image_origin2, $image_root.$image_url)); // 로컬 원본 _315x452  
                    if ($remote_image === false) {
                        error_log (date("Y-m-d H:i:s")." 09 : local origin _315x452 not found ".$image_root.$image_url."\n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
                        $is_width_height = false;
                    } else {
                        $resize_image = str_replace($width_height, $image_origin2, $resize_image);
                        error_log (date("Y-m-d H:i:s")." 15 : local origin _315x452 found ".$resize_image."\n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
                    }
                } else {
                    $resize_image = str_replace($width_height, $image_origin1, $resize_image);
                    error_log (date("Y-m-d H:i:s")." 16 : local origin _src found ".$resize_image."\n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
                }                
            } else { // resize 정보가 없으면                
                $is_width_height = false;
            }
        }
    
        if ($is_width_height == true) {
            error_log (date("Y-m-d H:i:s")." 19 : ** resize ".$resize_image."\n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
            $resizeObj = new resize($resize_image);
            $resizeObj->resizeImage($width, $height, $option);
            $resizeObj->viewImage($extension);
            // $resizeObj->saveImage($save_image); // resize image save            
        } else {
            error_log (date("Y-m-d H:i:s")." 21 : ** not resize ".$resize_image."\n", 3, "/home/manager/server/php-7.1.5/log/debug.log");            
            $resizeObj = new resize($resize_image);
            header('Content-type: image/'.$extension);
                                
            if ($extension == 'png') {
                imagepng($resizeObj->getImage());
            } else {
                imagejpeg($resizeObj->getImage(), NULL, 100);
            }            
        }
    }
?>
