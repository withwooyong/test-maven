<?php
        //error_reporting(0);
    // *** Include the class
    include("resize-class.php");
    $image_url = $_REQUEST["u"];
    $remote_url = "http://stimage.hanafostv.com:8080"; // BMT 후 제거
    $image_origin1 = "_src";
    $image_origin2 = "_315x452"; 
    // $image_url = /home/manager/server/images/contents/smartdelivery/20168261/kobis_still_20168261_1_142x142.jpg
    // $image_url = /home/manager/server/images/contents/movie/33700/M336599_src.jpg
    // $image_url = http://stimage.hanafostv.com:8080/smartdelivery/20168261/kobis_still_20168261_1_142x142.jpg
    $is_width_height = true;
    
    $resize_image = "/home/manager/server/images/contents".$image_url;
    // $resize_image = /home/manager/server/images/stimage.hanafostv.com:8080/smartdelivery/20168261/kobis_still_20168261_1_142x142.jpg
    
    $image_file_path = substr($resize_image, 0, strrpos($resize_image, '/')).'/';
    // $image_file_path = /home/manager/server/images/stimage.hanafostv.com:8080/smartdelivery/20168261/kobis_still_20168261_1_142x142/
    
    $image_file_name = str_replace($image_file_path, "", $resize_image);
    // $image_file_name = kobis_still_20168261_1_142x142.jpg
    $image_file_name = substr($image_file_name, 0, strrpos($image_file_name, '.'));  
    // $image_file_name = kobis_still_20168261_1_142x142
    
    // 141394_car3new2_menu_pro_v1_0630_374x258.png
    $width_height = strrchr($image_file_name, "_");
    // $width_height = _300x429
    
    $width = "";
    $height = "";
    $remote_image = "";       
    $extension = strtolower(substr(strrchr($image_url, "."), 1));
    if ($extension == 'jpg') {
        $extension = 'jpeg';
    }
    // $extension = .jpg
    $save_image = $resize_image;
    $option = "exact"; // auto crop exact 
    
    if (file_exists($resize_image)) { // 파일이 존재하면
        $resizeObj = new resize($resize_image);
        header('Content-type: image/'.$extension);
        if ($extension == 'png') {
                //$log_dir = "/home/manager/server/nginx_image/logs/";
                //$log_file = fopen($log_dir."php.log", "a");
                //fwrite($log_file, '1 '.$resize_image."\r\n");
                //fclose($log_file);
                // Output the image
                imagepng($resizeObj->getImage());
        } else {
                imagejpeg($resizeObj->getImage(), NULL, 100);
        }
    } else { // 파일이 없으면
        umask(0002);    
        // mount 되면 삭제
        $path = pathinfo($resize_image);
        if (!file_exists($path['dirname'])) {
                mkdir($path['dirname'], 0777, true);
        }
        // $image = file_get_contents(str_replace($width_height, "", $image_url));
        // $size = getimagesize("http://www.example.com/gifs/logo.gif");
        // $resize_image
        if ($width_height == NULL) { // src 원본 이미지
                //copy($image_url, $resize_image);
                $remote_image = file_get_contents($remote_url.$image_url);
                file_put_contents($resize_image, $remote_image);
                $is_width_height = false;
        } else { // resize 이미지    
                $width = substr($width_height, 1, strrpos($width_height, 'x')-1); // 300
                $height = substr($width_height, strrpos($width_height, 'x')+1, strlen($width_height)); // 429
                if (is_numeric($width) && is_numeric($height)) { // resize 정보가 있으면
                /////////////////// 요기 수정해야 함.
                        $remote_image = @file_get_contents(str_replace($width_height, $image_origin1, $remote_url.$image_url));
                        if ($remote_image === false) {
                                $remote_image = file_get_contents($remote_url.$image_url);
                                $is_width_height = false;
                        } else {
                                $resize_image = str_replace($width_height, $image_origin1, $resize_image);
                                // $option = "auto";
                                $option = "exact";
                        }
                        // $resize_image
                        file_put_contents($resize_image, $remote_image);
                } else { // resize 정보가 없으면
                        //copy($image_url, $resize_image);
                        $remote_image = file_get_contents($remote_url.$image_url);
                        file_put_contents($resize_image, $remote_image);
                        $is_width_height = false;
                }    
        }
    
        if ($is_width_height == true) {
                $resizeObj = new resize($resize_image);
                $resizeObj->resizeImage($width, $height, $option);
                $resizeObj->viewImage($extension);
                $resizeObj->saveImage($save_image); // resize image save
        } else {
                $resizeObj = new resize($resize_image);
                header('Content-type: image/'.$extension);
                //header("Cache-Control: private, max-age=300, pre-check=300");
                //header("Pragma: private");
                //header("Expires: " . date(DATE_RFC822, strtotime(" 2 day")));
                if ($extension == 'png') {
                        imagepng($resizeObj->getImage());
                } else {
                        imagejpeg($resizeObj->getImage(), NULL, 100);
                }
        }
    }
?>