select
    c2.id as id,
    c2.genotype as genotype,
    c1.genotype as parent_genotype
from
    ecoli_data c1 inner join ecoli_data c2
    on c1.id = c2.parent_id
where (c1.genotype & c2.genotype) = c1.genotype
order by c1.id
;