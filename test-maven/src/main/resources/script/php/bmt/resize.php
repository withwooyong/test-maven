<?php
    include("resize-class.php");
        
    // $array_width_height = array('_100x143', '_110x158', '_127x182', '_144x206', '_154x220', '_180x258', '_190x272', '_206x296', '_216x309', '_224x329', '_230x324', '_254x364', '_260x372', '_315x452', '_38x54', '_62x92', '_95x136', '_180x101', '_320x180', '_480x270');
    
    $image_url = $_REQUEST["u"]; // /ontv/11000/T98976_315x452.jpg    
    $is_bmt = true;
    
    $remote_url = "";
    if ($is_bmt == true) {
        $remote_url = "http://stimage.hanafostv.com:8080"; // BMT
    } else {
        $remote_url = "/home/manager/server/images/contents"; // LIVE
    }
    
    $image_origin1 = "_src";
    $image_origin2 = "_315x452";        
    
    $is_width_height = true;    
    
    $image_root = "/home/manager/server/images/contents";
    $resize_image = $image_root.$image_url; // /home/manager/server/images/contents/ontv/11000/T98976_315x452.jpg
    
    $image_file_path = substr($resize_image, 0, strrpos($resize_image, '/')).'/'; // /home/manager/server/images/contents/ontv/11000/    
    $image_file_name = str_replace($image_file_path, "", $resize_image); // T98976_315x452.jpg    
    $image_file_name = substr($image_file_name, 0, strrpos($image_file_name, '.'));  // T98976_315x452
    $width_height = strrchr($image_file_name, "_"); // _315x452
    
    $width = "";
    $height = "";
    $remote_image = "";       
    $extension = strtolower(substr(strrchr($image_url, "."), 1));
    
    if ($extension == 'jpg') {
        $extension = 'jpeg';
    }
    
    $save_image = $resize_image;
    $option = "exact"; // auto crop exact 
    
    if (file_exists($resize_image)) { // file exist
        $resizeObj = new resize($resize_image);
        header('Content-type: image/'.$extension);
        
        if ($extension == 'png') {
            imagepng($resizeObj->getImage());
        } else {
            imagejpeg($resizeObj->getImage(), NULL, 100);
        }
        ////////////////////////////////////////////////////////////////////////
    } else { // file not exist
        umask(0002);    
        
        $path = pathinfo($resize_image);
        
        if (!file_exists($path['dirname'])) {
            mkdir($path['dirname'], 0777, true);
        }
        
        if ($width_height == NULL) { // error 원본 이미지  
            if ($is_bmt == true) {
                $remote_image = file_get_contents($remote_url.$image_url); // BMT http://stimage.hanafostv.com:8080/ontv/11000/T98976_315x452.jpg            
                file_put_contents($resize_image, $remote_image); // BMT /home/manager/server/images/contents/ontv/11000/T98976_315x452.jpg
            }
            $is_width_height = false;
        } else { // resize 이미지
            $width = substr($width_height, 1, strrpos($width_height, 'x')-1); // 315
            $height = substr($width_height, strrpos($width_height, 'x')+1, strlen($width_height)); // 452
            
            if (is_numeric($width) && is_numeric($height)) { // resize 정보가 있으면    
                /*
                // png 파일 이미지 사이즈 등을 정의하지 못했음.
                if (in_array($width_height, $array_width_height) == false && $extension != 'png') { // resize 규칙에 어긋나면
                    header("HTTP/1.1 404 Not Found");                    
                    return;
                }
                */
                $remote_image = @file_get_contents(str_replace($width_height, $image_origin1, $image_root.$image_url)); // 로컬 원본 _src
                if ($remote_image === false) {
                    $remote_image = @file_get_contents(str_replace($width_height, $image_origin2, $image_root.$image_url)); // 로컬 원본 _src
                    if ($remote_image === false) {
                        $remote_image = @file_get_contents(str_replace($width_height, $image_origin1, $remote_url.$image_url)); // stimage 원본 _src                 
                        if ($remote_image === false) {
                            $remote_image = @file_get_contents(str_replace($width_height, $image_origin2, $remote_url.$image_url)); // stimage 원본 _315x452                    
                            if ($remote_image === false) { // 원본 없음
                                if ($is_bmt == true) {
                                    $remote_image = file_get_contents($remote_url.$image_url); // BMT
                                }
                                $is_width_height = false;
                            } else {
                                $resize_image = str_replace($width_height, $image_origin2, $resize_image);
                            }
                        } else {
                            $resize_image = str_replace($width_height, $image_origin1, $resize_image);
                        }                                                
                    } else {
                        $resize_image = str_replace($width_height, $image_origin2, $resize_image);
                    }
                } else {
                    $resize_image = str_replace($width_height, $image_origin1, $resize_image);
                }
                
                /*
                $remote_image = @file_get_contents(str_replace($width_height, $image_origin1, $remote_url.$image_url)); // 원본 _src                 
                if ($remote_image === false) {
                    $remote_image = @file_get_contents(str_replace($width_height, $image_origin2, $remote_url.$image_url)); // 원본 _315x452                    
                    if ($remote_image === false) { // 원본 없음
                        if ($is_bmt == true) {
                            $remote_image = file_get_contents($remote_url.$image_url); // BMT
                        }
                        $is_width_height = false;
                    } else {
                        $resize_image = str_replace($width_height, $image_origin2, $resize_image);
                    }
                } else {
                    $resize_image = str_replace($width_height, $image_origin1, $resize_image);
                }
                */
                
                if ($is_bmt == true) {
                    file_put_contents($resize_image, $remote_image); // stimage 서버에 있는 이미지 BMT 서버에 다운로드
                }
            } else { // resize 정보가 없으면
                if ($is_bmt == true) {
                    $remote_image = file_get_contents($remote_url.$image_url); // BMT
                    file_put_contents($resize_image, $remote_image); // BMT
                }
                $is_width_height = false;
            }
        }
    
        if ($is_width_height == true) {
            $resizeObj = new resize($resize_image);
            $resizeObj->resizeImage($width, $height, $option);
            $resizeObj->viewImage($extension);
            // $resizeObj->saveImage($save_image); // resize image save
        } else {
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
