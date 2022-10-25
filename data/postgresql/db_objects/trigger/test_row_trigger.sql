create table products (
	id serial primary key,
	name varchar(50),
	producer varchar(50),
	count integer default 0,
	price integer
);

create trigger discount_trigger
	after insert
	on products
	for each row
	execute procedure discount();


create or replace function discount()
	returns trigger as
$$
	begin
		update products
		set price = price - price * 0.2
		where count <= 5 and id = new.id;
		return new;
	end;
$$
language 'plpgsql';


insert into products (name, producer, count, price) values ('product_3', 'producer3', 8, 115);

insert into products (name, producer, count, price) values ('product1', 'producer_1', 3, 50);

alter table products disable trigger discount_trigger;

alter table products enable trigger discount_trigger;

drop trigger discount_trigger on products;

select * from products;