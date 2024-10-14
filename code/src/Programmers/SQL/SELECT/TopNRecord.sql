-- -- 코드를 입력하세요
-- SELECT
--     NAME
-- FROM (
--     SELECT *
--     FROM ANIMAL_INS
--     ORDER BY DATETIME
-- ) ANIMAL
-- -- WHERE ROWNUM = 1;
-- FETCH NEXT 1 ROWS ONLY

-- -- SELECT NAME
-- -- FROM (
-- --     SELECT *
-- --     FROM ANIMAL_INS
-- --     ORDER BY DATETIME
-- -- )
-- -- WHERE ROWNUM = 1;

-- 10/11
SELECT
    NAME
FROM (
         SELECT
             NAME
         FROM ANIMAL_INS
         ORDER BY DATETIME asc
     )
WHERE ROWNUM = 1;










