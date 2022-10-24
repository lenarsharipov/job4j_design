create table wholesale (
    id serial primary key,
    subdealers int,
    subdealers_turnover float
);

create table moderntrade (
    id serial primary key,
    modern_shops int,
    moderntrade_turnover float
);

create table small_retail (
    id serial primary key,
    small_shops int,
    small_shops_turnover float
);

create table debit (
    id serial primary key,
    payment_term varchar(255),
    debit_sum float,
    loading_date date,
    payment_date date
);

create table region (
    id serial primary key,
    region_name varchar(50)
);

insert into region(region_name) values ('Москва'), ('Нижний Новгород'), ('Санкт-Петербург'), ('Казань'), ('Россия');

create table company (
    id serial primary key,
    name varchar(200),
    active boolean default true,
    region_id int references region(id),
    wholesale_id int references wholesale(id),
    moderntrade_id int references moderntrade(id),
    small_retail_id int references small_retail(id),
    debit_id int references debit(id)
);

insert into wholesale(subdealers, subdealers_turnover) values (125, 15752.55), (1075, 175223.65);
insert into moderntrade(modern_shops, moderntrade_turnover) values (10, 55222), (90, 33254.55), (50, 75665);
insert into small_retail(small_shops, small_shops_turnover) values (1000, 9832), (2000, 18356);

insert into debit(payment_term, debit_sum, loading_date, payment_date) values ('предоплата', 3325, '2022-10-01', '2022-10-01');
insert into debit(payment_term, debit_sum, loading_date, payment_date) values ('отсрочка', 15632.65, '2022-08-01', '2022-10-01');
insert into debit(payment_term, debit_sum, loading_date, payment_date) values ('отсрочка', 23554.65, '2022-10-01', '2022-12-01');

insert into company (name,             region_id, wholesale_id, moderntrade_id, small_retail_id, debit_id)
             values ('DISTRIBUTOR 1',     1,          1,              1,              1,         1);

insert into company (name,             region_id, moderntrade_id, debit_id)
            values ('RETAIL CHAIN 1',       5,          1,           3);

insert into company (name,             region_id, wholesale_id, moderntrade_id, small_retail_id, debit_id)
            values ('DISTRIBUTOR 2',       2,          2,             3,             2,           2  );

