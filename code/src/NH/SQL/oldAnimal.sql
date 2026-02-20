SELECT
    i.name,
    i.datetime
FROM
    ANIMAL_INS i
where
    i.animal_id not in (
        SELECT
            o.ANIMAL_ID
        FROM
            ANIMAL_OUTS o
        )
order by i.datetime asc
LIMIT 3