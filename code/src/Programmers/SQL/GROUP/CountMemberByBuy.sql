-- -- 코드를 입력하세요
-- SELECT
--     TO_CHAR(D.SALES_DATE, 'YYYY') AS YEAR,
--     TO_NUMBER(TO_CHAR(D.SALES_DATE, 'MM')) AS MONTH,
--     D.GENDER AS GENDER,
--     COUNT(DISTINCT D.USER_ID) AS USERS
-- FROM(
--     SELECT
--         U.GENDER,
--         O.USER_ID,
--         O.SALES_DATE
--     FROM USER_INFO U, ONLINE_SALE O
--     WHERE U.USER_ID = O.USER_ID AND U.GENDER IS NOT NULL
-- )D
-- GROUP BY TO_CHAR(D.SALES_DATE, 'YYYY'), TO_CHAR(D.SALES_DATE, 'MM'), D.GENDER
-- ORDER BY YEAR, MONTH, GENDER

-- SELECT a.year, a.month, b.gender, count(distinct b.user_id) as users
-- FROM (SELECT user_id
--       ,to_char(sales_date, 'YYYY') as year
--       ,to_number(to_char(sales_date, 'MM')) as month
--       FROM online_sale
--      ) a,
--      (SELECT user_id, gender
--      FROM user_info
--      where gender is not null)b
-- WHERE a.user_id = b.user_id
-- group by a.year, a.month, b.gender
-- order by a.year, a.month , b.gender;

-- 10/24
SELECT
    TO_CHAR(O.SALES_DATE, 'YYYY') AS YEAR,
    EXTRACT(MONTH FROM O.SALES_DATE) AS MONTH,
    U.GENDER AS GENDER,
    COUNT(DISTINCT U.USER_ID) AS USERS
FROM USER_INFO U JOIN ONLINE_SALE O
ON U.USER_ID = O.USER_ID
WHERE U.GENDER IS NOT NULL
GROUP BY TO_CHAR(O.SALES_DATE, 'YYYY'), EXTRACT(MONTH FROM O.SALES_DATE), U.GENDER
ORDER BY TO_CHAR(O.SALES_DATE, 'YYYY'), EXTRACT(MONTH FROM O.SALES_DATE), U.GENDER