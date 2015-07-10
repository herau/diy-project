-- Admin and Application user
insert into users(username, password, enabled, firstname, lastname, email, state, company) values ('1234', '1234', TRUE, 'foo', 'bar', 'foo.bar@3ds.com', 0,0 );
insert into authorities(username, authority) values ('1234', 'USER, ADMIN');

-- Application user
insert into users(username, password, enabled, firstname, lastname, email, state, company) values ('321', '321', TRUE, 'bar', 'foo', 'bar.foo@3ds.com', 0,0 );
insert into authorities(username, authority) values ('321', 'USER');