
-- 1. Create Table // AUTO_INCREMENT -> SERIAL for Postgres 
create table viewers_stats
(
    id         SERIAL       PRIMARY KEY,
    account_id BIGINT       NOT NULL,
    region     VARCHAR(256) NOT NULL,
    created    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP
)
-- 2. Create Indexes for the Where and Aggregation
create index viewers_stats_created_idx on viewers_stats (created);
create index viewers_stats_account_idx on viewers_stats (account_id);

-- 3. Query Five regions with biggest numbers of unique views for the last month sorted descended 
select region, count(distinct account_id) 
from viewers_stats
where created > date_trunc('day', now() - interval '1 month')
group by region
order by count(distinct account_id) desc
limit 5;


-- 4. Show Indexes:

select tablename, indexname, indexdef
from pg_indexes
where schemaname = 'public'
order by tablename, indexname;

-- 5. Init Data

insert into viewers_stats (account_id, region, created) values (111, 'Region1', timestamptz '2022-03-06');
insert into viewers_stats (account_id, region, created) values (222, 'Region1', timestamptz '2022-03-06');
insert into viewers_stats (account_id, region, created) values (333, 'Region1', timestamptz '2022-03-06');

insert into viewers_stats (account_id, region, created) values (111, 'Region1', timestamptz '2022-04-06');
insert into viewers_stats (account_id, region, created) values (111, 'Region2', timestamptz '2022-04-06');
insert into viewers_stats (account_id, region, created) values (111, 'Region3', timestamptz '2022-04-06');

insert into viewers_stats (account_id, region, created) values (222, 'Region1', timestamptz '2022-04-06');
insert into viewers_stats (account_id, region, created) values (222, 'Region2', timestamptz '2022-04-06');

insert into viewers_stats (account_id, region, created) values (333, 'Region3', timestamptz '2022-04-06');
insert into viewers_stats (account_id, region, created) values (444, 'Region3', timestamptz '2022-04-06');

insert into viewers_stats (account_id, region, created) values (333, 'Region4', timestamptz '2022-04-06');

insert into viewers_stats (account_id, region, created) values (444, 'Region5', timestamptz '2022-04-06');
insert into viewers_stats (account_id, region, created) values (666, 'Region5', timestamptz '2022-04-06');
insert into viewers_stats (account_id, region, created) values (777, 'Region5', timestamptz '2022-04-06');
insert into viewers_stats (account_id, region, created) values (888, 'Region5', timestamptz '2022-04-06');

insert into viewers_stats (account_id, region, created) values (444, 'Region5', timestamptz '2022-04-06');

insert into viewers_stats (account_id, region, created) values (111, 'Region6', timestamptz '2022-04-06');

-- 6. Query All

select * from viewers_stats;
