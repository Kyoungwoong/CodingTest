-- 코드를 작성해주세요
-- 10/14
SELECT
    COUNT(*) AS COUNT
FROM ECOLI_DATA
WHERE GENOTYPE & 2 = 0 AND (GENOTYPE & 4 > 0 OR GENOTYPE & 1 > 0)

    # 1번 형질: GENOTYPE & 1 > 0 (첫 번째 비트)
    # 2번 형질: GENOTYPE & 2 > 0 (두 번째 비트)
    # 3번 형질: GENOTYPE & 4 > 0 (세 번째 비트)
    # 4번 형질: GENOTYPE & 8 > 0 (네 번째 비트)
