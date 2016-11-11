package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOReportes;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Sale;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

	@FXML
	private DatePicker JDFechaInicio;

	@FXML
	private DatePicker JDFechaFinal;

	@FXML
	private TextField JTTotalPrestamo;

	@FXML
	private TextField jTtotalVenta;

	// datos tabla rental
	@FXML
	private TableColumn<Rental, Integer> ColumnaAlquilerIdInventario;
	@FXML
	private TableColumn<Rental, String> ColumnaAlquilerTitulo;
	@FXML
	private TableColumn<Rental, String> ColumnaAlquilerCliente;
	@FXML
	private TableColumn<Rental, String> ColumnaAlquilerEstado;
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
		configurarTablaRentas();
		llenarComboTiendas();
		eventoAccionCombo();
		JTTotalPrestamo.setEditable(false);
		jTtotalVenta.setEditable(false);
	}

	/**
	 * metodo que permite configurar las columnas de la tabla obteniendo el
	 * atributo que se desea mostrar
	 */

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

	/**
	 * metodo que permite configurar las columnas de la tabla obteniendo el
	 * atributo que se desea mostrar
	 */

	private void configurarTablaRentas() {
		ColumnaAlquilerIdInventario
				.setCellValueFactory(new Callback<CellDataFeatures<Rental, Integer>, ObservableValue<Integer>>() {
					@Override
					public ObservableValue<Integer> call(CellDataFeatures<Rental, Integer> data) {
						return new SimpleObjectProperty<>(data.getValue().getInventory().getInventoryId());
					}
				});
		ColumnaAlquilerTitulo
				.setCellValueFactory(new Callback<CellDataFeatures<Rental, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Rental, String> data) {
						return new SimpleObjectProperty<>(data.getValue().getInventory().getFilm().getTitle());
					}
				});
		ColumnaAlquilerCliente
				.setCellValueFactory(new Callback<CellDataFeatures<Rental, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Rental, String> data) {
						return new SimpleObjectProperty<>(data.getValue().getCustomer().getFirstName());
					}
				});
		ColumnaAlquilerEstado
				.setCellValueFactory(new Callback<CellDataFeatures<Rental, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Rental, String> data) {
						if (data.getValue().getReturnDate() != null) {
							String entregada = "Entregada";
							return new SimpleObjectProperty<>(entregada);
						} else {
							String prestada = "Prestada";
							return new SimpleObjectProperty<>(prestada);
						}
					}
				});
	}

	/**
	 * llena el combo con las tiendas
	 */

	private void llenarComboTiendas() {
		List<Store> lista = boReportes.listarTiendas();
		for (Store store : lista) {
			jcbstore.getItems().add(store);
		}
	}

	/**
	 * evento de accion del combo
	 */
	@FXML
	public void eventoAccionCombo() {
		jcbstore.setOnAction((event) -> {
			Store s = jcbstore.getSelectionModel().getSelectedItem();
			llenarTablaRenta(s);
			llenarTablaVenta(s);

		});
	}

	/**
	 * llena la tabla venta
	 * 
	 * @param s
	 *            la tienda a la que se le van a obtener los datos
	 */
	@FXML
	public void llenarTablaVenta(Store s) {
		List<Sale> lista = boReportes.listarTablaVenta(s);
		for (int i = 0; i < lista.size(); i++) {
			listaSale.add(lista.get(i));
		}
		TBVenta.setItems(listaSale);
	}

	/**
	 * llena la tabla Renta
	 * 
	 * @param s
	 *            la tienda a la que se le van a obtener los datos
	 */
	@FXML
	public void llenarTablaRenta(Store s) {
		List<Rental> lista = boReportes.listarTablaRental(s);
		for (int i = 0; i < lista.size(); i++) {
			listaRenta.add(lista.get(i));
		}
		TBAlquiler.setItems(listaRenta);
	}

	/**
	 * obtiene los valores totales de las ventas y de las rentas hechas en un
	 * periodo de tiempo
	 */
	public void buscarTotales() {

		LocalDate dateUno = JDFechaInicio.getValue();
		LocalDate dateDos = JDFechaFinal.getValue();

		if ((dateUno != null) && (dateDos != null)) {
			LocalDate date = JDFechaInicio.getValue();
			Date FechaInicio = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

			LocalDate data = JDFechaFinal.getValue();
			Date FechaFinal = Date.from(data.atStartOfDay(ZoneId.systemDefault()).toInstant());

			Double totalPrestamos = boReportes.totalPorPrestamo(FechaInicio, FechaFinal);
			Double totalVentas = boReportes.totalPorVentas(FechaInicio, FechaFinal);

			JTTotalPrestamo.setText(String.valueOf(totalPrestamos));
			jTtotalVenta.setText(String.valueOf(totalVentas));
		} else {
			notificar("BUSQUEDA", "Asegurese de que los campos de las fechas esten seleccionados",
					TipoNotificacion.INFO);

		}
	}

}
