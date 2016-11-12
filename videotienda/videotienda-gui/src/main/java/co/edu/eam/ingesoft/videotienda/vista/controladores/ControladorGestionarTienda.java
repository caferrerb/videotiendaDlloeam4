package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.persistence.PersistenceException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOCiudad;
import co.edu.eam.ingesoft.videotienda.logica.bos.BODireccion;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOInventario;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Address;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Category;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Country;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.FilmCategory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.GeneradorReporte;
import co.edu.eam.ingesoft.videotienda.vista.util.MensajesGui;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Clase responsable de controlar la ventana de gestionar tienda
 * 
 * @author Jhoan Sebastian Salazar Henao<br/>
 *         email: jsebastian48@hotmail.com <br/>
 *         Fecha: 27/10/2016<br/>
 */
@Controller
public class ControladorGestionarTienda extends BaseController implements Initializable {

	@Autowired
	private BOTienda boTienda;

	@Autowired
	private BOEmpleado boEmpleado;

	@Autowired
	private BOCiudad boCiudad;

	@Autowired
	private BOInventario boInventario;

	@Autowired
	private BODireccion boDireccion;

	@Autowired
	private DataSource ds;
	
	@FXML
	private ComboBox<Store> cbTiendas;
	
	@FXML 
	private TextField TFNombreTienda;

	@FXML
	private TextField tfDireccion;

	@FXML
	private TextField tfDireccion2;

	@FXML
	private ComboBox<Staff> cbEmpleado;

	@FXML
	private TextField tfCodigoPostal;

	@FXML
	private TextField tfLocalidad;

	@FXML
	private ComboBox<City> cbCiudad;

	@FXML
	private TextField tfTelefono;

	@FXML
	private TableView<Staff> tbEmpleado;

	@FXML
	private TableColumn<Staff, Byte> cCodigo;

	@FXML
	private TableColumn<Staff, String> cNombre;

	@FXML
	private TableColumn<Staff, String> cApellido;

	/**
	 * Horario no configurado todavia
	 */
	@FXML
	private TableColumn<StaffSchedule, Date> cHorario;

	@FXML
	private TableColumn<Staff, String> cButtonEmpleados;

	@FXML
	private TableView<Inventory> tbInventario;

	@FXML
	private TableColumn<Inventory, Film> cTitulo;

	@FXML
	private TableColumn<FilmCategory, Category> cGenero;

	@FXML
	private final ObservableList<Staff> data = FXCollections.observableArrayList();

	@FXML
	private final ObservableList<Inventory> dataI = FXCollections.observableArrayList();

	/**
	 * Metodo para cargar la tabla empleados
	 * 
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 28/10/2016<br/>
	 */
	@FXML
	public void cargarTablaEmpleados() {
		
		try {
			if (cbTiendas.getSelectionModel().getSelectedIndex() == 0) {
				mostrarMensaje(AlertType.INFORMATION, null, null, "Por favor seleccione una tienda");
			} else {
				Store store = cbTiendas.getSelectionModel().getSelectedItem();

				List<Staff> lista = boEmpleado.listarEmpleadosTienda(store);
				for (Staff staff : lista) {
					data.add(staff);

				}
				tbEmpleado.setItems(data);


			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		cCodigo.setCellValueFactory(new PropertyValueFactory<Staff, Byte>("staffId"));
		cCodigo.setMinWidth(100);
		cNombre.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));
		cNombre.setMinWidth(100);
		cApellido.setCellValueFactory(new PropertyValueFactory<Staff, String>("lastName"));
		cApellido.setMinWidth(100);	
		cHorario.setCellValueFactory(new PropertyValueFactory<StaffSchedule, Date>("horaInicial"));
		cHorario.setMinWidth(100);
//		
//			@Override
//			public ObservableValue<Date> call(CellDataFeatures<StaffSchedule, Date> data){
//				return new SimpleObjectProperty<>(data.getValue().getHoraFinal());
//			}
//		});
		
		Callback<TableColumn<Staff, String>, TableCell<Staff, String>> cellFactory = new Callback<TableColumn<Staff,String>, TableCell<Staff,String>>() {
			
			@Override
			public TableCell<Staff, String> call(TableColumn<Staff, String> param){
				
				TableCell<Staff, String> cell = new TableCell<Staff, String>(){
					final Button btn = new Button("Ver");
					
					@Override
					protected void updateItem(String item, boolean empty){
						super.updateItem(item, empty);
						if(empty){
							setGraphic(null);
							setText(null);
						}else{
							btn.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event){
									
									Staff s = getTableView().getItems().get(getIndex());
									guardarEnSesion("Empleado", s);
									abrirVentana("/fxml/VentanaGestionEmpleados.fxml", ControladorGestionarEmpleado.class);
								}
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};	
		cButtonEmpleados.setCellFactory(cellFactory);
		
		
		
		
		}		
	

//	@FXML
//	private void llenarTabla(){
//		
//		try {
//			if (cbTiendas.getSelectionModel().getSelectedIndex() == 0) {
//				mostrarMensaje(AlertType.INFORMATION, null, null, "Por favor seleccione una tienda");
//			} else {
//				Store store = cbTiendas.getSelectionModel().getSelectedItem();
//
//				List<Staff> lista = boEmpleado.listarEmpleadosTienda(store);
//				for (Staff staff : lista) {
//					data.add(staff);
//
//				}
//				tbEmpleado.setItems(data);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	

	/**
	 * Metodo para cargar la tabla inventario
	 * 
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 2/11/2016<br/>
	 */
	public void cargarTablaInventario() {
		try {
			if (cbTiendas.getSelectionModel().getSelectedIndex() == 0) {
				mostrarMensaje(AlertType.INFORMATION, null, null, "Por favor seleccione una tienda");
			} else {
				Store store = cbTiendas.getSelectionModel().getSelectedItem();

				List<Inventory> lista = boInventario.listarInventarioTienda(store);
				for (Inventory inventory : lista) {
					dataI.add(inventory);

					cTitulo.setCellValueFactory(new PropertyValueFactory<Inventory, Film>("title"));
					cTitulo.setMinWidth(100);
					cGenero.setCellValueFactory(new PropertyValueFactory<FilmCategory, Category>("name"));
					cGenero.setMinWidth(100);
					tbInventario.setItems(dataI);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		llenarComboCiudad();
		llenarComboEmpleado();
		llenarComboTienda();
		// cargarTablaEmpleados();

	}

	private void llenarComboCiudad() {

		List<City> lista = boCiudad.listarCiudades();
		for (City city : lista) {
			cbCiudad.getItems().add(city);
		}
	}

	private void llenarComboEmpleado() {
		List<Staff> lista = boEmpleado.listarEmpleados();
		for (Staff staff : lista) {
			cbEmpleado.getItems().add(staff);
		}
	}

	private void llenarComboTienda() {
		cbTiendas.getItems().setAll(new ArrayList<Store>());
		cbTiendas.getItems().add(null);
		List<Store> lista = boTienda.listarTiendas();
		for (Store store : lista) {
			cbTiendas.getItems().add(store);
		}
	}

	private void empleadosTienda() {

	}

	/**
	 * Metodo que crea una tienda
	 * 
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 2/11/2016<br/>
	 */
	@FXML

	public void crear() {

		Address address = new Address();
		Store store = new Store();

		store.setNombreTienda(TFNombreTienda.getText());
		store.setAddress(address);
		address.setAddress(tfDireccion.getText());
		address.setAddress2(tfDireccion2.getText());
		store.setStaff((Staff) cbEmpleado.getValue());
		address.setPostalCode(tfCodigoPostal.getText());
		address.setDistrict(tfLocalidad.getText());
		address.setCity((City) cbCiudad.getValue());
		address.setPhone(tfTelefono.getText());

		boDireccion.crear(address);
		boTienda.crear(store);
		notificar("Crear Tienda", "La tienda se ha creado exitosamente", TipoNotificacion.INFO);
		limpiar();
		llenarComboTienda();
	}

//	public void crear(){
//				Address address = new Address();
//				Store store = new Store();
//				address.setAddress(tfDireccion.getText());
//				address.setAddress2(tfDireccion2.getText());
//				store.setStaff((Staff) cbEmpleado.getValue());
//				address.setPostalCode(tfCodigoPostal.getText());
//				address.setDistrict(tfLocalidad.getText());
//				address.setCity((City) cbCiudad.getValue());
//				address.setPhone(tfTelefono.getText());	
//				boDireccion.crear(address);
//				boTienda.crear(store);
//				notificar("Crear Tienda","La tienda se ha creado exitosamente", TipoNotificacion.INFO);
//				
//
//	}

	@FXML
	public void editar() {
		
		
		Store sto = boTienda.buscar(cbTiendas.getSelectionModel().getSelectedIndex());
		Address ad = boDireccion.buscar(sto.getAddress().getAddressId());
		sto.setNombreTienda(TFNombreTienda.getText());
		ad.setAddress(tfDireccion.getText());
		ad.setAddress2(tfDireccion2.getText());
		ad.setDistrict(tfLocalidad.getText());
		ad.setPhone(tfTelefono.getText());
		ad.setPostalCode(tfCodigoPostal.getText());
		ad.setCity(cbCiudad.getValue());
		sto.setStaff(cbEmpleado.getValue());
		boDireccion.editar(ad);
		notificar("Editar tienda", "La tienda se edito correctamente", TipoNotificacion.INFO);
		limpiar();
		llenarComboTienda();
	}
	
	@FXML
	public void limpiarCampos(){
		if(cbTiendas.getSelectionModel().getSelectedIndex() == 0){
			limpiar();
		}
	}

	@FXML
	public void buscar() {
//		llenarTabla();
		cargarTablaEmpleados();
		cargarTablaInventario();
		try{
			SingleSelectionModel<Store> s = cbTiendas.getSelectionModel();
			Store tienda = s.getSelectedItem();
			if(s !=null){
				TFNombreTienda.setText(tienda.getNombreTienda());
				tfDireccion.setText(tienda.getAddress().getAddress());
				tfDireccion2.setText(tienda.getAddress().getAddress2());
				tfCodigoPostal.setText(tienda.getAddress().getPostalCode());
				tfLocalidad.setText(tienda.getAddress().getDistrict());
				tfTelefono.setText(tienda.getAddress().getPhone());
				cbCiudad.setValue(tienda.getAddress().getCity());
				cbEmpleado.setValue(tienda.getStaff());
		}else{
			mostrarMensaje(AlertType.INFORMATION, MensajesGui.getMensaje("No existe"),
					MensajesGui.getMensaje("No existe"), 
					MensajesGui.getMensaje("No existe"));
		}
					
			}catch(PersistenceException e1){
				mostrarMensaje(AlertType.INFORMATION, MensajesGui.getMensaje("No existe"), 
						MensajesGui.getMensaje("No existe"), 
						MensajesGui.getMensaje("No existe"));
				e1.printStackTrace();
			}
		//}
	}
	
	@FXML
	public void limpiar(){
		TFNombreTienda.setText(null);
		tfDireccion.setText(null);
		tfDireccion2.setText(null);
		tfCodigoPostal.setText(null);
		tfLocalidad.setText(null);
		tfTelefono.setText(null);
		cbCiudad.setValue(null);
		cbEmpleado.setValue(null);
		
	}
	
	@FXML
	public void generarReporte(){
		
		try {
			if(cbTiendas.getSelectionModel() !=null){
				GeneradorReporte reporter=new GeneradorReporte(ds.getConnection());
				Map<String, Object> params=new HashMap<>();
//				params.put("idTienda", 1);
				params.put("idTienda", cbTiendas.getSelectionModel().getSelectedIndex());
				reporter.generarReporte(params, "/reportes/EmpleadosTienda.jrxml", "EmpleadosxTienda");
			}else{
				notificar("Error nombre de tienda", "Por favor seleccione una tienda", TipoNotificacion.ERROR);
			}	
		} catch (Exception e) {
			e.printStackTrace();
			notificar("Ejemplo", "Error generando el reporte", TipoNotificacion.ERROR);
		}
	}
	
	

}
