DROP USER c##noticeBoard CASCADE;

create user c##noticeBoard identified by noticeBoard1 default tablespace users temporary tablespace temp profile default;
grant connect, resource to c##noticeBoard;
GRANT UNLIMITED TABLESPACE TO c##noticeBoard;
alter user c##noticeBoard account unlock;