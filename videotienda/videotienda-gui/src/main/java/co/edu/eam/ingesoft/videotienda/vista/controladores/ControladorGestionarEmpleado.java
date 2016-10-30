package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOCiudad;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Address;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Country;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

@Controller
public class ControladorGestionarEmpleado extends BaseController implements Initializable {

	@Autowired
	private BOEmpleado boEmpleado;
	@Autowired
	private BOCiudad boCiudad;
	
	
	@FXML
	private TextField TfidTienda;
	@FXML
	private TextField TfPrimerNombre;
	@FXML
	private TextField TfSegundoNombre;
	@FXML
	private TextField TfEmail;
	@FXML
	private TextField TfDireccion;
	@FXML
	private CheckBox CheckActivo;
	@FXML
	private DatePicker DtFechaCreacion;
	@FXML
	private DatePicker DtFechaActualizacion;
	@FXML
	private ImageView PhFoto;
	@FXML
	private ComboBox<Store> comboBoxSelecTienda;
	@FXML
	private TableView<StaffSchedule> TbHorario;
	@FXML
	private TextField TFIdDireccion;
	@FXML
	private TextField TFDireccionA;
	@FXML
	private TextField TFDdireccionB;
	@FXML
	private ComboBox<City> CBCiudad;
	@FXML
	private TextField TFDepartamento;
	@FXML
	private DatePicker DTUlltimaActualizacionDir;
	@FXML
	private TextField TFTelefono;
	@FXML
	private TextField TFCodigoPos;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		llenarComboCiudad();
	}

	@FXML
	public void crearEmpleado(){
		
		Staff empleado = new Staff();
		Address direccion = new Address();
		//Busca una ciudad por su id
		City ciudad = boCiudad.buscar(CBCiudad.getSelectionModel().getSelectedItem().getCityId());
		direccion.setAddress(TFDireccionA.getText());
		direccion.setAddress2(TFDdireccionB.getText());
		direccion.setAddressId(Integer.parseInt(TFIdDireccion.getText()));
		direccion.setCity(ciudad);
		direccion.setDistrict(TFDepartamento.getText());
		direccion.setLastUpdate((Timestamp) DTUlltimaActualizacionDir.getUserData());
		direccion.setPhone(TFTelefono.getText());
		direccion.setPostalCode(TFCodigoPos.getText());
		empleado.setAddress(direccion);
		empleado.setEmail(TfEmail.getText());
		empleado.setFirstName(TfPrimerNombre.getText());
		empleado.setLastName(TfSegundoNombre.getText());
		empleado.setLastUpdate((Timestamp) DtFechaCreacion.getUserData());
		if(CheckActivo.isSelected() == true){
			empleado.setActive(true);
		}else{
			empleado.setActive(false);
		}
		
		boEmpleado.crear(empleado);
		
		notificar("Gestionar empleado","Empleado Creado con exito", TipoNotificacion.INFO);
		
	}

	@FXML
	public void editarEmpleado() {

	}

	@FXML
	public void buscarEmpleado() {

	}
	
	private void llenarComboCiudad() {
		List<City> lista = boCiudad.listarCiudades();
		for (City ciudad : lista) {
			CBCiudad.getItems().add(ciudad);
		}
	}

}
