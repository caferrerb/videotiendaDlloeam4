package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.ComboBoxEditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import javafx.fxml.Initializable;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
@Controller
public class ControladorVerPeliculasRentadas  extends BaseController implements Initializable{
	
	@Autowired
	private BOTienda boTienda;
	
	@FXML
	private ComboBox<Store>cbTienda;
	
	@FXML
	private TableView<Store>tbPeliculasDeTiendaR;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cargarComboTienda();
		
	}
	
	private void cargarComboTienda(){
		cbTienda.getItems().add(null);
		List<Store>lista=boTienda.listarTiendas();
		for(Store store: lista){
			cbTienda.getItems().add(store);
		}
	}

}
