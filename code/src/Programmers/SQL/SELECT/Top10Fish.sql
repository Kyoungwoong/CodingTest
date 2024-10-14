-- 코드를 작성해주세요
-- 10/14
SELECT ID, IFNULL(LENGTH, 10) AS LENGTH
FROM (
         SELECT ID, LENGTH, ROW_NUMBER() OVER (ORDER BY LENGTH DESC, ID ASC) AS ROWNUM
         FROM FISH_INFO
     ) AS temp
WHERE ROWNUM <= 10
ORDER BY LENGTH DESC, ID ASC;