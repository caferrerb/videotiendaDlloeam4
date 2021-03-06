package co.edu.eam.ingesoft.videotienda.persistencia.dao;

public class ConstantesNamedQueries {

	/**
	 * consulta para listar todos los paises
	 */
	public static final String CONSULTA_LISTARPAISES = "Pais.listartodos";

	public static final String CONSULTA_LISTARCIUDADES = "Ciudad.ListarTodos";

	public static final String CONSULTA_LISTAREMPLEADOS = "Empleado.ListarTodos";
	
	/**
	 * Consulta para listar todas las categorias (Generos)
	 */
	public static final String CONSULTA_LISTAR_GENEROS = "Category.listarCategorias";
	
	/**
	 * Consulta para buscar genero por nombre
	 */
	public static final String BUSCAR_GENERO_POR_NOMBRE = "Category.buscarPorNombre";
	
	/**
	 * Consulta para Listar todas las peliculas de un determinado genero
	 */
	public static final String CONSULTA_LISTAR_FILM_POR_CATEGORIA = "FilmCategory.listarPorCategoria";
	
	/**
	 * Consulta para Listar todos los actores que hallan trabajado en peliculas de un determinado genero
	 */
	public static final String CONSULTA_LISTAR_ACTOR_POR_CATEGORIA = "FilmActor.listarPorCategoriaActor";
	
	public static final String CONSULTA_BUSCAR_EMPLEADOS_POR_USUARIO = "Empleado.buscarEmpleadoPorUsuario";
	
	/**
	 * Lista los inventarios de una pel�cula
	 * ?1: La pel�cula
	 */
	public static final String CONSULTA_LISTA_INVENTARIO_PELICULA = "Inventory.listarInventarioPelicula";


	
	/**
	 * Obtiene las peliculas alquiladas por un cliente
	 *  El cliente
	 */
	public static final String CONSULTA_LISTAR_PELICULAS_CLIENTE = "Rental.PeliculasCliente";
	
	/**
	 * Obtiene las peliculas alquiladas por un cliente
	 * ?1: El cliente
	 */
	public static final String CONSULTA_LISTAR_FECHAS_CLIENTE = "Rental.fechaEntregaCliente";

	/**
	 * consulta para listar datos de las peliculas por nombre de la pelicula 
	 */

	public static final String CONSULTA_POR_NOMBRE_PELICULA = "Film.listarNombrePelicula";
	
    /**
     * 
     */
	public static final String CONSULTA_LISTAR_PELICULAS_NOMBRES = "Film.listarPorNombrePelis";
	
	public static final String CONSULTA_LISTAR_PELICULAS_PRESTADAS = "Rental.PeliculaPrestada";
	
	/**
	 * Obtine la lista de prestamos de un cliente
	 */
	public static final String CONSULTA_LISTAR_PRESTAMOS_CLIENTE = "Rental.listarPrestamosClientes";
	
	 /**
	  * 
	  */
	public static final String CONSULTA_LISTAR_INFO_PRESTAMOS = "Rental.TituloNombrePrestamos";
	
	/**
	 * Obtiene la lista de prestamos de un cliente
	 * ?1: El cliente
	 */
	public static final String CONSULTA_PELICULAS_RENTADAS_CLIENTE = "Rental.PeliculasRentadasCliente";
	
	 /**
	  * 
	  */
	public static final String CONSULTA_LISTAR_PRESTAMOS_DEL_CLIENTE = "Cliente.PrestamosCliente";
	
	/**
	 * obtiene los prestamos que esten repetidos
	 */
	public static final String CONSULTA_PRESTAMOS_REPETIDOS = "Rental.consultaRepetidos";
	
	/**
	 * obtiene la fecha de entrega de un titulo 
	 */
	public static final String CONSULTA_FECHA_ENTREGA_PELICULA = "Rental.fechaEntregaPelicula";

	/**
	 * Consulta para listar todos los roles
	 */
	public static final String CONSULTA_LISTAR_ROLES = "Rol.ListarTodos";

	public static final String CONSULTA_LISTAR_ROLES_POR_USUARIO = "UsuarioRol.ListarRolesPorUsuario";

	public static final String CONSULTA_LISTAR_USUARIO_POR_ROL = "UsuarioRol.ListarPorRol";

	public static final String CONSULTA_LISTAR_AUTORES = "Actor.ListarTodos";

	public static final String CONSULTA_LISTAR_LENGUAJES = "Language.ListarTodos";

	public static final String CONSULTA_LISTAR_TIENDAS = "Tienda.ListarTodos";

	public static final String CONSULTA_LISTAR_EMPLEADOS_TIENDA = "EmpleadosTienda.ListarTodos";

	public static final String CONSULTA_LISTAR_INVENTARIO_TIENDA = "InventarioTienda.ListarTodos";
	
	public static final String CONSULTA_LISTAR_INVENTARIO_PELICULA = "InventarioPelicula.ListarTodos";


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
	public static final String CONSULTA_LISTAR_PANTALLAS = "Acesso.ListarTodos";

	public static final String CONSULTA_LISTAR_FILM_ACTOR_POR_ID_FILM = "FilmActor.ListarTodos";

	public static final String CONSULTAR_DATOS_CLIENTE = "listarDatosClientes";
	

	public static final String CONSULTA_CATEGORIAS_FILM ="Category.listarTodas";

	public static final String CONSULTA_FILM_POR_ACTOR ="listarPeliculasPorActor";
	public static final String CONSULTA_FILMACTOR_POR_ACTOR = "listarFilmActorPorActor";

	
	/**
	 * Consulta para cargar todos los datos de la tabla de peliculas rentadas
	 */

	public static final String CONSULTA_TABLA_PELICULA_RENTADAS="Rental.datosTablaFilmRentada";
	
	public static final String CONSULTA_LISTAR_ACCESOS_ROL="AccesoRol.ListarPantallasRol";





	/**
	 * Consulta que lista todos accesosrol de un rol determinado
	 */
	public static final String CONSULTA_LISTAR_TODO_ACCESOS_ROL = "AccesoRol.ListarAccesoRol";

	/**
	 * Consulta que lista todos accesosrol existentes
	 */
	public static final String CONSULTA_VALIDAR_USUARIO = "Usuario.BuscarUsuario";

	/**
	 * consulta que suma el valor de las ventas realizadas
	 */
	public static final String CONSULTA_TOTALVENTAS = "Sale.TotalVentas";

	
	/**
	 * consulta que suma el valor de las Rentas realizadas
	 */
	public static final String CONSULTA_TOTALRENTAS = "Rental.TotalRentas";


	/**
	 * Horaras empelado
	 */
	public static final String  CONSULTA_HORARIO_EMPLEADO = "StaffSchedule.ListaHorasEmpleado";

	/**
	 * Horario empleado
	 */
	public static final String  CONSULTA_HORARIO = "StaffSchedule.ListaHorarios";
	
}
