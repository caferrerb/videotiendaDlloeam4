package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOCustomer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Sale;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

@Controller
public class ControladorvenderPelicula extends BaseController implements Initializable {

	@Autowired
	private BOCustomer boCus;

	@FXML
	private TextField jtfIdCliente;

	@FXML
	private TextField jtfNombreCliente;

	@FXML
	private TextField jtfIdPelicula;

	private Film film;

	@FXML
	public void buscarCliente() {
		try {
			System.out.println("Buscando");
			Sale fecha = new Sale();
			int idCliente = Integer.parseInt(jtfIdCliente.getText());
			List<Customer> cus = boCus.listaBucarCliente(idCliente);
			for (int i = 0; i < cus.size(); i++) {
				if (cus != null) {
					jtfNombreCliente.setText(cus.get(i).getFirstName());
//					fecha.
					
					// jtfIdPelicula.setText(film.getFilmId());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		film = (Film) obtenerValor("peliculavender");		
		int id = film.getFilmId();
		jtfIdPelicula.setText(String.valueOf(id));;
	}

}
