package co.edu.eam.ingesoft.videotienda.vista.controladores;




import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Category;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.FilmCategory;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
	@FXML
	private TableColumn<Film, String> jcolumnaGenero;	
	@FXML
	private TableColumn<Film, String> jcolumnaTitulo;	
	@FXML
	private TableColumn<Film, Integer> jcolumnaLong;	
	@FXML
	private TableColumn<Film, Double> jcolumnaPrecio;	
	@FXML
	private TableColumn<Film, Button> jcolumnaboton;	
	@FXML
	private final ObservableList<Film> data = FXCollections.observableArrayList();
	
	
	@FXML
	public void buscarPelicula(){
		System.out.println("buscando...");
		try {
			String nomPelicula = jtfTitulo.getText();
			List<Film> pelicula = boPelicula.listarPeliculas(nomPelicula);
			for (int i = 0; i < pelicula.size(); i++) {
				data.add(pelicula.get(i));
				jcolumnaGenero.setCellValueFactory(new PropertyValueFactory<Film, String>(""));
				jcolumnaGenero.setMinWidth(100);
				jcolumnaTitulo.setCellValueFactory(new PropertyValueFactory<Film, String>("title"));
				jcolumnaTitulo.setMinWidth(100);
				jcolumnaLong.setCellValueFactory(new PropertyValueFactory<Film, Integer>("length"));
				jcolumnaLong.setMinWidth(100);
				jcolumnaPrecio.setCellValueFactory(new PropertyValueFactory<Film, Double>("rentalRate"));
				jcolumnaPrecio.setMinWidth(100);
				jcolumnaboton.setCellValueFactory(new PropertyValueFactory<Film, Button>("boton"));
				jcolumnaboton.setMinWidth(100);
				
						
				
				jttablacontenidoPelicula.setItems(data);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void listarPeliculas(){
		
	}

}
