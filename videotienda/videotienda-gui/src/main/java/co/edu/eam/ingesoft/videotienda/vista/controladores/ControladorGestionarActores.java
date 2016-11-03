/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sun.javafx.font.freetype.FTFactory;

import co.edu.eam.ingesis.gestorlab.gui.MainApp;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOActores;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOPais;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Country;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.MainController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

		if (imgFile != null) {
			if (imgFile.length() <= 200 * 1024) {
				Image image = new Image("file:" + imgFile.getAbsolutePath());
				imgFoto.setImage(image); // Mostar la imagen
			}

		}
	}

	public void crearActores() throws ExcepcionNegocio {

		try {
			if (imgFile == null || tfApellido.getText().isEmpty() || tfNombre.getText().isEmpty()) {

				notificar("Crear Actor", "Verifique que todos los campos esten llenos y haya cargado una imagen ",
						TipoNotificacion.ERROR);

			} else {
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
				notificar("Crear Actor", "El Actor se ha creado exitosamente", TipoNotificacion.INFO);
				limpiar();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void editarActores() throws ExcepcionNegocio, IOException {
		
		if (imgFile == null || tfApellido.getText().isEmpty() || tfNombre.getText().isEmpty()) {

			notificar("Editar Actor", "Verifique que todos los campos esten llenos y haya cargado una imagen ",
					TipoNotificacion.ERROR);

		} else {
		
		byte[] bites = new byte[(int) imgFile.length()];
		FileInputStream fin = new FileInputStream(imgFile);
		fin.read(bites);
		
			Actor act = new Actor();
			act.setActorId(Integer.parseInt(tfDocumento.getText()));
			act.setFirstName(tfNombre.getText());
			act.setLastName(tfApellido.getText());
			act.setCountry((Country) cbCiudad.getValue());
			act.setPhoto(bites);

			boActores.editar(act);
			notificar("Editar Actor", "El Actor se edito correctamente", TipoNotificacion.INFO);
			limpiar();
		}

	}

	@FXML
	public void buscarActores() {
		
	if(tfDocumento == null){
		notificar("Buscar Actor", "Este actor no se encuentra registrado", TipoNotificacion.ERROR);
	}else if(tfDocumento.getText().isEmpty()){
		notificar("Buscar Actor", "Este actor no se encuentra registrado", TipoNotificacion.ERROR);
	}else{
		Actor ac = boActores.buscar(Integer.parseInt(tfDocumento.getText()));
		if (ac != null) {
			tfNombre.setText(ac.getFirstName());
			tfApellido.setText(ac.getLastName());
			cbCiudad.setValue(ac.getCountry());
			Image im = new Image(new ByteArrayInputStream(ac.getPhoto()));
			imgFoto.setImage(im);
		}else{
			limpiar();
			notificar("Buscar Actor", "Este actor no se encuentra registrado", TipoNotificacion.ERROR);
		}
	}
	}

	@FXML
	public void eliminarActores() {
		
		if (tfDocumento == null) {

			notificar("Eliminar Actor", "Primero busque antes de eliminar ",
					TipoNotificacion.ERROR);

		} else if(tfDocumento.getText().isEmpty()){
			notificar("Eliminar Actor", "Primero busque antes de eliminar ",TipoNotificacion.ERROR);
		}else{
		boActores.eliminar(Integer.parseInt(tfDocumento.getText()));
		notificar("Eliminar Actor", "El actor se elimino correctamente", TipoNotificacion.INFO);
		limpiar();
		}
	}

	@FXML
	public void limpiar() {
		tfNombre.setText(null);
		tfDocumento.setText("");
		tfApellido.setText(null);
		cbCiudad.setValue(null);
		imgFoto.setImage(null);

	}

//	@FXML
//	private void mostrarVentanaPelicula() throws IOException {
//
//		String fxmlFile = "/fxml/VentanaGestionarPeliculas.fxml";
//		FXMLLoader loader = new FXMLLoader();
//		Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
//
//		Scene scene = new Scene(rootNode, 1000, 690);
//		scene.getStylesheets().add("/styles/styles.css");
//	}

}
