package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.event.TableColumnModelListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;

/**
 *Clase responsable de controlar la ventana ver peliculas rentadas
 * @author Cristian Sinisterra
 *         email: cristiansinisterra@hotmail.com <br/>
 *         Fecha: 27/10/2016<br/>
 * @param <Date>
 */
@Controller
public class ControladorVerPeliculasRentadas<Date>  extends BaseController implements Initializable{
	
	@Autowired
	private BOTienda boTienda;
	
	@FXML
	private ComboBox<Store>cbTienda;
	
	@FXML
	private TableView<Store>tbPeliculasDeTiendaR;
	
	@FXML 
	private TableColumn<Inventory, Integer> cIDInventario;
	
	@FXML 
	private TableColumn<Film, String> cTitulo;
	
	@FXML 
	private TableColumn<Customer, Integer> cCliente;
	
	@FXML 
	private TableColumn<Rental, Date> cFecha;
	
	@FXML 
	private TableColumn<Rental, Button> cEntrega;
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cargarComboTienda();
		cargarTablaPeliculas();
	}
	
	private void cargarComboTienda(){
		cbTienda.getItems().add(null);
		
		List<Store>lista=boTienda.listarTiendas();
		for(Store store: lista){
			cbTienda.getItems().add(store);
		}
	}
	
	@FXML
	public void cargarTablaPeliculas(){
		try{
			
//			if(cbTienda.getSelectionModel().getSelectedIndex()==0){
//			//	mostrarMensaje(AlertType.INFORMATION,null,null, "No tiene peliculas rentadas");
//			}else{
//				Store store= cbTienda.getSelectionModel().getSelectedItem();
//			}
			ObservableList<Rol> data = FXCollections.observableArrayList(
					new Rol(1, "Uno"),
					new Rol(2, "Dos")
					);
			
		}catch(Exception e1){
			
		}
	}

}
