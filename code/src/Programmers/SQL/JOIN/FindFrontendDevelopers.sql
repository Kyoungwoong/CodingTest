-- 코드를 작성해주세요
-- 10/28
SELECT
    D.ID,
    D.EMAIL,
    D.FIRST_NAME,
    D.LAST_NAME
FROM
    DEVELOPERS D
WHERE
    EXISTS (
            SELECT 1
            FROM SKILLCODES S
            WHERE S.CATEGORY = 'FRONT END'
              AND (D.SKILL_CODE & S.CODE) <> 0
        )
ORDER BY
    D.ID ASC;

