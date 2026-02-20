select
    u.user_id,
    u.nickname,
    r.total_sales
from
    used_goods_user u inner join (
        SELECT
            b.writer_id,
            sum(b.price) as total_sales
        FROM
            USED_GOODS_BOARD b
        where
            b.status = 'DONE'
        group by b.writer_id
        having sum(b.price) >= 700000
    ) r
    on u.user_id = r.writer_id
order by r.total_sales asc