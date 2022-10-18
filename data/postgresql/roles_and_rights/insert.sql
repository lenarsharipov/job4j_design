insert into rules(rules_text) values('rule No 1');
insert into role(role_name) values('admin');
insert into role_rules(role_id, rules_id) values(1, 1);
insert into users(user_name, role_id) values('Ivan', 1);
insert into category(category_name) values('cat1');
insert into state(state_name) values('active');
insert into item(item_desc, users_id, category_id, state_id) values ('description', 1, 1, 1);
insert into comments(comments_details, item_id) values ('comments', 1);
insert into attachs(files, item_id) values ('file path and name', 1);