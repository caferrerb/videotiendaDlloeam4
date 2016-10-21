package co.edu.eam.ingesoft.videotienda.vista.controladores;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Category;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

@Controller
public class CrearPeliculaController extends BaseController {

	@Autowired
	private BOFilm boFilm;
	
	@FXML
	private ComboBox<Category> cbGeneros;
	
	@FXML
	private TextField tfNombre;
	
	@FXML
	private TextField tfDuracion;
	
	@PostConstruct
	public void inicializar(){
		System.out.println("init!!!!"+this);
	}
	
	@FXML
	public void crear(){
		System.out.println("crear!!!!");
		Film film=new Film();
		film.setFilmId((int)System.currentTimeMillis());
		film.setTitle(tfNombre.getText());
		System.out.println("init!!!!"+this);
//		boFilm.crear(film);
		//notificar("Creacion pelicula","Creado!!!!");
		
	}
	
	
	
	
}
