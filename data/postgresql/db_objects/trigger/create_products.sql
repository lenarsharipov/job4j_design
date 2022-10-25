drop table if exists products cascade;

create table products (
	id serial primary key,
	name varchar(50),
	producer varchar(50),
	count integer default 0,
	price integer
);

drop view if exists products_view;
create view products_view as
    select p.name, p.producer, p.count, p.price from products p;