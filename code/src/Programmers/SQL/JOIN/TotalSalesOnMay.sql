-- -- 코드를 입력하세요
-- SELECT
--     FO.PRODUCT_ID,
--     FP.PRODUCT_NAME,
--     FP.PRICE * FO.AMOUNT AS TOTAL_SALES
-- FROM FOOD_PRODUCT FP, (
--     SELECT
--         PRODUCT_ID, SUM(AMOUNT) AS AMOUNT
--     FROM FOOD_ORDER
--     WHERE TO_CHAR(PRODUCE_DATE, 'YYYY-MM') = '2022-05'
--     GROUP BY PRODUCT_ID
-- )FO
-- WHERE FO.PRODUCT_ID = FP.PRODUCT_ID
-- ORDER BY TOTAL_SALES DESC, PRODUCT_ID

-- 10/28
SELECT
    P.PRODUCT_ID,
    P.PRODUCT_NAME,
    P.PRICE * O.AMOUNT AS TOTAL_SALES
FROM FOOD_PRODUCT P JOIN (
    SELECT PRODUCT_ID, SUM(AMOUNT) AS AMOUNT
    FROM FOOD_ORDER O
    WHERE TO_CHAR(PRODUCE_DATE, 'YYYY-MM') = '2022-05'
    GROUP BY PRODUCT_ID
) O ON P.PRODUCT_ID = O.PRODUCT_ID
ORDER BY TOTAL_SALES DESC, P.PRODUCT_ID ASC










