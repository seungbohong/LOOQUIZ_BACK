show databases;
create database lookie default character set utf8;
use lookie;

select * from member;
select * from board;

drop table member;
drop table region;
drop table hint;
drop table quiz;

create table member(
   uid varchar(20) not null primary KEY,
    pw varchar(20) not null,
    uname varchar(20) not null,
    email varchar(40) not null
);

create table region(
   rname varchar(20) not null,
    rid int(20) not null auto_increment primary KEY,
    cityname varchar(30) not null
);

create table quiz(
   uid varchar(20) not null references member(uid) on delete cascade,
    qid int(20) not null auto_increment primary KEY, 
    dname varchar(20) not null,
    qname varchar(50) not null,
    qcontent1 varchar(20) not null,
    qcontent2 varchar(20) not null,
    qcontent3 varchar(20) not null,
    qcontent4 varchar(20) not null,
    qcontent5 varchar(20) not null,
    rname varchar(20) not null references region(rname) on delete cascade,
    hcontent varchar(50) not null,
    codenum varchar(20) references quizroom(codenum)
);


create table participation(
   uid varchar(20) not null references member(uid) on delete cascade,
    qid int(20) not null references quiz(qid) on delete cascade
);

create table quizRoom(
   uid varchar(20) not null references member(uid) on delete cascade,
    qrname varchar(20) not null,
    codenum varchar(30) not null primary key,
    endtime datetime
);

create table roomUser(
   uid varchar(20) not null references member(uid) on delete cascade,
    codenum varchar(30) not null references quizRoom(codenum) on delete cascade
);

create table storeList(
   sname varchar(20) not null,
   rname varchar(20) not null references region(rname) on delete cascade,
    bid int(20) not null references badge(bid) on delete cascade
);

create table badge(
   bid int(20) not null auto_increment primary KEY, 
   rname varchar(20) not null references region(rname) on delete cascade,
    img_url long
);

create table userBadge(
   uid varchar(20) not null references member(id) on delete cascade,
    bid int(20) not null references badge(bid) on delete cascade
);