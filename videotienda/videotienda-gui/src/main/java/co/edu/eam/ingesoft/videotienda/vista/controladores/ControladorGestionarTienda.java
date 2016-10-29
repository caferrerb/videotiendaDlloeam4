package co.edu.eam.ingesoft.videotienda.vista.controladores;


import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOCiudad;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Clase responsable de controlar la ventana de gestionar tienda
 * @author Jhoan Sebastian Salazar Henao<br/>
 *         email: jsebastian48@hotmail.com <br/>
 *         Fecha: 27/10/2016<br/>
 */
@Controller
public class ControladorGestionarTienda extends BaseController implements Initializable {

	@Autowired
	private BOTienda boTienda;
		
	@Autowired
	private BOEmpleado boEmpleado;
	
	@Autowired
	private BOCiudad boCiudad;
	
	@FXML
	private ComboBox<Store> cbTiendas;
	
	@FXML
	private TextField tfDireccion;
	
	@FXML
	private TextField tfDireccion2;
	
	@FXML
	private ComboBox<Staff> cbEmpleado;
	
	@FXML
	private TextField tfCodigoPostal;
	
	@FXML
	private TextField tfLocalidad;
	
	@FXML
	private ComboBox<City> cbCiudad;
	
	@FXML
	private TextField tfTelefono;
	
	@FXML
	private TableView<Staff> tbEmpleado;
	
	@FXML
	private TableColumn<Staff, Byte> cCodigo;
	
	@FXML
	private TableColumn<Staff, String> cNombre;
	
	@FXML
	private TableColumn<Staff, String> cApellido;
	
	/**
	 * Horario no configurado todavia
	 */
	@FXML
	private TableColumn<StaffSchedule, Date> cHorario;
	
	@FXML
	private TableColumn<Staff, Button> cButtonEmpleados;
	
	@FXML
	private final ObservableList<Staff> data = FXCollections.observableArrayList();
	
	/**
	 * Metodo para cargar la tabla empleados
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 28/10/2016<br/>
	 */
	@FXML
	public void cargarTablaEmpleados(){
		try{
			if(cbTiendas.getSelectionModel().getSelectedIndex()==0){
				mostrarMensaje(AlertType.INFORMATION,null, null, "No tiene empleados");
			}else{
				Store store = cbTiendas.getSelectionModel().getSelectedItem();
				
				List<Staff> lista = boEmpleado.listarEmpleados();
				for (Staff staff : lista){
					data.add(staff);
					
				cCodigo.setCellValueFactory(new PropertyValueFactory<>("CODIGO"));
				cNombre.setCellValueFactory(new PropertyValueFactory<>("NOMBRE"));
				cApellido.setCellValueFactory(new PropertyValueFactory<>("APELLIDO"));
				cHorario.setCellValueFactory(new PropertyValueFactory<>("HORARIO"));
				cButtonEmpleados.setCellValueFactory(new PropertyValueFactory<>("VER"));
				tbEmpleado.setItems(data);
				
			}
		}		
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		llenarComboCiudad();
		llenarComboEmpleado();
		llenarComboTienda();
		cargarTablaEmpleados();
		
	}
	
	private void llenarComboCiudad(){
		
		List<City> lista = boCiudad.listarCiudades();
		for (City city : lista){
			cbCiudad.getItems().add(city);
		}
	}
	
	private void llenarComboEmpleado(){
		List<Staff> lista = boEmpleado.listarEmpleados();
		for (Staff staff : lista){
			cbEmpleado.getItems().add(staff);
		}
	}
	
	private void llenarComboTienda(){
		cbTiendas.getItems().add(null);
		List<Store> lista = boTienda.listarTiendas();
		for (Store store : lista){
			cbTiendas.getItems().add(store);
		}
	}
	
	public void crear(){
	
		/*
		Address address = new Address();
		address.setAddress(tfDireccion.getText());
		address.setAddress2(tfDireccion2.getText());
		Staff staff = (Staff) cbEmpleado.getSelectedItem();
		address.setPostalCode(tfCodigoPostal.getText());
		address.setDistrict(tfLocalidad.getText());
		City city = (City) cbCiudad.getSelectedItem();
		address.setPhone(tfTelefono.getText());
		**/
	}
	
	public void editar(){
		
	}
	
	public void buscar(){
		
	}
	
	
	
	
	
	
}
