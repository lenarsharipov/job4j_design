/*
1)  Триггер должен срабатывать после вставки данных,
для любого товара и просто насчитывать налог на товар
(нужно прибавить налог к цене товара). Действовать он
должен не на каждый ряд, а на запрос (statement уровень)
*/

create or replace function vat()
    returns trigger as
$$
    begin
        update products
        set price = price + price * 0.2
        where id = (select id from inserted);
        return new;
    end;
$$
language 'plpgsql';

drop trigger add_vat_trigger on products;
create trigger add_vat_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure vat();

insert into products (name, producer, count, price) values ('product1', 'producer1', 10, 100);
insert into products (name, producer, count, price) values ('product2', 'producer2', 20, 200);

select * from products;

insert into products (name, producer, count, price) values ('product3', 'producer3', 30, 300);

alter table products disable trigger add_vat_trigger;
alter table products enable trigger add_vat_trigger;