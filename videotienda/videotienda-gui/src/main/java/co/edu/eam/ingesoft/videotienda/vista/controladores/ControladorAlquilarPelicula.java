package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ResourceBundle;

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
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

@Controller
public class ControladorAlquilarPelicula extends BaseController implements Initializable {

	@Autowired
	private BOCliente boCliente;

	@Autowired
	private BOFilm pelicula;

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
	private TableView<Rental> tTPrestamos;

	@FXML
	private AnchorPane iDAlquilarPelicula;

	@FXML
	private ImageView PhFoto;
	
	@FXML
	private DatePicker dFechaEntrega;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		llenarComboPeliculas();

	}

	@FXML
	public void buscarCliente() {
		if (tFIdentificacion.getText().isEmpty()) {
			notificar("Busqueda", "Debe ingresar el campo de IDENTIFICACION para buscar", TipoNotificacion.ERROR);
		} else {
			int identificacion = Integer.parseInt(tFIdentificacion.getText());
			Customer cliente = boCliente.buscar(identificacion);
			if (cliente != null) {
				tFNombre.setText(cliente.getFirstName() + " " + cliente.getLastName());

				Image img = new Image(new ByteArrayInputStream(cliente.getPicture()));
				PhFoto.setImage(img);

			} else {
				notificar("Busqueda", "El cliente que busca no ha sido encontrado", TipoNotificacion.ERROR);
			}
		}

	}

	@FXML
	public void prestamosClientes() {
		// int idCustomer = Integer.parseInt(tFIdentificacion.getText());
		//
		// List<Rental> listaPrestamos =
		// boAlquiPelicula.listarPrestamoCliente(idCustomer);
		// ObservableList<Rental> prestamos = prestamos.setAll(listaPrestamos);

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

		if (tFIdentificacion.getText().isEmpty()) {
			notificar("Prestamo", "Debe llenar los campos para poder realizar el prestamo", TipoNotificacion.ERROR);
		} else {
			int idCliente = Integer.parseInt(tFIdentificacion.getText());
			Film f = cBPeliculas.getSelectionModel().getSelectedItem();
			LocalDate fechaEntrega = dFechaEntrega.getValue();
			
			try {
				boAlquiPelicula.registrarPrestamo(idCliente, f, fechaEntrega);

				notificar("Prestamo", "Se ha prestado la pelicula", TipoNotificacion.INFO);
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

	}

}
