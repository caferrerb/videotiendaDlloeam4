package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOAcceso;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOAccesoRol;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BORol;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuario;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuarioRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.UsuarioRol;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
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
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

@Controller
public class ControladorSeguridad extends BaseController implements Initializable {

	@Autowired
	private BORol boRol;
	@Autowired
	private BOEmpleado boEmpleado;
	@Autowired
	private BOUsuarioRol boUsuarioRol;
	@Autowired
	private BOUsuario boUsuario;
	@Autowired
	private BOAcceso boAcceso;

	@Autowired
	private BOAccesoRol boAccesoRol;

	@FXML
	private TextField tfNombreRol;
	@FXML
	private ComboBox<Rol> cbRoles;
	@FXML
	private ComboBox<Acceso> cbPantalla;
	// Asignar usuario.
	@FXML
	private TextField tfUsuario;
	@FXML
	private TextField tfPassword;

	@FXML
	private ComboBox<Staff> cbEmpleado;
	@FXML
	private ComboBox<Rol> cbRolUsuario;
	@FXML
	private TableColumn<Rol, Rol> eliminarRolCL;

	// Declarar tabla y columnas de ROL !
	@FXML
	private TableView<Rol> tablaRoles;
	@FXML
	private TableColumn<Rol, Integer> idRolCL;
	@FXML
	private TableColumn<Rol, String> nombreRolCL;

	ObservableList<Rol> roles;

	// Declarar tabla y columnas de Usuario
	@FXML
	private TableView<Rol> tablaRolUsuario;
	@FXML
	private TableColumn<Rol, String> rolUsuarioCL;
	@FXML
	private TableColumn<Rol, Rol> quitarRolCL;

	ObservableList<Rol> rolesUsuario;

	// Declarar tabla y columnas de Acceso rol !
	@FXML
	private TableView<AccesoRol> tablaPantallaRol;
	@FXML
	private TableColumn<Rol, String> rolPantallaCL;
	@FXML
	private TableColumn<Acceso, String> pantallaCL;
	@FXML
	private TableColumn<AccesoRol, AccesoRol> eliminarPantallaCL;

	ObservableList<AccesoRol> accesoRoles;

	private Usuario usu;
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
				tfUsuario.setText(null);
				tfPassword.setText(null);
			}
		});
	}

	@FXML
	public void crearRol() {

		Rol rol = new Rol();
		rol.setDescripcion(tfNombreRol.getText());
		if (tfNombreRol.getText().isEmpty()) {
			notificar("Crear Rol", "Debe completar los campos", TipoNotificacion.INFO);
		} else {

			boRol.crear(rol);
			notificar("Crear Rol", "Rol creado con exito!", TipoNotificacion.INFO);
			llenarComboRoles();
			llenarTablaRoles();
		}
	}

	@FXML
	public void establecerUsuario() {

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
	}

	@FXML
	public void asignarRol() {
		if (cbRolUsuario.getSelectionModel().getSelectedItem().equals("Seleccione un rol")) {
			notificar("Asignar rol al usuario", "Debe seleccionar el rol a asignar", TipoNotificacion.ERROR);
		} else {
			UsuarioRol usuarioRol = new UsuarioRol();
			Rol rolCombo = cbRolUsuario.getSelectionModel().getSelectedItem();
			usuarioRol.setRol(rolCombo);
			usuarioRol.setUsuario(usu);
			boUsuarioRol.crear(usuarioRol);
			llenarTablaRolUsuario();
		}
	}

	@FXML
	public void agregarPantalla() {
		if (cbRoles.getSelectionModel().getSelectedItem().equals("Seleccione un rol")) {
			notificar("Asignar rol al usuario", "Debe seleccionar el rol a asignar", TipoNotificacion.ERROR);
		} else {
			AccesoRol accesoRol = new AccesoRol();
			Acceso acceCombo = cbPantalla.getSelectionModel().getSelectedItem();
			Rol rolCombo = cbRoles.getSelectionModel().getSelectedItem();
			accesoRol.setRol(rolCombo);
			accesoRol.setAcceso(acceCombo);
			boAccesoRol.crear(accesoRol);
			notificar("Asignar pantalla a rol", "Se ha asignado la pantalla correctamente", TipoNotificacion.INFO);
			llenarTablaRolPantalla();
		}
	}

	@FXML
	public void llenarTablaRoles() {
		listaRoles = boRol.listar();
		roles.setAll(listaRoles);
		tablaRoles.setItems(roles);
	}

	@FXML
	public void llenarTablaRolUsuario() {
		listaRolesUsuario = boUsuarioRol.listarRolesPorUsuario(usu.getUsuario());
		rolesUsuario.setAll(listaRolesUsuario);
		tablaRolUsuario.setItems(rolesUsuario);
	}

	@FXML
	public void llenarTablaRolPantalla() {
		listaAccesoRol = boAccesoRol.listar();
		accesoRoles.setAll(listaAccesoRol);
		tablaPantallaRol.setItems(accesoRoles);
	}

	public void inicializarTabla() {
		// Inicializar listas
		roles = FXCollections.observableArrayList();
		rolesUsuario = FXCollections.observableArrayList();
		accesoRoles = FXCollections.observableArrayList();

		// Enlazar listas
		tablaRoles.setItems(roles);
		tablaRolUsuario.setItems(rolesUsuario);
		tablaPantallaRol.setItems(accesoRoles);

		// Enlazar columnas con atributos
		idRolCL.setCellValueFactory(new PropertyValueFactory<Rol, Integer>("id"));
		nombreRolCL.setCellValueFactory(new PropertyValueFactory<Rol, String>("descripcion"));
		rolUsuarioCL.setCellValueFactory(new PropertyValueFactory<Rol, String>("Rol"));
		rolPantallaCL.setCellValueFactory(new PropertyValueFactory<Rol, String>("Rol"));
		pantallaCL.setCellValueFactory(new PropertyValueFactory<Acceso, String>("Pantalla"));
		eliminarRolCL.setSortable(false);
		quitarRolCL.setSortable(false);
		eliminarPantallaCL.setSortable(false);

		eliminarRolCL.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		eliminarRolCL.setCellFactory(param -> new TableCell<Rol, Rol>() {
			private final Button deleteButton = new Button("Eliminar");

			@Override
			protected void updateItem(Rol rol, boolean empty) {
				super.updateItem(rol, empty);

				if (rol == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(new EventHandler<ActionEvent>() {

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
							notificar("Eliminar Rol", "Rol eliminado con exito!", TipoNotificacion.INFO);
						}
					}
				});
			}
		});

		quitarRolCL.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		quitarRolCL.setCellFactory(param -> new TableCell<Rol, Rol>() {
			private final Button deleteButton = new Button("Eliminar");

			@Override
			protected void updateItem(Rol usuR, boolean empty) {
				super.updateItem(usuR, empty);

				if (usuR == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(new EventHandler<ActionEvent>() {

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

		eliminarPantallaCL.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		eliminarPantallaCL.setCellFactory(param -> new TableCell<AccesoRol, AccesoRol>() {
			private final Button deleteButton = new Button("Eliminar");

			@Override
			protected void updateItem(AccesoRol accR, boolean empty) {
				super.updateItem(accR, empty);

				if (accR == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(new EventHandler<ActionEvent>() {

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

	private void llenarComboRoles() {
		List<Rol> lista = boRol.listar();
		for (Rol rol : lista) {
			cbRoles.getItems().add(rol);
			cbRolUsuario.getItems().add(rol);
		}
	}

	private void llenarComboPantallas() {
		List<Acceso> lista = boAcceso.listar();
		for (Acceso acceso : lista) {
			cbPantalla.getItems().add(acceso);
		}
	}

	private void llenarComboEmpleados() {
		List<Staff> lista = boEmpleado.listarEmpleados();
		for (Staff emp : lista) {
			cbEmpleado.getItems().add(emp);
		}
	}
	
}
