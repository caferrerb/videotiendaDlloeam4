/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;


import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOInventario;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;



/**
 * @author XgioserX
 *
 */
@Controller
public class ControladorGestionInventario extends BaseController implements Initializable{

	@Autowired
	private BOInventario boInventario;
	@Autowired
	private BOTienda boTienda;
	@Autowired
	private BOFilm boFilm;
	@FXML
	private TextField JTFTituloDarAlta;
	@FXML
	private TextField JTFIdPeliculaDarAlta;
	@FXML
	private TextArea JTARazonDarAlta;
	@FXML
	private TextField JTFGenero;
	@FXML
	private TextField JTFTitulo;
	@FXML
	private TextField JTFIdPelicula;
	@FXML
	private ComboBox<Store> JCBTienda;
	
	@FXML
	private TableView<Inventory> jPTienda;
	
	@FXML
	private TableColumn<Film, String> Titulo;
	
	@FXML
	private TableColumn<Film, Integer> Inventario;
	
	@FXML
	private TableColumn<Film, Button> Info;
	
//	@FXML
//	private jjj JPDarDeBaja;
	
	@FXML
	private final ObservableList<Film> data = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		llenarComboTiendas();
		
	}

	
	private void llenarComboTiendas() {
		List<Store> lista = boTienda.listarTiendas();
		for (Store tienda : lista) {
			JCBTienda.getItems().add(tienda);
		}
	}
	
	private void mostrarPanelDarBaja() {
		
	
	}
	@FXML
	public void buscarPelicula()throws Exception{
		if(JTFIdPelicula.getText().isEmpty()){
			notificar("Ingrese", "Ingrese el Id de la pelicula que desea buscar", TipoNotificacion.ERROR);
		}else{
			int idPelicula=Integer.parseInt(JTFIdPelicula.getText());
			Film pelicula=boFilm.buscar(JTFIdPelicula);
			if(pelicula!=null){
				JTFTitulo.setText(pelicula.getTitle());
				//JTFGenero.setText(pelicula.get);
			}else{
				notificar("¡ERROR!", "La pelicula con el ID= ''"+JTFIdPelicula+"'' (NO) se encuentra registrada",  TipoNotificacion.ERROR);

			}

		}
	}
}
