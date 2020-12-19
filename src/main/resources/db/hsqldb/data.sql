INSERT INTO trabajador(id,nombre,apellidos,dni,telefono, direccion, correo, tipocategoria) VALUES (1,'Carlos Jesus', 'Villadiego', '78461836B','666888888','C/Garcia Marquez n2', 'calvirgar@alum.us.es', 0);
INSERT INTO trabajador(id,nombre,apellidos,dni,telefono, direccion, correo, tipocategoria) VALUES (2,'Carlos Jesus2', 'Villadiego', '78461836B','666888888','C/Garcia Marquez n2', 'calvirgar@alum.us.es', 2);

/*INSERT INTO horario(id,horaInicio, horaFin, trabajador_id, descripcion) VALUES (1,'2020/12/10 12:00', '2020/12/10 15:00',1, 'Limpiar en Acuario de Sevilla');
INSERT INTO horario(id,horaInicio, horaFin, trabajador_id, descripcion) VALUES (2,'2020/01/31 17:30', '2020/01/31 20:30',2, 'Limpiar en Hostal San Jerónimo (Sevilla)');

INSERT INTO instalacion(id,horaInicio, lugar, cliente_id, dimension) VALUES (1,'Calle Águila Piso 1ºB',1, '20.3');
INSERT INTO instalacion(id,horaInicio, lugar, cliente_id, dimension) VALUES (2,'Calle Betis Piso 1ºD',2, '17.7');
*/

INSERT INTO contratoTrabajador(id,fechainicial,fechafinal,trabajador_id,sueldo) VALUES (1, '2010-01-01', '2019-12-31', 1, 1200.0);
INSERT INTO cliente(nombre,apellidos,telefono,direccion,dni,correo) VALUES ('Manolito', 'Pies de Plata', '624586245', 'Calle Agata', '25673519P', 'manolito@gmail.com');

INSERT INTO proveedores(name,telefono,email,direccion) VALUES ('Lejias SL', '645681128', 'lejiassl@gmail.com', 'Calle Reina 14, Sevilla'); 
INSERT INTO proveedores(name,telefono,email,direccion) VALUES ('Jabones SA', '645681127', 'jabonessa@gmail.com', 'Calle Mercedes 14, Madrid'); 

INSERT INTO productos(name, cantidad) VALUES ('Lejia', 30);
INSERT INTO productos(name, cantidad) VALUES ('jabon', 23);
INSERT INTO productos(name, cantidad) VALUES ('Escoba', 13);
INSERT INTO productos(name, cantidad) VALUES ('Fregona', 10);
INSERT INTO productos(name, cantidad) VALUES ('Estropajo', 14);
INSERT INTO productos(name, cantidad) VALUES ('Limpiacristales', 4);
INSERT INTO productos(name, cantidad) VALUES ('Amoniaco', 9);
INSERT INTO productos(name, cantidad) VALUES ('KH7', 10);
INSERT INTO productos(name, cantidad) VALUES ('Rollos Papel', 40);


INSERT INTO pedidos(id, fecha) VALUES (1, '2020-04-20');
INSERT INTO pedidos(id, fecha) VALUES (2, '2020-02-20');
INSERT INTO pedidos(id, fecha) VALUES (3, '2020-01-20');

INSERT INTO facturas(id, fecha, precio_total, proveedor_id, pedido_id) VALUES (1, '2020-10-20', 10.0, 1, 1);
INSERT INTO facturas(id, fecha, precio_total, proveedor_id, pedido_id) VALUES (2, '2020-10-22', 137.89, 1, 2);
INSERT INTO facturas(id, fecha, precio_total, proveedor_id, pedido_id) VALUES (3, '2020-10-22', 56.01, 2, 3);

INSERT INTO curriculum(name, tipocategoria) VALUES ('Carlos Jesus', 0);
INSERT INTO curriculum(name, tipocategoria) VALUES ('Carlos Jesus2', 2);


INSERT INTO ofertas(name, precioU) VALUES ('Fregona', 2.5);
INSERT INTO ofertas(name, precioU) VALUES ('Escoba', 3);

INSERT INTO administrador(nombre, apellidos, tipocategoria) VALUES ('Carlos Jesus','Morales Borreguero', 2);
INSERT INTO administrador(nombre, apellidos, tipocategoria) VALUES ('Carlos','Borreguero', 0);

INSERT INTO instalacion(lugar, dimension, cliente_id) VALUES ('Calle Albania', 130, 1);

INSERT INTO presupuesto(precio, tipopresupuesto, instalacion_id) VALUES (4579.5, 1, 1);
INSERT INTO presupuesto(precio, tipopresupuesto, instalacion_id) VALUES (50.0, 0, 1);
INSERT INTO presupuesto(precio, tipopresupuesto, instalacion_id) VALUES (123.12, 0, 1);
INSERT INTO presupuesto(precio, tipopresupuesto, instalacion_id) VALUES (18000.0, 1, 1);


INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado) VALUES ('Av Reina Mercedes', 0, '2019-10-20', '2020-12-31', 0);

INSERT INTO contratoservicio(fechainicial, fechafinal, fechapago, cliente_dni, presupuesto_id, periodoprueba, servicio_id) VALUES ('2019-10-20', '2020-12-31', '2021-01-10', 1, 2, 0, 1);




/* -- One admin user, named admin1 with passwor 4dm1n and authority admin
INSERT INTO users(username,password,enabled) VALUES ('admin1','4dm1n',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (1,'admin1','admin');
-- One owner user, named owner1 with passwor 0wn3r
INSERT INTO users(username,password,enabled) VALUES ('owner1','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (2,'owner1','owner');

INSERT INTO users(username,password,enabled) VALUES ('josbejpoz','0wn3r',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (14,'josbejpoz','owner');

INSERT INTO users(username,password,enabled) VALUES ('pabgonmon2','asd123',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (11,'pabgonmon2','owner');

INSERT INTO users(username,password,enabled) VALUES ('josmorbor3','zxc123',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (20,'josmorbor3','owner');

INSERT INTO users(username,password,enabled) VALUES ('fervalnav','qwerty1',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (12,'fervalnav','owner');

INSERT INTO users(username,password,enabled) VALUES ('carvilgar1','12345',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (32,'carvilgar1','owner');

-- One vet user, named vet1 with passwor v3t
INSERT INTO users(username,password,enabled) VALUES ('vet1','v3t',TRUE);
INSERT INTO authorities(id,username,authority) VALUES (3,'vet1','veterinarian');

INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiology');
INSERT INTO specialties VALUES (2, 'surgery');
INSERT INTO specialties VALUES (3, 'dentistry');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'cat');
INSERT INTO types VALUES (2, 'dog');
INSERT INTO types VALUES (3, 'lizard');
INSERT INTO types VALUES (4, 'snake');
INSERT INTO types VALUES (5, 'bird');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023', 'owner1');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749', 'owner1');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763', 'owner1');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198', 'owner1');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765', 'owner1');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654', 'owner1');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387', 'owner1');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683', 'owner1');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435', 'owner1');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487', 'owner1');
INSERT INTO owners VALUES (21, 'José Manuel', 'Bejarano', 'San Jerónimo', 'Sevilla', '6375921655', 'owner1');

INSERT INTO owners VALUES (11, 'Pablo', 'Gonzalez', '2335 Holanda Sev', 'Antique', '600000000', 'pabgonmon2');
INSERT INTO owners VALUES (20, 'Jose Carlos', 'Morales', '4596 Portugal Sev', 'Occo', '604007080', 'josmorbor3');
INSERT INTO owners VALUES (12, 'Fernando', 'Valdes', '41800 Sanlucar la Mayor', 'Sevilla', '671193543', 'fervalnav');
INSERT INTO owners VALUES (32, 'Carlos Jesus', 'Villadiego', '41800 Sevilla', 'Sion', '600000000', 'carvilgar1');


INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (13, 'Sly', '2012-06-08', 1, 10);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (27, 'Simba', '2011-06-30', 2, 21);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (32, 'Sion', '2019-04-1', 2, 32);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (14, 'Sirka', '2010-06-03', 2, 11);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (15, 'Lebron', '2020-05-01', 2, 12);
INSERT INTO pets(id,name,birth_date,type_id,owner_id) VALUES (16, 'Hache', '2020-03-01', 2, 20);

INSERT INTO visits(id,pet_id,visit_date,description) VALUES (1, 7, '2013-01-01', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (2, 8, '2013-01-02', 'rabies shot');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (3, 8, '2013-01-03', 'neutered');
INSERT INTO visits(id,pet_id,visit_date,description) VALUES (4, 7, '2013-01-04', 'spayed');

*/