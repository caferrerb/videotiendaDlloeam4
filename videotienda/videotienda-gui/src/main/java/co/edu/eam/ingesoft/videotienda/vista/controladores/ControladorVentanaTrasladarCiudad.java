package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.DataSource;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesis.gestorlab.gui.MainApp;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOCiudad;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOCliente;
import co.edu.eam.ingesoft.videotienda.logica.bos.BODireccion;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.GeneradorReporte;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

@Controller
public class ControladorVentanaTrasladarCiudad extends BaseController implements Initializable {

	@Autowired
	private BOCliente boCliente;

	private Customer cliente;
	
	@Autowired
	private DataSource ds;

	@Autowired
	private BOCiudad boCiudad;
	
	@FXML
	private Button btnReporte;
	
	@FXML
	private Button btnTrasladar;

	@FXML
	private TextField jTFIdentificacion;

	@FXML
	private TextField jTFNombre;

	@FXML
	private TextField jTFCiudadActual;
	
	@FXML
	private ComboBox<City> comboCiudadReporte;

	@FXML
	private ComboBox<City> jCBCiudadTraslado;

	@FXML
	private TextField jTFNuevaDir;

	@FXML
	private ImageView imgViewFoto;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		llenarComboCiudades();
		cliente = null;
		jTFCiudadActual.setEditable(false);
		jTFNombre.setEditable(false);
	}

	/**
	 * LLena los combos de ciudades
	 */
	private void llenarComboCiudades() {
		List<City> listaCiudades = boCiudad.listarCiudades();
		if (listaCiudades.size() != 0) {
			for (City city : listaCiudades) {
				jCBCiudadTraslado.getItems().add(city);
				comboCiudadReporte.getItems().add(city);
			}
			if (!listaCiudades.isEmpty()) {
				jCBCiudadTraslado.getSelectionModel().selectFirst();
				comboCiudadReporte.getSelectionModel().selectFirst();
			} else {
				btnTrasladar.setVisible(false);
				btnReporte.setVisible(false);
			}
		}
	}

	/**
	 * Permite buscar un cliente registrado
	 */
	@FXML
	public void buscar() {

		Customer c = boCliente.buscar(Integer.valueOf(jTFIdentificacion.getText()));
		if (c != null) {
			cliente = c;
			jTFNombre.setText(c.getFirstName() + " " + c.getLastName());
			jTFCiudadActual.setText(c.getAddress().getCity().getCity());

			// Cargar imagen
			Image im = new Image(new ByteArrayInputStream(c.getPicture()));
			imgViewFoto.setImage(im);

		} else {
			notificar("Busqueda", "El cliente que busca no ha sido encontrado", TipoNotificacion.ERROR);
		}

	}

	/**
	 * Traslada la ciudad de un cliente
	 */
	@FXML
	public void trasladar() {

		try {
			boCliente.trasladar(cliente, jCBCiudadTraslado.getValue(), jTFNuevaDir.getText());
			notificar("Traslado", "Se ha trasladado la ciudad", TipoNotificacion.INFO);
		} catch (ExcepcionNegocio e) {
			notificar("Traslado", e.getMessage(), TipoNotificacion.ERROR);
		}

	}
	
	@FXML
	public void generarReporte(){
		
		try {
			City ciudad = comboCiudadReporte.getValue();
			GeneradorReporte reporter=new GeneradorReporte(ds.getConnection());
			Map<String, Object> params=new HashMap<>();
			params.put("idciudad", ciudad.getCityId());
			reporter.generarReporte(params, "/reportes/clienteciudad.jrxml", "Clientes de una ciudad");
		} catch (Exception e) {
			notificar("Ejemplo", "Error generando el reporte", TipoNotificacion.ERROR);
		}
	}

}
