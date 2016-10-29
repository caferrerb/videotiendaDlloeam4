package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BORol;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuario;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuarioRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.UsuarioRol;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
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

	@FXML
	private TextField tfNombreRol;
	@FXML
	private ComboBox<Rol> cbRoles;
	// Asignar usuario
	@FXML
	private TextField tfUsuario;
	@FXML
	private TextField tfPassword;

	@FXML
	private ComboBox<Staff> cbEmpleado;
	@FXML
	private ComboBox<Rol> cbRolUsuario;
	@FXML
	private TableColumn eliminarRolCL;

	// Declarar tabla y columnas de ROL !
	@FXML
	private TableView<Rol> tablaRoles;
	@FXML
	private TableColumn<Rol, Integer> idRolCL;
	@FXML
	private TableColumn<Rol, String> nombreRolCL;

	ObservableList<Rol> roles;

	private Usuario usu;
	private Staff empleado;

	/**
	 * Se declara una lista para usarla en la ventana
	 */
	private List<Rol> listaRoles;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inicializarTabla();
		llenarTablaRoles();
		llenarComboRoles();
		llenarComboEmpleados();
		empleado = null;
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
			llenarTablaRoles();
		}
	}

	@FXML
	public void establecerUsuario() {

		// Usuario usu = new Usuario();
		usu.setUsuario(tfUsuario.getText());
		usu.setPass(tfPassword.getText());
		if (tfUsuario.getText().isEmpty() || tfPassword.getText().isEmpty()) {
			notificar("Asignar usuario", "Debe completar los campos", TipoNotificacion.ERROR);
		} else {
			 if (cbEmpleado.getSelectionModel().getSelectedItem().equals("Elegir el empleado")) {
			 notificar("Asignar usuario", "Debe seleccionar el empleado",
			 TipoNotificacion.ERROR);
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
			notificar("Asignar rol al usuario", "Debe seleccionar el rol a asignar",
					 TipoNotificacion.ERROR);			
		} else {
			UsuarioRol usuarioRol = new UsuarioRol();
			Rol rolCombo = cbRolUsuario.getSelectionModel().getSelectedItem();
			usuarioRol.setRol(rolCombo);
			usuarioRol.setUsuario(usu);
			boUsuarioRol.crear(usuarioRol);
		}
	}

	@FXML
	public void llenarTablaRoles() {
		listaRoles = boRol.listar();
		roles.setAll(listaRoles);
		tablaRoles.setItems(roles);
	}

	public void inicializarTabla() {
		// Inicializar listas
		roles = FXCollections.observableArrayList();

		// Enlazar listas
		tablaRoles.setItems(roles);

		// Enlazar columnas con atributos
		idRolCL.setCellValueFactory(new PropertyValueFactory<Rol, Integer>("id"));
		nombreRolCL.setCellValueFactory(new PropertyValueFactory<Rol, String>("descripcion"));
		eliminarRolCL.setSortable(false);

		// Indicamos que muestre el ButtonCell creado mas abajo.
		eliminarRolCL.setCellFactory(new Callback<TableColumn<Rol, Boolean>, TableCell<Rol, Boolean>>() {

			public TableCell<Rol, Boolean> call(TableColumn<Rol, Boolean> p) {
				return new ButtonCell(tablaRoles);
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

	private void llenarComboEmpleados() {
		List<Staff> lista = boEmpleado.listarEmpleados();
		for (Staff emp : lista) {
			cbEmpleado.getItems().add(emp);
		}
	}

	private class ButtonCell extends TableCell<Rol, Boolean> {

		// boton a mostrar
		final Button cellButton = new Button("Borrar");

		ButtonCell(final TableView tblView) {

			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					int num = getTableRow().getIndex();
					// borramos el objeto obtenido de la fila
					Rol r = listaRoles.get(num);
					if (boUsuarioRol.listarUsuarioPorRol(r.getId()).size() != 0) {
						notificar("Eliminar Rol", "No se puede eliminar el rol," + "ya que alguien lo tiene asignado",
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

		// Muestra un boton si la fila no es nula
		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if (!empty) {
				setGraphic(cellButton);
			}
		}

	}

}
