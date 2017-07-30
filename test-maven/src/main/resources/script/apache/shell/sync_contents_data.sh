#!/bin/sh
Contents_count=`ps -ef | grep ::contents | grep -v grep | wc -l`

if [ $Contents_count -lt 2 ]
then

rsync -av --progress --size-only --exclude=.* --bwlimit=10000 1.226.200.38::contents /contents

fi
