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
INSERT INTO `language` (`language_id`, `last_update`, `name`) VALUES ('3', '2016-04-04 00:00:00', 'Español');
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
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('4', '1');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('5', '1');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('6', '1');
INSERT INTO `t_acceso_rol` (`ID_ACCESO`, `ID_ROL`) VALUES ('7', '1');
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
INSERT INTO `VideoTienda`.`country` (`country_id`, `country`, `last_update`) VALUES ('43', 'Colombia', '2000-8-9');
INSERT INTO `VideoTienda`.`country` (`country_id`, `country`, `last_update`) VALUES ('34', 'USA', '2000-8-8');
INSERT INTO `VideoTienda`.`country` (`country_id`, `country`, `last_update`) VALUES ('23', 'España', '2000-3-3');
INSERT INTO `VideoTienda`.`country` (`country_id`, `country`, `last_update`) VALUES ('32', 'Alemania', '2000-2-2');

INSERT INTO `VideoTienda`.`city` (`city_id`, `city`, `country_id`) VALUES ('12', 'Armenia', '43');
INSERT INTO `VideoTienda`.`city` (`city_id`, `city`, `country_id`) VALUES ('21', 'Brandenton', '34');
INSERT INTO `VideoTienda`.`city` (`city_id`, `city`, `country_id`) VALUES ('56', 'Berlin', '32');
INSERT INTO `VideoTienda`.`city` (`city_id`, `city`, `country_id`) VALUES ('65', 'Madrid', '23');

INSERT INTO `VideoTienda`.`address` (`address_id`, `address`, `address2`, `district`, `last_update`, `phone`, `postal_code`, `city_id`) VALUES ('321', 'lakdn', 'knads', '2', '2000-2-2', '0983921', '912', '12');
INSERT INTO `VideoTienda`.`address` (`address_id`, `address`, `address2`, `district`, `last_update`, `phone`, `postal_code`, `city_id`) VALUES ('123', 'sajk', 'skand', '1', '2000-3-3', '23213', '345', '21');
INSERT INTO `VideoTienda`.`address` (`address_id`, `address`, `address2`, `district`, `last_update`, `phone`, `postal_code`, `city_id`) VALUES ('231', 'asnd', 'qjnd', '2', '2000-4-4', '91829', '290', '56');
INSERT INTO `VideoTienda`.`address` (`address_id`, `address`, `address2`, `district`, `last_update`, `phone`, `postal_code`, `city_id`) VALUES ('312', 'iwndjas', 'wqjnd', '1', '2000-5-5', '31423', '453', '65');

INSERT INTO `VideoTienda`.`store` (`store_id`, `last_update`, `address_id`) VALUES ('432', '2000-1-1', '123');
INSERT INTO `VideoTienda`.`store` (`store_id`, `last_update`, `address_id`) VALUES ('234', '2000-2-2', '231');
INSERT INTO `VideoTienda`.`store` (`store_id`, `last_update`, `address_id`) VALUES ('342', '2000-3-3', '312');
INSERT INTO `VideoTienda`.`store` (`store_id`, `last_update`, `address_id`) VALUES ('324', '2000-8-8', '321');

INSERT INTO `VideoTienda`.`language` (`language_id`, `last_update`, `name`) VALUES ('2', '2000-3-3', 'Español');
INSERT INTO `VideoTienda`.`language` (`language_id`, `last_update`, `name`) VALUES ('4', '2000-5-5', 'Aleman');

INSERT INTO `VideoTienda`.`category` (`category_id`, `last_update`, `name`) VALUES ('54', '2000-3-3', 'Accion');
INSERT INTO `VideoTienda`.`category` (`category_id`, `last_update`, `name`) VALUES ('32', '2000-4-4', 'Comedia');
INSERT INTO `VideoTienda`.`category` (`category_id`, `last_update`, `name`) VALUES ('91', '2000-6-6', 'Horror');
INSERT INTO `VideoTienda`.`category` (`category_id`, `last_update`, `name`) VALUES ('81', '2000-4-4', 'Suspenso');

INSERT INTO `VideoTienda`.`film` (`film_id`, `description`, `last_update`, `length`, `rating`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('9', 'asjkjks', '2000-3-3', '23', '3', '2000-4-4', '23', '12', '213', 'sakdnk', 'Civil War', '54', '1', '1');
INSERT INTO `VideoTienda`.`film` (`film_id`, `description`, `last_update`, `length`, `rating`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('8', 'akdjsd', '2000-1-1', '44', '4', '2000-3-3', '21', '23', '656', 'sadkjn', 'Doctor Strange', '54', '1', '1');
INSERT INTO `VideoTienda`.`film` (`film_id`, `description`, `last_update`, `length`, `rating`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('7', 'kqnjkd', '2000-2-2', '43', '4', '2000-5-5', '24', '21', '675', 'asdkln', 'El Coco', '32', '2', '2');
INSERT INTO `VideoTienda`.`film` (`film_id`, `description`, `last_update`, `length`, `rating`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('6', 'sadkl', '2000-4-4', '64', '5', '2000-7--7', '67', '98', '123', 'sajndksa', 'Age Of Ultran', '54', '3', '1');
INSERT INTO `VideoTienda`.`film` (`film_id`, `description`, `last_update`, `length`, `rating`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('10', 'sdkda', '2000-04-04', '33', '5', '2000-09-09', '43', '211', '324', 'qkjd', 'Avengers', '54', '1', '1');
INSERT INTO `VideoTienda`.`film` (`film_id`, `description`, `last_update`, `length`, `rating`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('11', 'aasjkdhjk', '2000-05-05', '33', '5', '2000-08-08', '43', '213', '34', 'asdjk', 'Winter Soldier', '54', '1', '1');
INSERT INTO `VideoTienda`.`film` (`film_id`, `description`, `last_update`, `length`, `rating`, `release_year`, `rental_duration`, `rental_rate`, `replacement_cost`, `special_features`, `title`, `category`, `language_id`, `original_language_id`) VALUES ('12', 'askdl', '2000-06-06', '33', '5', '2000-10-10', '43', '23', '234', 'asmdj', 'Ant-Man', '54', '1', '1');

INSERT INTO `VideoTienda`.`staff` (`staff_id`, `active`, `email`, `first_name`, `last_name`, `last_update`, `address_id`, `store_id`) VALUES ('18', 1, 'sadkns', 'Pedro', 'Morientes', '2000-3-3', '123', '234');
INSERT INTO `VideoTienda`.`staff` (`staff_id`, `active`, `email`, `first_name`, `last_name`, `last_update`, `address_id`, `store_id`) VALUES ('19', 1, 'sadklknal', 'Juliana', 'Arias', '2000-1-1', '231', '324');
INSERT INTO `VideoTienda`.`staff` (`staff_id`, `active`, `email`, `first_name`, `last_name`, `last_update`, `address_id`, `store_id`) VALUES ('17', 1, 'asjasjdº', 'Lucho', 'Bolivar', '2000-4-4', '312', '342');
INSERT INTO `VideoTienda`.`staff` (`staff_id`, `active`, `email`, `first_name`, `last_name`, `last_update`, `address_id`, `store_id`) VALUES ('16', 1, 'asnnjaks', 'Daniel', 'Bolivar', '2000-2-2', '321', '432');

INSERT INTO `VideoTienda`.`customer` (`customer_id`, `active`, `create_date`, `email`, `first_name`, `last_name`, `last_update`, `address_id`, `store_id`) VALUES ('989', 1, '2000-1-1', 'aslkdn', 'Juliana', 'Arias', '2000-9-9', '123', '234');
INSERT INTO `VideoTienda`.`customer` (`customer_id`, `active`, `create_date`, `email`, `first_name`, `last_name`, `last_update`, `address_id`, `store_id`) VALUES ('898', 1, '2000-2-2', 'sjkas', 'Lucho', 'Bolivar', '2000-4-4', '231', '324');
INSERT INTO `VideoTienda`.`customer` (`customer_id`, `active`, `create_date`, `email`, `first_name`, `last_name`, `last_update`, `address_id`, `store_id`) VALUES ('787', 1, '2000-1-1', 'sadnjk', 'Daniel', 'Bolivar', '2000-6-6', '312', '342');
INSERT INTO `VideoTienda`.`customer` (`customer_id`, `active`, `create_date`, `email`, `first_name`, `last_name`, `last_update`, `address_id`, `store_id`) VALUES ('878', 1, '2000-6-6', 'qwhd', 'Claudia', 'Bolivar', '2000-5-5', '321', '432');

INSERT INTO `VideoTienda`.`inventory` (`inventory_id`, `last_update`, `film_id`, `store_id`) VALUES ('12', '2000-1-1', '6', '234');
INSERT INTO `VideoTienda`.`inventory` (`inventory_id`, `last_update`, `film_id`, `store_id`) VALUES ('32', '2000-2-2', '10', '324');
INSERT INTO `VideoTienda`.`inventory` (`inventory_id`, `last_update`, `film_id`, `store_id`) VALUES ('43', '2000-3-3', '8', '342');
INSERT INTO `VideoTienda`.`inventory` (`inventory_id`, `last_update`, `film_id`, `store_id`) VALUES ('56', '2000-4-4', '9', '432');

INSERT INTO `VideoTienda`.`rental` (`rental_id`, `last_update`, `rental_date`, `return_date`, `customer_id`, `inventory_id`, `staff_id`) VALUES ('21', '2000-8-8', '2000-5-5', '2001-2-2', '787', '12', '16');
INSERT INTO `VideoTienda`.`rental` (`rental_id`, `last_update`, `rental_date`, `return_date`, `customer_id`, `inventory_id`, `staff_id`) VALUES ('31', '2000-9-9', '2000-6-6', '2001-3-3', '878', '32', '17');
INSERT INTO `VideoTienda`.`rental` (`rental_id`, `last_update`, `rental_date`, `return_date`, `customer_id`, `inventory_id`, `staff_id`) VALUES ('41', '2000-10-10', '2000-7-7', '2001-4-4', '898', '43', '18');
INSERT INTO `VideoTienda`.`rental` (`rental_id`, `last_update`, `rental_date`, `return_date`, `customer_id`, `inventory_id`, `staff_id`) VALUES ('51', '2000-11-11', '2000-8-8', '2001-5-5', '898', '56', '19');

