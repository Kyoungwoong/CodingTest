select
    id,
    case
        when ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) / COUNT(*) OVER () <= 0.25
            then 'CRITICAL'
        when ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) / COUNT(*) OVER () <= 0.5
            then 'HIGH'
        when ROW_NUMBER() OVER (ORDER BY SIZE_OF_COLONY DESC) / COUNT(*) OVER () <= 0.75
            then 'MEDIUM'
        ELSE 'LOW'
end AS colony_name
from ecoli_data
order by id;