package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

@Controller
public class ControladorSeguridad extends BaseController implements Initializable {

	@FXML
	private TextField tfNombreRol;
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

	@FXML
	public void crear() {

		Rol rol = new Rol();
		rol.setDescripcion(tfNombreRol.getText());
		rol.setDescripcion(tfNombreRol.getText());


//		boCiudad.crear(ciudad);
//		notificar("Gestionar Ciudad", "Ciudad creada con exito!!!", TipoNotificacion.INFO);
	}
	
	@FXML
	public void llenarTabla(){
		
		
	}
	
}
