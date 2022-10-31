drop table if exists sex cascade;
create table sex (
    id serial primary key,
    sex varchar(30)
);

insert into sex (sex)
values
('женский'),
('мужской');

drop table if exists movies cascade;
 create table movies (
	id serial primary key,
	title varchar(200)
);

insert into movies (title)
values
('Пираты Карибского моря'),
('Матрица: Революция'),
('Человек, который изменил всё'),
('Интерстеллар');

drop table if exists clients cascade;
 create table clients (
	id serial primary key,
	full_name varchar(50),
	address text,
	movie_id int references movies(id),
	sex_id int references sex(id)
);

insert into clients (full_name, address, movie_id, sex_id)
values
('Ольга Егорова', '1-ый Казанский переулок, д.14', 1, 1),
('Ольга Егорова', '1-ый Казанский переулок, д.14', 2, 1),
('Иванов Сергей', 'ул. Центральная, д.40, кв.74', 3, 2),
('Иванов Сергей', 'ул. Центральная, д.40, кв.74', 4, 2),
('Иванов Сергей', 'ул. Ленина, д.7, кв.130', 2, 2);

select c.full_name, c.address, m.title, s.sex
from clients c
left join movies m on m.id = c.movie_id
left join sex s on s.id = c.sex_id;