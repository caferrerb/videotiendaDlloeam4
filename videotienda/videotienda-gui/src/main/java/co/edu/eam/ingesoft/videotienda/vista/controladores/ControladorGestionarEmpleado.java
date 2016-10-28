package co.edu.eam.ingesoft.videotienda.vista.controladores;



import java.awt.Checkbox;
import java.awt.Image;
import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.image.ImageView;

@Controller
public class ControladorGestionarEmpleado extends BaseController implements Initializable {

	@FXML
	private  TextField TfidTienda;
	@FXML
	private TextField TfPrimerNombre;	
	@FXML
	private TextField TfSegundoNombre;
	@FXML
	private TextField TfEmail;
	@FXML
	private TextField TfDireccion;
	@FXML
	private Checkbox CheckActivo;
	@FXML
	private DatePicker DtFechaCreacion;
	@FXML
	private DatePicker DtFechaActualizacion;
	@FXML
	private ImageView PhFoto;
	
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	

	


}
