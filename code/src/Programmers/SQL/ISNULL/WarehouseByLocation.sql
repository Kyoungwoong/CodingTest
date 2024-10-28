-- 코드를 입력하세요
-- SELECT
--     WAREHOUSE_ID,
--     WAREHOUSE_NAME ,
--     ADDRESS,
--     NVL(FREEZER_YN, 'N') AS FREEZER_YN
-- FROM FOOD_WAREHOUSE
-- WHERE WAREHOUSE_NAME LIKE '%경기%'
-- ORDER BY WAREHOUSE_ID

-- SELECT
--     warehouse_id ,
--     warehouse_name ,
--     address ,
--     CASE
--         WHEN freezer_yn = 'Y' THEN 'Y'
--         WHEN freezer_yn = 'N' THEN 'N'
--         WHEN freezer_yn is null THEN 'N'
--     END
-- FROM food_warehouse
-- WHERE warehouse_name LIKE '%경기%'
-- ORDER BY warehouse_id asc

-- 10/25
SELECT
    WAREHOUSE_ID,
    WAREHOUSE_NAME,
    ADDRESS,
    NVL(FREEZER_YN, 'N') AS FREEZER_YN
FROM FOOD_WAREHOUSE
WHERE ADDRESS LIKE '경기%'
ORDER BY WAREHOUSE_ID ASC









