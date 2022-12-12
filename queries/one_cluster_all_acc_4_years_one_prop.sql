-- Stop

SELECT A;

-- Relation Single Key

SET enable_seqscan = OFF;
DISCARD ALL;
EXPLAIN (ANALYZE ON, BUFFERS ON, SETTINGS ON, WAL ON, SUMMARY ON, TIMING ON, FORMAT JSON) SELECT
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
        parent.end_date < '2026-01-01'
ORDER BY parent.start_date, account.member_id;

-- Stop

SELECT A;

-- Relation Composite Key

SET enable_seqscan = OFF;
DISCARD ALL;
EXPLAIN (ANALYZE ON, BUFFERS ON, SETTINGS ON, WAL ON, SUMMARY ON, TIMING ON, FORMAT JSON) SELECT
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
        parent.end_date < '2026-01-01'
ORDER BY parent.start_date, account.member_id;

-- Stop

SELECT A;

-- Data Duplication

SET enable_seqscan = OFF;
DISCARD ALL;
EXPLAIN (ANALYZE ON, BUFFERS ON, SETTINGS ON, WAL ON, SUMMARY ON, TIMING ON, FORMAT JSON) SELECT
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
        dup.end_date < '2026-01-01'
ORDER BY dup.start_date, account.member_id;

-- Stop

SELECT A;

-- Data Duplication Extra

SET enable_seqscan = OFF;
DISCARD ALL;
EXPLAIN (ANALYZE ON, BUFFERS ON, SETTINGS ON, WAL ON, SUMMARY ON, TIMING ON, FORMAT JSON) SELECT
    dup.cluster_member_id AS cluster_member_id,
    dup.account_member_id AS account_member_id,
    dup.start_date,
    dup.end_date,
    dup.property,
    dup.percentage
FROM pp_dup_extra AS dup
WHERE
        dup.property = 'property1' AND
        dup.cluster_member_id = 1 AND
        dup.start_date > '2021-12-31' AND
        dup.end_date < '2026-01-01'
ORDER BY dup.start_date, dup.account_member_id;