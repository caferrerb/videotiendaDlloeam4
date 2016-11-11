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
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesis.gestorlab.gui.MainApp;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOActores;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilmActor;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilmActorPK;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOLanguage;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Category;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.FilmActor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.FilmActorPK;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Language;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.GeneradorReporte;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.Callback;

/**
 * @author TOSHIBAP55W
 *
 */
@Controller
public class ControladorGestionarPelicula extends BaseController implements Initializable{

	@Autowired
	private DataSource ds;
	
	
	@Autowired
	private BOFilm boFilm;
	
	@Autowired
	private BOFilmActor boFilmActor;
	
	@Autowired
	private BOActores boActores;
	
	@Autowired
	private BOLanguage boLenguaje;
	
	@Autowired
	private BOFilmActorPK boFilmActorPK;
	
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
	
	@FXML
	private ComboBox<Category> jCBCategory;
	
	private  File imgFile;
	
	/*---------- VENTANA TABLA ----------*/
	
	@FXML
	private TextField jTFPersonaje;
	@FXML
	private ComboBox<Actor> jCBActores;	
	@FXML
	private TableColumn<FilmActor, String> columnaActor;	
	@FXML
	private TableColumn<FilmActor, String> columnaPersonaje;		
	@FXML
	private TableColumn<FilmActor,String> columnaBotones1;
	@FXML
	private TableColumn<FilmActor,String> columnaBotones2;
	@FXML
	private TableView<FilmActor> tabla;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		listarActores();
		ListarLenguajes();
		listarCategorias();
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
				fin.close();
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
	
	public void listarCategorias(){
		List<Category> listaCategorias = boFilm.listarCategorias();
		for (Category category : listaCategorias) {
			jCBCategory.getItems().add(category);
		}
	}

	@FXML
	private void crearPelicula()throws Exception{
		
		if(jTFFilmID.getText().isEmpty()||jTFRentalDuration.getText().isEmpty()||jTFRentalRate.getText().isEmpty()||
				jTFReplacementCost.getText().isEmpty()||jTFTitle.getText().isEmpty()||jCBLanguage1.getValue()==null|| 
				jCBLanguage2.getValue()==null || jCBCategory.getValue()==null){
			
			notificar("¡INGRESE!", "Por favor ingrese todos los datos ",  TipoNotificacion.ERROR);
		}else{
		
			if(imgFile==null){
				notificar("¡INGRESE!", "Por favor ingrese el poster de la pelicula",  TipoNotificacion.ERROR);
				return;
			}
	
		if(jTFRating.getText().length()>1){
			notificar("¡ERROR!", "La clasificacion de la pelicula solo puede contener 1 LETRA",  TipoNotificacion.ERROR);
		}else{
		
			
		int idFilm=Integer.parseInt(jTFFilmID.getText());
		Film fi = boFilm.buscar(idFilm);
		if(fi!=null){
			notificar("¡ERROR!", "La pelicula con el id= ''"+idFilm+"'' ya se encuentra registrada",  TipoNotificacion.ERROR);
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
		
		
		
		/*portada de la pelicula*/
		if(imgFile!=null){
			byte[] imagen=new byte[(int)imgFile.length()];
			FileInputStream fin=new FileInputStream(imgFile);
			fin.read(imagen);
			film.setPoster(imagen);
			fin.close();
		}
		
		
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
		film.setCategory(jCBCategory.getValue());
		film.setLength(Integer.parseInt(jTFLength.getText()));
		
		boFilm.crear(film);
		notificar("¡Pelicula Creada!", "Se ha creado la pelicula exitosamente",  TipoNotificacion.INFO);
		limpiarCampos();
		
		
		}catch (NumberFormatException ex){
			
			notificar("¡VERIFICAR!", "Por favor verifique que los datos de "
					+ " (ID Pelicula, Duracion de alquiler, Tarifa de Alquiler,Costo de remplazo y duracion de pelicula)"
					+ " solo contengan valores NUMERICOS",  TipoNotificacion.ERROR);
		}
		}
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
		jCBCategory.setValue(null);
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
			DatePicker date = new DatePicker(LocalDate.of(fi.getReleaseYear().getYear()+1900, fi.getReleaseYear().getMonth()+1, fi.getReleaseYear().getDate()));
			jYearRelease.setValue(date.getValue());
			
			jTFRentalDuration.setText(fi.getRentalDuration()+"");
			jTFRentalRate.setText(fi.getRentalRate()+"");
			jTFReplacementCost.setText(fi.getReplacementCost()+"");
			jTFFactures.setText(fi.getSpecialFeatures());
			jTFTitle.setText(fi.getTitle());
			
			jCBLanguage1.setValue(fi.getLanguage1());
			jCBLanguage2.setValue(fi.getLanguage2());
			jCBCategory.setValue(fi.getCategory());
			jTFLength.setText(fi.getLength()+"");
			
			if(fi.getPoster()!=null){
			Image im=new Image(new ByteArrayInputStream(fi.getPoster()));
			jPoster.setImage(im); 
			}
			
			llenarTabla(idFilm);
			agregarActorFilmTabla();
		
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
							jCBLanguage2.getValue()==null ||  jYearRelease.getValue()==null || jCBCategory.getValue()==null){
						
						notificar("¡INGRESE!", "Por favor ingrese todos los datos",  TipoNotificacion.ERROR);
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
			       
				    if(imgFile!=null){
				    	byte[] imagen=new byte[(int)imgFile.length()];
						FileInputStream fin=new FileInputStream(imgFile);
						fin.read(imagen);
						film.setPoster(imagen);
						fin.close();
				   
				    }
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
					film.setCategory(jCBCategory.getValue());
					
					
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
		
		if(jTFFilmID.getText().isEmpty()||jCBActores.getValue()==null){
			notificar("¡INGRESE!", "Por favor ingrese (ID de pelicula y Actor )",  TipoNotificacion.ERROR);
		}else{
		try {
			
			if(jTFPersonaje.getText().isEmpty()){
				notificar("¡INGRESE!", "Por favor el personaje",  TipoNotificacion.ERROR);
				return;
			}
			String personaje = jTFPersonaje.getText();
			Actor actor = jCBActores.getValue();
			int idFilm=Integer.parseInt(jTFFilmID.getText());
			Film film = boFilm.buscar(idFilm);
			
			FilmActorPK filacPK = new FilmActorPK();
			filacPK.setActorId(actor.getActorId());
			filacPK.setFilmId(film.getFilmId());
			filacPK.setCaracter(personaje);
			
			FilmActor fiAc = new FilmActor();
			
			fiAc.setId(filacPK);
			fiAc.setActor(actor);
			fiAc.setFilm(film);
			fiAc.setLastUpdate(new Timestamp(new Date().getTime()));
			fiAc.setCaracter(personaje);
			

			
			boFilmActor.crear(fiAc);
			notificar("¡EXITO!", "Se ha agragado un personaje exitosamente",  TipoNotificacion.INFO);
			limpiarCamposFilmActor();
			agregarActorFilmTabla();
			llenarTabla(idFilm);
			
		}catch(ExcepcionNegocio ex){
			notificar("¡ERROR!", ex.getMessage(), TipoNotificacion.ERROR);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	}
	
	private void limpiarCamposFilmActor(){
		
		jCBActores.setValue(null);
		jTFPersonaje.setText(null);
	}
	
	private void llenarTabla(int idFilm) {

		List<FilmActor> lista = boFilmActor.listarFilmesActores(idFilm);
		ObservableList<FilmActor> listaTabla = FXCollections.observableArrayList();
		for (FilmActor filmActor : lista) {
			listaTabla.add(filmActor);
		}
		
		tabla.setItems(listaTabla);

	}
	
	private void agregarActorFilmTabla(){
	
		columnaPersonaje.setCellValueFactory(new PropertyValueFactory<>("caracter"));
		columnaActor.setCellValueFactory(new Callback<CellDataFeatures<FilmActor, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<FilmActor, String> data) {
				return new SimpleObjectProperty<>(data.getValue().getActor().getFirstName()+" "+data.getValue().getActor().getLastName());
			}
		});
		
		Callback<TableColumn<FilmActor, String>, TableCell<FilmActor, String>> cellFactory = new Callback<TableColumn<FilmActor, String>, TableCell<FilmActor, String>>() {

			@Override
			public TableCell<FilmActor, String> call(TableColumn<FilmActor, String> param) {
				// TODO Auto-generated method stub
				TableCell<FilmActor, String> cell = new TableCell<FilmActor, String>() {
					final Button btn1 = new Button("Editar");
					//final Button btn2 = new Button("Eliminar");

					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						}else{
							btn1.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									
									FilmActor f=getTableView().getItems().get(getIndex());
									
									
										guardarEnSesion("peliculaActor", f);
										abrirVentana("/fxml/DialogoEditarFilmActor.fxml", ControladorDialogoFilmActor.class);
										llenarTabla(f.getFilm().getFilmId());
										
									
								}
							});
							setGraphic( btn1 );
                            setText( null );
                            
                          
						}
						
					}
				};
				return cell;
			}
		};
		
		
		Callback<TableColumn<FilmActor, String>, TableCell<FilmActor, String>> cellFactoryB = new Callback<TableColumn<FilmActor, String>, TableCell<FilmActor, String>>() {

			@Override
			public TableCell<FilmActor, String> call(TableColumn<FilmActor, String> param) {
				// TODO Auto-generated method stub
				TableCell<FilmActor, String> cell = new TableCell<FilmActor, String>() {
					final Button btn1 = new Button("Eliminar");

					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						}else{
							btn1.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									
									FilmActor f=getTableView().getItems().get(getIndex());
									
									boFilmActor.eliminar(f.getId());
									notificar("¡EXITO!", "Se ha eliminado exitosamente",  TipoNotificacion.INFO);
									guardarEnSesion("pelicula", f);
									llenarTabla(f.getFilm().getFilmId());
								}
							});
							setGraphic( btn1 );
                            setText( null );
                          
						}
						
					}
				};
				return cell;
			}
		};
		columnaBotones1.setCellFactory(cellFactory);
		columnaBotones2.setCellFactory(cellFactoryB);
	}
	
	@FXML
	public void generarReporte(){
		
		int idFilm=Integer.parseInt(jTFFilmID.getText());
		try {
			GeneradorReporte reporter=new GeneradorReporte(ds.getConnection());
			Map<String, Object> params=new HashMap<>();
			params.put("idFilm",idFilm);
			reporter.generarReporte(params, "/reportes/actoresxpelicula.jrxml", "ActoresXPelicula");
		} catch (Exception e) {
			notificar("Ejemplo", "Error generando el reporte", TipoNotificacion.ERROR);
		}
	}
	
	@FXML
	public void hacerReporte(){
		
		if(jTFFilmID.getText().isEmpty()){
			notificar("¡INGRESE!", "Ingrese el ID de la pelicula",  TipoNotificacion.ERROR);
			return;
		}
		int idFilm=Integer.parseInt(jTFFilmID.getText());
		Film fi = boFilm.buscar(idFilm);
		if(fi==null){
			notificar("¡ERROR!", "La pelicula con el id= ''"+idFilm+"'' (NO) se encuentra registrada",  TipoNotificacion.ERROR);
		}else{
			generarReporte();
		}
	}
}
	



