package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOReportes;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Sale;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@Controller
public class ControladorReporteVentaAlquiler extends BaseController implements Initializable {

	@Autowired
	private BOReportes boReportes;

	@FXML
	private Button jBbuscar;
	
	@FXML
	private ComboBox<Store> jcbstore;

	@FXML
	private TableView<Rental> TBAlquiler;

	@FXML
	private TableView<Sale> TBVenta;

	// datos tabla rental
	@FXML
	private TableColumn<Inventory, Integer> ColumnaAlquilerIdInventario;
	@FXML
	private TableColumn<Film, String> ColumnaAlquilerTitulo;
	@FXML
	private TableColumn<Customer, Integer> ColumnaAlquilerCliente;
	// falta estado
	// datos tabla venta
	@FXML
	private TableColumn<Film, Integer> ColumnaVentaIdPelicula;
	@FXML
	private TableColumn<Film, String> ColumnaVentaTitulo;
	@FXML
	private TableColumn<Customer, Integer> ColumnaVentaCliente;
	@FXML
	private TableColumn<Sale, Date> ColumnaVentaFecha;

	ObservableList<Rental> listaRenta;
	ObservableList<Sale> listaVenta;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		llenarComboTiendas();

	}

	@FXML
	private void llenarComboTiendas() {
		List<Store> lista = boReportes.listarTiendas();
		for (Store store : lista) {
			jcbstore.getItems().add(store);
		}
	}

	@FXML
	public void eventoAccionCombo() {
		if (jcbstore.getSelectionModel().getSelectedIndex() != 0) {
			Store s = jcbstore.getSelectionModel().getSelectedItem();
			llenarTablaRenta(s);
			llenarTablaVenta(s);
		} else {
			notificar("NOTIFICACION", "seleccione una tienda del combo", TipoNotificacion.ERROR);

		}
	}

	@FXML
	public void llenarTablaRenta(Store s) {
		List<Rental> lista = boReportes.listarTablaRental(s);
		for (Rental rental : lista) {
			listaRenta.add(rental);
			ColumnaAlquilerIdInventario
					.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("ID INVENTARIO"));
			ColumnaAlquilerTitulo.setCellValueFactory(new PropertyValueFactory<Film, String>("TITULO"));
			ColumnaAlquilerCliente.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("CLIENTE"));
			TBAlquiler.setItems(listaRenta);
		}

	}

	@FXML
	public void llenarTablaVenta(Store s) {
		List<Sale> lista = boReportes.listarTablaVenta(s);
		for (Sale sale : lista) {
			listaVenta.add(sale);
			ColumnaVentaIdPelicula.setCellValueFactory(new PropertyValueFactory<Film, Integer>("ID PELICULA"));
			ColumnaVentaTitulo.setCellValueFactory(new PropertyValueFactory<Film, String>("TITULO"));
			ColumnaVentaCliente.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("CLIENTE"));
			ColumnaVentaFecha.setCellValueFactory(new PropertyValueFactory<Sale, Date>("FECHA"));
			TBVenta.setItems(listaVenta);
		}
	}
}
