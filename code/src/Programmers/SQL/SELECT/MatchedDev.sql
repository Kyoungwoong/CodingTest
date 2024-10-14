-- 코드를 작성해주세요
-- 10/11
# SELECT
          #     *
          # FROM (
                     #     SELECT
#         ID,
#         EMAIL,
#         FIRST_NAME,
#         LAST_NAME
#     FROM DEVELOPERS
#     WHERE SKILL_CODE % 1024 = 0 OR SKILL_CODE % 256 = 0
# ) R
      # ORDER BY R.ID ASC

SELECT DISTINCT D.ID, D.EMAIL, D.FIRST_NAME, D.LAST_NAME
FROM DEVELOPERS D
         JOIN SKILLCODES S ON (D.SKILL_CODE & S.CODE) > 0
WHERE S.NAME IN ('Python', 'C#')
ORDER BY D.ID ASC;
