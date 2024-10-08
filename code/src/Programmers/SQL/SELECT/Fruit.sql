-- 코드를 입력하세요
SELECT
    FLAVOR
FROM (
    SELECT A.FLAVOR, B.INGREDIENT_TYPE, A.TOTAL_ORDER
    FROM FIRST_HALF A, ICECREAM_INFO B
    WHERE A.TOTAL_ORDER >= 3000 AND A.FLAVOR = B.FLAVOR AND B.INGREDIENT_TYPE = 'fruit_based'
)
ORDER BY TOTAL_ORDER DESC

SELECT *
FROM
(
    SELECT
        a.flavor
    FROM
        first_half a ,
        icecream_info b
    WHERE
        b.flavor = a.flavor AND
        a.total_order >= 3000 AND
        b.ingredient_type = 'fruit_based'
)


-- 10/8
SELECT A.FLAVOR
FROM FIRST_HALF A JOIN ICECREAM_INFO B
                       ON A.FLAVOR = B.FLAVOR
WHERE A.total_order > 3000 and B.INGREDIENT_TYPE = 'fruit_based'
ORDER BY A.total_order DESC
