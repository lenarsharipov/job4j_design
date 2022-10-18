create table requisites(
	id serial primary key,
	ogrn bigint,
	inn bigint,
	kpp bigint
);

create table companies(
	id serial primary key,
	name text
);

create table requisites_companies(
	id serial primary key,
	requisites_id int references requisites(id) unique,
	companies_id int references companies(id) unique
);

insert into requisites(ogrn, inn, kpp) values(1027802253856, 7805553300, 770555111);
insert into requisites(ogrn, inn, kpp) values(1027811153856, 7806663110, 767676111);
insert into companies(name) values('Рога и копыта');
insert into companies(name) values('Потенциал');

insert into requisites_companies(requisites_id, companies_id) values (1, 2);
insert into requisites_companies(requisites_id, companies_id) values (2, 1);

select * from requisites;
select * from companies;
select * from requisites_companies;