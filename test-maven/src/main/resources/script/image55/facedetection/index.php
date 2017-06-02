<?php

include "FaceDetector.php";
$file_path = '/home/manager/server/app/imgbackup/';
$detector = new svay\FaceDetector('detection.dat');
$detector->faceDetect($file_path.$_GET['file']);
// $detector->toJpeg();
$detector->cropFaceToJpeg();