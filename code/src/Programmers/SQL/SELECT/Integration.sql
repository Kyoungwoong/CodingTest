-- 코드를 입력하세요
SELECT
    TO_CHAR(SALES_DATE, 'YYYY-MM-DD') AS SALES_DATE,
    PRODUCT_ID,
    USER_ID,
    SALES_AMOUNT
FROM(
    SELECT
        USER_ID,
        PRODUCT_ID,
        SALES_AMOUNT,
        SALES_DATE
    FROM ONLINE_SALE
    WHERE TO_CHAR(SALES_DATE, 'MM') = 03

    UNION

    SELECT
        NULL AS USER_ID,
        PRODUCT_ID,
        SALES_AMOUNT,
        SALES_DATE
    FROM OFFLINE_SALE
    WHERE TO_CHAR(SALES_DATE, 'MM') = 03
)
ORDER BY SALES_DATE, PRODUCT_ID, USER_ID

-- 10/9
SELECT
    A.SALES_DATE,
    A.PRODUCT_ID,
    A.USER_ID,
    A.SALES_AMOUNT
FROM    (
            SELECT
                TO_CHAR(SALES_DATE, 'YYYY-MM-DD') AS SALES_DATE,
                PRODUCT_ID,
                USER_ID,
                SALES_AMOUNT
            FROM ONLINE_SALE

            UNION

            SELECT
                TO_CHAR(SALES_DATE, 'YYYY-MM-DD') AS SALES_DATE,
                PRODUCT_ID,
                NULL as USER_ID,
                SALES_AMOUNT
            FROM OFFLINE_SALE
        ) A
WHERE A.SALES_DATE like '2022-03%'
ORDER BY
    A.SALES_DATE ASC,
    A.PRODUCT_ID ASC,
    A.USER_ID ASC









