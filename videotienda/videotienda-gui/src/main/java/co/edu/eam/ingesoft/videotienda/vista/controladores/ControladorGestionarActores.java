/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.awt.TextField;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.html.ImageView;

import org.hibernate.event.spi.InitializeCollectionEventListener;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;

/**
 * @author GAR-T
 *
 */
@Controller
public class ControladorGestionarActores extends BaseController {

	@FXML
	private TextField jtfDocumento;
	
	@FXML
	private TextField jtfNombre;
	
	@FXML
	private TextField jtfApellido;
	
	@FXML
	private ImageView imgFoto;
	
	public void initializable(URL location, ResourceBundle resources){
		
	}
}
