<?php
    $total= face_count('/home/manager/server/app/images.jpg','haarcascade_frontalface_alt.xml');
    $coord= face_detect('/home/manager/server/app/images.jpg','haarcascade_frontalface_alt.xml');
    print_r($coord);
?>