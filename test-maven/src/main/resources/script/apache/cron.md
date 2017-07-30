#Time Sync
45 1 * * * rdate -s time.hanafostv.com;clock -w

#rsync
#5,15,25,35,45,55 * * * * /data/shell/sync_contents_data.sh

#rsync(from 121.125.28.55)
3,13,23,33,43,53 * * * * /data/shell/sync_corner_data.sh


#igs rsync
#*/2 * * * * /data/shell/sync_igs_data.sh

#log delete script (2015.06.26, KSI)####################
50 05 * * * su - root -c 'sh /root/shell/log_delete.sh'