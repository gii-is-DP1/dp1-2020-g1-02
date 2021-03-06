INSERT INTO users(username,password,enabled) VALUES ('admin','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('Nick Furia','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('Ironman','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('Capitan America','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('Don Limpio','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('JosePabloSL','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('JoseCarlos','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);
INSERT INTO users(username,password,enabled) VALUES ('Sistema','$2a$10$scVXDdcGmWu8SNk/6EP/y.ox5UKp8eQoTpZTzYC6Q.Rgf0/gJW6C6',TRUE);

INSERT INTO authorities(id,username,authority) VALUES (1,'admin','administrador');
INSERT INTO authorities(id,username,authority) VALUES (2,'Nick Furia','cliente');
INSERT INTO authorities(id,username,authority) VALUES (3,'Ironman','cliente');
INSERT INTO authorities(id,username,authority) VALUES (4,'Capitan America','cliente');
INSERT INTO authorities(id,username,authority) VALUES (5,'Don Limpio','proveedor');
INSERT INTO authorities(id,username,authority) VALUES (6,'JosePabloSL','proveedor');
INSERT INTO authorities(id,username,authority) VALUES (7,'JoseCarlos','trabajador');
INSERT INTO authorities(id,username,authority) VALUES (8,'Sistema','administrador');

INSERT INTO curriculum(nombre, apellidos, telefono, correo, descripcion, tipocategoria) VALUES ('Carlos', 'Villadiego', '666666666', 'carvilgar1@us.es', 'Mucha experiencia en el sector', 0);
INSERT INTO curriculum(nombre, apellidos, telefono, correo, descripcion, tipocategoria) VALUES ('Carlos Jesus2', 'Villadiego', '666666666', 'carvilgar1@us.es', 'Muchas ganas de trabajar', 2);
INSERT INTO curriculum(nombre, apellidos, telefono, correo, descripcion, tipocategoria) VALUES ('Jose Carlos', 'Morales', '666888884','josmorbor3@alum.us.es','Muchas ganas de trabajar', 2);
INSERT INTO curriculum(nombre, apellidos, telefono, correo, descripcion, tipocategoria) VALUES ('Javier', 'Garcia', '666888887','javier@alum.us.es','Muchas ganas de empezar a trabajar', 1);
INSERT INTO curriculum(nombre, apellidos, telefono, correo, descripcion, tipocategoria) VALUES ('Manuel', 'Ruiz', '666888800','manuel@alum.us.es','Gran experiencia en el sector', 1);

INSERT INTO trabajador(id,nombre,apellidos,dni,telefono, direccion, correo, tipocategoria, curriculum) VALUES (1,'Carlos Jesus', 'Villadiego', '78461836B','666888888','C/Garcia Marquez n2', 'calvirgar@alum.us.es', 0, 1);
INSERT INTO trabajador(id,nombre,apellidos,dni,telefono, direccion, correo, tipocategoria, curriculum) VALUES (2,'Carlos Jesus2', 'Villadiego', '78461835B','666888888','C/Garcia Marquez n2', 'calvirgar@alum.us.es', 2, 2);
INSERT INTO trabajador(id,nombre,apellidos,dni,telefono, direccion, correo, tipocategoria, username, curriculum) VALUES (3,'Jose Carlos', 'Morales', '20099009B','666888884','C/Huertas', 'josmorbor3@alum.us.es', 2, 'JoseCarlos', 3);

INSERT INTO horario(fecha, hora_inicio, hora_fin, trabajador, descripcion) VALUES ('2020-12-10' , '12:00', '15:00', 1, 'Limpiar en Acuario de Sevilla');
INSERT INTO horario(fecha, hora_inicio, hora_fin, trabajador, descripcion) VALUES ('2020-12-10', '17:30', '20:30', 3, 'Limpiar en Hostal San Jerónimo (Sevilla)');

INSERT INTO registro_hora(fecha, hora_inicio, hora_fin, trabajador) VALUES ('2019-06-16', '11:00', '15:00', 1);
INSERT INTO registro_hora(fecha, hora_inicio, hora_fin, trabajador) VALUES ('2019-06-17', '09:00', '14:30', 1);
INSERT INTO registro_hora(fecha, hora_inicio, hora_fin, trabajador) VALUES ('2019-06-18', '10:00', '14:00', 1);
INSERT INTO registro_hora(fecha, hora_inicio, hora_fin, trabajador) VALUES ('2019-08-16', '19:00', '23:00', 2);

INSERT INTO contratoTrabajador(fechainicial,fechafinal,trabajador_id,sueldo) VALUES ('2010-01-01', '2019-12-31', 1, 1200.0);
INSERT INTO contratoTrabajador(fechainicial,fechafinal,trabajador_id,sueldo) VALUES ('2010-01-01', '2019-12-31', 2, 1200.0);
INSERT INTO contratoTrabajador(fechainicial,fechafinal,trabajador_id,sueldo) VALUES ('2020-01-01', '2020-12-31', 2, 1200.0);

INSERT INTO cliente(id, nombre,apellidos,telefono,direccion,dni,correo, username) VALUES (1, 'Jesus', 'Garcia', '644586245', 'Calle Cristie', '33445566P', 'jesus@gmail.com', 'Nick Furia');
INSERT INTO cliente(id, nombre,apellidos,telefono,direccion,dni,correo, username) VALUES (2, 'Ruben', 'Bueno', '655788999', 'Calle Misco', '11223344T', 'ruben@gmail.com', 'Ironman');
INSERT INTO cliente(id, nombre,apellidos,telefono,direccion,dni,correo, username) VALUES (3, 'Manolito', 'Pies de Plata', '624586245', 'Calle Agata', '25673519P', 'manolito@gmail.com', 'Capitan America');

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

INSERT INTO administrador(id,nombre,apellidos,dni,telefono, direccion, correo, tipocategoria, username) VALUES (1,'CARLOS JESUS', 'VILLADIEGO GARCIA', '78461836G','666666666','C/Motorhead, 6', 'calvirgar@alum.us.es', 0, 'admin');
INSERT INTO administrador(nombre, apellidos, tipocategoria) VALUES ('Carlos','Borreguero', 0);

INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Av Reina Mercedes', 0, '2019-10-20', '2020-12-31', 1, 1);
INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Benito Villamarin', 0, '2021-10-20', '2022-12-31', 1, 1);
INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Acuario de Sevilla', 1, '2020-10-20', '2021-12-20', 2, 1);
INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Oficinas Sabadell', 2, '2019-10-20', '2020-12-31', 1, 2);
INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Teatro la Maestranza', 2, '2019-10-20', '2020-12-31', 0, 2);
INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Ayuntamiento Sanlucar la Mayor', 3, '2019-10-20', '2021-02-28', 2, 3);
INSERT INTO servicio(lugar, tipocategoria, fechainicio, fechafin, estado, cliente_id) VALUES ('Ramon Sanchez Pizjuan', 3, '2019-10-20', '2021-02-28', 1, 3);

INSERT INTO presupuesto(precio, tipopresupuesto, servicio_id, estado) VALUES (4579.5, 1, 1, 2);
INSERT INTO presupuesto(precio, tipopresupuesto, servicio_id, estado) VALUES (50.0, 0, 1, 1);
INSERT INTO presupuesto(precio, tipopresupuesto, servicio_id, estado) VALUES (123.12, 0, 2, 0);
INSERT INTO presupuesto(precio, tipopresupuesto, servicio_id, estado) VALUES (18000.0, 1, 2, 1);
INSERT INTO presupuesto(precio, tipopresupuesto, servicio_id, estado) VALUES (100.0, 1, 4, 1);
INSERT INTO presupuesto(precio, tipopresupuesto, servicio_id, estado) VALUES (50.0, 1, 7, 1);

INSERT INTO contratoservicio(fechainicial, fechafinal, fechapago,  periodoprueba, presupuesto) VALUES ('2019-10-20', '2020-12-31', '2020-12-10',0, 2);
INSERT INTO contratoservicio(fechainicial, fechafinal, fechapago, periodoprueba, presupuesto) VALUES ('2019-10-20', '2021-02-26', '2020-12-10', 0, 4);
INSERT INTO contratoservicio(fechainicial, fechafinal, periodoprueba, presupuesto) VALUES ('2020-10-20', '2021-10-20', 1, 5);

INSERT INTO reclamacion(fecha, descripcion, cliente_id, servicio_id) VALUES ('2019-10-20', 'El servicio está incompleto', 1, 1);
INSERT INTO reclamacion(fecha, descripcion, cliente_id, servicio_id) VALUES ('2019-10-20', 'El servicio está lamentable', 2, 2);

INSERT INTO valoracion(fecha,valor, servicio_id) VALUES ('2019-10-20', 4, 1);
INSERT INTO valoracion(fecha,valor, servicio_id) VALUES ('2020-10-12', 5, 2);
INSERT INTO valoracion(fecha,valor, servicio_id) VALUES ('2019-10-20', 4, 3);
INSERT INTO valoracion(fecha,valor, servicio_id) VALUES ('2020-10-12', 5, 4);
INSERT INTO valoracion(fecha,valor, servicio_id) VALUES ('2020-10-12', 5, 6);

INSERT INTO mensaje(id, fecha, asunto, cuerpo, emisor, leido) VALUES (1, '2021-02-02', 'Hola que tal', 'Hola como estamos que tal', 'JosePabloSL', FALSE);
INSERT INTO mensaje(id, fecha, asunto, cuerpo, emisor, leido) VALUES (2, '2021-02-02', 'Muy bien gracias', 'Genial, y tu?', 'admin', TRUE);

INSERT INTO aux VALUES ('admin', 1);
INSERT INTO aux VALUES ('JosePabloSL', 2);
INSERT INTO aux VALUES ('JoseCarlos', 2);

INSERT INTO aux_ts VALUES (1, 1);
INSERT INTO aux_ts VALUES (2, 1);
INSERT INTO aux_ts VALUES (2, 2);
INSERT INTO aux_ts VALUES (3, 2);