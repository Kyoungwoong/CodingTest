with recursive cte as (
    -- anchor
    select id, parent_id, 1 as generation
    from ecoli_data
    where parent_id is null

    union all

    -- recursive
    select c2.id, c2.parent_id, c1.generation+1 as generation
    from cte c1 inner join ecoli_data c2
        on c1.id = c2.parent_id
)

select
    *
from cte;