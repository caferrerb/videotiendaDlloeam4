/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;


import java.net.URL;
import java.util.ResourceBundle;


import org.springframework.stereotype.Controller;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * @author GAR-T
 *
 */
@Controller
public class ControladorGestionarActores extends BaseController implements Initializable {

	@FXML
	private TextField tfDocumento;
	
	@FXML
	private TextField tfNombre;
	
	@FXML
	private TextField tfApellido;
	
	@FXML
	private ImageView imgFoto;
	
	@FXML
	private DatePicker dateFecha;
	
	@FXML
	private TextField tfCiudad;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
	}
	
	public void crear(){
		System.out.println("Creando...");
	}
}
