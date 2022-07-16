drop sequence IF EXISTS SEC_AUTHOR;
drop sequence IF EXISTS SEC_GENRE;
drop sequence IF EXISTS SEC_BOOK;
drop sequence IF EXISTS SEC_COMMENT;
drop table IF EXISTS AUTHOR;
drop table IF EXISTS GENRE;
drop table IF EXISTS BOOK;
drop table IF EXISTS comment;

create sequence SEC_AUTHOR start with 1;
create TABLE AUTHOR(
        ID BIGINT DEFAULT NEXT VALUE FOR SEC_AUTHOR PRIMARY KEY
        , NAME VARCHAR(255)
        );

create sequence SEC_GENRE start with 1;
create TABLE GENRE(
        ID BIGINT DEFAULT NEXT VALUE FOR SEC_GENRE PRIMARY KEY
        , NAME VARCHAR(255)
        );

create sequence SEC_BOOK start with 1;
create TABLE BOOK(
        ID BIGINT DEFAULT NEXT VALUE FOR SEC_BOOK PRIMARY KEY
        , NAME VARCHAR(255)
        , AUTHOR_ID INT NOT NULL
        , GENRE_ID INT NOT NULL
        , FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR(ID)
        , FOREIGN KEY (GENRE_ID) REFERENCES GENRE(ID)
        );

create sequence SEC_COMMENT start with 1;
create TABLE comment(
        ID BIGINT DEFAULT NEXT VALUE FOR SEC_COMMENT PRIMARY KEY
        , NAME VARCHAR(255)
        , BOOK_ID INT NOT NULL
        , FOREIGN KEY (BOOK_ID) REFERENCES BOOK(ID)
        );

