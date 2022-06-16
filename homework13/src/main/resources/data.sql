INSERT INTO AUTHOR(NAME) VALUES('ANTON CHEHOV');
INSERT INTO AUTHOR(NAME) VALUES('JACK LONDON');

INSERT INTO GENRE(NAME) VALUES('Romantic literature');
INSERT INTO GENRE(NAME) VALUES('Adventure literature');

INSERT INTO BOOK(NAME, AUTHOR_ID, GENRE_ID)
    SELECT
        'Martin Eden'
        , CURRENT VALUE FOR SEC_AUTHOR
        , CURRENT VALUE FOR SEC_GENRE;

INSERT INTO BOOK(NAME, AUTHOR_ID, GENRE_ID)
    SELECT
        'The Little Lady'
        , CURRENT VALUE FOR SEC_AUTHOR
        , CURRENT VALUE FOR SEC_GENRE;

INSERT INTO COMMENT(NAME, BOOK_ID)
    SELECT
        'COMMENT-1'
        , CURRENT VALUE FOR SEC_BOOK
UNION ALL
    SELECT
        'COMMENT-2'
        , CURRENT VALUE FOR SEC_BOOK
UNION ALL
    SELECT
        'COMMENT-3'
        , 1
UNION ALL
    SELECT
        'COMMENT-4'
        , 1;

INSERT INTO READER(ID, FULLNAME, LOGIN, "PASSWORD", "ROLE")
VALUES(0, 'JOHN DOE', 'DOE', 'DOE', 'ADMIN');

INSERT INTO READER(ID, FULLNAME, LOGIN, "PASSWORD", "ROLE")
VALUES(1, 'MR SMITH', 'SMITH', 'SMITH', 'USER');
