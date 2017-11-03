<?php
    include("resize-class.php");
    
    $image_url = $_REQUEST["u"]; // /ontv/11000/T98976_315x452.jpg
    $is_bmt = false;
    
    $remote_url = "";
    if ($is_bmt == true) {
        $remote_url = "http://stimage.hanafostv.com:8080"; // rsync server url 
    } else {
        $remote_url = "/contents"; // LIVE image local path
    }
        
    $image_root = "/contents";
    $resize_image = $image_root.$image_url; // /home/manager/server/images/contents/ontv/11000/T98976_315x452.jpg
    $extension = strtolower(substr(strrchr($image_url, "."), 1));
    
    if ($extension == 'jpg') {
        $extension = 'jpeg';
    }
    
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
        $monitoring_log = "/usr/local/php-7.1.5/log/monitoring.log";
        error_log (date("Y-m-d H:i:s")." resize.php _REQUEST : ".$image_url."\n", 3, $monitoring_log);
        $image_origin1 = "_src";
        $image_origin2 = "_315x452";
        $is_width_height = true;

        $image_file_path = substr($resize_image, 0, strrpos($resize_image, '/')).'/'; // /home/manager/server/images/contents/ontv/11000/    
        $image_file_name = str_replace($image_file_path, "", $resize_image); // T98976_315x452.jpg    
        $image_file_name = substr($image_file_name, 0, strrpos($image_file_name, '.'));  // T98976_315x452
        $width_height = strrchr($image_file_name, "_"); // _315x452        
        $width = "";
        $height = "";
        $save_image = $resize_image;
        $remote_image = "";

        umask(0002);    
        
        $path = pathinfo($resize_image);
        
        if (!file_exists($path['dirname'])) {
            mkdir($path['dirname'], 0777, true);
        }
        
        if ($width_height == NULL || $extension == 'png') { // png or 원본 이미지
            if ($is_bmt == true) {
                $remote_image = file_get_contents($remote_url.$image_url); // rsync 서버 BMT http://stimage.hanafostv.com:8080/ontv/11000/T98976_315x452.jpg            
                file_put_contents($resize_image, $remote_image); // BMT /home/manager/server/images/contents/ontv/11000/T98976_315x452.jpg
            }
            $is_width_height = false;
        } else { // resize 이미지
            $width = substr($width_height, 1, strrpos($width_height, 'x')-1); // 315
            $height = substr($width_height, strrpos($width_height, 'x')+1, strlen($width_height)); // 452
            
            if (is_numeric($width) && is_numeric($height)) { // resize 정보가 있으면    

                $remote_image = @file_get_contents(str_replace($width_height, $image_origin1, $image_root.$image_url)); // 로컬 원본 _src
                if ($remote_image === false) {
                    $remote_image = @file_get_contents(str_replace($width_height, $image_origin2, $image_root.$image_url)); // 로컬 원본 _315x452  
                    if ($remote_image === false) {
                        if ($is_bmt == true) {
                            $remote_image = @file_get_contents(str_replace($width_height, $image_origin1, $remote_url.$image_url)); // stimage 원본 _src                 
                            if ($remote_image === false) {
                                $remote_image = @file_get_contents(str_replace($width_height, $image_origin2, $remote_url.$image_url)); // stimage 원본 315x452                    
                                if ($remote_image === false) { // 원본 없음
                                    error_log (date("Y-m-d H:i:s")." resize.php image not found bmt : ".$image_url."\n", 3, $monitoring_log);
                                    $is_width_height = false;
                                    header("HTTP/1.1 404 Not Found");
                                    return;
                                } else {
                                    $resize_image = str_replace($width_height, $image_origin2, $resize_image);
                                }
                            } else {
                                $resize_image = str_replace($width_height, $image_origin1, $resize_image);
                            }                                                
                        } else {
                            error_log (date("Y-m-d H:i:s")." resize.php image not found : ".$image_url."\n", 3, $monitoring_log);
                            header("HTTP/1.1 404 Not Found");
                            return;
                        }
                    } else {
                        $resize_image = str_replace($width_height, $image_origin2, $resize_image);
                    }
                } else {
                    $resize_image = str_replace($width_height, $image_origin1, $resize_image);
                }
                
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