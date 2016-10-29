package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BORol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Country;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

@Controller
public class ControladorSeguridad extends BaseController implements Initializable {

	@Autowired
	private BORol boRol;
	@Autowired
	private BOEmpleado boEmpleado;
	
	@FXML
	private TextField tfNombreRol;
	@FXML
	private ComboBox<Rol> cbRoles;

	// @FXML
	// private ComboBox<Rol> cbPantalla;

	
	@FXML
	private ComboBox<Staff> cbEmpleado;
	@FXML
	private ComboBox<Rol> cbRolUsuario;
	// @FXML
	// private TextField tfUsuario;
	// @FXML
	// private TextField tfPassword;
	
	// Declarar tabla y columnas de ROL !
	@FXML
	private TableView<Rol> tablaRoles;
	@FXML
	private TableColumn<Rol, Integer> idRolCL;
	@FXML
	private TableColumn<Rol, String> nombreRolCL;

	ObservableList<Rol> roles;

	private int posRolTabla;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		inicializarTabla();
		llenarTablaRoles();
		llenarComboRoles();
		llenarComboEmpleados();
	}

	@FXML
	public void crearRol() {

		Rol rol = new Rol();
		rol.setDescripcion(tfNombreRol.getText());

		boRol.crear(rol);
		notificar("Crear Rol", "Rol creado con exito!", TipoNotificacion.INFO);
		llenarTablaRoles();
	}

	@FXML
	public void llenarTablaRoles() {

		roles.setAll(boRol.listar());
		tablaRoles.setItems(roles);
	}

	public void inicializarTabla() {
		//Inicializar listas
		roles = FXCollections.observableArrayList();
	
		//Enlazar listas
		tablaRoles.setItems(roles);
		
		//Enlazar columnas con atributos
		idRolCL.setCellValueFactory(new PropertyValueFactory<Rol, Integer>("id"));
		nombreRolCL.setCellValueFactory(new PropertyValueFactory<Rol, String>("descripcion"));
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

}
