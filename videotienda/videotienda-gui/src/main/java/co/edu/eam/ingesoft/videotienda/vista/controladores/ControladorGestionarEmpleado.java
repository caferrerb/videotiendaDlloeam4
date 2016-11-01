package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesis.gestorlab.gui.MainApp;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOCiudad;
import co.edu.eam.ingesoft.videotienda.logica.bos.BODireccion;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuario;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.main.ContextFactory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Address;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

@Controller
public class ControladorGestionarEmpleado extends BaseController implements Initializable {

	@Autowired
	private BOEmpleado boEmpleado;

	@Autowired
	private BOCiudad boCiudad;

	@Autowired
	private BODireccion boDireccion;

	@Autowired
	private BOUsuario boUsuario;

	@Autowired
	private BOTienda boTienda;

	@FXML
	private TextField TFIdEmpleado;
	@FXML
	private TextField TFIdUsuario;
	@FXML
	private TextField TfPrimerNombre;
	@FXML
	private TextField TfSegundoNombre;
	@FXML
	private TextField TfEmail;
	@FXML
	private CheckBox CheckActivo;
	@FXML
	private TextField TFFechaCreacion;
	@FXML
	private ImageView PhFoto;
	@FXML
	private ComboBox<Store> comboBoxSelecTienda;
	@FXML
	private TableView<StaffSchedule> TbHorario;
	@FXML
	private TextField TFIdDireccion;
	@FXML
	private TextField TFDireccionA;
	@FXML
	private TextField TFDdireccionB;
	@FXML
	private ComboBox<City> CBCiudad;
	@FXML
	private TextField TFDepartamento;
	@FXML
	private TextField TFUlltimaActualizacionDir;
	@FXML
	private TextField TFTelefono;
	@FXML
	private TextField TFCodigoPos;
	@FXML
	private AnchorPane contenido;
	@FXML
	private Button BtAgregarHorario;

	private File imgFile;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		llenarComboCiudad();
		llenarTienda();
	}

	@FXML
	public void crearEmpleado() throws Exception {
		try {
			Staff empleado = new Staff();
			Address direccion = new Address();
			// Busca una ciudad por su id
			City ciudad = boCiudad.buscar(CBCiudad.getSelectionModel().getSelectedItem().getCityId());
			direccion.setAddress(TFDireccionA.getText());
			direccion.setAddress2(TFDdireccionB.getText());
			direccion.setAddressId(Integer.parseInt(TFIdDireccion.getText()));
			direccion.setCity(ciudad);
			direccion.setDistrict(TFDepartamento.getText());
			direccion.setLastUpdate(new Timestamp(new Date().getTime()));
			direccion.setPhone(TFTelefono.getText());
			direccion.setPostalCode(TFCodigoPos.getText());
			// Empleado
			empleado.setAddress(direccion);
			empleado.setStaffId((byte) Integer.parseInt(TFIdEmpleado.getText()));
			empleado.setEmail(TfEmail.getText());
			empleado.setFirstName(TfPrimerNombre.getText());
			empleado.setLastName(TfSegundoNombre.getText());
			empleado.setLastUpdate(new Timestamp(new Date().getTime()));

			// Imagen del empleado
			byte[] imagen = new byte[(int) imgFile.length()];
			FileInputStream emp = new FileInputStream(imgFile);
			emp.read(imagen);
			empleado.setPicture(imagen);

			Usuario usuario = boUsuario.buscar(TFIdUsuario.getText());
			empleado.setUsuario(usuario);

			Store tienda = boTienda.buscar(comboBoxSelecTienda.getSelectionModel().getSelectedItem().getStoreId());
			empleado.setStore(tienda);

			if (CheckActivo.isSelected() == true) {
				empleado.setActive(true);
			} else {
				empleado.setActive(false);
			}
			boDireccion.crearDireccion(direccion);
			boEmpleado.crearEmpleado(empleado);

			notificar("Gestionar empleado", "Empleado Creado con exito", TipoNotificacion.INFO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void editarEmpleado() throws Exception {
		try {
			Staff empleado = new Staff();
			Address direccion = new Address();
			// Busca una ciudad por su id
			City ciudad = boCiudad.buscar(CBCiudad.getSelectionModel().getSelectedItem().getCityId());
			direccion.setAddress(TFDireccionA.getText());
			direccion.setAddress2(TFDdireccionB.getText());
			direccion.setAddressId(Integer.parseInt(TFIdDireccion.getText()));
			direccion.setCity(ciudad);
			direccion.setDistrict(TFDepartamento.getText());
			direccion.setLastUpdate(new Timestamp(new Date().getTime()));
			direccion.setPhone(TFTelefono.getText());
			direccion.setPostalCode(TFCodigoPos.getText());
			// Empleado
			empleado.setAddress(direccion);
			empleado.setStaffId((byte) Integer.parseInt(TFIdEmpleado.getText()));
			empleado.setEmail(TfEmail.getText());
			empleado.setFirstName(TfPrimerNombre.getText());
			empleado.setLastName(TfSegundoNombre.getText());
			// cambiar CAPTURAR LA FECHA QUE SALE EN EL TEXFILE AL BUSCAR
			empleado.setLastUpdate(new Timestamp(new Date().getTime()));
			empleado.setAddress(direccion);

			// Imagen del empleado
			byte[] imagen = new byte[(int) imgFile.length()];
			FileInputStream emp = new FileInputStream(imgFile);
			emp.read(imagen);
			empleado.setPicture(imagen);

			Usuario usuario = boUsuario.buscar(TFIdUsuario.getText());
			empleado.setUsuario(usuario);

			Store tienda = boTienda.buscar(comboBoxSelecTienda.getSelectionModel().getSelectedItem().getStoreId());
			empleado.setStore(tienda);

			if (CheckActivo.isSelected() == true) {
				empleado.setActive(true);
			} else {
				empleado.setActive(false);
			}

			boEmpleado.editarEmpleado(empleado);
			boDireccion.editarDireccion(direccion);
			notificar("Editar empleado", "Empleado editado con exito", TipoNotificacion.INFO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void buscarEmpleado() {
		Staff empleado = boEmpleado.buscar((byte) Integer.parseInt(TFIdEmpleado.getText()));

		if (empleado != null) {
			TFIdUsuario.setText(empleado.getUsuario().getUsuario());
			TfPrimerNombre.setText(empleado.getFirstName());
			TfSegundoNombre.setText(empleado.getLastName());
			TfEmail.setText(empleado.getEmail());
			TFFechaCreacion.setText(empleado.getLastUpdate().toString());
			if (empleado.getActive() == true) {
				CheckActivo.setSelected(true);
			}

			// JOptionPane.showMessageDialog(null, Integer.valueOf(empleado.getAddress()));

			 boDireccion.buscar(Integer.valueOf(empleado.getAddress().getAddressId()));
			 TFIdDireccion.setText(Integer.toString(empleado.getAddress().getAddressId()));
			 TFDireccionA.setText(empleado.getAddress().getAddress());
			 TFDdireccionB.setText(empleado.getAddress().getAddress2());
			 CBCiudad.setValue(empleado.getAddress().getCity());
			 TFDepartamento.setText(empleado.getAddress().getDistrict());
			 TFUlltimaActualizacionDir.setText(empleado.getAddress().getLastUpdate().toString());
			 TFTelefono.setText(empleado.getAddress().getPhone());
			 TFCodigoPos.setText(empleado.getAddress().getPostalCode());
		} else {
			notificar("gestionar Buscar", "No existe este empleado", TipoNotificacion.ERROR);
		}
	}

	private void llenarComboCiudad() {
		List<City> lista = boCiudad.listarCiudades();
		for (City ciudad : lista) {
			CBCiudad.getItems().add(ciudad);
		}
	}

	private void llenarTienda() {
		List<Store> lista = boTienda.listarTiendas();
		for (Store tienda : lista) {
			comboBoxSelecTienda.getItems().add(tienda);
		}
	}

	public void abrirImagen() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Buscar Imagen");

		// Agregar filtros para facilitar la busqueda
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

		// Obtener la imagen seleccionada
		imgFile = fileChooser.showOpenDialog(MainApp.getStage());

		// Mostar la imagen
		if (imgFile != null) {
			// Image im=new Image(new ByteArrayInputStream(arreglo)); (lINA PARA
			// BUCAR IMAGEN)
			Image image = new Image("file:" + imgFile.getAbsolutePath());
			PhFoto.setImage(image);

			// Lineas para crear la imagen en la bd
			try {
				if (imgFile != null) {
					byte[] bites = new byte[(int) imgFile.length()];
					FileInputStream fin = new FileInputStream(imgFile);
					fin.read(bites);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@FXML
	private void llamarVentana() throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/VentanaGestionEmpleadosSub.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Horario empleado");
		stage.setScene(new Scene(root1));
		stage.show();

	}
}
