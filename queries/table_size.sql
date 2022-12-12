-- Stop

SELECT A;

-- Relation Single Key

SELECT
       pg_total_relation_size('pp_rel_parent_single')
           + pg_total_relation_size('pp_rel_child_single')
           + pg_total_relation_size('account')
           + pg_total_relation_size('cluster'),
       pg_table_size('pp_rel_parent_single')
           + pg_table_size('pp_rel_child_single')
           + pg_table_size('account')
           + pg_table_size('cluster'),
       pg_relation_size('pp_rel_parent_single')
           + pg_relation_size('pp_rel_child_single')
           + pg_relation_size('account')
           + pg_relation_size('cluster');

-- Stop

SELECT A;

-- Relation Composite Key

SELECT
       pg_total_relation_size('pp_rel_parent_composite')
           + pg_total_relation_size('pp_rel_child_composite')
           + pg_total_relation_size('account')
           + pg_total_relation_size('cluster'),
       pg_table_size('pp_rel_parent_composite')
           + pg_table_size('pp_rel_child_composite')
           + pg_table_size('account')
           + pg_table_size('cluster'),
       pg_relation_size('pp_rel_parent_composite')
           + pg_relation_size('pp_rel_child_composite')
           + pg_relation_size('account')
           + pg_relation_size('cluster');

-- Stop

SELECT A;

-- Data Duplication

SELECT
       pg_total_relation_size('pp_dup_single')
           + pg_total_relation_size('account')
           + pg_total_relation_size('cluster'),
       pg_table_size('pp_dup_single')
           + pg_table_size('account')
           + pg_table_size('cluster'),
       pg_relation_size('pp_dup_single')
           + pg_relation_size('account')
           + pg_relation_size('cluster');

-- Stop

SELECT A;

-- Data Duplication Extra

SELECT
        pg_total_relation_size('pp_dup_extra'),
        pg_table_size('pp_dup_extra'),
        pg_relation_size('pp_dup_extra');
