create table vacancies(
	id serial primary key,
	vacancy varchar(255)
);

create table applicants(
	id serial primary key,
	name varchar(255),
	surname varchar(255)
);

create table applicants_vacancies(
	id serial primary key,
	applicant_id int references applicants(id),
	vacancy_id int references vacancies(id)
);

insert into applicants(name, surname) values('игорь', 'петров');
insert into applicants(name, surname) values('иван', 'иванов');
insert into applicants(name, surname) values('андрей', 'андреев');
insert into applicants(name, surname) values('петр', 'иванов');

insert into vacancies(vacancy) values('курьер');
insert into vacancies(vacancy) values('таксист');
insert into vacancies(vacancy) values('уборщик');
insert into vacancies(vacancy) values('водитель');

insert into applicants_vacancies(applicant_id, vacancy_id) values (1, 2);
insert into applicants_vacancies(applicant_id, vacancy_id) values (1, 4);
insert into applicants_vacancies(applicant_id, vacancy_id) values (2, 1);
insert into applicants_vacancies(applicant_id, vacancy_id) values (2, 2);
insert into applicants_vacancies(applicant_id, vacancy_id) values (2, 3);
insert into applicants_vacancies(applicant_id, vacancy_id) values (3, 1);
insert into applicants_vacancies(applicant_id, vacancy_id) values (4, 2);
insert into applicants_vacancies(applicant_id, vacancy_id) values (4, 4);