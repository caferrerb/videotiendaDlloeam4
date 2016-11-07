package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOAlquilarPeliculas;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

@Controller
public class ControladorVerPeliEjemplo extends BaseController implements Initializable{

	@Autowired
	private BOFilm boPeli;
	
	@FXML
	private TextField tfNom;
	
	@FXML
	private TextField tfdur;
	
	private Film pelicula;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pelicula=(Film) obtenerValor("pelicula");
		
		if(pelicula!=null){
			tfNom.setText(pelicula.getTitle());
			tfdur.setText(String.valueOf(pelicula.getLength()));
		}
		
	}

}
