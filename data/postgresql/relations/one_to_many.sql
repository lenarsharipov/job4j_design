create table doctor(
	id serial primary key,
	position varchar(255)
);

create table patients(
	id serial primary key,
	name varchar(255),
	surname varchar(255),
	doctor_id int references doctor(id)
);

insert into doctor(position) values ('Surgery');
insert into patients(name, surname, doctor_id) values ('Petr', 'Petrov', 1);
insert into patients(name, surname, doctor_id) values ('Olga', 'Petrova', 1);

select * from patients;

select * from doctor where id in (select doctor_id from patients);