-- 코드를 입력하세요
-- SELECT
--     ANIMAL.ANIMAL_ID, ANIMAL.NAME
-- FROM (
--     SELECT *
--     FROM ANIMAL_INS A, ANIMAL_OUTS B
--     WHERE A.ANIMAL_ID = B.ANIMAL_ID
--     ORDER BY (B.DATETIME - A.DATETIME) DESC
-- )ANIMAL
-- WHERE ROWNUM = 1 OR ROWNUM = 2

-- SELECT
--     *
-- FROM
--     (
--         SELECT
--             a.animal_id ,
--             a.name
--         FROM
--             animal_ins a ,
--             animal_outs b
--         WHERE
--             a.animal_id = b.animal_id
--         ORDER BY
--             (b.datetime - a.datetime) desc
--     )
-- WHERE ROWNUM = 1 OR ROWNUM = 2

-- SELECT
--     I.ANIMAL_ID,
--     I.NAME
-- FROM ANIMAL_INS I
-- JOIN ANIMAL_OUTS O
-- ON I.ANIMAL_ID = O.ANIMAL_ID
-- ORDER BY O.DATETIME - I.DATETIME DESC
-- FETCH NEXT 2 ROWS ONLY

-- 11/01
SELECT ANIMAL_ID, NAME
FROM (
         SELECT
             INS.ANIMAL_ID,
             INS.NAME,
             (OUTS.DATETIME - INS.DATETIME) AS PROTECTION_DAYS
         FROM
             ANIMAL_INS INS
                 JOIN ANIMAL_OUTS OUTS ON INS.ANIMAL_ID = OUTS.ANIMAL_ID
         ORDER BY
             PROTECTION_DAYS DESC
     )
WHERE ROWNUM <= 2;