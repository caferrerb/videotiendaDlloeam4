INSERT INTO `t_acceso` (`ID_ACCESO`, `NOMBRE`, `PANTALLA`) VALUES ('1', 'Inicio', 'Inicio');
INSERT INTO `t_acceso` (`ID_ACCESO`, `NOMBRE`, `PANTALLA`) VALUES ('2', 'Peliculas', 'Peliculas');
INSERT INTO `t_acceso` (`ID_ACCESO`, `NOMBRE`, `PANTALLA`) VALUES ('3', 'Clientes', 'Clientes');
INSERT INTO `t_acceso` (`ID_ACCESO`, `NOMBRE`, `PANTALLA`) VALUES ('4', 'Actores', 'Actores');
INSERT INTO `t_acceso` (`ID_ACCESO`, `NOMBRE`, `PANTALLA`) VALUES ('5', 'Generos', 'Generos');
INSERT INTO `t_acceso` (`ID_ACCESO`, `NOMBRE`, `PANTALLA`) VALUES ('6', 'Empleados', 'Empleados');
INSERT INTO `t_acceso` (`ID_ACCESO`, `NOMBRE`, `PANTALLA`) VALUES ('7', 'Prestamos', 'Prestamos');
INSERT INTO `t_acceso` (`ID_ACCESO`, `NOMBRE`, `PANTALLA`) VALUES ('8', 'Parametrizacion', 'Parametrizacion');
INSERT INTO `t_acceso` (`ID_ACCESO`, `NOMBRE`, `PANTALLA`) VALUES ('9', 'Tiendas', 'Tiendas');
INSERT INTO `t_acceso` (`ID_ACCESO`, `NOMBRE`, `PANTALLA`) VALUES ('10', 'Autorizacion', 'Autorizacion');
INSERT INTO `t_acceso` (`ID_ACCESO`, `NOMBRE`, `PANTALLA`) VALUES ('11', 'Reportes', 'Reportes');
INSERT INTO `film` (`film_id`, `description`, `last_update`, `length`, `rating`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('3', 'Latin Awards', '2016-11-09 21:59:46', '1', 'S', '2016-11-16', '11', '6443', '34554', 'Jhan Glock', 'La Suicida', '1', '2', '1');
INSERT INTO `country` (`country_id`, `country`, `last_update`) VALUES ('1', 'Colombia', '2016-10-06');
INSERT INTO `city` (`city_id`, `city`, `country_id`) VALUES ('1', 'Armenia', '1');
INSERT INTO `city` (`city_id`, `city`, `country_id`) VALUES ('2', 'Bogota', '1');
INSERT INTO `address` (`address`, `address2`, `district`, `last_update`, `phone`, `postal_code`, `city_id`) VALUES ('B/ laureles', 'null', 'comuna 3', '2007-10-15', '320735', '2672762', '1');
INSERT INTO `address` (`address`, `address2`, `district`, `last_update`, `phone`, `postal_code`, `city_id`) VALUES ('B/ las americas', 'null', 'comuna 7', '2010-11-15', '72939', '53562', '1');
INSERT INTO `store` (`store_id`, `last_update`, `Nombre_tienda`, `address_id`) VALUES ('17', '2010-10-26', 'Video net', '1');
INSERT INTO `store` (`store_id`, `last_update`, `Nombre_tienda`, `address_id`) VALUES ('23', '2014-10-26', 'Video Armenia', '2');
INSERT INTO `language` (`language_id`, `last_update`, `name`) VALUES ('3', '2016-04-04 00:00:00', 'Espa�ol');
INSERT INTO `language` (`language_id`, `last_update`, `name`) VALUES ('1', '2016-04-04 00:00:00', 'Ingles');
INSERT INTO `category` (`category_id`, `last_update`, `name`) VALUES ('3', '2016-04-04 00:00:00', 'romantica');
INSERT INTO `category` (`category_id`, `last_update`, `name`) VALUES ('4', '2016-04-04 00:00:00', 'humor');
INSERT INTO `category` (`category_id`, `last_update`, `name`) VALUES ('5', '2016-04-04 00:00:00', 'accion');
INSERT INTO `film` (`film_id`, `description`, `last_update`, `length`, `rating`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('123', 'burna', '2016-04-04 08:51:04', '1', '1', '2016-11-10', '12', '12000', '156000', 'as', 'bajo la misma estrella', '3', '1', '1');
INSERT INTO `film` (`film_id`, `description`, `last_update`, `length`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('24', 'buena', '2016-11-07 15:50:21', '1', '2016-11-10', '11', '10000', '23000', 'as', 'la purga', '4', '1', '1');
INSERT INTO `film` (`film_id`, `description`, `last_update`, `length`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('2', 'buena', '2016-11-07 15:50:21', '1', '2016-11-10', '11', '10000', '23000', 'as', 'ciudades de papel', '3', '1', '1');
INSERT INTO `film` (`film_id`, `description`, `last_update`, `length`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('124', 'buena', '2016-11-07 15:50:21', '1', '2016-11-10', '11', '10000', '23000', 'as', 'correr o morir', '5', '1', '1');
INSERT INTO `t_rol` (`ID_ROL`, `DESCRIPCION`) VALUES ('1', 'Administrador');
INSERT INTO `t_rol` (`ID_ROL`, `DESCRIPCION`) VALUES ('2', 'Cliente');
INSERT INTO `t_rol` (`ID_ROL`, `DESCRIPCION`) VALUES ('3', 'Empleado');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('1', '1');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('1', '2');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('1', '3');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('2', '1');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('3', '1');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('4', '3');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('5', '3');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('6', '3');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('7', '3');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('8', '1');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('9', '1');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('10', '1');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('11', '1');
INSERT INTO `t_usuario` (`USUARIO`, `PASS`) VALUES ('admin', '1234');
INSERT INTO `t_usuario` (`USUARIO`, `PASS`) VALUES ('empleado', '1234');
INSERT INTO `t_usuario_rol` (`ID_ROL`, `ID_USUARIO`) VALUES ('1', 'admin');
INSERT INTO `t_usuario_rol` (`ID_ROL`, `ID_USUARIO`) VALUES ('3', 'empleado');
INSERT INTO staff (staff_id, active, email, first_name, last_name, last_update, address_id) VALUES (1, 1, 'juju', 'hghj', 'hb', '2015-04-03', '1')
UPDATE `staff` SET `id_usuario`='admin' WHERE `staff_id`='1';

