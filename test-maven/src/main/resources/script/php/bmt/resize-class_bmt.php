<?php
// ========================================================================#
//
// Author: Jarrod Oberto
// Version: 1.0
// Date: 17-Jan-10
// Purpose: Resizes and saves image
// Requires : Requires PHP5, GD library.
// Usage Example:
// include("classes/resize_class.php");
// $resizeObj = new resize('images/cars/large/input.jpg');
// $resizeObj -> resizeImage(150, 100, 0);
// $resizeObj -> saveImage('images/cars/large/output.jpg', 100);
//
//
// ========================================================================#
        class resize {                
                // *** Class variables
                private $image;
                private $width;
                private $height;
                private $imageResized;
                private $originImage;
                function __construct($fileName) {
                        // *** Open up the file
                        $this->image = $this->openImage ( $fileName );
                        if ($this->image == null) {
                                $this->image = $this->openImage ( '/home/manager/server/images/contents/movie/33700/M336546_src.jpg' );
                        } 
                        // *** Get width and height
                        $this->width = imagesx ( $this->image );
                        $this->height = imagesy ( $this->image );
                }

                // # --------------------------------------------------------
                private function openImage($file) {
                        // *** Get extension
                        $this->originImage = $file;

                        error_log (date("Y-m-d H:i:s")." resize-class ### openImage jpg : this->file=".exif_imagetype('/home/manager/server/images/contents/movie/33700/M336546_src.jpg')." \n", 3, "/home/manager/server/php-7.1.5/log/debug.log");

                        switch (exif_imagetype ( $file )) {
                                case 1 : // IMAGETYPE_GIF
                                        $img = @imagecreatefromgif ( $file );
                                        break;
                                case 2 : // IMAGETYPE_JPEG
                                        error_log (date("Y-m-d H:i:s")." resize-class openImage jpg : this->file=".$file." \n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
                                        $img = @imagecreatefromjpeg ( $file );
                                        break;
                                case 3 : // IMAGETYPE_PNG
                                        error_log (date("Y-m-d H:i:s")." resize-class openImage png : this->file=".$file." \n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
                                        $img = @imagecreatefrompng ( $file );
                                        // integer representation of the color black (rgb: 0,0,0)
                                        $background = imagecolorallocate($img, 0, 0, 0);
                                        // removing the black from the placeholder
                                        imagecolortransparent($img, $background);
                                        // turning off alpha blending (to ensure alpha channel information
                                        // is preserved, rather than removed (blending with the rest of the
                                        // image in the form of black))
                                        imagealphablending($img, false);
                                        // turning on alpha channel information saving (to ensure the full range
                                        // of transparency is preserved)
                                        imagesavealpha($img, true);
                                        break;
                                default :
                                        $img = @imagecreatefromjpeg ( $file );
                                        break;
                        }
                        return $img;
                }

                // # --------------------------------------------------------
                public function resizeImage($newWidth, $newHeight, $option = "auto") {
                        // *** Get optimal width and height - based on $option
                        $optionArray = $this->getDimensions ( $newWidth, $newHeight, $option );
                        $optimalWidth = $optionArray ['optimalWidth'];
                        $optimalHeight = $optionArray ['optimalHeight'];

                        // *** Resample - create image canvas of x, y size
                        $this->imageResized = imagecreatetruecolor ( $optimalWidth, $optimalHeight );
                        imagecopyresampled ( $this->imageResized, $this->image, 0, 0, 0, 0, $optimalWidth, $optimalHeight, $this->width, $this->height );

                        // *** if option is 'crop', then crop too
                        if ($option == 'crop') {
                                $this->crop ( $optimalWidth, $optimalHeight, $newWidth, $newHeight );
                        }
                }
                // # --------------------------------------------------------
                public function calcHeightRatio16x9($width) {
                        return round ( ($width / 16) * 9 );
                }
                public function cropImageBy16x9($newWidth, $option = "center") {
                        $cropStartX = 0;
                        $newHeight = $this->calcHeightRatio16x9 ( $newWidth );
                        $optimalHeight = $this->calcHeightRatio16x9 ( $this->width );

                        // *** if option is 'crop', then crop too
                        switch ($option) {
                                case 'top' :
                                        $cropStartY = 0;
                                        break;
                                case 'bottom' :
                                        $cropStartY = $this->height - $optimalHeight;
                                        break;
                                default :
                                        $cropStartY = round ( ($this->height - $optimalHeight) / 2 );
                        }

                        // *** Now crop from center to exact requested size
                        $this->imageResized = imagecreatetruecolor ( $newWidth, $newHeight );
                        // *** bool imagecopyresampled ( resource $dst_image , resource $src_image , int $dst_x , int $dst_y , int $src_x , int $src_y , int $dst_w , int $dst_h , int $src_w , int $src_h )
                        imagecopyresampled ( $this->imageResized, $this->image, 0, 0, $cropStartX, $cropStartY, $newWidth, $newHeight, $this->width, $optimalHeight );
                }

                // # --------------------------------------------------------
                private function getDimensions($newWidth, $newHeight, $option) {
                        switch ($option) {
                                case 'exact' :
                                        $optimalWidth = $newWidth;
                                        $optimalHeight = $newHeight;
                                        break;
                                case 'portrait' :
                                        $optimalWidth = $this->getSizeByFixedHeight ( $newHeight );
                                        $optimalHeight = $newHeight;
                                        break;
                                case 'landscape' :
                                        $optimalWidth = $newWidth;
                                        $optimalHeight = $this->getSizeByFixedWidth ( $newWidth );
                                        break;
                                case 'auto' :
                                        $optionArray = $this->getSizeByAuto ( $newWidth, $newHeight );
                                        $optimalWidth = $optionArray ['optimalWidth'];
                                        $optimalHeight = $optionArray ['optimalHeight'];
                                        break;
                                case 'crop' :
                                        $optionArray = $this->getOptimalCrop ( $newWidth, $newHeight );
                                        $optimalWidth = $optionArray ['optimalWidth'];
                                        $optimalHeight = $optionArray ['optimalHeight'];
                                        break;
                        }
                        return array (
                                        'optimalWidth' => $optimalWidth,
                                        'optimalHeight' => $optimalHeight 
                        );
                }

                // # --------------------------------------------------------
                private function getSizeByFixedHeight($newHeight) {
                        $ratio = $this->width / $this->height;
                        $newWidth = $newHeight * $ratio;
                        return $newWidth;
                }
                private function getSizeByFixedWidth($newWidth) {
                        $ratio = $this->height / $this->width;
                        $newHeight = $newWidth * $ratio;
                        return $newHeight;
                }
                private function getSizeByAuto($newWidth, $newHeight) {
                        if ($this->height < $this->width) 
                        // *** Image to be resized is wider (landscape)
                        {
                                $optimalWidth = $newWidth;
                                $optimalHeight = $this->getSizeByFixedWidth ( $newWidth );
                        } elseif ($this->height > $this->width) 
                        // *** Image to be resized is taller (portrait)
                        {
                                $optimalWidth = $this->getSizeByFixedHeight ( $newHeight );
                                $optimalHeight = $newHeight;
                        } else          // *** Image to be resizerd is a square
                        {
                                if ($newHeight < $newWidth) {
                                        $optimalWidth = $newWidth;
                                        $optimalHeight = $this->getSizeByFixedWidth ( $newWidth );
                                } else if ($newHeight > $newWidth) {
                                        $optimalWidth = $this->getSizeByFixedHeight ( $newHeight );
                                        $optimalHeight = $newHeight;
                                } else {
                                        // *** Sqaure being resized to a square
                                        $optimalWidth = $newWidth;
                                        $optimalHeight = $newHeight;
                                }
                        }
                        return array (
                                        'optimalWidth' => $optimalWidth,
                                        'optimalHeight' => $optimalHeight 
                        );
                }

                // # --------------------------------------------------------
                private function getOptimalCrop($newWidth, $newHeight) {
                        $heightRatio = $this->height / $newHeight;
                        $widthRatio = $this->width / $newWidth;

                        if ($heightRatio < $widthRatio) {
                                $optimalRatio = $heightRatio;
                        } else {
                                $optimalRatio = $widthRatio;
                        }
                        $optimalHeight = $this->height / $optimalRatio;
                        $optimalWidth = $this->width / $optimalRatio;
                        return array (
                                        'optimalWidth' => $optimalWidth,
                                        'optimalHeight' => $optimalHeight 
                        );
                }

                // # --------------------------------------------------------
                private function crop($optimalWidth, $optimalHeight, $newWidth, $newHeight) {
                        // *** Find center - this will be used for the crop
                        $cropStartX = ($optimalWidth / 2) - ($newWidth / 2);
                        $cropStartY = ($optimalHeight / 2) - ($newHeight / 2);
                        $crop = $this->imageResized;
                        // imagedestroy($this->imageResized);

                        // *** Now crop from center to exact requested size
                        $this->imageResized = imagecreatetruecolor ( $newWidth, $newHeight );
                        imagecopyresampled ( $this->imageResized, $crop, 0, 0, $cropStartX, $cropStartY, $newWidth, $newHeight, $newWidth, $newHeight );
                }

                // # --------------------------------------------------------
                public function saveImage($savePath, $imageQuality = "100") {
                        // *** Get extension
                        $extension = strrchr ( $savePath, '.' );
                        $extension = strtolower ( $extension );

                        if (! file_exists ( dirname ( $savePath ) ))
                                mkdir ( dirname ( $savePath ), 0777, true );

                        switch (exif_imagetype ( $this->originImage )) {
                                case 2 :
                                        imagejpeg ( $this->imageResized, $savePath, $imageQuality );
                                        break;
                                case 1 :
                                        imagegif ( $this->imageResized, $savePath );
                                        break;
                                case 3 :
                                        // *** Scale quality from 0-100 to 0-9
                                        $scaleQuality = round ( ($imageQuality / 100) * 9 );

                                        // *** Invert quality setting as 0 is best, not 9
                                        $invertScaleQuality = 9 - $scaleQuality;
                                        imagepng ( $this->imageResized, $savePath, $invertScaleQuality );
                                        break;
                                // ... etc
                                default :
                                        // *** No extension - No save.
                                        break;
                        }
                        imagedestroy ( $this->imageResized );
                }

                // # --------------------------------------------------------
                public function viewImage($resized) { 
                        error_log (date("Y-m-d H:i:s")." ### viewImage test ### \n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
                        error_log (date("Y-m-d H:i:s")." ### resized = ".$resized ." \n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
                        if ($resized == 'N') {
                                $this->imageResized = $this->originImage;
                                error_log (date("Y-m-d H:i:s")." resize-class viewImage resized == false : ".$this->imageResized." \n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
                        } 
                        switch (exif_imagetype ( $this->imageResized )) {
                                case 1 : // IMAGETYPE_GIF
                                        header ( 'Content-type: image/gif');
                                        imagegif ( $this->imageResized);
                                        break;
                                case 2 : // IMAGETYPE_JPEG
                                        header ( 'Content-type: image/jpeg');
                                        error_log (date("Y-m-d H:i:s")." resize-class viewImage jpg : ".$this->imageResized." \n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
                                        imagejpeg ( $this->imageResized, NULL, 100);
                                        break;                                
                                case 3 : // IMAGETYPE_PNG
                                        header ( 'Content-type: image/png');
                                        error_log (date("Y-m-d H:i:s")." resize-class viewImage png : ".$this->imageResized." \n", 3, "/home/manager/server/php-7.1.5/log/debug.log");
                                        imagepng ( $this->imageResized, NULL, 0);
                                        break;
                                default :
                                        header ( 'Content-type: image/jpeg');
                                        imagejpeg ( $this->imageResized, NULL, 100);
                                        break;
                        } 
                        //imagedestroy($this->imageResized);
                }

                public function getWidth() {
                        return $this->width;
                }
                public function getHeight() {
                        return $this->height;
                }
                public function getImage() {
                        return $this->image;
                }
        }
?>