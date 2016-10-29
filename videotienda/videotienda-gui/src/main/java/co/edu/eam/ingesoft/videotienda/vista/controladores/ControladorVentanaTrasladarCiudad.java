package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOCiudad;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOCliente;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

@Controller
public class ControladorVentanaTrasladarCiudad extends BaseController implements Initializable {

	@Autowired
	private BOCliente boCliente;
	
	private Customer cliente;
	
	@Autowired
	private BOCiudad boCiudad;
	
	@FXML
	private TextField jTFIdentificacion;
	
	@FXML
	private TextField jTFNombre;
	
	@FXML
	private TextField jTFCiudadActual;
	
	@FXML
	private ComboBox<City> jCBCiudadTraslado;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		llenarComboCiudades();
	}
	
	private void llenarComboCiudades(){
		List<City> listaCiudades = boCiudad.listarCiudades();
		for (City city : listaCiudades) {
			jCBCiudadTraslado.getItems().add(city);
		}
	}
	
	@FXML
	public void buscar(){
		
		Customer c = boCliente.buscar(Integer.valueOf(jTFIdentificacion.getText()));
		if (c != null){
			cliente = c;
			jTFNombre.setText(c.getFirstName() + " " + c.getLastName());
			jTFCiudadActual.setText(c.getAddress().getCity().getCity());
		} else {
			notificar("Busqueda", "El cliente que busca no ha sido encontrado", TipoNotificacion.ERROR);
		}
		
	}
	
	@FXML
	public void trasladar(){
		if (cliente == null){
			notificar("Busqueda", "Debe buscar el cliente para hacer el traslado", TipoNotificacion.ERROR);
		} else {
			boCliente.trasladarCiudad(jCBCiudadTraslado.getValue(), cliente);
			notificar("Traslado", "Se ha hecho el trsalado exitosamente", TipoNotificacion.INFO);
		}
	}

}
