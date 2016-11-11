/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOActores;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilmActor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.FilmActor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.FilmActorPK;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * @author TOSHIBAP55W
 *
 */
@Controller
public class ControladorDialogoFilmActor extends BaseController implements Initializable{

	@Autowired
	private BOActores boActores;
	
	@Autowired
	private BOFilm boFilm;
	
	@Autowired
	private BOFilmActor boFilmActor;
	
	@FXML
	private ComboBox<Actor> jCBActorFA;
	
	@FXML
	private ComboBox<Film> jCBPeliculaFA;
	
	@FXML
	private TextField jTFPersonajeFA;
	
	private FilmActor fiAc;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
	fiAc = (FilmActor) obtenerValor("peliculaActor");

				
		if(fiAc!=null){
			listarActores();
			listarPeliculas();
			int idFilm =fiAc.getFilm().getFilmId();
			Film film = boFilm.buscar(idFilm);
			jCBPeliculaFA.setValue(film);
			int idActor =fiAc.getActor().getActorId();
			Actor actor = boActores.buscar(idActor);
			jCBActorFA.setValue(actor);
			String personaje = fiAc.getCaracter();
			jTFPersonajeFA.setText(personaje);
			borrarSesion("pelicula");
		}
		
		
	}
	
	
	public void listarActores(){
		
		List<Actor> listaAutores = boActores.listarAutores();
		for (Actor actor : listaAutores) {
			jCBActorFA.getItems().add(actor);
		}
	}

	public void listarPeliculas(){
		
		List<Film> listaFilms = boFilm.listarTodos();
		for (Film film : listaFilms) {
			jCBPeliculaFA.getItems().add(film);
		}
	}
	
	public void editarFilmActor(){
		
		if(jTFPersonajeFA.getText().isEmpty()){
			notificar("INGRESE", "Por favor ingrese todos los datos", TipoNotificacion.ERROR);
		
		}else{
			
			String personaje = jTFPersonajeFA.getText();
			Actor actor = jCBActorFA.getValue();
			Film film = jCBPeliculaFA.getValue();
			
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
			
			boFilmActor.editar(fiAc);
			notificar("¡EXITO!", "Se ha editado exitosamente",  TipoNotificacion.INFO);
		}
	}

}
