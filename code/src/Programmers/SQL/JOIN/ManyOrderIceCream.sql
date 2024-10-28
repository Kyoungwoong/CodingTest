-- 코드를 입력하세요
-- SELECT FLAVOR
-- FROM (
--     SELECT F.FLAVOR, F.TOTAL_ORDER + J.TOTAL_ORDER AS TOTAL_ORDER
--     FROM FIRST_HALF F
--     JOIN (
--         SELECT FLAVOR, SUM(TOTAL_ORDER) AS TOTAL_ORDER
--         FROM JULY
--         GROUP BY FLAVOR
--     )J
--     ON F.FLAVOR = J.FLAVOR
--     ORDER BY TOTAL_ORDER DESC
--     FETCH NEXT 3 ROWS ONLY
-- )

-- 10/28
SELECT
    H.FLAVOR
FROM (
         SELECT FLAVOR, TOTAL_ORDER
         FROM FIRST_HALF
     ) H JOIN (
    SELECT FLAVOR, SUM(TOTAL_ORDER) AS TOTAL_ORDER
    FROM JULY
    GROUP BY FLAVOR
) J ON H.FLAVOR = J.FLAVOR
ORDER BY H.TOTAL_ORDER + J.TOTAL_ORDER DESC
    FETCH NEXT 3 ROWS ONLY










