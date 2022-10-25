/*
Нужно написать триггер на row уровне, который при
вставке продукта в таблицу products, будет заносить
имя, цену и текущую дату в таблицу history_of_price.
*/

drop table if exists history_of_price cascade;
create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function add_inserts()
    returns trigger as
$$
    begin
        if TG_OP = 'INSERT' then
            insert into history_of_price(name, price, date) values (new.name, new.price, now());
            insert into products(name, producer, count, price) values (new.name, new.producer, new.count, new.price);
            return new;
        end if;
    end;
$$
language 'plpgsql';


create or replace trigger add_inserts_info_trigger
    instead of insert
    on products_view
    for each row
    execute procedure add_inserts();

insert into products_view (name, producer, count, price) values ('product1', 'producer1', 10, 100);
insert into products_view (name, producer, count, price) values ('product2', 'producer2', 20, 200);

select * from products;
select * from products_view;
select * from history_of_price;
