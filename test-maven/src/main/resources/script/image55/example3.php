<?php
include "FaceDetector.php";

/* We now extend the above class so we can add our own methods */
class FaceModify extends Face_Detector {
	public function Rotate() {
		$canvas = imagecreatetruecolor ( $this->face ['w'], $this->face ['w'] );
		imagecopy ( $canvas, $this->canvas, 0, 0, $this->face ['x'], $this->face ['x'], $this->face ['w'], $this->face ['w'] );
		$canvas = imagerotate ( $canvas, 180, 0 );
		$this->_outImage ( $canvas );
	}
	public function toGrayScale() {
		$canvas = imagecreatetruecolor ( $this->face ['w'], $this->face ['w'] );
		imagecopy ( $canvas, $this->canvas, 0, 0, $this->face ['x'], $this->face ['x'], $this->face ['w'], $this->face ['w'] );
		imagefilter ( $canvas, IMG_FILTER_GRAYSCALE );
		$this->_outImage ( $canvas );
	}
	public function resizeFace($width, $height) {
		$canvas = imagecreatetruecolor ( $width, $width );
		imagecopyresized ( $canvas, $this->canvas, 0, 0, $this->face ['x'], $this->face ['y'], $width, $height, $this->face ['w'], $this->face ['w'] );
		$this->_outImage ( $canvas );
	}
	private function _outImage($canvas) {
		header ( 'Content-type: image/jpeg' );
		imagejpeg ( $canvas );
	}
}

/* Using the extended class */
$face_detect = new FaceModify ( 'detection.dat' );
$face_detect->face_detect ( $_GET ['file'] );
$face_detect->resizeFace ( 100, 100 );
?>
