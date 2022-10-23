create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('android phone', 25455.65);
insert into devices(name, price) values ('iphone phone', 95600.89);
insert into devices(name, price) values ('samsung tv', 75010.85);
insert into devices(name, price) values ('acer notebook', 86500);

insert into people(name) values ('Ivan'), ('Andrey'), ('Olga'), ('Anna');

insert into devices_people(device_id, people_id) values (1, 1), (1, 3);
insert into devices_people(device_id, people_id) values (2, 3), (2, 4);
insert into devices_people(device_id, people_id) values (3, 1), (3, 2), (3, 3), (3, 4);
insert into devices_people(device_id, people_id) values (4, 1), (4, 2), (4, 4);

select avg(price) from devices;

select people.name, avg(devices.price)
from people
join devices_people on people.id = devices_people.people_id
join devices on devices.id = devices_people.device_id
group by people.name;

select p.name Имя, avg(d.price) Средняя_стоимость
from people p
join devices_people dp on p.id = dp.people_id
join devices d on d.id = dp.device_id
group by p.name
having avg(d.price) > 50000;