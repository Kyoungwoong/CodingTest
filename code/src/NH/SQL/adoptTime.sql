WITH RECURSIVE hours(t) AS (
    SELECT 0
    UNION ALL
    SELECT t + 1
    FROM hours
    WHERE t < 23
)
SELECT
    h.t,
    COUNT(o.datetime) AS cnt
FROM hours h
         LEFT JOIN ANIMAL_OUTS o
                   ON h.t = HOUR(o.datetime)
GROUP BY h.t
ORDER BY h.t;
