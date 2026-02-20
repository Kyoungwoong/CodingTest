select
    p.id,
    p.name,
    p.host_id
from
    places p
where p.id in (
        select
            p.host_id
        from
            places p
        group by p.host_id
        having count(*) >= 2
    )
order by p.id