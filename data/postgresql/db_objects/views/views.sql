create view show_active_clients_with_details
as select c.name, c.active,
r.region_name,
d.loading_date, d.payment_date, d.payment_term, d.debit_sum,
w.subdealers, w.subdealers_turnover,
mt.modern_shops, mt.moderntrade_turnover,
sr.small_shops, small_shops_turnover
from company c
full join region r on r.id = c.region_id
full join debit d on d.id = c.debit_id
full join wholesale w on w.id = c.wholesale_id
full join moderntrade mt on mt.id = c.moderntrade_id
full join small_retail sr on sr.id = c.small_retail_id
where c.name is not null;

select * from show_active_clients_with_details;



create view show_active_clients_with_defferred_payment
as select c.name, c.active,
r.region_name,
d.loading_date, d.payment_date, d.payment_term, d.debit_sum,
w.subdealers, w.subdealers_turnover,
mt.modern_shops, mt.moderntrade_turnover,
sr.small_shops, small_shops_turnover
from company c
full join region r on r.id = c.region_id
full join debit d on d.id = c.debit_id
full join wholesale w on w.id = c.wholesale_id
full join moderntrade mt on mt.id = c.moderntrade_id
full join small_retail sr on sr.id = c.small_retail_id
where d.payment_term = 'отсрочка' and c.active = true;

select * from show_active_clients_with_defferred_payment;