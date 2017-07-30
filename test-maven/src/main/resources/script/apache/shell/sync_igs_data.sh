#!/bin/sh
Contents_count=`ps -ef | grep "/contents/igs" | grep -v grep | wc -l`

if [ $Contents_count -lt 2 ]
then

rsync -av --size-only --exclude=.* 1.226.200.38::contents/igs/ /contents/igs/

fi
