<?php
        // *** Include the class
        include("resize-class.php");

        $image_url = $_REQUEST["u"];

        if (strpos($image_url, 'stimage.hanafostv.com:8080') > 0 || strpos($image_url, '1.255.144.') > 0) {
                $stimage_full_path = str_replace("http://", '/home/manager/server/images/', $image_url);
                $stimage_file_path = substr($stimage_full_path, 0, strrpos($stimage_full_path, '/')).'/';

                // T30222_src_300x429.jpg
                $stimage_file_name = str_replace($stimage_file_path, "", $stimage_full_path);
                $stimage_file_name = substr($stimage_file_name, 0, strrpos($stimage_file_name, '.'));

                // T30222_src_300x429
                // width_height _300x429
                $width_height = strrchr($stimage_file_name, "_");
                if ($width_height != NULL) {
                    $width = substr($width_height, 1, strrpos($width_height, 'x')-1); // 300
                    $height = substr($width_height, strrpos($width_height, 'x')+1, strlen($width_height)); // 429
                }

                // .jpg
                $stimage_extension = strtolower(strrchr($image_url, "."));
                // /home/manager/server/images/stimage.hanafostv.com:8080/ontv/10250/T30222_src.165.jpg

                $resize_image = $stimage_full_path;

                if (file_exists($resize_image)) {
                        $resizeObj = new resize($resize_image);
                        header('Content-type: image/jpeg');
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