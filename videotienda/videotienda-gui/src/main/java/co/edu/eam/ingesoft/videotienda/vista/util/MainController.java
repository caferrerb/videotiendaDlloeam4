package co.edu.eam.ingesoft.videotienda.vista.util;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.context.ApplicationContext;

import co.edu.eam.ingesoft.videotienda.main.ContextFactory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarActores;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarCIudad;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarEmpleado;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarPelicula;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarTienda;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorGestionarVenta;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorSeguridad;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorReporteVentaAlquiler;
import co.edu.eam.ingesoft.videotienda.vista.controladores.ControladorVentanaTrasladarCiudad;
import co.edu.eam.ingesoft.videotienda.vista.controladores.CrearPeliculaController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
	private Menu mnuInicio;

	/**
	 * Menu equipos
	 */
	@FXML
	private Menu mnuPeliculas;

	/**
	 * Menu equipos
	 */
	@FXML
	private Menu mnuActores;

	/**
	 * Menu equipos
	 */
	@FXML
	private Menu mnuGeneros;
	
	/**
	 * Menu equipos
	 */
	@FXML
	private Menu menuCliente;

	/**
	 * Menu equipos
	 */
	@FXML
	private Menu mnuEmpleados;

	/**
	 * Menu equipos
	 */
	@FXML
	private Menu mnuPrestamos;
	
	@FXML
	private Menu mnuAutorizacion;
	
	@FXML
	private Menu menuItemReportes;

	/**
	 * item de iniciar sesion.
	 */
	@FXML
	private MenuItem mnuSucursales;
	
	@FXML
	private MenuItem mnuItemGestionarCiudad;
	
	@FXML
	private MenuItem mnuItemGestionarActores;
	
	@FXML
	private MenuItem mIVenderPeliculas;

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
	
	/**
	 * Inicializacion de la ventana.
	 */

	public void initialize(URL arg0, ResourceBundle arg1) {

		esconderMenus();
		agregarVentana("/fxml/crearpelicula.fxml",CrearPeliculaController.class);

	}

	/**
	 * MEtodo para agregar una ventana.
	 *
	 * @param url,
	 *            ruta del archivo xml de la vista.
	 */
	private void agregarVentana(String pagina, Class controller) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pagina));
			 fxmlLoader.setResources(ResourceBundle.getBundle("i18n.mensajes"));
			
			BaseController control = fxmlLoader.<BaseController> getController();
			ApplicationContext context = ContextFactory.getContext();
			fxmlLoader.setController(context.getBean(controller));
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
		// mnuEquipos.setVisible(false);
		// mnuUsuarios.setVisible(false);
		// mnuPrestamos.setVisible(false);
		// mnuMultas.setVisible(false);
		// mnuMonitores.setVisible(false);
		// mnuItemIniciarSesion.setVisible(true);
	}

	/**
	 * + Metodo para mostrar los menus.
	 *
	 * @author Camilo Andres Ferrer Bustos<br/>
	 *         email: caferrerb@gmail.com <br/>
	 *         Fecha: 19/10/2015<br/>
	 */
	public void mostrarMenus() {

	}
	@FXML
	public void abrirGEstionarCiudad(){
		agregarVentana("/fxml/gestionarciudad.fxml", ControladorGestionarCIudad.class);
		
	}
	
	@FXML
	public void abrirTrasladarCliente(){
		agregarVentana("/fxml/VentanaTrasladarCiudadCliente.fxml", ControladorVentanaTrasladarCiudad.class);
	}
	
	@FXML
	public void abrirGestionarActores(){
		agregarVentana("/fxml/GestionarActores.fxml", ControladorGestionarActores.class);
	
	}
	
	@FXML
	public void abrirGestionarVenta(){
		agregarVentana("/fxml/GestionarVentaPeliculas.fxml", ControladorGestionarVenta.class);
	}

	@FXML
	public void abrirGestionarEmpleados(){
		agregarVentana("/fxml/VentanaGestionEmpleados.fxml", ControladorGestionarEmpleado.class);	
	}
	
	@FXML
	public void abrirGestionarPeliculas(){
		agregarVentana("/fxml/VentanaGestionarPeliculas.fxml", ControladorGestionarPelicula.class);
	}
	
	@FXML
	public void abrirGestionarTienda(){		
		agregarVentana("/fxml/VentanaGestionarTienda.fxml", ControladorGestionarTienda.class);
	}
	
	@FXML
	public void abrirAutorizacion(){		
		agregarVentana("/fxml/Seguridad.fxml", ControladorSeguridad.class);
	}
	
	@FXML
	public void abrirGestionReportesVentasAlquiler(){
		agregarVentana("/fxml/VentanaReportesVentasAlquiler.fxml", ControladorReporteVentaAlquiler.class);
	}
}
