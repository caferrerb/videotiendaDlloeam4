package co.edu.eam.ingesoft.videotienda.vista.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOAccesoRol;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuarioRol;
import co.edu.eam.ingesoft.videotienda.main.ContextFactory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarActores;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarCIudad;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarClientes;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarEmpleado;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarPelicula;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorAlquilarPelicula;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorEjemploTabla;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionInventario;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarTienda;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarVenta;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorSeguridad;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorReporteVentaAlquiler;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorVentanaTrasladarCiudad;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorVerPeliculasRentadas;
import co.edu.eam.ingesoft.videotienda.vista.controladores.CrearPeliculaController;
import co.edu.eam.ingesoft.videotienda.vista.controladores.LoginUsuarioController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * Ventana principal de la aplicaicon
 *
 * @author Camilo Andres Ferrer Bustso.
 *
 */
@SuppressWarnings("restriction")
public class MainController implements Initializable {

	/**
	 * Contenedor de las ventanas.
	 */
	@FXML
	private Pane panelCentro;

	/**
	 * Menu equipos
	 */
	@FXML
	public Menu mnuInicio;

	/**
	 * Menu prestamos
	 */
	@FXML
	public Menu mnuPrestamos;
	
	/**
	 * Menu peliculas
	 */
	@FXML
	public Menu mnuPeliculas;
	

	/**
	 * Menu inventario
	 */
	@FXML
	public Menu mnuInventario;

	/**
	 * Menu reportes
	 */
	@FXML
	public Menu menuReportes;
	
	@FXML
	public Menu mnuTiendas;
	
	@FXML
	public Menu mnuSucursales;
	
	
	/**
	 * Menu actores
	 */
	@FXML
	public Menu mnuActores;

	/**
	 * Menu generos
	 */
	@FXML
	public Menu mnuGeneros;

	/**
	 * Menu equipos
	 */
	@FXML
	public Menu menuCliente;

	@FXML
	public Menu mnuParametrizacion;
	
	/**
	 * Menu equipos
	 */
	@FXML
	public Menu mnuEmpleados;

	@FXML
	public Menu mnuAutorizacion;

	/**
	 * item de iniciar sesion.
	 */

	@FXML
	private MenuItem mnuItemGestionarCiudad;

	@FXML
	private MenuItem mnuItemGestionarInventario;
	
	@FXML
	private MenuItem mnuItemGestionarActores;

	@FXML
	private MenuItem mIVenderPeliculas;
	
	@FXML
	private MenuItem mIVerPeliculasRentadas;

	@FXML
	private AnchorPane contenido;

	@FXML
	private MenuItem mnuItemGestionarEmpleado;

	@FXML
	private MenuItem menuItemGestionarPelicula;

	@FXML
	private MenuItem mnuItemRoles;

	@FXML
	private MenuItem menuItemTraslado;

	@FXML
	private MenuItem menuItemVerPeliculasRentadas;

	@FXML
	private MenuItem MenuItemReportes;

	@FXML
	private MenuItem menuItemGestionPrestamo;
	
	@FXML
	private MenuItem menuItemEjemplo; 
	
	public Button btnCerrarSesion;
	
	/**
	 * Inicializacion de la ventana.
	 */
	public void initialize(URL arg0, ResourceBundle arg1) {

		boton();
		esconderMenus();
		agregarVentana("/fxml/Login.fxml", LoginUsuarioController.class);

	}

	/**
	 * MEtodo para agregar una ventana.
	 *
	 * @param url,
	 *            ruta del archivo xml de la vista.
	 */
	public void agregarVentana(String pagina, Class controller) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pagina));
			fxmlLoader.setResources(ResourceBundle.getBundle("i18n.mensajes"));

			BaseController control = fxmlLoader.<BaseController> getController();

			ApplicationContext context = ContextFactory.getContext();
			BaseController controlador = (BaseController) context.getBean(controller);
			controlador.init(this);
			fxmlLoader.setController(controlador);
			AnchorPane cmdPane = (AnchorPane) fxmlLoader.load();
			contenido.getChildren().setAll(cmdPane);

			// panelCentro.getChildren().add(window);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * + Metodo para esconder los menus.
	 *
	 * @author Camilo Andres Ferrer Bustos<br/>
	 *         email: caferrerb@gmail.com <br/>
	 *         Fecha: 19/10/2015<br/>
	 */
	public void esconderMenus() {
		mnuInicio.setVisible(false);
		mnuPeliculas.setVisible(false);
		mnuActores.setVisible(false);
		mnuGeneros.setVisible(false);
		menuCliente.setVisible(false);
		mnuEmpleados.setVisible(false);
		mnuAutorizacion.setVisible(false);
		mnuParametrizacion.setVisible(false);
		menuReportes.setVisible(false);
		mnuTiendas.setVisible(false);
		mnuPrestamos.setVisible(false);
		mnuSucursales.setVisible(false);
		btnCerrarSesion.setVisible(false);
		mnuItemGestionarInventario.setVisible(false);
	}

	/**
	 * + Metodo para mostrar los menus.
	 *
	 * @author Camilo Andres Ferrer Bustos<br/>
	 *         email: caferrerb@gmail.com <br/>
	 *         Fecha: 19/10/2015<br/>
	 */
	public void mostrarMenus(){

	}

	@FXML
	public void abrirGEstionarCiudad() {
		agregarVentana("/fxml/gestionarciudad.fxml", ControladorGestionarCIudad.class);
	}
	
	@FXML
	public void abrirGestionarInventario() {
		agregarVentana("/fxml/GestionarInventario.fxml", ControladorGestionInventario.class);
	}

	@FXML
	public void abrirTrasladarCliente() {
		agregarVentana("/fxml/VentanaTrasladarCiudadCliente.fxml", ControladorVentanaTrasladarCiudad.class);
	}

	@FXML
	public void abrirGestionarActores() {
		agregarVentana("/fxml/GestionarActores.fxml", ControladorGestionarActores.class);

	}

	@FXML
	public void abrirGestionarVenta() {
		agregarVentana("/fxml/GestionarVentaPeliculas.fxml", ControladorGestionarVenta.class);
	}

	@FXML
	public void abrirGestionarEmpleados() {
		agregarVentana("/fxml/VentanaGestionEmpleados.fxml", ControladorGestionarEmpleado.class);
	}

	@FXML
	public void abrirGestionarPeliculas() {
		agregarVentana("/fxml/VentanaGestionarPeliculas.fxml", ControladorGestionarPelicula.class);
	}

	@FXML
	public void abrirGestionarTienda() {
		agregarVentana("/fxml/VentanaGestionarTienda.fxml", ControladorGestionarTienda.class);
	}

	@FXML
	public void abrirAutorizacion() {
		agregarVentana("/fxml/Seguridad.fxml", ControladorSeguridad.class);
	}

	@FXML
	public void abrirGestionReportesVentasAlquiler() {
		agregarVentana("/fxml/VentanaReportesVentasAlquiler.fxml", ControladorReporteVentaAlquiler.class);
	}

	@FXML
	public void abrirverpeliculasrentadas() {
		agregarVentana("/fxml/verpeliculasrentadas.fxml", ControladorVerPeliculasRentadas.class);
	}
	
	@FXML
	public void abrirInicio() {
		agregarVentana("/fxml/Inicio.fxml", LoginUsuarioController.class);
	}
	
	@FXML
	public void abrirGestionarPrestamos(){
		agregarVentana("/fxml/AlquilarPelicula.fxml", ControladorAlquilarPelicula.class);
	}
	
	@FXML
	public void cerrarSesion(){
		agregarVentana("/fxml/Login.fxml", LoginUsuarioController.class);
		esconderMenus();
	}
	
	@FXML
	public void abrirEjemplo(){
		agregarVentana("/fxml/ejemplotabla.fxml", ControladorEjemploTabla.class);
	}
	
	public void boton(){
		mnuInicio.getItems().add(new MenuItem("Inicio"));
	}
	
	@FXML
	public void abrirGestionarClientes(){
		agregarVentana("/fxml/VentanaGestionarClientes.fxml", ControladorGestionarClientes.class);
	}

	
}
