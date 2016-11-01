package co.edu.eam.ingesoft.videotienda.persistencia.dao;

public class ConstantesNamedQueries {

	/**
	 * consulta para listar todos los paises
	 */
	public static final String CONSULTA_LISTARPAISES = "Pais.listartodos";

	public static final String CONSULTA_LISTARCIUDADES = "Ciudad.ListarTodos";

	public static final String CONSULTA_LISTAREMPLEADOS = "Empleado.ListarTodos";

	/**
	 * <<<<<<< HEAD consulta para listar datos de las peliculas por nombre de la
	 * pelicula ======= consulta para listar datos de las peliculas.. >>>>>>>
	 * branch 'master' of https://github.com/caferrerb/videotiendaDlloeam4.git
	 */
	public static final String CONSULTA_POR_NOMBRE_PELICULA = "Film.listarNombrePelicula";

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

	/**
	 * consulta que lista los datos de la tabla rentas en la ventana reporte
	 */
	public static final String CONSULTA_LISTAR_DATOS_RENTA = "datosTablaRental.Listar";

	/**
	 * consulta que lista los datos de la tabla ventas en la ventana reporte
	 */
	public static final String CONSULTA_LISTAR_DATOS_VENTA = "datosTablaVentas.Listar";
}
