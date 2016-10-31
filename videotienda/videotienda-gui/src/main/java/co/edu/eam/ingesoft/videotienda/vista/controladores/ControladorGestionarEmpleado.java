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
import co.edu.eam.ingesoft.videotienda.logica.bos.BODireccion;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuario;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Address;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
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

	@Autowired
	private BODireccion boDireccion;

	@Autowired
	private BOUsuario boUsuario;

	@Autowired
	private BOTienda boTienda;

	@FXML
	private TextField TFIdEmpleado;
	@FXML
	private TextField TFIdUsuario;
	@FXML
	private TextField TfPrimerNombre;
	@FXML
	private TextField TfSegundoNombre;
	@FXML
	private TextField TfEmail;
	@FXML
	private CheckBox CheckActivo;
	@FXML
	private TextField TFFechaCreacion;
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
	private TextField TFUlltimaActualizacionDir;
	@FXML
	private TextField TFTelefono;
	@FXML
	private TextField TFCodigoPos;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		llenarComboCiudad();
		llenarTienda();
	}

	@FXML
	public void crearEmpleado() throws ExcepcionNegocio {
		
			Staff empleado = new Staff();
			Address direccion = new Address();
			//Busca una ciudad por su id
			City ciudad = boCiudad.buscar(CBCiudad.getSelectionModel().getSelectedItem().getCityId());
			direccion.setAddress(TFDireccionA.getText());
			direccion.setAddress2(TFDdireccionB.getText());
			direccion.setAddressId(Integer.parseInt(TFIdDireccion.getText()));
			direccion.setCity(ciudad);
			direccion.setDistrict(TFDepartamento.getText());
			direccion.setLastUpdate(new Timestamp(new Date().getTime()));
			direccion.setPhone(TFTelefono.getText());
			direccion.setPostalCode(TFCodigoPos.getText());
			// Empleado
			empleado.setAddress(direccion);
			empleado.setStaffId((byte) Integer.parseInt(TFIdEmpleado.getText()));
			empleado.setEmail(TfEmail.getText());
			empleado.setFirstName(TfPrimerNombre.getText());
			empleado.setLastName(TfSegundoNombre.getText());
			empleado.setLastUpdate (new Timestamp(new Date().getTime()));
			empleado.setAddress(direccion);
			
			Usuario usuario = boUsuario.buscar(TFIdUsuario.getText());
			empleado.setUsuario(usuario);
			
			Store tienda = boTienda.buscar(comboBoxSelecTienda.getSelectionModel().getSelectedItem().getStoreId());
			empleado.setStore(tienda);

			if (CheckActivo.isSelected() == true) {
				empleado.setActive(true);
			} else {
				empleado.setActive(false);
			}
			boDireccion.crearDireccion(direccion);
			boEmpleado.crearEmpleado(empleado);

			notificar("Gestionar empleado", "Empleado Creado con exito", TipoNotificacion.INFO);

		
	}

	@FXML
	public void editarEmpleado() {

	}

	@FXML
	public void buscarEmpleado() {
		Staff empleado = boEmpleado.buscar((byte) Integer.parseInt(TFIdEmpleado.getText()));
		
		if(empleado != null){
			TFIdUsuario.setText(empleado.getUsuario().getUsuario());
			TfPrimerNombre.setText(empleado.getFirstName());
			TfSegundoNombre.setText(empleado.getLastName());
			TfEmail.setText(empleado.getEmail());
			TFFechaCreacion.setText(empleado.getLastUpdate().toString());
			if(empleado.getActive()== true){
				CheckActivo.setSelected(true);
			}
			//TFIdDireccion.setText(Integer.toString(empleado.getAddress().getAddressId()));
			TFDireccionA.setText(empleado.getAddress().getAddress());
			TFDdireccionB.setText(empleado.getAddress().getAddress2());
			//CBCiudad.setSelectionModel(empleado.getAddress().getCity().getCity());
			TFDepartamento.setText(empleado.getAddress().getDistrict());
			TFUlltimaActualizacionDir.setText(empleado.getAddress().getLastUpdate().toString());
			TFTelefono.setText(empleado.getAddress().getPhone());
			TFCodigoPos.setText(empleado.getAddress().getPostalCode());
		}
	}

	private void llenarComboCiudad() {
		List<City> lista = boCiudad.listarCiudades();
		for (City ciudad : lista) {
			CBCiudad.getItems().add(ciudad);
		}
	}

	
	private void llenarTienda() {
		List<Store> lista = boTienda.listarTiendas();
		for (Store tienda : lista) {
			comboBoxSelecTienda.getItems().add(tienda);
		}
	}
}
