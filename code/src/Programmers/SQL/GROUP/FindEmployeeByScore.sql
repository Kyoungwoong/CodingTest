-- 코드를 작성해주세요
-- 10/24
SELECT
    G.SCORE,
    G.EMP_NO,
    E.EMP_NAME,
    E.POSITION,
    E.EMAIL
FROM (
         SELECT
             EMP_NO,
             SUM(SCORE) AS SCORE
         FROM HR_GRADE
         GROUP BY EMP_NO, YEAR
         ORDER BY SCORE DESC
             LIMIT 1
     ) G
         JOIN HR_EMPLOYEES E ON G.EMP_NO = E.EMP_NO;