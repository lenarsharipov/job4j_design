create table requisites(
	id serial primary key,
	ogrn bigint,
	inn bigint,
	kpp bigint
);

create table companies(
	id serial primary key,
	name text,
	requisites_id int references requisites(id) unique
);

insert into requisites(ogrn, inn, kpp) values(1027802253856, 7805553300, 770555111);
insert into companies(name, requisites_id) values('Рога и копыта', 1);

select * from requisites;
select * from companies;