package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.awt.TextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOCiudad;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.fxml.Initializable;

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
	
	/**
	@Autowired
	private BOStaff boEmpleado;
	*/
	
	@Autowired
	private BOCiudad boCiudad;
	
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
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		llenarComboCiudad();
		
	}
	
	private void llenarComboCiudad(){
		List<City> lista = boCiudad.listarCiudades();
		for (City city : lista){
			cbCiudad.getItems().add(city);
		}
	}
	
	
	
	
	
	
}
