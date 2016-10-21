package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOCiudad;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOPais;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Country;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

@Controller
public class ControladorGestionarCIudad extends BaseController implements Initializable {

	@Autowired
	private BOCiudad boCiudad;

	@Autowired
	private BOPais boPais;

	@FXML
	private TextField tfId;

	@FXML
	private TextField tfNom;

	@FXML
	private ComboBox<Country> cbPais;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		llenarComboPaises();

	}

	private void llenarComboPaises() {
		List<Country> lista = boPais.listarPaises();
		for (Country country : lista) {
			cbPais.getItems().add(country);
		}
	}

	@FXML
	public void crear() {

		City ciudad = new City();
		ciudad.setCity(tfNom.getText());
		ciudad.setCountry(cbPais.getValue());
		ciudad.setCityId(Integer.valueOf(tfId.getText()));

		boCiudad.crear(ciudad);
		notificar("Gestionar Ciudad", "Ciudad creada con exito!!!", TipoNotificacion.INFO);
	}

	@FXML
	public void buscar() {
		City ciudad=boCiudad.buscar(Integer.valueOf(tfId.getText()));
		if(ciudad!=null){
			tfNom.setText(ciudad.getCity());
			cbPais.setValue(ciudad.getCountry());
		}else{
			notificar("Gestionar Ciudad", "Ciudad no existe", TipoNotificacion.ERROR);

		}

	}

	@FXML
	public void editar() {
		System.out.println("editar....");
	}

}
