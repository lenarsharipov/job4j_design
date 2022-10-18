create table role (
	id serial primary key,
	role_name text
);

create table users (
	id serial primary key,
	user_name text,
	role_id int references role(id)
);

create table rules (
	id serial primary key,
	rules_text text
);

create table role_rules (
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);

create table category (
	id serial primary key,
	category_name text
);

create table state (
	id serial primary key,
	state_name text
);

create table item (
	id serial primary key,
	item_desc text,
	users_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table comments (
	id serial primary key,
	comments_details text,
	item_id int references item(id)
);

create table attachs (
	id serial primary key,
	files text,
	item_id int references item(id)
);