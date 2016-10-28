/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOActores;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * @author GAR-T
 *
 */
@Controller
public class ControladorGestionarActores extends BaseController implements Initializable{

	@Autowired
	private BOActores boActores;
	
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
	
	@FXML
	private TableView tbListaPelicula;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void crearActores(){
		
		Actor act = new Actor();
		act.setActorId((Integer.parseInt(tfDocumento.getText())));
		act.setFirstName(tfNombre.getText());
		act.setLastName(tfApellido.getText());
		//act.setLastUpdate();
		
		System.out.println("Creandoooooo...");
	}
	public void EditarActores(){
		System.out.println("Editandoo...");
	}
	public void buscarActores(){
		System.out.println("Buscandoo...");
	}
	public void eliminarActores(){
		System.out.println("Eliminandoo...");
	}

	

	
	
}
