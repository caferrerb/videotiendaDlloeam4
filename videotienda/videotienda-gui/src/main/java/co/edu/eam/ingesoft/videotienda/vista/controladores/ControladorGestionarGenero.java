/**
 * @author Carlos Alfredo Martinez Villada
 * Controlador encargado de todas las funciones de la ventana gestionar genero.
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOGenero;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Category;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Country;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Language;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.GeneradorReporte;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

@Controller
public class ControladorGestionarGenero extends BaseController implements Initializable{
	@Autowired
	private DataSource ds;
	
	@Autowired
	private BOGenero bo;
	
	/**
	 * Componentes de la Ventana gestionar genero
	 */
	@FXML
	private TextField tFnombreGenero;
	
	@FXML
	private TextField tFnuevoNombreGenero;
	
	@FXML
	private GridPane nuevoNombre;
	
	/* Combo con todas los Generos */
	@FXML
	private ComboBox<java.util.Locale.Category> CbGeneros;
	
	/* Tabla Generos */
	@FXML
	private TableView<Category> TbGeneros;
	
	@FXML
	private TableColumn<Category, Byte> TbGenerosID;
	
	@FXML
	private TableColumn<Category, String> TbGenerosNombre;
	
	@FXML
	private TableColumn<Category, Timestamp> TbGenerosFecha;
	
	@FXML
	private final ObservableList<Category> list = FXCollections.observableArrayList();
	
	/* Tabla peliculas por genero */ 
	@FXML
	private TableView<Film> TbPeliculas;
	
	@FXML
	private TableColumn<Film, String> TbPeliculasTitulo;
	
	@FXML
	private TableColumn<Film, Date> TbPeliculasYear;
	
	@FXML
	private TableColumn<Language, String> TbPeliculasIdioma;
	
	@FXML
	private TableColumn<Film, Byte> TbPeliculasValor;
	
	/* Tabla Actores por genero */
	@FXML
	private TableView<Actor> TbActores;
	
	@FXML
	private TableColumn<Actor, String> TbActoresNombres;
	
	@FXML
	private TableColumn<Actor, String> TbActoresApellidos;
	
	@FXML
	private TableColumn<Country, String> TbActoresCiudad;
	
	@FXML
	private TableColumn<Country, String> TbActoresPais;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nuevoNombre.setVisible(false);
		cargarTablaGeneros();
		cargarComboGeneros();
		if(list.size() > 0){
			cargarTablaPeliculasPorGenero(list.get(0));
			cargarTablaActoresPorGenero(list.get(0));
		}
	}
	
	/**
	 * Registra la informacion de un genero
	 */
	@FXML
	public void crearGenero(){
		String name = tFnombreGenero.getText();
		if(name.isEmpty()){
			notificar("Falta Informacion", "Por favor ingrese el nombre del genero",  TipoNotificacion.ERROR);
		}else{
			try{
				Category categoria = new Category();
				Date date = new Date();
				long time = date.getTime();
				Timestamp lastUpdate = new Timestamp(time);
				categoria.setLastUpdate(lastUpdate);
				categoria.setName(name);
				bo.crear(categoria);
				notificar("Genero creado exitosamente", "Se ha creado el genero "+name+" correctamente",  TipoNotificacion.INFO);
				limpiarCampos();
				cargarTablaGeneros();
				cargarComboGeneros();
			}catch(ExcepcionNegocio ex){
				notificar("Administrador Genero", ex.getMessage(),  TipoNotificacion.ERROR);
				limpiarCampos();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Buscar un Genero
	 */
	@FXML
	public void buscarGenero(){
		String name = tFnombreGenero.getText();
		if(name.isEmpty()){
			notificar("Falta Informacion", "Por favor ingrese el nombre del genero a buscar",  TipoNotificacion.ERROR);
		}else{
			try{
				Category genero = bo.buscarPorNombre(name);
				if(genero != null){
					/* Aqui debo refrescar todas las tablas y el Combobox */
					cargarTablaGeneros();
				}else{
					notificar("Sin Resultados", "No se ha encontrado ningun Genero\ncon el nombre "+name,  TipoNotificacion.INFO);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Editar Informacion de un Genero
	 */
	@FXML
	public void editarGenero(){
		String name = tFnombreGenero.getText();
		if(name.isEmpty()){
			notificar("Busque el Genero", "Por favor busque el Genero a Editar",  TipoNotificacion.ERROR);
		}else{
			nuevoNombre.setVisible(true);
			String nuevoNombre = tFnuevoNombreGenero.getText();
			if(nuevoNombre.isEmpty()){
				notificar("Falta Informacion", "Por favor, ingrese el nuevo nombre de la categoria",  TipoNotificacion.INFO);
			}else{
				try{
					Category genero = bo.buscarPorNombre(name);
					if(genero != null){
						genero.setName(nuevoNombre);
						bo.editar(genero);
						notificar("Genero editado exitosamente", "Se ha editadoo el genero "+name+" correctamente\nPaso a llamarse "+nuevoNombre,  TipoNotificacion.INFO);
						limpiarCampos();
						cargarTablaGeneros();
						cargarComboGeneros();
					}else{
						notificar("Administrador Generos", "Usted puede creer... que no he encontrado\nninguna categoria con el nombre "+name, TipoNotificacion.INFO);
					}
				}catch(ExcepcionNegocio ex){
					notificar("Administrador Genero", ex.getMessage(),  TipoNotificacion.ERROR);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Limpiamos los datos del genero
	 */
	public void limpiarCampos(){
		tFnombreGenero.setText(null);
		tFnuevoNombreGenero.setText(null);
	}
	
	/**
	 * Carga todos los generos con su informacion en una tabla
	 */
	public void cargarTablaGeneros(){
		try{
			/* Clear para eliminar todos los elementos existentes en el list
			 * asi evitamos que se dupliquen los elementos.
			 */
			list.clear(); 
			List<Category> lista = bo.listarGeneros();
			for (Category category : lista) {
				/* Agregamos un elemento al list*/
				list.add(category);
				/* Agregamos cada uno de los valores a su respectiva columna */
				TbGenerosID.setCellValueFactory(new PropertyValueFactory<Category, Byte>("categoryId"));
				TbGenerosNombre.setCellValueFactory(new PropertyValueFactory<Category, String>("name"));
				TbGenerosFecha.setCellValueFactory(new PropertyValueFactory<Category, Timestamp>("lastUpdate"));
			}	
			/* Insertamos el list en la tabla */
			TbGeneros.setItems(list);
		}catch(Exception e){
				e.printStackTrace();
		}
	}
	
	/**
	 * Carga todos los generos en un Combobox
	 */
	public void cargarComboGeneros(){
		if(list.size() > 0){
			/* Insertamos el list en el combobox */
			CbGeneros.setItems(list);
			/* Elegimos que elemento del Combobox sera seleccionado por defecto */
			SingleSelectionModel<Category> selec = CbGeneros.getSelectionModel();
			selec.select(0);
		}else{
			/* Editamos el texto default del combobox */
			CbGeneros.setPromptText("No hay Generos");
		}
	}
	
	/**
	 * Vamos a cargar las tablas Peliculas y actores
	 * dependiendo de lo que el usuario eliga en el ComboGeneros
	 */
	public void cambioCombo(){
		Category genero = CbGeneros.getValue();
		cargarTablaPeliculasPorGenero(genero);
		cargarTablaActoresPorGenero(genero);
	}
	
	/**
	 * Carga todas las peliculas de un determinado genero en una tabla
	 */
	public void cargarTablaPeliculasPorGenero(Category c){
		try{
			ObservableList<Film> peliculas = FXCollections.observableArrayList();
			List<Film> lista = bo.listarPeliculasPorGenero(c);
			for (Film film : lista) {
				/* Agregamos un elemento al ObservableList peliculas */
				peliculas.add(film);
				/* Agregamos cada uno de los valores a su respectiva columna */
				TbPeliculasTitulo.setCellValueFactory(new PropertyValueFactory<Film, String>("title"));
				TbPeliculasYear.setCellValueFactory(new PropertyValueFactory<Film, Date>("releaseYear"));
				TbPeliculasIdioma.setCellValueFactory(new PropertyValueFactory<Language, String>("name"));
				TbPeliculasValor.setCellValueFactory(new PropertyValueFactory<Film, Byte>("rentalRate"));
			}
			/* Insertamos el list en la tabla */
			TbPeliculas.setItems(peliculas);			
		}catch(Exception e){
				e.printStackTrace();
		}
	}
	
	/**
	 * Carga todas los actores que hallan trabajado en peliculas
	 * con un determinado genero en una tabla
	 */
	public void cargarTablaActoresPorGenero(Category c){
		try{
			ObservableList<Actor> actores = FXCollections.observableArrayList();
			List<Actor> lista = bo.listarActoresPorGenero(c);
			for (Actor actor : lista) {
				/* Agregamos un elemento al ObservableList actores */
				actores.add(actor);
				/* Agregamos cada uno de los valores a su respectiva columna */
				TbActoresNombres.setCellValueFactory(new PropertyValueFactory<Actor, String>("firstName"));
				TbActoresApellidos.setCellValueFactory(new PropertyValueFactory<Actor, String>("lastName"));
				TbActoresCiudad.setCellValueFactory(new PropertyValueFactory<Country, String>("country"));
				TbActoresPais.setCellValueFactory(new PropertyValueFactory<Country, String>("country"));
			}
			/* Insertamos el list en la tabla */
			TbActores.setItems(actores);			
		}catch(Exception e){
				e.printStackTrace();
		}
	}
	/**
	 * Generar reporte de peliculas por genero
	 */
	public void reportePeliculasGenero(){
		Category genero = CbGeneros.getSelectionModel().getSelectedItem();
		if(genero == null){
			notificar("Genero no seleccionado", "por favor, seleccione un genero",  TipoNotificacion.ERROR);
		}else{
			try{
				GeneradorReporte reporte = new GeneradorReporte(ds.getConnection());
				Map<String, Object> parametros = new HashMap<>();
				parametros.put("genero",genero.getCategoryId());
				reporte.generarReporte(parametros, "/reportes/peliculasPorGenero.jrxml", "peliculas por Genero");
			} catch (Exception e) {
				notificar("Error reporte", "Error al intentar generar el reporte", TipoNotificacion.ERROR);
			}
		}
	}
}
