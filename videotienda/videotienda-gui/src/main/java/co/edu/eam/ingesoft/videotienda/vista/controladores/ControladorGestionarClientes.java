package co.edu.eam.ingesoft.videotienda.vista.controladores;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesis.gestorlab.gui.MainApp;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOCiudad;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOCliente;
import co.edu.eam.ingesoft.videotienda.logica.bos.BODireccion;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Address;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Country;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import co.edu.uniquindio.videotienda.dtos.DayEnum;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

@Controller
public class ControladorGestionarClientes extends BaseController implements Initializable {

	@Autowired
	private BOCliente boCliente;

	@Autowired
	private BODireccion boDir;

	@Autowired
	private BOCiudad boCiudad;
	
	@Autowired
	private BOTienda boTienda;
	
	@FXML
	private TextField jTFNombres;
	
	@FXML
	private TextField jTFApellidos;

	@FXML
	private TextField jTFId;
	
	@FXML
	private ComboBox<City> jCBCiudad;
	
	@FXML
	private TextField jTFDireccion;
	
	@FXML
	private TextField jTFDireccion2;
	
	@FXML
	private TextField jTFDistrito;
	
	@FXML
	private TextField jTFTelefono;
	
	@FXML
	private TextField jTFCodPostal;
	
	@FXML
	private ComboBox<Store> jCBTienda;
	
	@FXML
	private ImageView imgFoto;
	
	private File imgFile;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		llenarComboCiudades();
		llenarComboTiendas();
		
	}

	/**
	 * LLena el combo de ciudades..
	 */
	private void llenarComboCiudades() {
		List<City> listaCiudades = boCiudad.listarCiudades();
		for (City city : listaCiudades) {
			jCBCiudad.getItems().add(city);
		}
		if (!listaCiudades.isEmpty()){
			jCBCiudad.getSelectionModel().selectFirst();
		}
	}
	
	@FXML
	private void llenarComboTiendas() {
		List<Store> lista = boTienda.listarTiendas();
		for (Store tienda : lista) {
			jCBTienda.getItems().add(tienda);
		}
	}
	
	@FXML
	public void abrirImagen() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar Imagen");
		// Agregar filtros para facilitar la busqueda
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

		imgFile = fileChooser.showOpenDialog(MainApp.getStage()); // Obtener la

		if (imgFile != null) {
			if (imgFile.length() <= 200 * 1024) {
				Image image = new Image("file:" + imgFile.getAbsolutePath());
				imgFoto.setImage(image); // Mostar la imagen
			} else {
				notificar("Crear Actor", "La imagen supero el tama�o maximo de 100k", TipoNotificacion.ERROR);
			}

		}
	}
	
	public void crearCliente()  {

		try {
			if (  jTFApellidos.getText().isEmpty() || jTFNombres.getText().isEmpty() || jTFCodPostal.getText().isEmpty()
				|| jTFDireccion.getText().isEmpty() || jTFDireccion2.getText().isEmpty() || jTFDistrito.getText().isEmpty()
				|| jTFId.getText().isEmpty() || jTFTelefono.getText().isEmpty() || jCBCiudad.getValue()==null || jCBTienda.getValue()==null
					) {

				notificar("Crear Cliente", "Verifique que todos los campos est�n diligenciados",
						TipoNotificacion.ERROR);

			} else {
				Customer cliente = new Customer();
				Address direccion = new Address();
				// Busca una ciudad por su id
				City ciudad = boCiudad.buscar(jCBCiudad.getSelectionModel().getSelectedItem().getCityId());
				direccion.setAddress(jTFDireccion.getText());
				direccion.setAddress2(jTFDireccion2.getText());
				// direccion.setAddressId(Integer.parseInt(TFIdDireccion.getText()));
				direccion.setCity(ciudad);
				direccion.setDistrict(jTFDistrito.getText());
				direccion.setLastUpdate(new Timestamp(new Date().getTime()));
				direccion.setPhone(jTFTelefono.getText());
				direccion.setPostalCode(jTFCodPostal.getText());
				// Cliente

				cliente.setAddress(direccion);
				cliente.setCustomerId(Integer.parseInt(jTFId.getText()));
				cliente.setActive(true);
				cliente.setCreateDate(new Date());
				cliente.setFirstName(jTFNombres.getText());
				cliente.setLastName(jTFApellidos.getText());
				cliente.setLastUpdate(new Timestamp(new Date().getTime()));

				// Imagen del Cliente
				if (imgFile != null) {
					byte[] imagen = new byte[(int) imgFile.length()];
					FileInputStream cli = new FileInputStream(imgFile);
					cli.read(imagen);
					cliente.setPicture(imagen);
				} else {
					cliente.setPicture(null);
				}				

				Store tienda = boTienda.buscar(jCBTienda.getSelectionModel().getSelectedItem().getStoreId());
				cliente.setStore(tienda);

				//boDir.crearDireccion(direccion);
				boCliente.crear(cliente);
				
				notificar("Crear Cliente", "El Cliente se ha creado exitosamente", TipoNotificacion.INFO);
				limpiarCampos();

			}

		} catch (ExcepcionNegocio e) {
			notificar("Crear Cliente", "Este Cliente ya se encuentra registrado", TipoNotificacion.ERROR);
		}  catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	@FXML
	public void buscarCliente() {
		if (jTFId.getText() != null) {
			Customer cliente = boCliente.buscar(Integer.parseInt(jTFId.getText()));

			if (cliente != null) {
				jTFNombres.setText(cliente.getFirstName());
				jTFApellidos.setText(cliente.getLastName());
				jCBTienda.setValue(cliente.getStore());
				jTFDireccion.setText(cliente.getAddress().getAddress());
				jTFDireccion2.setText(cliente.getAddress().getAddress2());
				jTFDistrito.setText(cliente.getAddress().getDistrict());
				jTFTelefono.setText(cliente.getAddress().getPhone());
				jTFCodPostal.setText(cliente.getAddress().getPostalCode());
				jCBCiudad.setValue(cliente.getAddress().getCity());
				
				if (cliente.getPicture() != null) {
					Image im = new Image(new ByteArrayInputStream(cliente.getPicture()));
					imgFoto.setImage(im);
				}

				
				
			} else {
				notificar("Buscar Cliente", "El empleado no se encuentra registrado", TipoNotificacion.ERROR);
				limpiarCampos();
			}
		} else {
			notificar("Buscar Cliente", "Debe diligenciar el campo: Numero de Documento*", TipoNotificacion.ERROR);
		}
	}
	
	public void editarCliente()  {

		try {
			if (  jTFApellidos.getText().isEmpty() || jTFNombres.getText().isEmpty() || jTFCodPostal.getText().isEmpty()
				|| jTFDireccion.getText().isEmpty() || jTFDireccion2.getText().isEmpty() || jTFDistrito.getText().isEmpty()
				|| jTFId.getText().isEmpty() || jTFTelefono.getText().isEmpty() || jCBCiudad.getValue()==null || jCBTienda.getValue()==null
					) {

				notificar("Crear Cliente", "Verifique que todos los campos est�n diligenciados",
						TipoNotificacion.ERROR);

			} else {
				Customer cliente = new Customer();
				Address direccion = new Address();
				// Busca una ciudad por su id
				City ciudad = boCiudad.buscar(jCBCiudad.getSelectionModel().getSelectedItem().getCityId());
				direccion.setAddress(jTFDireccion.getText());
				direccion.setAddress2(jTFDireccion2.getText());
				// direccion.setAddressId(Integer.parseInt(TFIdDireccion.getText()));
				direccion.setCity(ciudad);
				direccion.setDistrict(jTFDistrito.getText());
				direccion.setLastUpdate(new Timestamp(new Date().getTime()));
				direccion.setPhone(jTFTelefono.getText());
				direccion.setPostalCode(jTFCodPostal.getText());
				// Cliente

				cliente.setAddress(direccion);
				cliente.setCustomerId(Integer.parseInt(jTFId.getText()));
				cliente.setActive(true);
				cliente.setFirstName(jTFNombres.getText());
				cliente.setLastName(jTFApellidos.getText());
				cliente.setLastUpdate(new Timestamp(new Date().getTime()));

				// Imagen del Cliente
				if (imgFile != null) {
					byte[] imagen = new byte[(int) imgFile.length()];
					FileInputStream cli = new FileInputStream(imgFile);
					cli.read(imagen);
					cliente.setPicture(imagen);
				} else {
					cliente.setPicture(null);
				}				

				Store tienda = boTienda.buscar(jCBTienda.getSelectionModel().getSelectedItem().getStoreId());
				cliente.setStore(tienda);

				//boDir.crearDireccion(direccion);
				boCliente.editar(cliente);
				
				notificar("Editar Cliente", "El Cliente se ha editado exitosamente", TipoNotificacion.INFO);
				limpiarCampos();

			}

		} catch (ExcepcionNegocio e) {
			notificar("Editar Cliente", "Error al editar el cliente", TipoNotificacion.ERROR);
		}  catch (IOException e) {
			
			e.printStackTrace();
		}

	}
	
	@FXML
	public void limpiarCampos() {
		jTFNombres.setText(null);
		jTFId.setText("");
		jTFApellidos.setText(null);
		jCBCiudad.setValue(null);
		jTFDireccion.setText(null);
		jTFDireccion2.setText(null);
		jTFDistrito.setText(null);
		jTFTelefono.setText(null);
		jTFCodPostal.setText(null);
		jCBTienda.setValue(null);
		imgFoto.setImage(null);
	}
}
