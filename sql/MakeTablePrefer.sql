DROP TABLE PREFER;

CREATE TABLE PREFER (
    PREFER_ID NUMBER PRIMARY KEY,
    NOTICEBOARD_ID NUMBER,
    GOOD NUMBER DEFAULT 0,
    CONSTRAINT fk_PREFERTB_NOTICEBOARD_ID_FK FOREIGN KEY (NOTICEBOARD_ID) REFERENCES NOTICEBOARD(NOTICEBOARD_ID)
);

DROP SEQUENCE PREFERTB_PREFER_ID;
CREATE SEQUENCE PREFERTB_PREFER_ID;


DROP TRIGGER trg_create_prefer;
DROP TRIGGER trg_delete_prefer;

CREATE OR REPLACE TRIGGER trg_create_prefer
AFTER INSERT ON NOTICEBOARD
FOR EACH ROW
BEGIN
    INSERT INTO PREFER (PREFER_ID, NOTICEBOARD_ID)
    VALUES (PREFERTB_PREFER_ID.NEXTVAL, :NEW.NOTICEBOARD_ID);
END;
/

CREATE OR REPLACE TRIGGER trg_delete_prefer
AFTER DELETE ON NOTICEBOARD
FOR EACH ROW
BEGIN
    DELETE FROM PREFER
    WHERE NOTICEBOARD_ID = :OLD.NOTICEBOARD_ID;
END;
/



