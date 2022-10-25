drop table if exists products cascade;
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace function f_insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
returns void
language 'plpgsql' as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;

/*
Добавьте функцию, которая будет
удалять записи. Условия выбирайте сами – удаление
по id, удалить если количество товара равно 0 и т.п.
*/
create or replace function f_delete_data(i_count integer)
returns void
language plpgsql as
$$
    begin
        delete from products p
        where p.count <= i_count;
    end;
$$;

select f_insert_data('f_product_1', 'f_producer_1', 25, 50);
select f_insert_data('f_product_2', 'f_producer_2', 15, 32);
select f_insert_data('f_product_3', 'f_producer_3', 8, 115);

select f_delete_data(15);

select * from products;