create table car_bodies (
	id serial primary key,
	name varchar(200)
);

create table car_engines (
	id serial primary key,
	name varchar(200)
);

create table car_transmissions (
	id serial primary key,
	name varchar(200)
);

create table cars (
	id serial primary key,
	name varchar(200),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values ('sedan'),
                                    ('minivan'),
                                    ('crossover'),
                                    ('off-road'),
                                    ('cabriolet'),
                                    ('truck');

insert into car_engines(name) values ('petrol engine'),
                                     ('gas engine'),
                                     ('diesel engine'),
                                     ('hybrid engine'),
                                     ('electric engine');

insert into car_transmissions(name) values ('manual'),
                                           ('automatic'),
                                           ('semi-automatic'),
                                           ('cvt'),
                                           ('robot');

insert into cars(name, body_id, engine_id, transmission_id) values  ('bmw',     1, 1, 2),
																	('toyota',  2, 2, 4),
																	('lada',    3, 1, 1),
																	('peugeot', 1, 3, 1);

insert into cars(name, body_id) values  ('uaz', 4);
insert into cars(name, engine_id) values  ('tavria', 1);
insert into cars(name, transmission_id) values  ('citroen', 1);