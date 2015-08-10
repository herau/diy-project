-- Admin and Application user
insert into users(personal_number, password, firstname, lastname, email, state, company, role) values ('1234', '$2a$10$A5Uju8tCESUtWzYygujOPeatXxwjUksz0GR1ozxdwEJb7fuaq9tOm', 'foo', 'bar', 'foo.bar@3ds.com', 0,0 , 'ADMIN');
-- Application user
insert into users(personal_number, password, firstname, lastname, email, state, company, role) values ('321', '$2a$10$e2wcKCBAr/KtVOSh5eGQfO2tQpKN3SM22sSxXAU5rlqqb0RPWOBJa', 'bar', 'foo', 'bar.foo@3ds.com', 0,0 , 'USER');