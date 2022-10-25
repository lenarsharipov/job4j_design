drop table if exists products cascade;
create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

create or replace procedure insert_data(i_name varchar, prod varchar, i_count integer, i_price integer)
language 'plpgsql' as
$$
    begin
        insert into products (name, producer, count, price)
        values (i_name, prod, i_count, i_price);
    end;
$$;


/*
Добавьте процедуру, которая будет
удалять записи. Условия выбирайте сами – удаление
по id, удалить если количество товара равно 0 и т.п.
*/
create or replace procedure delete_data(prod varchar)
language plpgsql as
$$
    begin
        delete from products p
        where p.producer = prod;
    end;
$$;

call insert_data('p_product_2', 'producer_2', 15, 32);
call insert_data('p_product_1', 'producer_1', 3, 50);
call insert_data('p_product_3', 'producer_3', 8, 115);

call delete_data('producer_1');

select * from products;