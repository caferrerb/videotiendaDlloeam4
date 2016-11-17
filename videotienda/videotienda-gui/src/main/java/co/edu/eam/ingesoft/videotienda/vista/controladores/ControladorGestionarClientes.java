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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesis.gestorlab.gui.MainApp;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOAlquilarPeliculas;
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
import co.edu.eam.ingesoft.videotienda.vista.util.GeneradorReporte;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import co.edu.uniquindio.videotienda.dtos.DayEnum;
import co.edu.uniquindio.videotienda.dtos.PrestamoDTO;
import co.edu.uniquindio.videotienda.dtos.PrestamosClienteDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

@Controller
public class ControladorGestionarClientes extends BaseController implements Initializable {

	/**
	 * BO con los métodos de la lógica de Cliente
	 */
	@Autowired
	private BOCliente boCliente;

	/**
	 * BO con los métodos de la lógica de Ciudad
	 */
	@Autowired
	private BOCiudad boCiudad;
	
	/**
	 * BO con los métodos de la lógica de Tienda
	 */
	@Autowired
	private BOTienda boTienda;
	
	/**
	 * DataSource 
	 */
	@Autowired
	private DataSource ds;
	
	/**
	 * TextField nombres del cliente
	 */
	@FXML
	private TextField jTFNombres;
	
	/**
	 * TextField apellidos del cliente
	 */
	@FXML
	private TextField jTFApellidos;

	/**
	 * TextField Documento del cliente
	 */
	@FXML
	private TextField jTFId;
	
	/**
	 * ComboBox ciudad del cliente
	 */
	@FXML
	private ComboBox<City> jCBCiudad;
	
	/**
	 * TextField Direccion del cliente
	 */
	@FXML
	private TextField jTFDireccion;
	
	/**
	 * TextField Direccion 2 del cliente
	 */
	@FXML
	private TextField jTFDireccion2;
	
	/**
	 * TextField Distrito del cliente
	 */
	@FXML
	private TextField jTFDistrito;
	
	/**
	 * TextField Telefono del cliente
	 */
	@FXML
	private TextField jTFTelefono;
	
	/**
	 * TextField Codigo postal del cliente
	 */
	@FXML
	private TextField jTFCodPostal;
	
	/**
	 * ComboBox tienda del cliente
	 */
	@FXML
	private ComboBox<Store> jCBTienda;
	
	/**
	 * TableView de los prestamos del cliente
	 */
	@FXML
	private TableView<PrestamosClienteDTO> TVPrestamos;

	/**
	 * TableColumn contiene el titulo de la pelicula del prestamo
	 */
	@FXML
	private TableColumn<PrestamosClienteDTO, String> TCTitulo;
	
	/**
	 * TableColumn contiene la fecha del prestamo del cliente
	 */
	@FXML
	private TableColumn<PrestamosClienteDTO, Date> TCFechaPres;
	
	/**
	 * TableColumn contiene la fecha de entrega del prestamo del cliente
	 */
	@FXML
	private TableColumn<PrestamosClienteDTO, Date> TCFechaEnt;
	
	/**
	 * TableColumn contiene los botones para ir a la ventana de entregar prestamos
	 */
	@FXML
	private TableColumn<PrestamosClienteDTO, PrestamosClienteDTO> TCBoton;
	
	/**
	 * ImageView imagen del cliente
	 */
	@FXML
	private ImageView imgFoto;
	
	/**
	 * ImageView imagen del cliente
	 */
	private File imgFile;
	
	/**
	 * Cliente traido de la ventana de la ventana peliculas rentadas
	 */
	private Customer idCliente;
	
	/**
	 * Se declara la lista de los prestamos de los prestamos del cliente
	 *  para usarse en la ventana
	 */
	List<PrestamosClienteDTO> listaPrestamosCliente;

	/**
	 * ObservableList para llenar la tabla de los prestamos del cliente
	 */
	ObservableList<PrestamosClienteDTO> prestamosListarCliente;
	
	
	/**
	 * Constructor de la ventana
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		llenarComboCiudades();
		llenarComboTiendas();
		inicializarTabla();
		idCliente = (Customer) obtenerValor("cliente");
		if (idCliente != null) {
			buscarClienteTraido(idCliente);
			
		}
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
	
	/**
	 * LLena el combo de las tiendas
	 */
	@FXML
	private void llenarComboTiendas() {
		List<Store> lista = boTienda.listarTiendas();
		for (Store tienda : lista) {
			jCBTienda.getItems().add(tienda);
		}
	}
	
	/**
	 * Metodo para abrir la imagen que desea cargar el cliente
	 */
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
				notificar("Crear Actor", "La imagen supero el tamaï¿½o maximo de 100k", TipoNotificacion.ERROR);
			}

		}
	}
	
	/**
	 * Metodo para crear el cliente
	 */
	public void crearCliente()  {

		try {
			if (  jTFApellidos.getText().isEmpty() || jTFNombres.getText().isEmpty() || jTFCodPostal.getText().isEmpty()
				|| jTFDireccion.getText().isEmpty() || jTFDireccion2.getText().isEmpty() || jTFDistrito.getText().isEmpty()
				|| jTFId.getText().isEmpty() || jTFTelefono.getText().isEmpty() || jCBCiudad.getValue()==null || jCBTienda.getValue()==null
					) {

				notificar("Crear Cliente", "Verifique que todos los campos esten diligenciados",
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

				Customer cus = boCliente.buscar(Integer.parseInt(jTFId.getText()));
				if(cus == null){
					//boDir.crearDireccion(direccion);
					boCliente.crear(cliente);				
					notificar("Crear Cliente", "El Cliente se ha creado exitosamente", TipoNotificacion.INFO);
					limpiarCampos();
				} else{
					notificar("Crear Cliente", "el cliente con documento "+"'"+cus.getCustomerId()+"'"+" ya se encuentra registrado", TipoNotificacion.ERROR);
				}

			}

		} catch (ExcepcionNegocio e) {
			notificar("Crear Cliente", "Este Cliente ya se encuentra registrado", TipoNotificacion.ERROR);
		}  catch (IOException e) {
			notificar("Crear Cliente", "Error al crear el cliente", TipoNotificacion.ERROR);
			//e.printStackTrace();
		}

	}
	
	/**
	 * metodo para listar y cargar los prestamos del cliente
	 */
	@FXML
	public void listarPrestamosClientes() {
		int idCliente = Integer.parseInt(jTFId.getText());
		listaPrestamosCliente = boCliente.listarPrestamosDelCliente(idCliente);
		prestamosListarCliente = FXCollections.observableArrayList();
		 for (PrestamosClienteDTO dto : listaPrestamosCliente) {
			 prestamosListarCliente.add(dto);
		} 
		
		 TVPrestamos.setItems(prestamosListarCliente);

	}
	
	/**
	 * Metodo para inicializar las columnas de las tablas y agregar los botones
	 */
	public void inicializarTabla() {
		TCTitulo.setCellValueFactory(new PropertyValueFactory<PrestamosClienteDTO, String>("titulo"));
		TCFechaPres.setCellValueFactory(new PropertyValueFactory<PrestamosClienteDTO, Date>("fechaPrestamo"));
		TCFechaEnt.setCellValueFactory(new PropertyValueFactory<PrestamosClienteDTO, Date>("fechaEntrega"));
		TCBoton.setSortable(false);
		
		TCBoton.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		TCBoton.setCellFactory(param -> new TableCell<PrestamosClienteDTO, PrestamosClienteDTO>() {
			private final Button EntregarButton = new Button("Entregar");

			@Override
			protected void updateItem(PrestamosClienteDTO prestamo, boolean empty) {
				super.updateItem(prestamo, empty);

				if (prestamo == null) {
					setGraphic(null);
					return;
				}

				setGraphic(EntregarButton);
				EntregarButton.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent t) {
						int num = getTableRow().getIndex();
						//Abrimos la ventana de prestamos
						guardarEnSesion("clienteId", boCliente.buscar(Integer.parseInt(jTFId.getText())));
						abrirVentana("/fxml/AlquilarPelicula.fxml", ControladorAlquilarPelicula.class);

					}
				});
			}
		});

	}
	
	/**
	 * metodo buscar un cliente registrado
	 */
	@FXML
	public void buscarCliente() {
		if (!jTFId.getText().isEmpty()) {
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
				listarPrestamosClientes();
				
				if (cliente.getPicture() != null) {
					Image im = new Image(new ByteArrayInputStream(cliente.getPicture()));
					imgFoto.setImage(im);
				}

				
				
			} else {
				notificar("Buscar Cliente", "El cliente no se encuentra registrado", TipoNotificacion.ERROR);
				limpiarCampos();
			}
		} else {
			notificar("Buscar Cliente", "Debe diligenciar el campo: Numero de Documento*", TipoNotificacion.ERROR);
		}
	}
	
	/**
	 * metodo para buscar el cliente que se trae de la ventana de peliculas rentadas
	 */
	@FXML
	public void buscarClienteTraido(Customer cliTraido) {
		Customer cliente = boCliente.buscar(cliTraido.getCustomerId());
			if (cliente != null) {
				jTFId.setText(String.valueOf(cliTraido.getCustomerId()));
				jTFNombres.setText(cliente.getFirstName());
				jTFApellidos.setText(cliente.getLastName());
				jCBTienda.setValue(cliente.getStore());
				jTFDireccion.setText(cliente.getAddress().getAddress());
				jTFDireccion2.setText(cliente.getAddress().getAddress2());
				jTFDistrito.setText(cliente.getAddress().getDistrict());
				jTFTelefono.setText(cliente.getAddress().getPhone());
				jTFCodPostal.setText(cliente.getAddress().getPostalCode());
				jCBCiudad.setValue(cliente.getAddress().getCity());
				listarPrestamosClientes();
				
				if (cliente.getPicture() != null) {
					Image im = new Image(new ByteArrayInputStream(cliente.getPicture()));
					imgFoto.setImage(im);
				}
				
			} else {
				notificar("Buscar Cliente", "El cliente no se encuentra registrado", TipoNotificacion.ERROR);
				limpiarCampos();
			}
	}
	
	/**
	 * metodo para editar un cliente registrado
	 */
	public void editarCliente()  {

		try {
			if (  jTFApellidos.getText().isEmpty() || jTFNombres.getText().isEmpty() || jTFCodPostal.getText().isEmpty()
				|| jTFDireccion.getText().isEmpty() || jTFDireccion2.getText().isEmpty() || jTFDistrito.getText().isEmpty()
				|| jTFId.getText().isEmpty() || jTFTelefono.getText().isEmpty() || jCBCiudad.getValue()==null || jCBTienda.getValue()==null
					) {

				notificar("Editar Cliente", "Debe Buscar un Cliente y verificar que todos los campos esten diligenciados",
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
	
	/**
	 * metodo para generar el reporte de un cliente por su documento de identidad
	 */
	@FXML
	public void generarReporteCliente(){
		try {
			if(jTFId.getText().isEmpty()){
				notificar("Generar Reporte", "Debe diligenciar el campo: Numero de Documento* para el reporte del cliente", TipoNotificacion.ERROR);
			}else{
				Customer cliente = boCliente.buscar(Integer.parseInt(jTFId.getText()));

				if (cliente != null) {
					
					GeneradorReporte reporter=new GeneradorReporte(ds.getConnection());
					Map<String, Object> params=new HashMap<>();
					int idCliente = Integer.parseInt(jTFId.getText());
					params.put("idcus", idCliente);
					reporter.generarReporte(params, "/reportes/rentascliente.jrxml", "RentasDelCliente");
					
				} else {
					
					notificar("Generar Reporte", "El cliente no se encuentra registrado", TipoNotificacion.ERROR);
					limpiarCampos();
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			notificar("Generar Reporte", "Error generando el reporte!", TipoNotificacion.ERROR);
		}
	}
	
	/**
	 * metodo para limpiar los campos de texto de la ventana del cliente
	 */
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
