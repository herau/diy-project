-- Admin and Application user
INSERT INTO users (personal_number, password, firstname, lastname, email, state, company, role) VALUES
  ('1234', '$2a$10$A5Uju8tCESUtWzYygujOPeatXxwjUksz0GR1ozxdwEJb7fuaq9tOm', 'foo', 'bar', 'foo.bar@3ds.com', 0, 0, 'ADMIN');
-- Application user
INSERT INTO users (personal_number, password, firstname, lastname, email, state, company, role) VALUES
  ('321', '$2a$10$e2wcKCBAr/KtVOSh5eGQfO2tQpKN3SM22sSxXAU5rlqqb0RPWOBJa', 'bar', 'foo', 'bar.foo@3ds.com', 0, 0, 'USER');
-- Member user
INSERT INTO users (personal_number, password, firstname, lastname, email, state, company, role) VALUES
  ('0123', '$2a$10$tq86j.XU4jgX2zbWpeyCqukHKZ93ZEiFvepqYRzWEm9vDqj4na3vK', 'fooBar', 'barFoo', 'foo.foo@3ds.com', 0, 0, 'MEMBER');
