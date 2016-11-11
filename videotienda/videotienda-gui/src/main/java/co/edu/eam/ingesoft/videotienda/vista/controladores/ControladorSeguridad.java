package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOAcceso;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOAccesoRol;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BORol;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuario;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuarioRol;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.UsuarioRol;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.GeneradorReporte;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * Controlador de la ventana Autorizacion - Roles
 *
 * @author Richard Alexander Vanegas Ochoa<br/>
 *         email: Richardvanegas8@gmail.com <br/>
 *         Fecha: 23/10/2015<br/>
 */
@Controller
public class ControladorSeguridad extends BaseController implements Initializable {

	/**
	 * DataSource 
	 */
	@Autowired
	private DataSource ds;
	
	/**
	 * BO con los métodos de la lógica de Rol
	 */
	@Autowired
	private BORol boRol;

	/**
	 * BO con los métodos de la lógica de Empleado
	 */
	@Autowired
	private BOEmpleado boEmpleado;

	/**
	 * BO con los métodos de la lógica de UsuarioRol
	 */
	@Autowired
	private BOUsuarioRol boUsuarioRol;

	/**
	 * BO con los métodos de la lógica de usuario
	 */
	@Autowired
	private BOUsuario boUsuario;

	/**
	 * BO con los métodos de la lógica de Acceso
	 */
	@Autowired
	private BOAcceso boAcceso;

	/**
	 * BO con los métodos de la lógica de AccesoRol
	 */
	@Autowired
	private BOAccesoRol boAccesoRol;

	/**
	 * TextField nombre del rol
	 */
	@FXML
	private TextField tfNombreRol;

	/**
	 * ComboBox rol a asignar pantalla
	 */
	@FXML
	private ComboBox<Rol> cbRoles;

	/**
	 * ComboBox pantalla a asignar al rol
	 */
	@FXML
	private ComboBox<Acceso> cbPantalla;

	// Asignar usuario.

	/**
	 * TextField nickname del usuario a crear
	 */
	@FXML
	private TextField tfUsuario;

	/**
	 * PasswordField password del usuario a crear
	 */
	@FXML
	private PasswordField tfPassword;

	/**
	 * ComboBox empleado al cual se le asignara el usuario
	 */
	@FXML
	private ComboBox<Staff> cbEmpleado;

	// Asignar RolUsuario

	/**
	 * ComboBox rol que sera asignado al usuario
	 */
	@FXML
	private ComboBox<Rol> cbRolUsuario;

	/**
	 * TableColumn contiene el boton eliminar rol
	 */
	@FXML
	private TableColumn<Rol, Rol> eliminarRolCL;

	// Declarar tabla y columnas de ROL !

	/**
	 * TableView de roles
	 */
	@FXML
	private TableView<Rol> tablaRoles;

	/**
	 * TableColumn contiene el id del rol
	 */
	@FXML
	private TableColumn<Rol, Integer> idRolCL;

	/**
	 * TableColumn contiene la descri
	 * 
	 * 
	 * 
	 * pcion del rol
	 */
	@FXML
	private TableColumn<Rol, String> nombreRolCL;

	/**
	 * ObservableList para llenar tabla roles
	 */
	ObservableList<Rol> roles;

	// Declarar tabla y columnas de Usuario

	/**
	 * TableView de roles de usuario
	 */
	@FXML
	private TableView<Rol> tablaRolUsuario;

	/**
	 * TableColumn contiene asignado al usuario
	 */
	@FXML
	private TableColumn<Rol, String> rolUsuarioCL;

	/**
	 * TableView de roles
	 */
	@FXML
	private TableColumn<Rol, Rol> quitarRolCL;

	/**
	 * ObservableList para llenar tabla de roles usuario
	 */
	ObservableList<Rol> rolesUsuario;

	// Declarar tabla y columnas de Acceso rol !

	/**
	 * TableView de acceso rol
	 */
	@FXML
	private TableView<AccesoRol> tablaPantallaRol;

	/**
	 * TableColumn contiene el rol del acceso
	 */
	@FXML
	private TableColumn<Rol, String> rolPantallaCL;

	/**
	 * TableColumn contiene el acceso del rol
	 */
	@FXML
	private TableColumn<Acceso, String> pantallaCL;

	/**
	 * TableColumn contiene el boton de eliminar acceso del rol
	 */
	@FXML
	private TableColumn<AccesoRol, AccesoRol> eliminarPantallaCL;

	/**
	 * ObservableList para llenar tabla de acceso roles
	 */
	ObservableList<AccesoRol> accesoRoles;

	/**
	 * ComboBox rol el cual contiene el rol por el cual se generara el reporte
	 */
	@FXML
	private ComboBox<Rol> cbReporte;
	
	private Usuario usu;

	private int idReporte;
	
	/**
	 * Empleado seleccionado en el combo
	 */
	private Staff empleado;

	/**
	 * Se declara una lista para usarla en la ventana
	 */
	private List<Rol> listaRoles;

	/**
	 * Se declara una lista para usarla en la ventana
	 */
	private List<Rol> listaRolesUsuario;

	/**
	 * Se declara una lista para usarla en la ventana
	 */
	private List<AccesoRol> listaAccesoRol;

	/**
	 * Constructor
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		inicializarTabla();
		llenarTablaRoles();
		llenarTablaRolPantalla();
		llenarComboRoles();
		llenarComboPantallas();
		llenarComboEmpleados();
		usu = new Usuario();
		empleado = new Staff();
		cbEmpleado.setOnAction((event) -> {
			Staff selectedEmpleado = cbEmpleado.getSelectionModel().getSelectedItem();
			if (selectedEmpleado.getUsuario() != null) {
				tfUsuario.setText(selectedEmpleado.getUsuario().getUsuario());
				tfPassword.setText(selectedEmpleado.getUsuario().getPass());
				usu = selectedEmpleado.getUsuario();
				llenarTablaRolUsuario();
			} else {
				tablaRolUsuario.setItems(null);
				tfUsuario.setText(null);
				tfPassword.setText(null);
			}
		});
		
		cbReporte.setOnAction((event) -> {
			Rol selectedRol = cbReporte.getSelectionModel().getSelectedItem();
			if (selectedRol != null) {
				idReporte = selectedRol.getId();
			}
		});
	}

	/**
	 * Metodo para crear un rol
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 */
	@FXML
	public void crearRol() {

		Rol rol = new Rol();
		rol.setDescripcion(tfNombreRol.getText());
		if (tfNombreRol.getText().isEmpty()) {
			notificar("Crear Rol", "Debe completar los campos", TipoNotificacion.INFO);
		} else {
			try {
				boRol.crear(rol);
				notificar("Crear Rol", "Rol creado con exito!", TipoNotificacion.INFO);
				llenarComboRoles();
				llenarTablaRoles();
			} catch (Exception e) {

			}
		}
	}

	/**
	 * Metodo para asignar usuario a un empleado
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 */
	@FXML
	public void establecerUsuario() {
		try {
			usu.setUsuario(tfUsuario.getText());
			usu.setPass(tfPassword.getText());
			if (tfUsuario.getText().isEmpty() || tfPassword.getText().isEmpty()) {
				notificar("Asignar usuario", "Debe completar los campos", TipoNotificacion.ERROR);
			} else {
				if (cbEmpleado.getSelectionModel().getSelectedItem().equals("Elegir el empleado")) {
					notificar("Asignar usuario", "Debe seleccionar el empleado", TipoNotificacion.ERROR);
				} else {
					empleado = (Staff) cbEmpleado.getSelectionModel().getSelectedItem();
					boUsuario.crear(usu);
					empleado.setUsuario(usu);
					boEmpleado.editar(empleado);
					notificar("Asignar usuario", "Usuario asignado con exito!", TipoNotificacion.INFO);
				}
			}
		} catch (Exception e) {

		}
	}

	/**
	 * Metodo para asignar rol a un usuario
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 */
	@FXML
	public void asignarRol() {
		if (cbRolUsuario.getSelectionModel().getSelectedItem().equals("Seleccione un rol")) {
			notificar("Asignar rol al usuario", "Debe seleccionar el rol a asignar", TipoNotificacion.ERROR);
		} else {
			try{
			UsuarioRol usuarioRol = new UsuarioRol();
			Rol rolCombo = cbRolUsuario.getSelectionModel().getSelectedItem();
			usuarioRol.setRol(rolCombo);
			usuarioRol.setUsuario(usu);
			boUsuarioRol.crear(usuarioRol);
			llenarTablaRolUsuario();
		} catch (ExcepcionNegocio e) {
			notificar("Asignar pantalla a rol", e.getMessage(), TipoNotificacion.ERROR);
		} catch (Exception e) {

		}
		}
	}

	/**
	 * Metodo para asignar pantalla a un rol
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 */
	@FXML
	public void agregarPantalla(){
		if (cbRoles.getSelectionModel().getSelectedItem().equals("Seleccione un rol")) {
			notificar("Asignar rol al usuario", "Debe seleccionar el rol a asignar", TipoNotificacion.ERROR);
		} else {
			try {
				AccesoRol accesoRol = new AccesoRol();
				Acceso acceCombo = cbPantalla.getSelectionModel().getSelectedItem();
				Rol rolCombo = cbRoles.getSelectionModel().getSelectedItem();
				accesoRol.setRol(rolCombo);
				accesoRol.setAcceso(acceCombo);
				boAccesoRol.crear(accesoRol);
				notificar("Asignar pantalla a rol", "Se ha asignado la pantalla correctamente", TipoNotificacion.INFO);
				llenarTablaRolPantalla();
			} catch (ExcepcionNegocio e) {
				notificar("Asignar pantalla a rol", e.getMessage(), TipoNotificacion.ERROR);
			} catch (Exception e) {

			}
		}
	}

	/**
	 * Metodo para llenar la tabla con los datos del rol
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 */
	@FXML
	public void llenarTablaRoles() {
		listaRoles = boRol.listar();
		// Inicializar lista
		roles = FXCollections.observableArrayList();
		roles.setAll(listaRoles);
		tablaRoles.setItems(roles);
	}

	/**
	 * Metodo para llenar la tabla con los datos del rol
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 */
	@FXML
	public void llenarTablaRolUsuario() {
		listaRolesUsuario = boUsuarioRol.listarRolesPorUsuario(usu.getUsuario());
		// Inicializar lista
		rolesUsuario = FXCollections.observableArrayList();
		rolesUsuario.setAll(listaRolesUsuario);
		tablaRolUsuario.setItems(rolesUsuario);
	}

	/**
	 * Metodo para llenar la tabla con los datos de acceso rol (nombre
	 * rol,nombre pantalla)
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 */
	@FXML
	public void llenarTablaRolPantalla() {
		listaAccesoRol = boAccesoRol.listar();
		// Inicializar lista
		accesoRoles = FXCollections.observableArrayList();
		accesoRoles.setAll(listaAccesoRol);
		tablaPantallaRol.setItems(accesoRoles);
	}

	/**
	 * Metodo para inicializar las columnas de las tablas y agregar los botones
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 */
	public void inicializarTabla() {

		// Enlazar columnas con atributos
		idRolCL.setCellValueFactory(new PropertyValueFactory<Rol, Integer>("id"));
		nombreRolCL.setCellValueFactory(new PropertyValueFactory<Rol, String>("descripcion"));
		rolUsuarioCL.setCellValueFactory(new PropertyValueFactory<Rol, String>("descripcion"));
		rolPantallaCL.setCellValueFactory(new PropertyValueFactory<Rol, String>("rol"));
		pantallaCL.setCellValueFactory(new PropertyValueFactory<Acceso, String>("acceso"));
		eliminarRolCL.setSortable(false);
		quitarRolCL.setSortable(false);
		eliminarPantallaCL.setSortable(false);

		// Creando boton y añadiendo el evento
		eliminarRolCL.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		eliminarRolCL.setCellFactory(param -> new TableCell<Rol, Rol>() {
			private final Button deleteButton = new Button("Eliminar");

			// Metodo que muestra boton en la fila que no sea null
			@Override
			protected void updateItem(Rol rol, boolean empty) {
				super.updateItem(rol, empty);

				if (rol == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(new EventHandler<ActionEvent>() {

					// Evento del boton en la tabla
					@Override
					public void handle(ActionEvent t) {
						int num = getTableRow().getIndex();
						// borramos el objeto obtenido de la fila
						Rol r = listaRoles.get(num);
						if (boUsuarioRol.listarUsuarioPorRol(r.getId()).size() != 0) {
							notificar("Eliminar Rol",
									"No se puede eliminar el rol," + "ya que alguien lo tiene asignado",
									TipoNotificacion.ERROR);

						} else {
							System.out.println("i dont know");
							boRol.eliminar(r.getId());
							roles.remove(num);
							llenarComboRoles();
							notificar("Eliminar Rol", "Rol eliminado con exito!", TipoNotificacion.INFO);
						}
					}
				});
			}
		});

		// Creando boton y añadiendo el evento
		quitarRolCL.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		quitarRolCL.setCellFactory(param -> new TableCell<Rol, Rol>() {
			private final Button deleteButton = new Button("Eliminar");

			// Metodo que muestra boton en la fila que no sea null
			@Override
			protected void updateItem(Rol usuR, boolean empty) {
				super.updateItem(usuR, empty);

				if (usuR == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(new EventHandler<ActionEvent>() {

					// Evento del boton en la tabla
					@Override
					public void handle(ActionEvent t) {
						int num = getTableRow().getIndex();
						// borramos el objeto obtenido de la fila
						Rol r = listaRolesUsuario.get(num);
						boUsuarioRol.eliminar(r.getId(), usu.getUsuario());
						rolesUsuario.remove(num);
						notificar("Quitar rol a usuario",
								"Se ha quitado el rol asignado anteriormente" + " a este usuario",
								TipoNotificacion.INFO);
					}

				});
			}
		});

		// Creando boton y añadiendo el evento
		eliminarPantallaCL.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		eliminarPantallaCL.setCellFactory(param -> new TableCell<AccesoRol, AccesoRol>() {
			private final Button deleteButton = new Button("Eliminar");

			// Metodo que muestra boton en la fila que no sea null
			@Override
			protected void updateItem(AccesoRol accR, boolean empty) {
				super.updateItem(accR, empty);

				if (accR == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(new EventHandler<ActionEvent>() {

					// Evento del boton en la tabla
					@Override
					public void handle(ActionEvent t) {
						int num = getTableRow().getIndex();
						// borramos el objeto obtenido de la fila
						AccesoRol acc = listaAccesoRol.get(num);
						boAccesoRol.eliminar(acc.getRol().getId(), acc.getAcceso().getId());
						accesoRoles.remove(num);
						notificar("Quitar acceso rol", "Se ha quitado el acceso a este rol", TipoNotificacion.INFO);
					}
				});
			}
		});
	}

	/**
	 * Metodo para cargar los combos de roles
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 */
	private void llenarComboRoles() {
		List<Rol> lista = boRol.listar();
		cbRoles.getItems().removeAll(cbRoles.getItems());
		cbReporte.getItems().removeAll(cbReporte.getItems());
		cbRolUsuario.getItems().removeAll(cbRolUsuario.getItems());
		for (Rol rol : lista) {
			cbRoles.getItems().add(rol);
			cbReporte.getItems().add(rol);
			cbRolUsuario.getItems().add(rol);
		}
	}

	/**
	 * Metodo para cargar el combo de accesos
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 */
	private void llenarComboPantallas() {
		List<Acceso> lista = boAcceso.listar();
		for (Acceso acceso : lista) {
			cbPantalla.getItems().add(acceso);
		}
	}

	/**
	 * Metodo para cargar el combo de empleados
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 */
	private void llenarComboEmpleados() {
		List<Staff> lista = boEmpleado.listarEmpleados();
		for (Staff emp : lista) {
			cbEmpleado.getItems().add(emp);
		}
	}

	/**
	 * Metodo para generar el reporte de acceso por rol
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 08/11/2015<br/>
	 */
	@FXML
	public void generarReporte(){
		
		try {
			GeneradorReporte reporter=new GeneradorReporte(ds.getConnection());
			Map<String, Object> params=new HashMap<>();
//			System.out.println(idReporte+"JAKJAKDSJAKSDJAS");
//			InputStream imgInputStream = 
//				    this.getClass().getResourceAsStream("/reportes/invoice-logo.png");
//				params.put("myImg", imgInputStream);
			params.put("idrol", idReporte);
			reporter.generarReporte(params, "/reportes/accesoroles.jrxml", "AccesosXRol");
		} catch (Exception e) {
			e.printStackTrace();
			
			notificar("Ejemplo", "Error generando el reporte", TipoNotificacion.ERROR);
		}
	}
	
}
