-- 코드를 입력하세요
SELECT
    FACTORY_ID, FACTORY_NAME, ADDRESS
FROM FOOD_FACTORY
WHERE ADDRESS LIKE '%강원%'
ORDER BY FACTORY_ID

-- 10/9
SELECT
    FACTORY_ID,
    FACTORY_NAME,
    ADDRESS
FROM FOOD_FACTORY
WHERE ADDRESS Like '강원%'
ORDER BY FACTORY_ID ASC








