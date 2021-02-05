/* One admin user, named admin1 with passwor 4dm1n and authority admin*/
INSERT INTO users(username,password,enabled) VALUES ('admin','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('Nick Furia','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('Ironman','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('Capitan América','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('Don Limpio','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('JosePabloSL','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('JoseCarlos','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);

INSERT INTO authorities(id,username,authority) VALUES (1,'admin','administrador');
INSERT INTO authorities(id,username,authority) VALUES (2,'Nick Furia','cliente');
INSERT INTO authorities(id,username,authority) VALUES (3,'Ironman','cliente');
INSERT INTO authorities(id,username,authority) VALUES (4,'Capitan América','cliente');
INSERT INTO authorities(id,username,authority) VALUES (5,'Don Limpio','proveedor');
INSERT INTO authorities(id,username,authority) VALUES (6,'JosePabloSL','proveedor');
INSERT INTO authorities(id,username,authority) VALUES (7,'JoseCarlos','trabajador');

INSERT INTO trabajador(id,nombre,apellidos,dni,telefono, direccion, correo, tipocategoria) VALUES (1,'Carlos Jesus', 'Villadiego', '78461836B','666888888','C/Garcia Marquez n2', 'calvirgar@alum.us.es', 0);
INSERT INTO trabajador(id,nombre,apellidos,dni,telefono, direccion, correo, tipocategoria) VALUES (2,'Carlos Jesus2', 'Villadiego', '78461836B','666888888','C/Garcia Marquez n2', 'calvirgar@alum.us.es', 2);
INSERT INTO trabajador(id,nombre,apellidos,dni,telefono, direccion, correo, tipocategoria, username) VALUES (3,'Jose Carlos', 'Morales', '20099009B','666888884','C/Huertas', 'josmorbor3@alum.us.es', 2, 'JoseCarlos');

INSERT INTO horario(hora_inicio, hora_fin, trabajador, descripcion) VALUES ('2020-12-10 12:00', '2020-12-10 15:00', 1, 'Limpiar en Acuario de Sevilla');
INSERT INTO horario(hora_inicio, hora_fin, trabajador, descripcion) VALUES ('2020-01-31 17:30', '2020-01-31 20:30', 2, 'Limpiar en Hostal San Jerónimo (Sevilla)');

INSERT INTO registro_hora(hora_entrada, hora_salida, trabajador) VALUES ('2019-06-16 11:00', '2019-06-16 14:00', 1);
INSERT INTO registro_hora(hora_entrada, hora_salida, trabajador) VALUES ('2019-08-16 19:00', '2019-08-16 23:00', 2);

INSERT INTO contratoTrabajador(id,fechainicial,fechafinal,trabajador_id,sueldo) VALUES (1, '2010-01-01', '2019-12-31', 1, 1200.0);
INSERT INTO contratoTrabajador(id,fechainicial,fechafinal,trabajador_id,sueldo) VALUES (2, '2010-01-01', '2019-12-31', 1, 1200.0);

INSERT INTO cliente(id, nombre,apellidos,telefono,direccion,dni,correo, username) VALUES (1, 'Jesus', 'Garcia', '644586245', 'Calle Cristie', '33445566P', 'jesus@gmail.com', 'Nick Furia');
INSERT INTO cliente(id, nombre,apellidos,telefono,direccion,dni,correo, username) VALUES (2, 'Ruben', 'Bueno', '655788999', 'Calle Misco', '11223344T', 'ruben@gmail.com', 'Ironman');
INSERT INTO cliente(id, nombre,apellidos,telefono,direccion,dni,correo, username) VALUES (3, 'Manolito', 'Pies de Plata', '624586245', 'Calle Agata', '25673519P', 'manolito@gmail.com', 'Capitan América');

INSERT INTO instalacion(lugar, dimension, cliente_id) VALUES ('Calle Águila Piso 1ºB',20.8, 1);
INSERT INTO instalacion(lugar, dimension, cliente_id) VALUES ('Calle Betis Piso 1ºD',17.3, 2);
INSERT INTO instalacion(lugar, dimension, cliente_id) VALUES ('Calle Albania', 130.9, 3);

INSERT INTO proveedor(name,telefono,email,direccion,username) VALUES ('Lejias SL', '645681128', 'lejiassl@gmail.com', 'Calle Reina 14, Sevilla','Don Limpio'); 
INSERT INTO proveedor(name,telefono,email,direccion) VALUES ('Jabones SA', '645681127', 'jabonessa@gmail.com', 'Calle Mercedes 14, Madrid'); 
INSERT INTO proveedor(name,telefono,email,direccion,username) VALUES ('Jose Pablo SL', '644332211', 'josepablosl@gmail.com', 'Calle Palmas 33, Badajoz','JosePabloSL'); 


INSERT INTO producto(name, cantidad) VALUES ('Lejia', 30);
INSERT INTO producto(name, cantidad) VALUES ('jabon', 23);
INSERT INTO producto(name, cantidad) VALUES ('Escoba', 13);
INSERT INTO producto(name, cantidad) VALUES ('Fregona', 10);
INSERT INTO producto(name, cantidad) VALUES ('Estropajo', 14);
INSERT INTO producto(name, cantidad) VALUES ('Limpiacristales', 4);
INSERT INTO producto(name, cantidad) VALUES ('Amoniaco', 9);
INSERT INTO producto(name, cantidad) VALUES ('KH7', 10);
INSERT INTO producto(name, cantidad) VALUES ('Rollos Papel', 40);

INSERT INTO oferta(name, precioU, producto_id, proveedor) VALUES ('Fregona', 2.5, 4, 1);
INSERT INTO oferta(name, precioU, producto_id, proveedor) VALUES ('Escoba', 3, 3, 2);
INSERT INTO oferta(name, precioU, producto_id, proveedor) VALUES ('Amoniaco', 4, 7, 3);
INSERT INTO oferta(name, precioU, producto_id, proveedor) VALUES ('KH7', 10, 8, 3);

INSERT INTO pedido(fecha, cantidad, oferta_id) VALUES ('2020-04-20', 5, 1);
INSERT INTO pedido(fecha, cantidad, oferta_id) VALUES ('2020-02-20', 2, 2);
INSERT INTO pedido(fecha, cantidad, oferta_id) VALUES ('2021-01-20', 10, 4);




INSERT INTO factura(fecha, precio_total, proveedor, pedido) VALUES ('2020-10-20', 10.0, 2, 1);
INSERT INTO factura(fecha, precio_total, proveedor, pedido) VALUES ('2020-10-20', 137.89, 1, 2);
INSERT INTO factura(fecha, precio_total, proveedor, pedido) VALUES ('2021-01-20', 56.01, 3, 3);


INSERT INTO curriculum(nombre, apellidos, telefono, correo, descripcion, tipocategoria) VALUES ('Carlos', 'Villadiego', '666666666', 'carvilgar1@us.es', 'Mucha experiencia en el sector', 0);
INSERT INTO curriculum(nombre, apellidos, telefono, correo, descripcion, tipocategoria) VALUES ('Carlos Jesus2', 'Villadiego', '666666666', 'carvilgar1@us.es', 'Muchas ganas de trabajar', 2);


INSERT INTO administrador(id,nombre,apellidos,dni,telefono, direccion, correo, tipocategoria, username) VALUES (1,'CARLOS JESUS', 'VILLADIEGO GARCIA', '78461836B','666666666','C/Motorhead, 6', 'calvirgar@alum.us.es', 0, 'admin');
INSERT INTO administrador(nombre, apellidos, tipocategoria) VALUES ('Carlos','Borreguero', 0);


INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Av Reina Mercedes', 0, '2019-10-20', '2020-12-31', 0, 1);
INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Av Reina Mercedes', 0, '2019-10-20', '2020-12-31', 1, 1);
INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Av Reina Mercedes', 1, '2019-10-20', '2020-12-31', 1, 1);
INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Av Reina Mercedes', 2, '2019-10-20', '2020-12-31', 1, 1);
INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Av Reina Mercedes', 0, '2019-10-20', '2020-12-31', 1, 2);
INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Av Reina Mercedes', 0, '2019-10-20', '2021-02-28', 1, 2);

INSERT INTO presupuesto(precio, tipopresupuesto, servicio_id, estado) VALUES (4579.5, 1, 1, 0);
INSERT INTO presupuesto(precio, tipopresupuesto, servicio_id, estado) VALUES (50.0, 0, 1, 0);
INSERT INTO presupuesto(precio, tipopresupuesto, servicio_id, estado) VALUES (123.12, 0, 1, 0);
INSERT INTO presupuesto(precio, tipopresupuesto, servicio_id, estado) VALUES (18000.0, 1, 1, 1);

INSERT INTO contratoservicio(fechainicial, fechafinal, fechapago, cliente, presupuesto, periodoprueba, servicio) VALUES ('2019-10-20', '2020-12-31', '2020-12-10', 1, 2, 0, 2);
INSERT INTO contratoservicio(fechainicial, fechafinal, fechapago, cliente, presupuesto, periodoprueba, servicio) VALUES ('2019-10-20', '2021-02-28', '2020-12-10', 1, 2, 0, 5);
INSERT INTO contratoservicio(fechainicial, fechafinal, fechapago, cliente, presupuesto, periodoprueba, servicio) VALUES ('2019-10-20', '2020-12-31', '2020-12-10', 3, 3, 1, 3);
INSERT INTO contratoservicio(fechainicial, fechafinal, cliente, presupuesto, periodoprueba, servicio) VALUES ('2020-10-20', '2021-10-20', 2, 1, 1, 4);

INSERT INTO reclamacion(fecha, descripcion, cliente_id, servicio_id) VALUES ('2019-10-20', 'El servicio está incompleto', 1, 1);
INSERT INTO reclamacion(fecha, descripcion, cliente_id, servicio_id) VALUES ('2019-10-20', 'El servicio está lamentable', 2, 2);

INSERT INTO valoracion(fecha,nivelsatisfaccion, cliente_id, servicio_id) VALUES ('2019-10-20', 0, 1, 1);
INSERT INTO valoracion(fecha,nivelsatisfaccion, cliente_id, servicio_id) VALUES ('2020-10-12', 2, 2, 2);

INSERT INTO mensaje(fecha, asunto, cuerpo, receptor, emisor) VALUES ('2021-02-02', 'Hola que tal', 'Hola como estamos que tal', 'admin', 'JosePabloSL');
INSERT INTO mensaje(fecha, asunto, cuerpo, receptor, emisor) VALUES ('2021-02-02', 'Muy bien gracias', 'Genial, y tu?', 'JosePabloSL', 'admin');


/*-- One owner user, named owner1 with passwor 0wn3r
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

*/