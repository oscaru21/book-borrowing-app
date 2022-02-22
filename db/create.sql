create table test(id bigint auto_increment, name varchar(255));
insert into test(name) values('hello');
insert into test(name) values('world');
select * from test; 

CREATE TABLE BOOK
(
  ID    INT NOT NULL AUTO_INCREMENT,
  TITLE VARCHAR(255) NOT NULL,
  STOCK INT NOT NULL,
  PRIMARY KEY (ID)
);

CREATE TABLE USER
(
  ID INT NOT NULL AUTO_INCREMENT,
  NAME     VARCHAR(255) NOT NULL,
  EMAIL    VARCHAR(255)     NULL,
  PASSWORD VARCHAR(255)     NULL,
  PHONE    VARCHAR(255)     NULL,
  PRIMARY KEY (ID)
);