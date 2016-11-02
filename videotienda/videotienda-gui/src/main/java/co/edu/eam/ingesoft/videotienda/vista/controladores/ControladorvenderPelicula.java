package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOCustomer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

@Controller
public class ControladorvenderPelicula extends BaseController implements Initializable  {

//	@Autowired
//	private BOCustomer boCustomer;
	private BOCustomer boCus;
	
	@FXML
	private TextField jtfIdCliente;
	
	@FXML
	private TextField jtfNombreCliente;
	
	@FXML
	public void buscarCliente(){
		Customer cliente = boCus.buscar(jtfIdCliente.getText());
		
		if (cliente != null) {
			jtfNombreCliente.setText(cliente.getFirstName());
			} else {
			notificar("gestionar Buscar", "No existe este empleado", TipoNotificacion.ERROR);
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
