select * from products;

create or replace function tax()
    returns trigger as
$$
    begin
        update products
        set price = price - price * 0.2
        where id = (select id from inserted) and count <= 5;
        return new;
    end;
$$
language 'plpgsql';

create trigger tax_trigger
        after insert on products
        referencing new table as inserted
        for each statement
        execute procedure tax();

alter table products disable trigger tax_trigger;
alter table products enable trigger tax_trigger;

drop trigger tax_trigger on products;

insert into products (name, producer, count, price) values ('product1111', 'producer_1111', 3, 50);
