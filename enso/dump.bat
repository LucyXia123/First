rem mysqldump -uroot -hlocalhost --databases studyonline -pLX2017enso > studyonline.sql

mysqldump -uroot -h192.168.10.18 --databases studyonline -proot > studyonline.sql
mysqldump -uroot -h192.168.10.18 --databases studyonline --tables t_video -proot > t_video.sql
mysqldump -uroot -h192.168.10.18 --databases studyonline --tables t_journal -proot > t_journal.sql
mysqldump -uroot -h192.168.10.18 --databases studyonline --tables t_theme -proot > t_theme.sql
