drop table if exists contacts cascade;
create table contacts (
    id serial primary key,
    address text
);

insert into contacts(address) values ('1-ый Казанский переулок, д.14'),
                                     ('ул. Центральная, д.40, кв.74'),
                                     ('ул. Ленина, д.7, кв.130');

drop table if exists clients cascade;
 create table clients (
	id serial primary key,
	full_name varchar(50),
	address_id int references contacts(id),
	sex varchar(10)
);

insert into clients (full_name, sex) values ('Ольга Егорова', 'женский');
insert into clients (full_name, sex) values ('Иванов Сергей', 'мужской');
insert into clients (full_name, sex) values ('Иванов Сергей', 'мужской');

drop table if exists client_contact cascade;
create table client_contact (
    id serial primary key,
    client_id int references clients(id),
    contact_id int references contacts(id)
);

insert into client_contact (client_id, contact_id) values (1, 1), (2, 2), (3, 3);

drop table if exists movies cascade;
 create table movies (
	id serial primary key,
	title varchar(200)
);

drop table if exists client_movie cascade;
create table client_movie (
    id serial primary key,
    client_id int references clients(id),
    movie_id int references movies(id)
);

insert into movies (title) values ('Пираты Карибского моря');
insert into movies (title) values ('Матрица: Революция');
insert into movies (title) values ('Человек, который изменил всё');
insert into movies (title) values ('Интерстеллар');

insert into client_movie (client_id, movie_id) values (1, 1), (1, 2), (2, 3), (2, 4), (3, 2);

select c.full_name, cnt.address, m.title, c.sex
from movies m
left join client_movie cm on m.id = cm.movie_id
left join clients c on c.id = cm.client_id
left join client_contact cc on cc.client_id = c.id
left join contacts cnt on cnt.id = cc.contact_id;