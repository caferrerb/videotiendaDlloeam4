package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOReportes;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

@Controller
public class ControladorReporteVentaAlquiler extends BaseController implements Initializable{

	@Autowired
	private BOReportes boReportes;
	
	@FXML
	private ComboBox<Store> jcbstore;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1){
		llenarComboTiendas();
		
	}
	

	@FXML
	private void llenarComboTiendas() {
		List<Store> lista = boReportes.listarTiendas();
		for (Store store : lista) {
			jcbstore.getItems().add(store);
		}
		
	}
}
