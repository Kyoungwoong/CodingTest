-- 코드를 입력하세요
-- SELECT
--     SUBSTR(PRODUCT_CODE, 1, 2) AS CATEGORY,
--     COUNT(PRODUCT_CODE) AS PRODUCTS
-- FROM PRODUCT
-- GROUP BY SUBSTR(PRODUCT_CODE, 1, 2)
-- ORDER BY CATEGORY

-- SELECT
--     category , count(category)
-- FROM
--     (
--         SELECT substr(product_code,1,2) AS category
--         FROM product
--     )
-- GROUP BY category
-- ORDER BY category

-- SELECT SUBSTR(PRODUCT_CODE, 0, 2) AS CATEGORY, COUNT(*)
-- FROM PRODUCT
-- GROUP BY SUBSTR(PRODUCT_CODE, 0, 2)
-- ORDER BY CATEGORY

-- 11/01
SELECT
    SUBSTR(PRODUCT_CODE, 0, 2) AS CATEGORY,
    COUNT(*)
FROM PRODUCT
GROUP BY SUBSTR(PRODUCT_CODE, 0, 2)
ORDER BY CATEGORY