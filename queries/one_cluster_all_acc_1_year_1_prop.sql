-- Stop

SELECT A;

-- Relation Single Key

EXPLAIN (FORMAT JSON, ANALYZE ON, BUFFERS ON, TIMING ON) SELECT
    cluster.member_id AS cluster_member_id,
    account.member_id AS account_member_id,
    parent.start_date,
    parent.end_date,
    child.property,
    child.percentage
FROM pp_rel_parent_single AS parent
         LEFT JOIN account ON parent.account_member_id = account.member_id
         LEFT JOIN cluster ON account.cluster_member_id = cluster.member_id
         LEFT JOIN pp_rel_child_single AS child ON child.parent_id = parent.id
WHERE
        child.property = 'property1' AND
        cluster.member_id = 1 AND
        parent.start_date > '2021-12-31' AND
        parent.end_date < '2023-01-01'
ORDER BY parent.start_date, account.member_id;

-- Stop

SELECT A;

--Relation Composite Key

EXPLAIN (FORMAT JSON, ANALYZE ON, BUFFERS ON, TIMING ON) SELECT
    cluster.member_id AS cluster_member_id,
    account.member_id AS account_member_id,
    parent.start_date,
    parent.end_date,
    child.property,
    child.percentage
FROM pp_rel_parent_composite AS parent
        LEFT JOIN account ON parent.account_member_id = account.member_id
        LEFT JOIN cluster ON account.cluster_member_id = cluster.member_id
        LEFT JOIN pp_rel_child_composite AS child ON child.parent_id = parent.id
WHERE
        child.property = 'property1' AND
        cluster.member_id = 1 AND
        parent.start_date > '2021-12-31' AND
        parent.end_date < '2023-01-01'
ORDER BY parent.start_date, account.member_id;

-- Stop

SELECT A;

-- Date Duplication

EXPLAIN (FORMAT JSON, ANALYZE ON, BUFFERS ON, TIMING ON) SELECT
    cluster.member_id AS cluster_member_id,
    account.member_id AS account_member_id,
    dup.start_date,
    dup.end_date,
    dup.property,
    dup.percentage
FROM pp_dup_single AS dup
         LEFT JOIN account ON dup.account_member_id = account.member_id
         LEFT JOIN cluster ON account.cluster_member_id = cluster.member_id
WHERE
        dup.property = 'property1' AND
        cluster.member_id = 1 AND
        dup.start_date > '2021-12-31' AND
        dup.end_date < '2023-01-01'
ORDER BY dup.start_date, account.member_id;
