create table departments (
    id serial primary key,
    name varchar(255)
);

create table employees (
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('IT'), ('SALE'), ('HR'), ('PURCHASING');

insert into employees(name, department_id) values   ('IVAN', 1), ('DIMA', 1), ('OLGA', 1),
                                                    ('IGOR', 2), ('VASILIY', 2),
                                                    ('OLEG', 3), ('ANNA', 3);


/*
2. Выполнить запросы с left, right, full, cross соединениями
*/
select e.name Сотрудник, d.name Отдел
from employees e
right join departments d
on d.id = e.department_id

select e.name Сотрудник, d.name Отдел
from employees e
left join departments d
on d.id = e.department_id

select e.name Сотрудник, d.name Отдел
from employees e
full join departments d
on d.id = e.department_id

select e.name Сотрудник, d.name Отдел
from employees e
cross join departments d


/*
3. Используя left join найти департаменты, у которых нет работников
*/
select e.name Сотрудник, d.name Отдел
from departments d
left join employees e
on d.id = e.department_id
where e.id is null


/*
4. Используя left и right join написать запросы, которые давали бы
одинаковый результат (порядок вывода колонок в эти запросах также должен быть идентичный).
*/
select e.name Сотрудник, d.name Отдел
from departments d
left join employees e
on d.id = e.department_id;

select e.name Сотрудник, d.name Отдел
from employees e
right join departments d
on d.id = e.department_id;


/*
5. Создать таблицу teens с атрибутами name, gender и заполнить ее.
Используя cross join составить все возможные разнополые пары
*/
create table teens (
    id serial primary key,
    name varchar(255),
    gender varchar(50)
);

insert into teens(name, gender) values ('Ivan', 'male') , ('Anna', 'female');
insert into teens(name) values ('Dima'), ('Olga');

select t1.name, t2.gender
from teens t1
cross join teens t2;