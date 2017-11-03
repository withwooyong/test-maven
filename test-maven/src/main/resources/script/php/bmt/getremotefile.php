<?php

    // $remote_file = "http://stimage.hanafostv.com:8080/ontv/10800/T307805_src.jpg";
    // $local_file = "/contents/T307805_src.jpg";
    //copy($remote_file, $local_file);

    //file_put_contents($local_file, file_get_contents($remote_file));

    //$fp = fopen($local_file, "w"); 
    //fwrite($fp, file_get_contents($remote_file)); 
    //fclose($fp)
    /* 
    $fp = fopen($local_file, "w"); 
    $ch = curl_init(); 
    curl_setopt($ch, CURLOPT_URL, $remote_file); 
    curl_setopt($ch, CURLOPT_HEADER, 0); 
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1); 
    curl_setopt($ch, CURLOPT_USERAGENT, "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)"); 
    curl_setopt($ch, CURLOPT_FILE, $fp); 
    curl_exec($ch); 
    fclose($fp); 
    curl_close($ch);
    
    function get_contents() {
      file_get_contents("http://stimage.hanafostv.com:8080/ontv/10800/T307805_src.jpg");
      var_dump($http_response_header);
    }
    get_contents();
    var_dump($http_response_header);
    $foo = $http_response_header;
    var_dump($foo[3]);

    copy($remote_file, $local_file);
    $dt = filemtime($remote_file);
    touch($local_file, $dt);
    

    $fileURL = "http://stimage.hanafostv.com:8080/ontv/10800/T307805_src.jpg";
    $headers = get_headers($fileURL, 1);
    $date = "Error";
    
    if ($headers && (strpos($headers[0],'200') !== FALSE)) {
        $time = strtotime($headers['Last-Modified']);
        $date = date("d-m-Y H:i:s", $time);
    }

    */

    $remote_file = "http://stimage.hanafostv.com:8080/ontv/10800/T307805_src.jpg";
    $local_file = "/contents/T307805_src.jpg";

    $date = "Error";
    $headers = get_headers($remote_file, 1);
    var_dump($headers);    

    if ($headers && (strpos ($headers[0], '200') !== FALSE)) {        
        var_dump($headers['Last-Modified']);
        $time = strtotime($headers['Last-Modified']);
        $date = date("d-m-Y H:i:s", $time);
    }
    var_dump('date : '.$date);
    
    // "Last-Modified: Thu, 14 May 2015 02:54:59 GMT"
    file_put_contents($local_file, file_get_contents($remote_file));    
    //$time = strtotime(str_replace("Last-Modified: ", "", $http_response_header[3]));
    $date = date("d-m-Y H:i:s", strtotime(str_replace("Last-Modified: ", "", $http_response_header[3])));
    touch($local_file, $date);
    
    //parseHeaders($http_response_header);    
?>

$headers = get_headers("http://stimage.hanafostv.com:8080/ontv/10800/T307805_src.jpg", 1);

if ($headers && (strpos ($headers[0], '200') !== FALSE))
  {
var_dump($headers['Last-Modified']);
    $time = strtotime($headers['Last-Modified']);
    $date = date("d-m-Y H:i:s", $time);
}
// var_dump($http_response_header[3]);
 //   $time = strtotime($http_response_header[3]);
 //   $date = date("d-m-Y H:i:s", $time);
 //   var_dump('time : '.$time);
    var_dump('date : '.$date);