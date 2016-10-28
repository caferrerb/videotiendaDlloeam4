/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesis.gestorlab.gui.MainApp;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOActores;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

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
	
	@FXML
	private Button btnBuscarImagen;
	
	private  File imgFile;

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
	@FXML
	public void editarActores(){
		System.out.println("Editandoo...");
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
            imgFoto.setImage(image);
          
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
	@FXML
	public void buscarActores(){
		System.out.println("Buscandoo...");
	}
	@FXML
	public void eliminarActores(){
		System.out.println("Eliminandoo...");
	}

	

	
	
}
