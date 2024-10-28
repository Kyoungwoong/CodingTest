-- 코드를 입력하세요
-- SELECT
--     A.ANIMAL_ID,
--     A.NAME
-- FROM ANIMAL_INS A,
--     ANIMAL_OUTS B
-- WHERE (A.ANIMAL_ID = B.ANIMAL_ID) AND (A.DATETIME > B.DATETIME)
-- ORDER BY A.DATETIME

-- SELECT
--     A.ANIMAL_ID,
--     A.NAME
-- FROM ANIMAL_INS A,
-- JOIN ANIMAL_OUTS B
-- ON (A.ANIMAL_ID = B.ANIMAL_ID)
-- WHERE (A.DATETIME > B.DATETIME)
-- ORDER BY A.DATETIME;

-- SELECT I.ANIMAL_ID, I.NAME
-- FROM ANIMAL_INS I JOIN ANIMAL_OUTS O ON I.ANIMAL_ID = O.ANIMAL_ID
-- WHERE I.DATETIME > O.DATETIME
-- ORDER BY I.DATETIME

-- 10/28
SELECT
    I.ANIMAL_ID,
    I.NAME
FROM ANIMAL_INS I JOIN ANIMAL_OUTS O ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE I.DATETIME > O.DATETIME
ORDER BY I.DATETIME ASC









