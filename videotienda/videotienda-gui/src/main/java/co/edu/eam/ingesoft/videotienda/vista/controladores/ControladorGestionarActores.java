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
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesis.gestorlab.gui.MainApp;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOActores;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOPais;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Country;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class ControladorGestionarActores extends BaseController implements Initializable {

	@Autowired
	private BOActores boActores;

	@Autowired
	private BOPais boPais;

	@FXML
	private TextField tfDocumento;

	@FXML
	private TextField tfNombre;

	@FXML
	private TextField tfApellido;

	@FXML
	private ImageView imgFoto;

	@FXML
	private ComboBox cbCiudad;

	@FXML
	private TableView tbListaPelicula;

	@FXML
	private Button btnBuscarImagen;

	private File imgFile;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		llenarComboPaises();

	}

	@FXML
	private void llenarComboPaises() {
		List<Country> lista = boPais.listarPaises();
		for (Country country : lista) {
			cbCiudad.getItems().add(country);
		}
	}

	@FXML
	public void abrirImagen() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar Imagen");

		// Agregar filtros para facilitar la busqueda
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

		imgFile = fileChooser.showOpenDialog(MainApp.getStage()); // Obtener la
																	// imagen
																	// seleccionada

		if (imgFile != null) {
			if (imgFile.length() <= 100 * 1024) {
				Image image = new Image("file:" + imgFile.getAbsolutePath());
				imgFoto.setImage(image); // Mostar la imagen
			} else {
				notificar("Crear Actor", "Supero el tamaño maximo de la foto que son 100k", TipoNotificacion.ERROR);
			}

		}
	}

	public void crearActores() {
		
		try {
			if (imgFile == null || tfDocumento.getText().isEmpty() || tfApellido.getText().isEmpty() || tfNombre.getText().isEmpty()) {
			
				notificar("Crear Actor","Verifique que todos los campos esten llenos y haya cargado una imagen ", TipoNotificacion.ERROR);

			}else{
				byte[] bites = new byte[(int) imgFile.length()];
				FileInputStream fin = new FileInputStream(imgFile);
				fin.read(bites);
				
				Actor act = new Actor();
				
				act.setActorId((Integer.parseInt(tfDocumento.getText())));
				act.setFirstName(tfNombre.getText());
				act.setLastName(tfApellido.getText());
				act.setPhoto(bites);
				act.setCountry((Country) cbCiudad.getValue());
				
				boActores.crear(act);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void editarActores() {
		System.out.println("Editandoo...");

	}

	@FXML
	public void buscarActores() {
		if (tfDocumento != null) {
			Actor ac = boActores.buscar(tfDocumento.getText());
			tfNombre.setText(ac.getFirstName());
			tfApellido.setText(ac.getLastName());
			// imgFoto.set

		}

	}

	@FXML
	public void eliminarActores() {
		System.out.println("Eliminandoo...");
	}

}
