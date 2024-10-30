-- 코드를 작성해주세요
-- 10/25
SELECT
    COUNT(FISH_TYPE) AS FISH_COUNT,
    MAX(LENGTH) AS MAX_LENGTH,
    FISH_TYPE
FROM (
         SELECT
             ID,
             FISH_TYPE,
             IFNULL(LENGTH, 10) AS LENGTH
         FROM FISH_INFO
     ) F
GROUP BY FISH_TYPE
HAVING AVG(LENGTH) >= 33
ORDER BY FISH_TYPE ASC;