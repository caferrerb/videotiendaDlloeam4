package co.edu.eam.ingesoft.videotienda.vista.controladores;




import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

@Controller
public class ControladorGestionarVenta extends BaseController implements Initializable {

	@Autowired
	private BOFilm boPelicula;
	
	@FXML
	private TextField jtfTitulo;
	
	@FXML
	private TableView<Film> jttablacontenidoPelicula;
	
//	@FXML
//	private TableView jttablacontenidoPelicula;
	
	
	@FXML
	public void buscarPelicula(){
		System.out.println("buscando...");
		
		String nomPelicula = jtfTitulo.getText();
		List<Film> pelicula = boPelicula.listarPeliculas(nomPelicula);
		for (int i = 0; i < pelicula.size(); i++) {
			System.out.println(pelicula.get(i).getTitle());
			System.out.println(pelicula.get(i).getDescription());
		}
		
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void listarPeliculas(){
		
	}

}
