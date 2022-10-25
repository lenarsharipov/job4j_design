/*
2) Триггер должен срабатывать до вставки данных
и насчитывать налог на товар (нужно прибавить
налог к цене товара). Здесь используем row уровень.
*/

create or replace function vat_before()
    returns trigger as
$$
    begin
        new.price = new.price + new.price * 0.2;
        return new;
    end;
$$
language 'plpgsql';

drop trigger if exists add_vat_before_trigger on products cascade;
create trigger add_vat_before_trigger
    before insert
    on products
    for each row
    execute procedure vat_before();

insert into products (name, producer, count, price) values ('product1', 'producer1', 10, 100);
insert into products (name, producer, count, price) values ('product2', 'producer2', 20, 200);

select * from products;

alter table products disable trigger add_vat_before_trigger;
alter table products enable trigger add_vat_before_trigger;