/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOInventario;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * @author XgioserX
 *
 */
public class ControladorGestionInventario extends BaseController implements Initializable{

	@Autowired
	private BOInventario boInventario;
	@Autowired
	private BOTienda boTienda;
	
	@FXML
	private TextField JTFTituloDarAlta;
	@FXML
	private TextField JTFIdPeliculaDarAlta;
	@FXML
	private TextField JTARazonDarAlta;
	@FXML
	private TextField JTFGenero;
	@FXML
	private TextField JTFTitulo;
	@FXML
	private TextField JTFIdPelicula;
	@FXML
	private ComboBox<Store> JCBTienda;
	@FXML
	private TableView<StaffSchedule> JTCantidadPelisPorTienda;
	
	
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
}
