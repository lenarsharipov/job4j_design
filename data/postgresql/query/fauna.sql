create table fauna (
	id serial primary key,
	name text,
	avg_age int,
	discovery_date date
);

insert into fauna (name, avg_age, discovery_date) values ('сумчатый волк', 5475, '1792-05-13');
insert into fauna (name, avg_age, discovery_date) values ('псефур (рыба)', 10000, '1862-01-01');
insert into fauna (name, avg_age, discovery_date) values ('кошка', 5500, null);
insert into fauna (name, avg_age, discovery_date) values ('морской конек-малютка', 1100, '2008-01-01');
insert into fauna (name, avg_age, discovery_date) values ('слон', 20000, null);

select * from fauna
where name like '%рыба' or name like 'рыба%' or name like '%рыба%';

select * from fauna
where avg_age between 10000 and 21000;

select * from fauna
where discovery_date is null;

select * from fauna
where discovery_date < '1950-01-01';







