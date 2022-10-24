/*
Вывести список всех машин и все привязанные к ним детали.
Нужно учесть, что каких-то деталей машина может и не содержать.
В таком случае значение может быть null при выводе (например,
название двигателя null);
*/
select c.name Авто, cb.name Кузов, ce.name Двигатель, ct.name Трансмиссия
from cars c
left join car_bodies cb ON cb.id = c.body_id
left join car_engines ce ON ce.id = c.engine_id
left join car_transmissions ct ON ct.id = c.transmission_id;


/*
Вывести кузовы, которые не используются НИ в одной машине.
*/
select cb.name Кузов
from car_bodies cb
left join cars c on cb.id = c.body_id
where c.body_id is null;


/*
Вывести двигатели, которые не используются НИ в одной машине
*/
select ce.name Двигатель
from car_engines ce
left join cars c on ce.id = c.engine_id
where c.engine_id is null;


/*
Вывести коробки передач, которые не используются НИ в одной машине
*/
select ct.name Трансмиссия
from car_transmissions ct
left join cars c on ct.id = c.transmission_id
where c.transmission_id is null;