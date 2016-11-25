#!/bin/bash
#"simple" ffmpeg progress indicator, though because evil ffmepg doesn't use Carriage Returns or Line Feeds like any normal person, it is actually a right pain in the bum ;)
#by Rupert Plumridge
#for updates visit www.prupert.co.uk
#Creative Commons Attribution-Non-Commercial-Share Alike 2.0 UK: England & Wales Licence
#version 1.4beta
#12 May 2010
#the I OFFICIALLY HATE ffmpeg's output version
#man, ffmpeg outputs it info in the most stupid way, adding extra white spaces and then concatenating values at other time, ARGH
#anyway, this version adds extra error checking to try to get round this, though I think work still needs to be done
#also, I am gonna switch this other to python in a few versions time, since it is a much more robust language than bash
#and I want to make that my default programming language
#version 1.3
#12 May 2010
#added a little bit of error checking if fps =0
#version 1.2
#12 May 2010
#added an ETA based on fps being processed and frames, 
#should be pretty accurate, but will need to do a few checks
#version 1.1
#12 May 2010
#fixed a stupid typo where I was missing a hash ;)
#version1.0
#11 May 2010

#CUSTOMISE THE FOLLOWING
#change the ffmpeg command here to whatever you want, keeping "$1" as your input and output file names
#FYI this command seems to produce fairly decent files from .mpg transport streams recorded from MythTV
trans() {
ffmpeg -deinterlace -y -i "$1" -vcodec libx264 -level 41 -vpre normal -crf 24 -threads 0 -sn -acodec libfaac -ab 128k -ac 2 -ar 48000 -vsync 1 -async 1000 -map 0.0:0.0 -map 0.1:0.1 "$1".mkv
}

#DONT EDIT ANYTHING BELOW THIS UNLESS YOU KNOW WHAT YOU ARE DOING
progress() {
sleep 10
#some shenanigans due to the way ffmpeg uses carriage returns
cat -v ~/ffmpeg.log | tr '^M' '\n' > ~/ffmpeg1.log
#calculate percentage progress based on frames
cframe=( $(tac ~/ffmpeg1.log | grep -m 1 frame= | awk '{print $1}' | cut -c 7-) )
if [ "$cframe" = "" ]; then
    cframe=( $(tac ~/ffmpeg1.log | grep -m 1 frame= | awk '{print $2}') )
else
    cframe=$cframe
fi
percent=$((100 * cframe / frames))
#calculate time left and taken etc
fps=( $(tac ~/ffmpeg1.log | grep -m 1 frame= | awk '{print $3}') )
if [ "$fps" = "fps=" ]; then
    fps=( $(tac ~/ffmpeg1.log | grep -m 1 frame= | awk '{print $4}') )
else
    fps=$fps
fi
total=$(( frames + cframe + percent + fps ))
#simple check to ensure all values are numbers
if [ $total -eq $total 2> /dev/null ]; then
    #all ok continue
    if [ "$fps" = "0" ]; then
        echo -ne "\rffmpeg: $cframe of $frames frames, progress: $percent"%" and ETA: error fps:0"
    else
        remaining=$(( frames - cframe ))
        seconds=$(( remaining / fps ))
        h=$(( seconds / 3600 ))
        m=$(( ( seconds / 60 ) % 60 ))
        s=$(( seconds % 60 ))
        echo -ne "\rffmpeg: $cframe of $frames frames, progress: $percent"%" and ETA: "$h"h "$m"m "$s"s"
    fi
else
    echo "Error, one of the values wasn't a number, trying again in 10s."
fi
}

echo conversion of "$1" started on `date "+%m/%d/%y %l:%M:%S %p"` > ~/trans.log
# Get duration and fps
duration=( $(ffmpeg -i "$1" 2>&1 | sed -n "s/.* Duration: \([^,]*\), start: .*/\1/p") )
fps=( $(ffmpeg -i "$1" 2>&1 | sed -n "s/.*, \(.*\) tbr.*/\1/p") )

hours=( $(echo $duration | cut -d":" -f1) )
minutes=( $(echo $duration | cut -d":" -f2) )
seconds=( $(echo $duration | cut -d":" -f3) )
# Get the integer part with cut
frames=( $(echo "($hours*3600+$minutes*60+$seconds)*$fps" | bc | cut -d"." -f1) )
echo ""$1" has $frames frames, now converting" >> ~/trans.log
echo ""$1" has $frames frames, now converting" > ~/ffmpeg.log
echo ""$1" has $frames frames, now converting"
#call the conversion and fork it
trans "$1" &>> ~/ffmpeg.log &
#get the PID of the conversion
pid=$!
echo "ffmpeg PID = $pid" >> ~/trans.log
echo "ffmpeg PID = $pid"
#attmept to monitor the progression FUKC IT THIS IS IMPOSSIBLE
cframe=1
while [ -e /proc/$pid ]; do
    progress
done

echo ffmpeg stopped on `date "+%m/%d/%y %l:%M:%S %p"` >> ~/trans.log

exit