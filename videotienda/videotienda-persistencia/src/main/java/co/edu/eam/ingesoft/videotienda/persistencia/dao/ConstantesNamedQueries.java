package co.edu.eam.ingesoft.videotienda.persistencia.dao;

public class ConstantesNamedQueries {

	/**
	 * consulta para listar todos los paises
	 */
	public static final String CONSULTA_LISTARPAISES = "Pais.listartodos";

	public static final String CONSULTA_LISTARCIUDADES = "Ciudad.ListarTodos";

	public static final String CONSULTA_LISTAREMPLEADOS = "Empleado.ListarTodos";
	
	/**
	 * Obtiene las peliculas alquiladas por un cliente
	 */
	public static final String CONSULTA_LISTAR_PELICULAS_CLIENTE = "Rental.PeliculasCliente";

	/**
	 * <<<<<<< HEAD consulta para listar datos de las peliculas por nombre de la
	 * pelicula ======= consulta para listar datos de las peliculas.. >>>>>>>
	 * branch 'master' of https://github.com/caferrerb/videotiendaDlloeam4.git
	 */
	public static final String CONSULTA_POR_NOMBRE_PELICULA = "Film.listarNombrePelicula";
	
	public static final String CONSULTA_LISTAR_PELICULAS_NOMBRES = "Film.listarPorNombrePelis";

	/**
	 * Consulta para listar todos los roles
	 */
	public static final String CONSULTA_LISTAR_ROLES="Rol.ListarTodos";
	
	public static final String CONSULTA_LISTAR_ROLES_POR_USUARIO="UsuarioRol.ListarRolesPorUsuario";
	
	public static final String CONSULTA_LISTAR_USUARIO_POR_ROL="UsuarioRol.ListarPorRol";
	
	public static final String CONSULTA_LISTAR_AUTORES = "Actor.ListarTodos";

	public static final String CONSULTA_LISTAR_LENGUAJES = "Language.ListarTodos";

	public static final String CONSULTA_LISTAR_TIENDAS = "Tienda.ListarTodos";

	public static final String CONSULTA_LISTAR_EMPLEADOS_TIENDA = "EmpleadosTienda.ListarTodos";
	
	public static final String CONSULTA_LISTAR_INVENTARIO_TIENDA = "InventarioTienda.ListarTodos";

	/**
	 * consulta que lista los datos de la tabla rentas en la ventana reporte
	 */
	public static final String CONSULTA_LISTAR_DATOS_RENTA = "datosTablaRental.Listar";

	/**
	 * consulta que lista los datos de la tabla ventas en la ventana reporte
	 */
	public static final String CONSULTA_LISTAR_DATOS_VENTA = "datosTablaVentas.Listar";
	
	/**
	 * Consulta para listar todos las pantallas  
	 */
	public static final String CONSULTA_LISTAR_PANTALLAS="Acesso.ListarTodos";
	
	public static final String CONSULTA_LISTAR_FILM_ACTOR_POR_ID_FILM="FilmActor.ListarTodos";

	public static final String CONSULTAR_DATOS_CLIENTE ="listarDatosClientes";
	

	public static final String CONSULTA_CATEGORIAS_FILM ="Category.listarTodas";

	public static final String CONSULTA_FILM_POR_ACTOR ="listarPeliculasPorActor";

	
	/**
	 * Consulta para cargar todos los datos de la tabla de peliculas rentadas
	 */
	public static final String CONSULTA_TABLA_PELICULA_RENTADAS="datosTablaFilmRentada";
	
	public static final String CONSULTA_LISTAR_ACCESOS_ROL="AccesoRol.ListarPantallasRol";


	/**
	 * Consulta que lista todos accesosrol de un rol determinado
	 */
	public static final String CONSULTA_LISTAR_TODO_ACCESOS_ROL="AccesoRol.ListarAccesoRol";
	
	
	/**
	 * Consulta que lista todos accesosrol existentes
	 */
	public static final String CONSULTA_VALIDAR_USUARIO="Usuario.BuscarUsuario";

	
	/**
	 * Suma las horas iniciales de un empleado n
	 */
	public static final String  SUMA_HORA_INICIAL = "StaffSchedule.ListaHorasEmpleado";

}
