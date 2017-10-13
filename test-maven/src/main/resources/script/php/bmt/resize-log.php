<?php
function push_log($log_str , $line)
{
    global $log_filename;

    $now        = getdate();
    $today      = $now['year']."/".$now['mon']."/".$now['mday'];
    $now_time   = $now['hours'].":".$now['minutes'].":".$now['seconds'];
    $now        = $today." ".$now_time;
        $filep = fopen($log_filename, "a");
        if(!$filep) {
        die("can't open log file : ". $log_filename);
    }

    fputs($filep, "{$now} : ({$line}) : {$log_str}\n\r");
    fclose($filep);
}
?>

