-- 코드를 입력하세요
-- SELECT USER_ID, NICKNAME, CITY ||' '|| STREET_ADDRESS1 || ' ' || STREET_ADDRESS2, SUBSTR(TLNO, 0, 3) || '-' || SUBSTR(TLNO, 4, 4) || '-' || SUBSTR(TLNO, 8, 4)
-- FROM USED_GOODS_USER U JOIN (
--     SELECT WRITER_ID
--     FROM USED_GOODS_BOARD
--     GROUP BY WRITER_ID
--     HAVING COUNT(*) >= 3
-- ) B ON U.USER_ID = B.WRITER_ID
-- ORDER BY USER_ID DESC

-- 10/30
SELECT
    U.USER_ID,
    U.NICKNAME,
    CONCAT(CONCAT(CONCAT(CONCAT(CITY, ' '), STREET_ADDRESS1), ' '), STREET_ADDRESS2) AS 전체주소,
    CONCAT(CONCAT(CONCAT(SUBSTR(TLNO,0, 3), '-'), CONCAT(SUBSTR(TLNO, 4, 4), '-')), SUBSTR(TLNO, 8)) AS 전화번호
FROM USED_GOODS_USER U JOIN (
    SELECT
        WRITER_ID
    FROM USED_GOODS_BOARD
    GROUP BY WRITER_ID
    HAVING COUNT(*) >= 3
) B ON U.USER_ID = B.WRITER_ID
ORDER BY U.USER_ID DESC















