/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesis.gestorlab.gui.MainApp;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOActores;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilmActor;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOLanguage;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.FilmActor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Language;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * @author TOSHIBAP55W
 *
 */
@Controller
public class ControladorGestionarPelicula extends BaseController implements Initializable{

	@Autowired
	private BOFilm boFilm;
	
	@Autowired
	private BOFilmActor boFilmActor;
	
	@Autowired
	private BOActores boActores;
	
	@Autowired
	private BOLanguage boLenguaje;
	
	@FXML
	private TextField jTFFilmID;
	
	@FXML
	private TextArea jTFescriptionj;
	
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
	private ComboBox<Language> jCBLanguage1;
	
	@FXML
	private ComboBox<Language> jCBLanguage2;
	
	private  File imgFile;
	
	/*---------- VENTANA TABLA ----------*/
	
	@FXML
	private TextField jTFPersonaje;
	@FXML
	private ComboBox<Actor> jCBActores;
	@FXML
	private TableView<FilmActor> tabla;	
	@FXML
	private TableColumn<FilmActor, Actor> columnaActor;	
	@FXML
	private TableColumn<FilmActor, String> columnaPersonaje;		
	@FXML
	private TableColumn columnaBotones;	
	@FXML
	private ObservableList<FilmActor> data = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listarActores();
		ListarLenguajes();
		
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
	
	public void listarActores(){
	
		List<Actor> listaAutores = boActores.listarAutores();
		for (Actor actor : listaAutores) {
			jCBActores.getItems().add(actor);
		}
	}
	
	public void ListarLenguajes(){
		
		List<Language> listaLenguajes = boLenguaje.listarLenguajes();
		for (Language language : listaLenguajes) {
			jCBLanguage1.getItems().add(language);
			jCBLanguage2.getItems().add(language);
		}
	}

	@FXML
	private void crearPelicula()throws Exception{
		
		if(jTFFilmID.getText().isEmpty()||jTFRentalDuration.getText().isEmpty()||jTFRentalRate.getText().isEmpty()||
				jTFReplacementCost.getText().isEmpty()||jTFTitle.getText().isEmpty()||jCBLanguage1.getValue()==null|| jCBLanguage2.getValue()==null){
			
			notificar("¡INGRESE!", "Por favor ingrese todos los datos",  TipoNotificacion.ERROR);
		}else{
		
		try{
			
		if(jTFRating.getText().length()>1){
			notificar("¡ERROR!", "La clasificacion de la pelicula solo puede contener 1 LETRA",  TipoNotificacion.ERROR);
		}else{
		
			
		int idFilm=Integer.parseInt(jTFFilmID.getText());
		Film fi = boFilm.buscar(idFilm);
		if(fi!=null){
			notificar("¡ERROR!", "La pelicula con el id= ''"+idFilm+"'' ya se encuentra registrada",  TipoNotificacion.ERROR);
		}else{
			
		if(jPoster.getImage()==null){
			notificar("¡INGRESE!", "Por favor ingrese el poster de la pelicula",  TipoNotificacion.ERROR);
		}else{
		Film film = new Film();
			
		film.setFilmId(Integer.parseInt(jTFFilmID.getText()));
		film.setDescription(jTFescriptionj.getText());
		film.setLastUpdate(new Timestamp(new Date().getTime()));
		film.setRating(jTFRating.getText());
		
		LocalDate date= jYearRelease.getValue();
		Date anhoRealize = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		film.setReleaseYear(anhoRealize);
		
		
		
		/*portada de la pelicula*/
		
		byte[] imagen=new byte[(int)imgFile.length()];
		FileInputStream fin=new FileInputStream(imgFile);
		fin.read(imagen);
		film.setPoster(imagen);
		fin.close();
		
		/*---------------------*/
		/*Duracion de la renta de pelicula*/
		byte duracionRenta= Byte.parseByte((jTFRentalDuration.getText()));
		film.setRentalDuration(duracionRenta);
		/*--------------------------------*/
		film.setRentalRate(Double.parseDouble(jTFRentalRate.getText()));
		film.setReplacementCost(Double.parseDouble(jTFReplacementCost.getText()));
		film.setSpecialFeatures(jTFFactures.getText());
		film.setTitle(jTFTitle.getText());
		film.setLanguage1(jCBLanguage1.getValue());
		film.setLanguage2(jCBLanguage2.getValue());
		film.setLength(Integer.parseInt(jTFLength.getText()));
		
		boFilm.crear(film);
		notificar("¡Pelicula Creada!", "Se ha creado la pelicula exitosamente",  TipoNotificacion.INFO);
		limpiarCampos();
		}
		}
		}
		}catch (NumberFormatException ex){
			
			notificar("¡VERIFICAR!", "Por favor verifique que los datos de "
					+ " (ID Pelicula, Duracion de alquiler, Tarifa de Alquiler,Costo de remplazo y duracion de pelicula)"
					+ " solo contengan valores NUMERICOS",  TipoNotificacion.ERROR);
		}
	  }
	}
	
	public void limpiarCampos(){
		
		jTFFilmID.setText(null);;
		jTFescriptionj.setText(null);
		jTFRating.setText(null);
		jYearRelease.setValue(null);
		jPoster.setImage(null);
		jTFRentalDuration.setText(null);
		jTFRentalRate.setText(null);
		jTFReplacementCost.setText(null);
		jTFFactures.setText(null);
		jTFTitle.setText(null);
		jCBLanguage1.setValue(null);
		jCBLanguage2.setValue(null);
		jTFLength.setText(null);
	}
	
	@FXML
	public void buscarPelicula()throws Exception{
		
		if(jTFFilmID.getText().isEmpty()){
			notificar("¡INGRESE!", "Por favor ingrese el ID de la pelicula que desea buscar",  TipoNotificacion.ERROR);
		}else{
		int idFilm = Integer.parseInt(jTFFilmID.getText()); 
		Film fi =boFilm.buscar(idFilm);
		if(fi!=null){
			
			jTFFilmID.setText(fi.getFilmId()+"");
			jTFescriptionj.setText(fi.getDescription());
			jTFRating.setText(fi.getRating());
			
			//jYearRelease.setValue(LocalDate.of(fi.getReleaseYear().getYear(), fi.getReleaseYear().getMonth(), fi.getReleaseYear().getDay()));
			//DatePicker date = new DatePicker(LocalDate.of(fi.getReleaseYear().getYear(), fi.getReleaseYear().getMonth(), fi.getReleaseYear().getDay()));
			//jYearRelease.setValue(date.getValue());
			
			jTFRentalDuration.setText(fi.getRentalDuration()+"");
			jTFRentalRate.setText(fi.getRentalRate()+"");
			jTFReplacementCost.setText(fi.getReplacementCost()+"");
			jTFFactures.setText(fi.getSpecialFeatures());
			jTFTitle.setText(fi.getTitle());
			
			jCBLanguage1.setValue(fi.getLanguage1());
			jCBLanguage2.setValue(fi.getLanguage2());
			jTFLength.setText(fi.getLength()+"");
			
			Image im=new Image(new ByteArrayInputStream(fi.getPoster()));
			jPoster.setImage(im); 
	        
		
		}else{
			notificar("¡ERROR!", "La pelicula con el ID= ''"+idFilm+"'' (NO) se encuentra registrada",  TipoNotificacion.ERROR);

		}
		
	}
	}
	
	@FXML
	private void editarPelicula()throws Exception{
		
		if(jTFFilmID.getText().isEmpty()){
			notificar("¡ERROR!", "Para editar ingrese el ID de la pelicula y busquela ",  TipoNotificacion.ERROR);

		}else{
			
				int idFilm=Integer.parseInt(jTFFilmID.getText());
				Film fi = boFilm.buscar(idFilm);
				if(fi==null){
					notificar("¡ERROR!", "La pelicula con el id= ''"+idFilm+"'' (NO) se encuentra registrada",  TipoNotificacion.ERROR);
				}else{
					
					if(jTFFilmID.getText().isEmpty()||jTFRentalDuration.getText().isEmpty()||jTFRentalRate.getText().isEmpty()||
							jTFReplacementCost.getText().isEmpty()||jTFTitle.getText().isEmpty()||jCBLanguage1.getValue()==null|| 
							jCBLanguage2.getValue()==null ||  jYearRelease.getValue()==null){
						
						notificar("¡INGRESE!", "Por favor ingrese todos los datos",  TipoNotificacion.ERROR);
					}else{
						if(jPoster.getImage()==null){
							notificar("¡INGRESE!", "Por favor ingrese el poster de la pelicula",  TipoNotificacion.ERROR);
						}else{
					try{
					
						
					Film film = new Film();
					
					film.setFilmId(Integer.parseInt(jTFFilmID.getText()));
					film.setDescription(jTFescriptionj.getText());
					film.setLastUpdate(new Timestamp(new Date().getTime()));
					film.setRating(jTFRating.getText());
					
					LocalDate date= jYearRelease.getValue();
					Date anhoRealize = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
					film.setReleaseYear(anhoRealize);
			       
				    
				    	byte[] imagen=new byte[(int)imgFile.length()];
						FileInputStream fin=new FileInputStream(imgFile);
						fin.read(imagen);
						film.setPoster(imagen);
						fin.close();
				   
					 
					/*---------------------*/
					/*Duracion de la renta de pelicula*/
					byte duracionRenta= Byte.parseByte((jTFRentalDuration.getText()));
					film.setRentalDuration(duracionRenta);
					/*--------------------------------*/
					film.setRentalRate(Double.parseDouble(jTFRentalRate.getText()));
					film.setReplacementCost(Double.parseDouble(jTFReplacementCost.getText()));
					film.setSpecialFeatures(jTFFactures.getText());
					film.setTitle(jTFTitle.getText());
					film.setLanguage1(jCBLanguage1.getValue());
					film.setLanguage2(jCBLanguage2.getValue());
					film.setLength(Integer.parseInt(jTFLength.getText()));
					
					
					boFilm.editar(film);
					notificar("¡Pelicula Editada!", "Se ha editado la pelicula exitosamente",  TipoNotificacion.INFO);
					
						
			}catch (NumberFormatException ex){
				
				notificar("¡VERIFICAR!", "Por favor verifique que los datos de "
						+ " (Duracion de alquiler, Tarifa de Alquiler,Costo de remplazo y duracion de pelicula)"
						+ " solo contengan valores NUMERICOS",  TipoNotificacion.ERROR);
			}
			}
			}
		}
		}
		
	}
	@FXML
	private void eliminarPelicula(){
		
		if(jTFFilmID.getText().isEmpty()){
			notificar("¡ERROR!", "Para eliminar ingrese el ID de la pelicula ",  TipoNotificacion.ERROR);

		}else{
			int idFilm=Integer.parseInt(jTFFilmID.getText());
			Film fi = boFilm.buscar(idFilm);
			if(fi==null){
				notificar("¡ERROR!", "La pelicula con el id= ''"+idFilm+"'' (NO) se encuentra registrada",  TipoNotificacion.ERROR);
			}else{
				
				boFilm.eliminar(fi.getFilmId());
				limpiarCampos();
				notificar("¡EXITO!", "La pelicula ''"+idFilm+"'' se ha eliminado exitosamente",  TipoNotificacion.INFO);
			}
				
		}
	}
	
	@FXML
	private void agregarActorFilm(){
		
		try {
			
			String personaje = jTFPersonaje.getText();
			Actor actor = jCBActores.getValue();
			int idFilm=Integer.parseInt(jTFFilmID.getText());
			Film film = boFilm.buscar(idFilm);
			
			FilmActor fiAc = new FilmActor();
			fiAc.setActor(actor);
			fiAc.setFilm(film);
			fiAc.setLastUpdate(new Timestamp(new Date().getTime()));
			fiAc.setCaracter(personaje);
			
			boFilmActor.crear(fiAc);
			agregarActorFilmTabla();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	private void agregarActorFilmTabla(){
		
		int idFilm=Integer.parseInt(jTFFilmID.getText());
		List<FilmActor> filmsActors = boFilmActor.listarFilmesActores(idFilm);
		for (int i = 0; i < filmsActors.size(); i++) {
			
			data.add(filmsActors.get(i));
			columnaActor.setCellValueFactory(new PropertyValueFactory<FilmActor, Actor>("firstName "+"lastName"));
			columnaActor.setMinWidth(100);
			columnaPersonaje.setCellValueFactory(new PropertyValueFactory<FilmActor, String>("caracter"));
			columnaPersonaje.setMinWidth(100);
			
		}
	}
}
