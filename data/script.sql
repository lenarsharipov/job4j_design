create table employees (
	id serial primary key,
	name varchar(255),
	surname varchar(255),
	date date,
	salary decimal
);

insert into employees(name, surname, date, salary) values('Ivan', 'Ivanov', '2022-10-15', '42500.500');

select * from employees;

update employees set name = 'Petr', surname = 'Petrov', date = '2019-01-11', salary = '60000';

select * from employees;

delete from employees;

select * from employees;