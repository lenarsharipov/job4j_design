create table type (
	id serial primary key,
	name text
);

create table product (
	id serial primary key,
	name text,
	expired_date date,
	price float,
	type_id int references type(id)
);

insert into type(name) values ('СЫР'), ('МОЛОКО'), ('КОЛБАСА'), ('РЫБА');

insert into product (name, expired_date, price, type_id)
values  ('Сыр плавленный', '2022-10-20', 150.5, 1),
		('Сыр моцарелла', '2022-12-20', 300, 1),
		('Сыр колбасный', '2022-10-18', 250, 1),

		('Молоко топленое', '2022-10-26', 80, 2),
		('Молоко в бутылке', '2022-10-17', 85, 2),
		('Молоко 3,2%', '2022-10-23', 77, 2),
		('Молоко цельное', '2022-10-24', 82, 2),
		('Молоко 2,5%', '2022-10-27', 56, 2),
		('Молоко детское 3,2%', '2022-10-22', 35, 2),
		('Молоко ультрапастеризованное 3,2%', '2023-01-01', 103, 2),
		('Молоко козье 2,8 - 4%', '2022-10-22', 151, 2),
		('Молоко безлактозное 3,5-4,5%', '2022-10-18', 97, 2),
		('Молоко детское безлактозное 3,2%', '2022-10-27', 45, 2),

		('Колбаса вареная Клинский Молочная нарезка 190 г', '2022-10-18', 109, 3),
		('Колбаса вареная Вязанка Классическая 500 г', '2022-11-18', 208, 3),
		('Колбаса вареная с молоком', '2022-12-18', 292, 3),
		('Колбаса «Казылык» сыровяленая', '2023-04-01', 2090, 3),
		('Колбаса вареная «Классическая». Халяль, 430 г', '2022-12-01', 349, 3),
		('Колбаса «Еврейская полусухая» сырокопченая', '2022-11-01', 1728, 3),

		('Рыба масляная филе-ломтики Олива х/к 150 г', '2022-09-01', 299, 4),
		('Ледяная рыба замороженная', '2023-09-01', 1510, 4),
		('Скумбрия замороженная', '2023-05-01', 360, 4),
		('Кижуч стейк свежемороженный', '2023-03-01', 998, 4);

/*
1. Написать запрос получение всех продуктов с типом "СЫР"
*/
select name ПРОДУКТ, expired_date ГОДЕН_ДО, price ЦЕНА
from product
where type_id = 1;

/* ИЛИ */
select product.name, product.expired_date, product.price, type.name
from product
join type on type.id = product.type_id
where type.name = 'СЫР'


/*
2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
*/
select name ПРОДУКТ, expired_date ГОДЕН_ДО, price ЦЕНА
from product
where name like '%морожен%';


/*
3. Написать запрос, который выводит все продукты, срок годности которых уже истек
*/
select name ПРОДУКТ, expired_date ГОДЕН_ДО, price ЦЕНА
from product
where expired_date < current_date;


/*
4. Написать запрос, который выводит самый дорогой продукт. Запрос должен быть универсальный и находить все продукты с максимальной ценой.
*/
select name Продукт, price Цена
from product
where price  = (
	select max (price)
	from product
)


/*
5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
*/
select t.name Тип, count(t.name) Количество
from type t
join product p on p.type_id = t.id
group by t.name;


/*
6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
*/
select name Продукт
from product
where type_id between 1 and 2;

/* или */
select p.name Продукт, t.name Тип
from product p
join type t on t.id = p.type_id
where t.name = 'СЫР' or t.name = 'МОЛОКО'


/*
7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
Под количеством подразумевается количество продуктов определенного типа. Например, если есть тип "СЫР"
и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат, то количество продуктов типа "СЫР" будет 2.
*/
select t.name Тип, count(t.name) Количество
from type t
join product p on p.type_id = t.id
group by t.name
having count(t.name) < 10


/*
8. Вывести все продукты и их тип.
*/
select p.name Продукт, t.name Тип
from product p
join type t on t.id = p.type_id