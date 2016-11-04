package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

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
	private TableColumn<Sale, Integer> ColumnaVentaIdPelicula;
	@FXML
	private TableColumn<Sale, String> ColumnaVentaTitulo;
	@FXML
	private TableColumn<Sale, String> ColumnaVentaCliente;
	@FXML
	private TableColumn<Sale, Date> ColumnaVentaFecha;

	@FXML
	private final ObservableList<Rental> listaRenta = FXCollections.observableArrayList();

	@FXML
	private final ObservableList<Sale> listaSale = FXCollections.observableArrayList();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		configurarTAblaVentas();

		llenarComboTiendas();
		eventoAccionCombo();

	}

	private void configurarTAblaVentas() {
		ColumnaVentaIdPelicula
				.setCellValueFactory(new Callback<CellDataFeatures<Sale, Integer>, ObservableValue<Integer>>() {
					@Override
					public ObservableValue<Integer> call(CellDataFeatures<Sale, Integer> data) {
						return new SimpleObjectProperty<>(data.getValue().getFilm().getFilmId());
					}
				});

		ColumnaVentaTitulo.setCellValueFactory(new Callback<CellDataFeatures<Sale, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Sale, String> data) {
				return new SimpleObjectProperty<>(data.getValue().getFilm().getTitle());
			}
		});

		ColumnaVentaCliente
				.setCellValueFactory(new Callback<CellDataFeatures<Sale, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Sale, String> data) {
						return new SimpleObjectProperty<>(data.getValue().getCustomer().getFirstName());
					}
				});
		ColumnaVentaFecha.setCellValueFactory(new PropertyValueFactory<Sale, Date>("saleDate"));
	}

	private void llenarComboTiendas() {
		List<Store> lista = boReportes.listarTiendas();
		for (Store store : lista) {
			jcbstore.getItems().add(store);
		}
	}

	@FXML
	public void eventoAccionCombo() {
		jcbstore.setOnAction((event) -> {
			Store s = jcbstore.getSelectionModel().getSelectedItem();
			llenarTablaRenta(s);
			llenarTablaVenta(s);

		});
	}

	@FXML
	public void llenarTablaRenta(Store s) {
		List<Rental> lista = boReportes.listarTablaRental(s);
		for (int i = 0; i < lista.size(); i++) {
			listaRenta.add(lista.get(i));
			ColumnaAlquilerIdInventario
					.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("inventoryId"));
			ColumnaAlquilerTitulo.setCellValueFactory(new PropertyValueFactory<Film, String>("title"));
			ColumnaAlquilerCliente.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("firstName"));
			TBAlquiler.setItems(listaRenta);

		}

	}

	@FXML
	public void llenarTablaVenta(Store s) {
		List<Sale> lista = boReportes.listarTablaVenta(s);

		for (int i = 0; i < lista.size(); i++) {
			listaSale.add(lista.get(i));

		}
		TBVenta.setItems(listaSale);
	}
}
