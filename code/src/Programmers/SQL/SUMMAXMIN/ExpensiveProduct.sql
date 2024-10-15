-- 코드를 입력하세요
-- SELECT *
-- FROM (
--     SELECT *
--     FROM FOOD_PRODUCT
--     ORDER BY PRICE DESC
-- )PRODUCT
-- -- WHERE ROWNUM = 1
-- FETCH NEXT 1 ROWS ONLY

-- SELECT *
-- FROM FOOD_PRODUCT
-- ORDER BY PRICE DESC
-- FETCH NEXT 1 ROWS ONLY

-- SELECT *
-- FROM FOOD_PRODUCT
-- WHERE MAX(PRICE) = PRICE --group function is not allowed here


-- SELECT *
-- FROM food_product
-- WHERE
--     price =
-- (
--     SELECT max(price)
--     FROM food_product
-- )

-- 10/15
SELECT
    A.PRODUCT_ID,
    A.PRODUCT_NAME,
    A.PRODUCT_CD,
    A.CATEGORY,
    A.PRICE
FROM FOOD_PRODUCT A, (
    SELECT MAX(PRICE) AS MAX_PRICE
    FROM FOOD_PRODUCT
) B
WHERE A.PRICE = B.MAX_PRICE;