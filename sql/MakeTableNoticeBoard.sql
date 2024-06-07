DROP TABLE NOTICEBOARD;

CREATE TABLE NOTICEBOARD
(
    NOTICEBOARD_ID    NUMBER(10) NOT NULL,
    MANAGEMENT_ID    NUMBER(10) NOT NULL,
    TITLE    VARCHAR2(150) NOT NULL,
    CODE_ID    VARCHAR2(6) NOT NULL,
    NICKNAME    VARCHAR2(36) NOT NULL,
    BCONTENT    CLOB NOT NULL,
    HIT    NUMBER(10) NOT NULL,
    PREFERENCE_ID    NUMBER(10)  NOT NULL,
    CDATE    TIMESTAMP DEFAULT SYSDATE NOT NULL,
    UDATE    TIMESTAMP DEFAULT SYSDATE NOT NULL
);

COMMENT ON COLUMN NOTICEBOARD.NOTICEBOARD_ID IS '게시글번호';

COMMENT ON COLUMN NOTICEBOARD.MANAGEMENT_ID IS '내부관리용아이디';

COMMENT ON COLUMN NOTICEBOARD.TITLE IS '제목';

COMMENT ON COLUMN NOTICEBOARD.CODE_ID IS '게시글분류코드';

COMMENT ON COLUMN NOTICEBOARD.NICKNAME IS '작성자이름(별칭)';

COMMENT ON COLUMN NOTICEBOARD.BCONTENT IS '본문내용';

COMMENT ON COLUMN NOTICEBOARD.HIT IS '조회수';

COMMENT ON COLUMN NOTICEBOARD.CDATE IS '작성일';

COMMENT ON COLUMN NOTICEBOARD.UDATE IS '수정일';

COMMENT ON TABLE NOTICEBOARD IS '게시글';

CREATE UNIQUE INDEX NOTICEBOARD_PK ON NOTICEBOARD
( NOTICEBOARD_ID,MANAGEMENT_ID );

CREATE INDEX NOTICEBOARD_PK_IDX ON NOTICEBOARD (NOTICEBOARD_ID);

ALTER TABLE NOTICEBOARD
ADD CONSTRAINT NOTICEBOARD_PK PRIMARY KEY ( NOTICEBOARD_ID)
USING INDEX NOTICEBOARD_PK_IDX;


ALTER TABLE NOTICEBOARD
ADD CONSTRAINT NOTICEBOARDTB_MANAGEMENT_ID_FK
FOREIGN KEY (MANAGEMENT_ID)
REFERENCES MEMBER(MANAGEMENT_ID);

ALTER TABLE NOTICEBOARD
ADD CONSTRAINT NOTICEBOARDTB_CODE_ID_FK
FOREIGN KEY (CODE_ID)
REFERENCES CLASS_CD(CODE_ID);

DROP SEQUENCE NOTICEBOARDTB_NOTICEBOARD_ID;
CREATE SEQUENCE NOTICEBOARDTB_NOTICEBOARD_ID;

INSERT INTO NOTICEBOARD(NOTICEBOARD_ID,MANAGEMENT_ID,TITLE,CODE_ID,NICKNAME,BCONTENT,HIT,preference_id)
VALUES(NOTICEBOARDTB_NOTICEBOARD_ID.NEXTVAL,2,'SEKIRO','B0101','가나다','HARD',0,1);
