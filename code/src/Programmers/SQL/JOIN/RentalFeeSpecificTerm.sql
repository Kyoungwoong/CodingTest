-- 코드를 입력하세요
-- 10/28
--!
-- 자동차 종류가 '세단' 또는 'SUV' 인 자동차
-- 2022년 11월 1일부터 2022년 11월 30일까지 대여 가능
-- 30일간의 대여 금액이 50만원 이상 200만원 미만인 자동차
-- 자동차 ID, 자동차 종류, 대여 금액(컬럼명: FEE) 리스트를 출력하는 SQL문
SELECT
    DISTINCT H.CAR_ID,
             C.CAR_TYPE,
             C.DAILY_FEE * 30 * (1 - (DISCOUNT_RATE / 100)) AS FEE
FROM (
         SELECT CAR_ID
         FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
         GROUP BY CAR_ID
         HAVING MAX(TO_CHAR(END_DATE, 'YYYY-MM')) < '2022-11'
     ) H JOIN (
    SELECT CAR_ID, CAR_TYPE, DAILY_FEE
    FROM CAR_RENTAL_COMPANY_CAR C
    WHERE CAR_TYPE = '세단' OR CAR_TYPE = 'SUV'
) C ON H.CAR_ID = C.CAR_ID JOIN (
    SELECT CAR_TYPE, DURATION_TYPE, DISCOUNT_RATE
    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN
    WHERE (CAR_TYPE = '세단' OR CAR_TYPE = 'SUV') AND (DURATION_TYPE = '30일 이상')
) P ON C.CAR_TYPE = P.CAR_TYPE
WHERE C.DAILY_FEE * 30 * (1 - (DISCOUNT_RATE / 100)) BETWEEN 500000 AND 2000000
ORDER BY FEE DESC, C.CAR_TYPE ASC, H.CAR_ID DESC
