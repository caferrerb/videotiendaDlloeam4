package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.event.TableColumnModelListener;
import javax.sql.DataSource;
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
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;

import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.GeneradorReporte;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	@Autowired
	private DataSource ds;

	@FXML
	private ComboBox<Store> cbTienda;
	/*
	 * Tabla de las peliculas rentadas
	 */
	@FXML
	private TableView<Rental> tbPeliculasDeTiendaR;
	
	//Atributos que lleva la tabla
	@FXML
	private TableColumn<Rental, Integer> cIDInventario;

	@FXML
	private TableColumn<Rental, String> cTitulo;

	@FXML
	private TableColumn<Rental, String> cCliente;

	@FXML
	private TableColumn<Rental, String> cFecha;

	@FXML
	private TableColumn<Rental, String> cEntrega;
	
	@FXML
	private TableColumn cBoton;

	@FXML
	private TableColumn cBoton1;

	@FXML
	private final ObservableList<Rental> filmRentadas = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cargarTablaPeliculas();
		cargarComboTienda();
		eventoSeleccionCombo();
	}
	
	/**
	 * Metodo que carga todas las tiendas 
	 */
	@FXML
	private void cargarComboTienda() {
		List<Store> lista = boTienda.listarTiendas();
		for (Store store : lista) {
			cbTienda.getItems().add(store);
		}
	}

	@FXML
	public void llenarTabla(Store  store){
		filmRentadas.removeAll(filmRentadas);
		List<Rental> lista = boRental.listarTablaRental(store);
		for (int i=0; i<lista.size(); i++) {
			filmRentadas.add(lista.get(i));
		}
		
		tbPeliculasDeTiendaR.setItems(filmRentadas);
	}
	/**
	 * Metodo que configura la tabla con los atributos que lleva
	 * 
	 */
	@FXML
	public void cargarTablaPeliculas() {
		
			cIDInventario.setCellValueFactory(new Callback<CellDataFeatures<Rental, Integer>, ObservableValue<Integer>>() {
			@Override
			public ObservableValue<Integer> call(CellDataFeatures<Rental, Integer> data) {
				return new SimpleObjectProperty<>(data.getValue().getInventory().getInventoryId());
			}
		});
			
			cTitulo.setCellValueFactory(new Callback<CellDataFeatures<Rental, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Rental, String> data) {
				return new SimpleObjectProperty<>(data.getValue().getInventory().getFilm().getTitle());
			}
		});
			
			cCliente.setCellValueFactory(new Callback<CellDataFeatures<Rental, String>, ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Rental, String> data) {
					return new SimpleObjectProperty<>(data.getValue().getCustomer().getFirstName());
				}
			});
			
			cFecha.setCellValueFactory(new Callback<CellDataFeatures<Rental, String>,ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Rental, String> data) {
					SimpleDateFormat df=new SimpleDateFormat("dd-MM-YYYY");
					return new SimpleObjectProperty<>(df.format(data.getValue().getRentalDate()));
				}
			});

			cEntrega.setCellValueFactory(new Callback<CellDataFeatures<Rental, String>,ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(CellDataFeatures<Rental, String> data) {
					SimpleDateFormat df=new SimpleDateFormat("dd-MM-YYYY");
					return new SimpleObjectProperty<>(df.format(data.getValue().getReturnDate()));
				}
			});
			cBoton.setCellFactory(new Callback<TableColumn<Rental, Boolean>, TableCell<Rental, Boolean>>() {
				public TableCell<Rental, Boolean> call(TableColumn<Rental, Boolean> p) {
					return new ButtonCell(tbPeliculasDeTiendaR);
				}

			});
			cBoton1.setCellFactory(new Callback<TableColumn<Rental, Boolean>, TableCell<Rental, Boolean>>() {
				public TableCell<Rental, Boolean> call(TableColumn<Rental, Boolean> p) {
					return new ButtonCell2(tbPeliculasDeTiendaR);
				}

			});
		
}


	

	/**
	 * Metodo que llena la tabla cuando se selecciona una tienda
	 */
	public void eventoSeleccionCombo() {

		cbTienda.setOnAction((event) -> {
			Store store = cbTienda.getSelectionModel().getSelectedItem();
			
			llenarTabla(store);
		});

	}
	
	/**
	 * 
	 * @author Cristian Sinisterra
	 *
	 */

	private class ButtonCell extends TableCell<Rental, Boolean> {
		// boton a mostrar
		final Button cellButton = new Button("Entregar");

		ButtonCell(final TableView tblView) {

			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					Rental r = getTableView().getItems().get(getIndex());
					guardarEnSesion("pelicula", r);
					abrirVentana("/fxml/AlquilarPelicula.fxml", ControladorAlquilarPelicula.class);
				}
			});
		}

		// Muestra un boton si la fila no es nula
		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if (!empty) {
				setGraphic(cellButton);
			}
		}
	}


	private class ButtonCell2 extends TableCell<Rental, Boolean> {
		// boton a mostrar
		final Button cellButton = new Button("Ver Cliente");

		ButtonCell2(final TableView tblView) {

			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					Rental r = getTableView().getItems().get(getIndex());
					guardarEnSesion("cliente", r);
				
					abrirVentana("/fxml/VentanaGestionarClientes.fxml", ControladorGestionarClientes.class);
				}
			});
		}

		// Muestra un boton si la fila no es nula
		@Override
		protected void updateItem(Boolean t, boolean empty) {
			super.updateItem(t, empty);
			if (!empty) {
				setGraphic(cellButton);
			}
		}
	}
	
	@FXML
	public void generarReporte(){
		try{
			GeneradorReporte report= new GeneradorReporte(ds.getConnection());
			Map<String, Object> params=new HashMap<>();
			params.put("idTienda",(cbTienda.getSelectionModel().getSelectedItem().getStoreId()) );
			report.generarReporte(params, "/reportes/peliculasrentadasportienda.jrxml", "PeliculasRentadasPorTienda");
		}catch (Exception e){
			notificar("Error", "Error generando el reporte", TipoNotificacion.ERROR);
			e.printStackTrace();
		}
	}
	
}
