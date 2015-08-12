-- Admin and Application user
insert into users(personal_number, password, firstname, lastname, email, state, company, role) values ('1234', '$2a$10$A5Uju8tCESUtWzYygujOPeatXxwjUksz0GR1ozxdwEJb7fuaq9tOm', 'foo', 'bar', 'foo.bar@3ds.com', 0,0 , 'ADMIN');
-- Application user
insert into users(personal_number, password, firstname, lastname, email, state, company, role) values ('321', '$2a$10$e2wcKCBAr/KtVOSh5eGQfO2tQpKN3SM22sSxXAU5rlqqb0RPWOBJa', 'bar', 'foo', 'bar.foo@3ds.com', 0,0 , 'USER');

insert into suppliers (name, city, street, zip, fax, phone) values ('MABEO INDUSTRIES', 'Trappes', 'Z.I. des Bruyeres - 6, avenue Jean Rostand', 78190, '0134829403', '0134829200');
insert into suppliers (name, city, street, zip, fax, phone) values ('CEGELEC NON DESTRUCTIVE TESTING', 'Brétigny-sur-orge', 'Z.I du Bois des Bordes – Le Plessis Pate', 91229, '0169886788', '0169886767');
insert into suppliers (name, city, street, zip, fax, phone) values ('REVERT SA', 'Versailles Cedex', '12, rue Carnot RP921', 78009, '0139530598', '0139201515');

INSERT INTO categories (name)
VALUES
	('Electro-portatif'),
	('Outil de batiment'),
	('Soudure'),
	('Appareil Nettoyage'),
	('Mesure et tracage'),
	('Gros outillage atelier'),
	('Etabli et rangement'),
	('Outillage à main'),
	('Jardin exterieur'),
	('Plomberie'),
	('Maconnerie'),
	('Automobile'),
	('Perceuse et perforateur'),
	('Perceuse/Visseuse'),
	('Piqueur brise beton'),
	('Ponceuse'),
	('Scie electrique'),
	('Meuleuse'),
	('Poste à souder'),
	('Nettoyeur haute Pression'),
	('Injecteur / extracteur (Nettoyeur à moquette)'),
	('Aspirateur'),
	('Niveau Laser'),
	('Laser chantier/ télémètre '),
	('Scanner mural '),
	('Compresseur'),
	('Enrouleur'),
	('Marche-Pied'),
	('Coffret dépannage'),
	('Diable/Plateau roulant'),
	('Ventouse'),
	('Etabli pliant/ Table à tapisser'),
	('Décolleuse papier peint'),
	('Agrafeuse Electrique'),
	('Rainureuse/Lamelleuse'),
	('Isolation'),
	('Nettoyage'),
	('Decoupe'),
	('Poncage'),
	('Pneumatique'),
	('Echelle'),
	('echafaudage'),
	('Menuiserie');

-- insert into tools (description, documentation_url, energy, localization, name, price, purchase_date, supplier_id, weight)