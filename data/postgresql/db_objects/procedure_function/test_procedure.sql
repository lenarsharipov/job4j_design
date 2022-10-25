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



create or replace procedure update_data(u_count integer, tax float, u_id integer)
language 'plpgsql' as
$$
    begin
        if u_count > 0 then
            update products set count = count - u_count where id = u_id;
        end if;
        if tax > 0 then
            update products set price = price + price * tax;
        end if;
    end;
$$;

call insert_data('p_product_2', 'p_producer_2', 15, 32);
call update_data(10, 0, 1);
call insert_data('p_product_1', 'p_producer_1', 3, 50);
call insert_data('p_product_3', 'p_producer_3', 8, 115);
call update_data(0, 0.2, 0);

select * from products;

alter procedure update_data(u_count integer, tax float, u_id integer) rename to update;
drop procedure update_data(u_count integer, tax float, u_id integer);

delete from products;
ALTER SEQUENCE products_id_seq RESTART WITH 1;

