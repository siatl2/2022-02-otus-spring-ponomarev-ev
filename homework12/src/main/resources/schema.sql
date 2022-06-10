DROP SEQUENCE IF EXISTS SEC_AUTHOR;
DROP SEQUENCE IF EXISTS SEC_GENRE;
DROP SEQUENCE IF EXISTS SEC_BOOK;
DROP SEQUENCE IF EXISTS SEC_COMMENT;
DROP TABLE IF EXISTS AUTHOR;
DROP TABLE IF EXISTS GENRE;
DROP TABLE IF EXISTS BOOK;
DROP TABLE IF EXISTS COMMENT;

CREATE SEQUENCE SEC_AUTHOR START WITH 1;
CREATE TABLE AUTHOR(
        ID BIGINT DEFAULT NEXT VALUE FOR SEC_AUTHOR PRIMARY KEY
        , NAME VARCHAR(255)
        );

CREATE SEQUENCE SEC_GENRE START WITH 1;
CREATE TABLE GENRE(
        ID BIGINT DEFAULT NEXT VALUE FOR SEC_GENRE PRIMARY KEY
        , NAME VARCHAR(255)
        );

CREATE SEQUENCE SEC_BOOK START WITH 1;
CREATE TABLE BOOK(
        ID BIGINT DEFAULT NEXT VALUE FOR SEC_BOOK PRIMARY KEY
        , NAME VARCHAR(255)
        , AUTHOR_ID INT NOT NULL
        , GENRE_ID INT NOT NULL
        , FOREIGN KEY (AUTHOR_ID) REFERENCES AUTHOR(ID) ON DELETE CASCADE
        , FOREIGN KEY (GENRE_ID) REFERENCES GENRE(ID) ON DELETE CASCADE
        );

CREATE SEQUENCE SEC_COMMENT START WITH 1;
CREATE TABLE COMMENT(
        ID BIGINT DEFAULT NEXT VALUE FOR SEC_COMMENT PRIMARY KEY
        , NAME VARCHAR(255)
        , BOOK_ID INT NOT NULL
        , FOREIGN KEY (BOOK_ID) REFERENCES BOOK(ID) ON DELETE CASCADE
        );

CREATE TABLE READER(
        ID BIGINT PRIMARY KEY
        , FULLNAME VARCHAR(255)
        , LOGIN VARCHAR(20)
        , "PASSWORD" VARCHAR(20)
)
