begin;
declare
	cursor_products scroll cursor
		for select * from products;

move last from cursor_products;

fetch from cursor_products;

fetch backward from cursor_products;

fetch backward 10 from cursor_products;

move first from cursor_products;

fetch 10 from cursor_products;

fetch last from cursor_products;;

close cursor_products;

commit;