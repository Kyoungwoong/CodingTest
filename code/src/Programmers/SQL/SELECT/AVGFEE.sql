-- 코드를 입력하세요
SELECT
    -- ROUND(SUM(DAILY_FEE) / COUNT(*), 0) AS AVERAGE_FEE
    -- ROUND(AVG(DAILY_FEE), 0) AS AVERAGE_FEE
    AVG(DAILY_FEE)
FROM (
    SELECT CAR_TYPE, DAILY_FEE
    FROM CAR_RENTAL_COMPANY_CAR
    WHERE CAR_TYPE = 'SUV'
)
GROUP BY CAR_TYPE

SELECT round(avg(daily_fee) , 0)
FROM car_rental_company_car
GROUP BY car_type
HAVING lower(car_type) = 'suv'

-- 10/8 --
SELECT ROUND(AVG(DAILY_FEE), 0)
FROM CAR_RENTAL_COMPANY_CAR
WHERE CAR_TYPE = 'SUV';