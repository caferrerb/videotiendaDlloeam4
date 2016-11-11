package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOAlquilarPeliculas;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOCliente;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOInventario;
import co.edu.eam.ingesoft.videotienda.logica.bos.BORental;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.GeneradorReporte;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import co.edu.uniquindio.videotienda.dtos.PrestamoDTO;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

@Controller
public class ControladorAlquilarPelicula extends BaseController implements Initializable {

	@Autowired
	private BOCliente boCliente;

	@FXML
	private Button jBPrestamo;

	@FXML
	private Button jBBorrar;

	@FXML
	private Button jBBuscar;

	private Customer customer;

	@Autowired
	private BORental boRental;

	@Autowired
	private BOFilm pelicula;

	@Autowired
	private DataSource ds;

	@Autowired
	private BOAlquilarPeliculas boAlquiPelicula;

	@FXML
	private ComboBox<Film> cBPeliculas;

	@FXML
	private TextField tFIdentificacion;

	@FXML
	private TextField tFNombre;

	@FXML
	private TextField tFFechaEntrega;

	@FXML
	private TextField iDClienteReporte;

	@FXML
	private AnchorPane iDAlquilarPelicula;

	@FXML
	private ImageView PhFoto;

	@FXML
	private DatePicker dFechaEntrega;

	@FXML
	private TableView<PrestamoDTO> tTPrestamos;

	@FXML
	private TableColumn<PrestamoDTO, String> cCTitulo;

	@FXML
	private TableColumn<PrestamoDTO, String> cCTienda;

	@FXML
	private TableColumn<PrestamoDTO, PrestamoDTO> cCbotonEliminar;

	List<PrestamoDTO> listaPrestamos;

	ObservableList<PrestamoDTO> prestamosListar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		// inicializarTabla();
		customer = null;
		inicializarTabla();
		llenarComboPeliculas();
		tFNombre.setEditable(false);
		jBPrestamo.setDisable(true);
		jBBorrar.setDisable(true);

	}

	@FXML
	public void generarReporte() {

		try {
			GeneradorReporte reporter = new GeneradorReporte(ds.getConnection());
			Map<String, Object> params = new HashMap<>();
			int idClienteRepor = Integer.parseInt(iDClienteReporte.getText());
			params.put("idCliente", idClienteRepor);
			reporter.generarReporte(params, "/reportes/ReportePrestamos.jrxml", "ReportePrestamoClientes");
		} catch (Exception e) {
			notificar("Ejemplo", "Error generando el reporte", TipoNotificacion.ERROR);
		}
	}

	@FXML
	public void buscarCliente() {
		if (tFIdentificacion.getText().isEmpty()) {
			notificar("Busqueda", "Debe ingresar el campo de IDENTIFICACION para buscar", TipoNotificacion.ERROR);
		} else {
			int identificacion = Integer.parseInt(tFIdentificacion.getText());
			Customer cliente = boCliente.buscar(identificacion);
			customer = cliente;
			if (cliente != null) {
				tFNombre.setText(cliente.getFirstName() + " " + cliente.getLastName());
				Image img = new Image(new ByteArrayInputStream(cliente.getPicture()));
				PhFoto.setImage(img);
				listarPrestamosClientes();
				jBPrestamo.setDisable(false);
				jBBuscar.setDisable(true);
				jBBorrar.setDisable(false);

			} else {
				notificar("Busqueda", "El cliente que busca no ha sido encontrado", TipoNotificacion.ERROR);
			}
		}

	}

	@FXML
	private void llenarComboPeliculas() {

		List<Film> list = boAlquiPelicula.listarPeliculasNombres();
		for (Film pelicula : list) {
			cBPeliculas.getItems().add(pelicula);
			if (!list.isEmpty()) {
				cBPeliculas.getSelectionModel().selectFirst();
			}
		}
	}

	@FXML
	public void prestamo() {

		if (tFIdentificacion.getText().isEmpty() || dFechaEntrega.getValue() == null) {
			notificar("Prestamo", "Debe llenar los campos para poder realizar el prestamo", TipoNotificacion.ERROR);
		} else {
			int idCliente = Integer.parseInt(tFIdentificacion.getText());
			Film f = cBPeliculas.getSelectionModel().getSelectedItem();
			LocalDate fechaEntrega = dFechaEntrega.getValue();

			try {
				boAlquiPelicula.registrarPrestamo(idCliente, f, fechaEntrega);

				notificar("Prestamo", "Se ha prestado la pelicula", TipoNotificacion.INFO);
				listarPrestamosClientes();
				jBBorrar.setDisable(false);
			} catch (ExcepcionNegocio e) {
				notificar("Prestamo", e.getMessage(), TipoNotificacion.ERROR);
			}
		}

	}

	@FXML
	public void borrarCampos() {
		PhFoto.setImage(null);
		tFIdentificacion.setText(null);
		tFNombre.setText(null);
		cBPeliculas.getSelectionModel().select(1);
		jBBorrar.setDisable(true);
		jBBuscar.setDisable(false);
		jBPrestamo.setDisable(true);

	}

	@FXML
	public void listarPrestamosClientes() {
		int idCliente = Integer.parseInt(tFIdentificacion.getText());
		listaPrestamos = boAlquiPelicula.listarLosPrestamosCliente(idCliente);
		prestamosListar = FXCollections.observableArrayList();
		for (PrestamoDTO dto : listaPrestamos) {
			prestamosListar.add(dto);
		}

		tTPrestamos.setItems(prestamosListar);

	}

	public void inicializarTabla() {
		cCTitulo.setCellValueFactory(new PropertyValueFactory<PrestamoDTO, String>("titulo"));
		cCTienda.setCellValueFactory(new PropertyValueFactory<PrestamoDTO, String>("nombreTienda"));
		cCbotonEliminar.setSortable(false);

		cCbotonEliminar.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		cCbotonEliminar.setCellFactory(param -> new TableCell<PrestamoDTO, PrestamoDTO>() {
			private final Button deleteButton = new Button("Retornar");

			@Override
			protected void updateItem(PrestamoDTO prestamo, boolean empty) {
				super.updateItem(prestamo, empty);

				if (prestamo == null) {
					setGraphic(null);
					return;
				}

				setGraphic(deleteButton);
				deleteButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent t) {
						int num = getTableRow().getIndex();
						// borramos el objeto obtenido de la fila
						PrestamoDTO p = getTableView().getItems().get(num);
						boRental.eliminar(p.getIdPrestamos());
						prestamosListar.remove(num);
						notificar("Eliminar Prestamo", "El prestamo a sido entragado correctamente",
								TipoNotificacion.INFO);

					}
				});
			}
		});

	}
}
