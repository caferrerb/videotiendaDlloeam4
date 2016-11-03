package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOCustomer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

@Controller
public class ControladorvenderPelicula extends BaseController implements Initializable  {


	@Autowired
	private BOCustomer boCus;
	
	@FXML
	private TextField jtfIdCliente;
	
	@FXML
	private TextField jtfNombreCliente;
	
	private Film film;
	
	@FXML
	public void buscarCliente(){
		try {
			System.out.println("Buscando");
			
			int idCliente = Integer.parseInt(jtfIdCliente.getText());
			List<Customer> cus = boCus.listaBucarCliente(idCliente);
			for (int i = 0; i < cus.size(); i++) {
				if(cus != null){
					jtfNombreCliente.setText(cus.get(i).getFirstName());
				}
			}
			
//			List<Customer> cliente = boCus.listaBucarCliente(idCliente);
//			if(cliente != null){
//				jtfNombreCliente.setText(((Customer) cliente).getFirstName());
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		film=(Film) obtenerValor("peliculavender");
	}

}
