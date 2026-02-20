select
    substr(p.product_code, 1, 2),
    count(*)
from
    product p
group by substr(p.product_code, 1, 2)
order by substr(p.product_code, 1, 2);