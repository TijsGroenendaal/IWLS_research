-- Stop

SELECT A;

-- Relation Single Key

SELECT pg_total_relation_size('pp_rel_parent_single') + pg_total_relation_size('pp_rel_child_single');

-- Stop

SELECT A;

-- Relation Composite Key

SELECT pg_total_relation_size('pp_rel_parent_composite') + pg_total_relation_size('pp_rel_child_composite');

-- Stop

SELECT A;

-- Data Duplication

SELECT pg_total_relation_size('pp_dup_single');