/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

/**
 * @author TOSHIBAP55W
 *
 */
@Controller
public class ControladorGestionarPelicula extends BaseController implements Initializable{

	@FXML
	private TextField jTFFilmID;
	
	@FXML
	private TextArea jTFescriptionj;
	
	@FXML
	private DatePicker jLastUpdate;
	
	@FXML
	private TextField jTFLength;
	
	@FXML
	private TextField jTFRating;
	
	@FXML
	private DatePicker jYearRelease;
	
	@FXML
	private ImageView jPoster;
	
	@FXML
	private TextField jTFRentalDuration;
	
	@FXML
	private TextField jTFRentalRate;
	
	@FXML
	private TextField jTFReplacementCost;
	
	@FXML
	private TextField jTFFactures;
	
	@FXML
	private TextField jTFTitle;
	
	@FXML
	private TextField jTFLanguaje1;
	
	@FXML
	private TextField jTFLanguaje2;
	
	@FXML
	private ComboBox<Actor> jCBActores;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
