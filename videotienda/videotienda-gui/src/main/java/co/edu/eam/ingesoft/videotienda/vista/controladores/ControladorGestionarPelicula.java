/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import org.springframework.stereotype.Controller;

import co.edu.eam.ingesis.gestorlab.gui.MainApp;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

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
	
	private  File imgFile;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void abrirImagen(){
		
		FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada
         imgFile = fileChooser.showOpenDialog(MainApp.getStage());

        // Mostar la imagen
        if (imgFile != null) {
        	//Image im=new Image(new ByteArrayInputStream(arreglo)); (lINA PARA BUCAR IMAGEN)
            Image image = new Image("file:" + imgFile.getAbsolutePath());
            jPoster.setImage(image);
          
            //Lineas para crear la imagen en la bd
            try {
            	if(imgFile!=null){
				byte[] bites=new byte[(int)imgFile.length()];
				FileInputStream fin=new FileInputStream(imgFile);
				fin.read(bites);
            	}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
	
	}
	
	
	


}
