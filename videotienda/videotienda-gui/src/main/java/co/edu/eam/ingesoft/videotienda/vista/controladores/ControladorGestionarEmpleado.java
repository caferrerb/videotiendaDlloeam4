package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import co.edu.eam.ingesis.gestorlab.gui.MainApp;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOCiudad;
import co.edu.eam.ingesoft.videotienda.logica.bos.BODireccion;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOHoraioEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOUsuario;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Address;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import co.edu.uniquindio.videotienda.dtos.DayEnum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

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

	@Autowired
	private BOHoraioEmpleado boHorario;

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
	private TableColumn<StaffSchedule, DayEnum> TBDia;
	@FXML
	private TableColumn<StaffSchedule, Integer> TBInicial;
	@FXML
	private TableColumn<StaffSchedule, Integer> tbFinal;
	@FXML
	private TableColumn TBOpciones;
	@FXML
	private ObservableList<StaffSchedule> data = FXCollections.observableArrayList();

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

	public void limpiarCampos() {
		TFIdEmpleado.setText(null);
		TFIdUsuario.setText(null);
		TfPrimerNombre.setText(null);
		TfSegundoNombre.setText(null);
		TfEmail.setText(null);
		TFDireccionA.setText(null);
		TFDdireccionB.setText(null);
		TFDepartamento.setText(null);
		TFTelefono.setText(null);
		TFCodigoPos.setText(null);
		CBCiudad.setSelectionModel(null);
	}

	@FXML
	public void crearEmpleado() throws Exception {
		try {
			if (TFIdEmpleado.getText() != null && TfPrimerNombre.getText() != null && TfSegundoNombre.getText() != null
					&& TfEmail.getText() != null && TFDireccionA.getText() != null && TFDdireccionB.getText() != null
					&& CBCiudad.getSelectionModel().getSelectedItem() != null && TFDepartamento.getText() != null
					&& TFTelefono.getText() != null && TFCodigoPos.getText() != null
					&& comboBoxSelecTienda.getSelectionModel().getSelectedItem() != null && PhFoto != null) {
				Staff empleado = new Staff();
				Address direccion = new Address();
				// Busca una ciudad por su id
				City ciudad = boCiudad.buscar(CBCiudad.getSelectionModel().getSelectedItem().getCityId());
				direccion.setAddress(TFDireccionA.getText());
				direccion.setAddress2(TFDdireccionB.getText());
				// direccion.setAddressId(Integer.parseInt(TFIdDireccion.getText()));
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
				if (PhFoto != null) {
					byte[] imagen = new byte[(int) imgFile.length()];
					FileInputStream emp = new FileInputStream(imgFile);
					emp.read(imagen);
					empleado.setPicture(imagen);
				} else {
					empleado.setPicture(null);
				}
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
				limpiarCampos();
			} else {
				notificar("Gestionar camposEmpeleado", "Debe llenar los campos obligatorios", TipoNotificacion.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void editarEmpleado() throws Exception {
		try {
			if (TFIdEmpleado.getText() != null && TfPrimerNombre.getText() != null && TfSegundoNombre.getText() != null
					&& TfEmail.getText() != null && TFDireccionA.getText() != null && TFDdireccionB.getText() != null
					&& CBCiudad.getSelectionModel().getSelectedItem() != null && TFDepartamento.getText() != null
					&& TFTelefono.getText() != null && TFCodigoPos.getText() != null
					&& comboBoxSelecTienda.getSelectionModel().getSelectedItem() != null && PhFoto != null) {
				Staff empleado = new Staff();
				Address direccion = new Address();
				// Busca una ciudad por su id
				City ciudad = boCiudad.buscar(CBCiudad.getSelectionModel().getSelectedItem().getCityId());
				direccion.setAddress(TFDireccionA.getText());
				direccion.setAddress2(TFDdireccionB.getText());
				// direccion.setAddressId(Integer.parseInt(TFIdDireccion.getText()));
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
				empleado.setAddress(direccion);

				// Imagen del empleado
				if (imgFile != null) {
					byte[] imagen = new byte[(int) imgFile.length()];
					FileInputStream emp = new FileInputStream(imgFile);
					emp.read(imagen);
					empleado.setPicture(imagen);
				} else {
					empleado.setPicture(null);
				}

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
			} else {
				notificar("Gestionar camposEmpeleado", "Debe llenar los campos obligatorios", TipoNotificacion.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void buscarEmpleado() {
		if (TFIdEmpleado.getText() != null) {
			Staff empleado = boEmpleado.buscar((byte) Integer.parseInt(TFIdEmpleado.getText()));

			if (empleado != null) {
				if (empleado.getUsuario() != null) {
					TFIdUsuario.setText(empleado.getUsuario().getUsuario());
				} else {
					TFIdUsuario.setText(null);
				}
				TfPrimerNombre.setText(empleado.getFirstName());
				TfSegundoNombre.setText(empleado.getLastName());
				TfEmail.setText(empleado.getEmail());
				TFFechaCreacion.setText(empleado.getLastUpdate().toString());
				if (empleado.getActive() == true) {
					CheckActivo.setSelected(true);
				}
				TFDireccionA.setText(empleado.getAddress().getAddress());
				TFDdireccionB.setText(empleado.getAddress().getAddress2());
				CBCiudad.setValue(empleado.getAddress().getCity());
				TFDepartamento.setText(empleado.getAddress().getDistrict());
				TFUlltimaActualizacionDir.setText(empleado.getAddress().getLastUpdate().toString());
				TFTelefono.setText(empleado.getAddress().getPhone());
				TFCodigoPos.setText(empleado.getAddress().getPostalCode());

				if (empleado.getPicture() != null) {
					Image im = new Image(new ByteArrayInputStream(empleado.getPicture()));
					PhFoto.setImage(im);
				}

				List<StaffSchedule> listahorarioEmple = boHorario.listaHorario(empleado);

				for (int i = 0; i < listahorarioEmple.size(); i++) {

					data.add(listahorarioEmple.get(i));
					TBDia.setCellValueFactory(new PropertyValueFactory<StaffSchedule, DayEnum>("dia"));
					TBDia.setMinWidth(100);
					TBInicial.setCellValueFactory(new PropertyValueFactory<StaffSchedule, Integer>("horaInicial"));
					TBInicial.setMinWidth(100);
					tbFinal.setCellValueFactory(new PropertyValueFactory<StaffSchedule, Integer>("horaFinal"));
					tbFinal.setMinWidth(100);

					// TBOpciones.setCellValueFactory(
					// new Callback<TableColumn<StaffSchedule, Boolean>,
					// TableCell<StaffSchedule, Boolean>>() {
					// public TableCell<StaffSchedule, Boolean>
					// call(TableColumn<StaffSchedule, Boolean> p) {
					// return new ButtonCell(TbHorario);
					// }
					// });
					TbHorario.setItems(data);
				}
			} else {
				notificar("Gestionar BuscarEmpleado", "El empleado no esta registrado", TipoNotificacion.ERROR);
			}
		} else {
			notificar("Gestionar CampoBuscarEmpleado", "Debe proporcionar un ID del EMPLEADO para buscar", TipoNotificacion.ERROR);
		}
	}

	/**
	 * carga el combo ciudad con las ciudades de la BD
	 */
	private void llenarComboCiudad() {
		List<City> lista = boCiudad.listarCiudades();
		for (City ciudad : lista) {
			CBCiudad.getItems().add(ciudad);
		}
	}

	/**
	 * Carga el comboTienda con el id's de las tiendas guardadas en la BD
	 */
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
	private void llamarVentana() {
		abrirVentana("/fxml/VentanaGestionEmpleadosSub.fxml", ControladorGestionarEmpleadoSub.class);
	}

	/**
	 * Boton tabla 1
	 * 
	 * @author CAMILO
	 *
	 */
	private class ButtonCell extends TableCell<StaffSchedule, Boolean> {

		// boton a mostrar
		final Button cellButton = new Button("Editar");
		private StaffSchedule StaffSchedule;

		ButtonCell(final TableView tblView) {
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {

					abrirVentana("/VentanaGestionEmpleadosSub.fxml", ControladorGestionarEmpleadoSub.class);
					int num = getTableRow().getIndex();

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

	/**
	 * Boton tabla 2
	 * 
	 * @author CAMILO
	 *
	 */
	private class ButtonCellB extends TableCell<StaffSchedule, Boolean> {

		// boton a mostrar
		final Button cellButton = new Button("Vender");
		private StaffSchedule StaffSchedule;

		ButtonCellB(final TableView tblView) {
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {

					abrirVentana("/fxml/venderPelicula.fxml", ControladorvenderPelicula.class);
					int num = getTableRow().getIndex();

					// }
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

}
