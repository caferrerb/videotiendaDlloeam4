package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOCliente;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

@Controller
public class ControladorAlquilarPelicula extends BaseController implements Initializable{
	
	@Autowired
	private BOCliente boCliente;
	
    @Autowired
    private BOFilm pelicula;
	
	@FXML
	private ComboBox<Film> cBPeliculas;
	
	@FXML
	private TextField tFIdentificacion;
	
	@FXML
	private TextField tFNombre;
	
	@FXML
	private TextField tFFechaEntrega;
	
	@FXML
	private TableView<Rental> tTPrestamos;
	
	@FXML
	private AnchorPane iDAlquilarPelicula;
	
	@FXML
	private ImageView PhFoto;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		 llenarComboPeliculas();
	}
	
	
	@FXML
	public void buscarCliente(){
		if(tFIdentificacion.getText().isEmpty()){
			notificar("Busqueda", "Debe ingresar el campo de IDENTIFICACION para buscar", TipoNotificacion.ERROR);
		}else{
		int identificacion = Integer.parseInt(tFIdentificacion.getText());
		Customer cliente = boCliente.buscar(identificacion);
		if (cliente != null){
			tFNombre.setText(cliente.getFirstName() + " " + cliente.getLastName());
			
			Image img = new Image (new ByteArrayInputStream(cliente.get));
			PhFoto.setImage(img);
		} else {
			notificar("Busqueda", "El cliente que busca no ha sido encontrado", TipoNotificacion.ERROR);
		}
		}
		
		
	}
	
	private void llenarComboPeliculas() {
		
		List<Film> lista = pelicula.listarPeliculasNombres();
		for (Film pelicula : lista) {
			cBPeliculas.getItems().add(pelicula);
		}
	}
	
	
	

}
