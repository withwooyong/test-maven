<?php

    $remote_file = "http://stimage.hanafostv.com:8080/ontv/10800/T307805_src.jpg";
    $local_file = "/contents/T307805_src.jpg";
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
    */

    function parseHeaders( $headers )
    {
        $head = array();
        foreach( $headers as $k=>$v )
        {
            $t = explode( ':', $v, 2 );
            if( isset( $t[1] ) )
                $head[ trim($t[0]) ] = trim( $t[1] );
            else
            {
                $head[] = $v;
                if( preg_match( "#HTTP/[0-9\.]+\s+([0-9]+)#",$v, $out ) )
                    $head['reponse_code'] = intval($out[1]);
            }
        }
        return $head;
    }

    file_get_contents("http://stimage.hanafostv.com:8080/ontv/10800/T307805_src.jpg");
    $time = strtotime($http_response_header['Last-Modified']);
    $date = date("d-m-Y H:i:s", $time);
    print_r($date);
    //parseHeaders($http_response_header);    
?>