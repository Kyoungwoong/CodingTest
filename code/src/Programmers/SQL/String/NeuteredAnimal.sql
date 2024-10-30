-- 코드를 입력하세요
-- SELECT
--     ANIMAL_ID,
--     NAME,
--     CASE WHEN SEX_UPON_INTAKE LIKE '%Neutered%' THEN 'O'
--          WHEN SEX_UPON_INTAKE LIKE '%Spayed%' THEN 'O'
--          ELSE 'X'
--     END AS "중성화"
-- FROM ANIMAL_INS
-- ORDER BY ANIMAL_ID

-- -- SELECT
-- --     animal_id ,
-- --     name ,
-- --     CASE
-- --         WHEN sex_upon_intake LIKE 'Neutered%' Then 'O'
-- --         WHEN sex_upon_intake LIKE 'Spayed%' Then 'O'
-- --         WHEN sex_upon_intake LIKE 'Intact%' THEN 'X'
-- --     END
-- -- FROM
-- --     animal_ins
-- -- ORDER BY
-- --     animal_id

-- SELECT ANIMAL_ID, NAME,
--     CASE WHEN SEX_UPON_INTAKE LIKE '%Neutered%' THEN 'O'
--          WHEN SEX_UPON_INTAKE LIKE '%Spayed%' THEN 'O'
--          ELSE 'X'
--     END AS "중성화"
-- FROM ANIAML_INS
-- ORDER BY ANIMAL_ID

-- 10/30
SELECT
    ANIMAL_ID,
    NAME,
    CASE
        WHEN SEX_UPON_INTAKE LIKE 'Neutered%' Then 'O'
        WHEN SEX_UPON_INTAKE LIKE 'Spayed%' Then 'O'
        WHEN SEX_UPON_INTAKE LIKE 'Intact%' THEN 'X'
        END AS "중성화"
FROM ANIMAL_INS
ORDER BY ANIMAL_ID














