-- 코드를 입력하세요
-- SELECT
--     I.ANIMAL_ID, I.ANIMAL_TYPE, I.NAME
-- FROM ANIMAL_INS I
-- JOIN ANIMAL_OUTS O
-- ON I.ANIMAL_ID = O.ANIMAL_ID
-- WHERE I.SEX_UPON_INTAKE LIKE 'Intact%' AND (O.SEX_UPON_OUTCOME LIKE 'Spayed%' OR O.SEX_UPON_OUTCOME LIKE 'Neutered%')
-- ORDER BY I.ANIMAL_ID

-- SELECT I.ANIMAL_ID, I.ANIMAL_TYPE, I.NAME
-- FROM ANIMAL_INS I
-- WHERE I.SEX_UPON_INTAKE LIKE 'Intact%' AND I.ANIMAL_ID IN(
--     SELECT ANIMAL_ID
--     FROM ANIMAL_OUTS
--     WHERE SEX_UPON_OUTCOME LIKE 'Spayed%' OR SEX_UPON_OUTCOME LIKE 'Neutered%'
-- )
-- ORDER BY I.ANIMAL_ID

-- 10/28
SELECT
    I.ANIMAL_ID,
    I.ANIMAL_TYPE,
    I.NAME
FROM ANIMAL_INS I JOIN ANIMAL_OUTS O ON I.ANIMAL_ID = O.ANIMAL_ID
WHERE I.SEX_UPON_INTAKE LIKE 'Intact%'
  AND (O.SEX_UPON_OUTCOME LIKE 'Spayed%' OR O.SEX_UPON_OUTCOME LIKE 'Neutered%')
ORDER BY I.ANIMAL_ID









