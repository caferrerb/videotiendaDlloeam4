package co.edu.eam.ingesoft.videotienda.vista.controladores;



import java.awt.TextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.text.TableView;

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
	private TableView jttablacontenidoPelicula;
	
	
	@FXML
	public void buscarPelicula(){
		System.out.println("buscando...");
		
		Film pelicula = (Film) boPelicula.listarPeliculas(jtfTitulo.getText());
		System.out.println(pelicula.getDescription());
		System.out.println(pelicula.getTitle());
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void listarPeliculas(){
		
	}

}
