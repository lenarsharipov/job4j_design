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


create or replace function f_update_data(u_count integer, tax float, u_id integer)
returns integer
language 'plpgsql' as
$$
    declare
        result integer;
    begin
        if u_count > 0 then
            update products set count = count - u_count where id = u_id;
            select into result count from products where id = u_id;
        end if;
        if tax > 0 then
            update products set price = price + price * tax;
            select into result sum(price) from products;
        end if;
        return result;
    end;
$$;

select f_insert_data('f_product_1', 'f_producer_1', 25, 50);
select f_update_data(10, 0, 1);
select f_insert_data('f_product_2', 'f_producer_2', 15, 32);
select f_insert_data('f_product_3', 'f_producer_3', 8, 115);
select f_update_data(0, 0.2, 0);

select * from products;