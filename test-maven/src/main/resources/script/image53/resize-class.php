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
				$this->image = $this->openImage ( '/home/manager/server/images/default.jpg' );
			}
			
			// *** Get width and height
			$this->width = imagesx ( $this->image );
			$this->height = imagesy ( $this->image );
		}
		
		// # --------------------------------------------------------
		private function openImage($file) {
			// *** Get extension
			$this->originImage = $file;
			
			switch (exif_imagetype ( $file )) {
				case 2 :
					$img = @imagecreatefromjpeg ( $file );
					break;
				case 1 :
					$img = @imagecreatefromgif ( $file );
					break;
				case 3 :
					$img = @imagecreatefrompng ( $file );
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
		public function blackBackgroundCrop() {
			$cut_top = 0;
			$cut_bottom = 0;
			$cut_left = 0;
			$cut_right = 0;
			// A �� I
			$break_yn = false;
			for($j = 0; $j < $this->height / 2; $j ++) {
				for($i = 0; $i < $this->width; $i ++) {
					if (imagecolorat ( $this->image, $i, $j ) != 0) {
						$cut_top = $j;
						$break_yn = true;
						break;
					}
				}
				if ($break_yn) {
					break;
				}
			}
			// X �� I
			$break_yn = false;
			for($j = $this->height; $j > $this->height / 2; $j --) {
				for($i = $this->width; $i > 0; $i --) {
					if (imagecolorat ( $this->image, $i, $j ) != 0) {
						$cut_bottom = $j;
						$break_yn = true;
						break;
					}
				}
				
				if ($break_yn) {
					break;
				}
			}
			
			// �� �� I
			$break_yn = false;
			for($i = 0; $i < $this->width / 2; $i ++) {
				for($j = 0; $j < $this->height; $j ++) {
					if (imagecolorat ( $this->image, $i, $j ) != 0) {
						$cut_left = $i;
						$break_yn = true;
						break;
					}
				}
				if ($break_yn) {
					break;
				}
			}
			
			// �� �� I
			$break_yn = false;
			for($i = $this->width; $i > $this->width / 2; $i --) {
				for($j = $this->height; $j > 0; $j --) {
					if (imagecolorat ( $this->image, $i, $j ) != 0) {
						$cut_right = $i;
						$break_yn = true;
						break;
					}
				}
				if ($break_yn) {
					break;
				}
			}
			
			if ($cut_top > 10 || $cut_bottom > 10) {
				$cut_top += 15;
				$cut_bottom -= 10;
			}
			
			if ($cut_left > 10 || $cut_right > 10) {
				$cut_left += 15;
				$cut_right -= 12;
			}
			
			if ($cut_top > 0 && $cut_bottom > 0 && $cut_left > 0 && $cut_right > 0) {
				$canvas = imagecreatetruecolor ( $this->width - $cut_left - ($this->width - $cut_right), $this->height - $cut_top - ($this->height - $cut_bottom) );
				imagecopy ( $canvas, $this->image, 0, 0, $cut_left, $cut_top, $this->width, $this->height );
				$this->image = $canvas;
				$this->width = imagesx ( $this->image );
				$this->height = imagesy ( $this->image );
			}
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
			} else		// *** Image to be resizerd is a square
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
		public function viewImage() {
			header ( 'Content-type: image/jpeg' );
			imagejpeg ( $this->imageResized );
			// imagedestroy($this->imageResized);
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
