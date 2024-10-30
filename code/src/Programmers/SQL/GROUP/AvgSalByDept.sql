-- 코드를 작성해주세요
-- 10/25
SELECT
    E.DEPT_ID,
    D.DEPT_NAME_EN,
    E.AVG_SAL
FROM HR_DEPARTMENT D JOIN (
    SELECT
        DEPT_ID,
        ROUND(AVG(SAL)) AS AVG_SAL
    FROM HR_EMPLOYEES
    GROUP BY DEPT_ID
) E ON D.DEPT_ID = E.DEPT_ID
ORDER BY E.AVG_SAL DESC