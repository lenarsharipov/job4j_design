truncate table company cascade;
CREATE TABLE company
(
    id integer NOT NULL,
    name character varying,
    CONSTRAINT company_pkey PRIMARY KEY (id)
);

truncate table person cascade;
CREATE TABLE person
(
    id integer NOT NULL,
    name character varying,
    company_id integer references company(id),
    CONSTRAINT person_pkey PRIMARY KEY (id)
);

insert into company(id, name)
values
(1, 'Mars company'),
(2, 'Snickers company'),
(3, 'Bounty company'),
(4, 'Twix company'),
(5, 'Nuts company');

insert into person(id, name, company_id)
values
(1, 'Анна', 1),
(2, 'Иван', 1),
(3, 'Андрей', 2),
(4, 'Ольга', 3),
(5, 'Матвей', 4),
(6, 'Джон', 4),
(7, 'Елена', 4),
(8, 'Юля', 5),
(9, 'Александр', 5),
(10, 'Игорь', 5);

select * from company;

select * from person;

/*
1. В одном запросе получить
- имена всех person, которые не состоят в компании с id = 5;
- название компании для каждого человека.
*/

select p.name person_name, c.name company_name, c.id company_id
from person p
join company c on c.id = p.company_id
where company_id != 5;

/*
2. Необходимо выбрать название компании с максимальным
количеством человек + количество человек в этой компании
нужно учесть, что таких компаний может быть несколько,
и вывести надо информацию о каждой компании.
*/

select c.name, count(p.name) employees
from person p
join company c on c.id = p.company_id
group by c.name
order by employees desc
fetch first 1 rows with ties;





