#!/bin/sh
Contents_count=`ps -ef | grep ::preview | grep -v grep | wc -l`

if [ $Contents_count -lt 2 ]
then

rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10000/*_T_* /contents/ontv/10000/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10050/*_T_* /contents/ontv/10050/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10100/*_T_* /contents/ontv/10100/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10150/*_T_* /contents/ontv/10150/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10200/*_T_* /contents/ontv/10200/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10250/*_T_* /contents/ontv/10250/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10300/*_T_* /contents/ontv/10300/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10350/*_T_* /contents/ontv/10350/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10400/*_T_* /contents/ontv/10400/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10450/*_T_* /contents/ontv/10450/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10500/*_T_* /contents/ontv/10500/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10550/*_T_* /contents/ontv/10550/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10600/*_T_* /contents/ontv/10600/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10650/*_T_* /contents/ontv/10650/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10700/*_T_* /contents/ontv/10700/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10750/*_T_* /contents/ontv/10750/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10800/*_T_* /contents/ontv/10800/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10850/*_T_* /contents/ontv/10850/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10900/*_T_* /contents/ontv/10900/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/10950/*_T_* /contents/ontv/10950/
sleep 2
rsync -av --progress --size-only --exclude=.* --bwlimit=10000 121.125.28.55::preview/ontv/11000/*_T_* /contents/ontv/11000/

fi
