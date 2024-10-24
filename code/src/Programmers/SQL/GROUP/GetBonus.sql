-- 코드를 작성해주세요
-- 10/24
SELECT
    G.EMP_NO,
    E.EMP_NAME,
    CASE
        WHEN G.SCORE / 2 >= 96 THEN 'S'
        WHEN G.SCORE / 2 >= 90 THEN 'A'
        WHEN G.SCORE / 2 >= 80 THEN 'B'
        ELSE 'C'
        END AS GRADE,
    E.SAL * CASE
                WHEN G.SCORE / 2 >= 96 THEN 0.2
                WHEN G.SCORE / 2 >= 90 THEN 0.15
                WHEN G.SCORE / 2 >= 80 THEN 0.1
                ELSE 0
        END AS BONUS
FROM (
         SELECT
             EMP_NO,
             SUM(SCORE) AS SCORE
         FROM HR_GRADE
         GROUP BY EMP_NO, YEAR
         ORDER BY SCORE DESC
     ) G
         JOIN HR_EMPLOYEES E ON G.EMP_NO = E.EMP_NO
ORDER BY G.EMP_NO;
