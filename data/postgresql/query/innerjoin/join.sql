create table purchase (
	id serial primary key,
	purchase_details text not null,
	total_sum decimal not null
);

create table customer (
	id serial primary key,
	first_name text not null,
	address text not null,
	purchase_id int references purchase(id) unique
);

insert into purchase(purchase_details, total_sum) values ('одежда', 15500.75);
insert into purchase(purchase_details, total_sum) values ('конструктор', 5750.33);
insert into purchase(purchase_details, total_sum) values ('электроника', 67885.25);

insert into customer(first_name, address, purchase_id) values ('Иван', 'Москва', 3);
insert into customer(first_name, address, purchase_id) values ('Аня', 'Питер', 1);
insert into customer(first_name, address, purchase_id) values ('Андрей', 'Ростов', 2);

select * from customer
inner join purchase on customer.purchase_id = purchase.id;

select c.first_name, c.address, p.purchase_details, p.total_sum
from customer as c
inner join purchase as p on c.purchase_id = p.id;

select c.first_name as Имя, c.address as Адрес, p.purchase_details as Покупки, p.total_sum as Стоимость_в_руб
from customer as c
inner join purchase as p on c.purchase_id = p.id;