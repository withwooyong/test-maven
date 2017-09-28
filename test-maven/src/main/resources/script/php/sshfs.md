https://blog.lael.be/post/1387

yum install epel-release -y
yum install fuse sshfs

-- 54번 56번에서 실행 root exec

sshfs manager@1.255.144.55:/home/manager/server/images /home/manager/server/images -o allow_other
sshfs manager@1.255.144.55:/home/manager/server/images /home/manager/server/images
#df -h
fusermount -u /home/manager/server/images

fusermount -u /mybackup

root
**fnxm!
