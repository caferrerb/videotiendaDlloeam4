package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.event.TableColumnModelListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BORental;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
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
 * Clase responsable de controlar la ventana ver peliculas rentadas
 * 
 * @author Cristian Sinisterra email: cristiansinisterra@hotmail.com <br/>
 *         Fecha: 27/10/2016<br/>
 * @param <Date>
 */
@Controller
public class ControladorVerPeliculasRentadas<Date> extends BaseController implements Initializable {

	@Autowired
	private BOTienda boTienda;

	@Autowired
	private BORental boRental;

	@FXML
	private ComboBox<Store> cbTienda;

	@FXML
	private TableView<Rental> tbPeliculasDeTiendaR;

	@FXML
	private TableColumn<Inventory, Integer> cIDInventario;

	@FXML
	private TableColumn<Film, String> cTitulo;

	@FXML
	private TableColumn<Customer, String> cCliente;

	@FXML
	private TableColumn<Rental, Date> cFecha;

	@FXML
	private TableColumn<Rental, Date> cEntrega;

	@FXML
	private TableColumn<Store, Button> prueba;

	@FXML
	private final ObservableList<Rental> filmRentadas= FXCollections.observableArrayList();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cargarComboTienda();

	}

	private void cargarComboTienda() {
		List<Store> lista = boTienda.listarTiendas();
		for (Store store : lista) {
			cbTienda.getItems().add(store);
		}
	}

	@FXML
	public void cargarTablaPeliculas(Store store) {
		List<Rental> lista = boRental.listarTablaRental(store);
		for (Rental rental : lista) {
			filmRentadas.add(rental);
			cIDInventario.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("Id Inventario"));
			cTitulo.setCellValueFactory(new PropertyValueFactory<Film, String>("Titulo Pelicula"));
			cCliente.setCellValueFactory(new PropertyValueFactory<Customer, String>("Nombre Cliente"));
			cFecha.setCellValueFactory(new PropertyValueFactory<Rental, Date>("Fecha Prestamo"));
			cEntrega.setCellValueFactory(new PropertyValueFactory<Rental, Date>("Fecha Entrega"));
			tbPeliculasDeTiendaR.setItems(filmRentadas);
		}

	}
	
	@FXML
	public void eventoSeleccionCombo(){
		if(cbTienda.getSelectionModel().getSelectedIndex() != 0){
			Store store=cbTienda.getSelectionModel().getSelectedItem();
			cargarTablaPeliculas(store);
			
		}else{
			notificar("NOTIFICACION", "Por favor selecione una tienda", TipoNotificacion.ERROR);
		}
	}
	
}
