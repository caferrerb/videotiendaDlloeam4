package co.edu.eam.ingesoft.videotienda.vista.controladores;


import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

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
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Clase responsable de controlar la ventana de gestionar tienda
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
	
	@FXML
	private ComboBox<Store> cbTiendas;
	
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
	private TableColumn<Staff, Button> cButtonEmpleados;
	
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
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 28/10/2016<br/>
	 */
	@FXML
	public void cargarTablaEmpleados(){
		try{
			if(cbTiendas.getSelectionModel().getSelectedIndex()==0){
				mostrarMensaje(AlertType.INFORMATION,null, null, "Por favor seleccione una tienda");
			}else{
				Store store = cbTiendas.getSelectionModel().getSelectedItem();
				
				List<Staff> lista = boEmpleado.listarEmpleadosTienda(store);
				for (Staff staff : lista){
					data.add(staff);
					
				cCodigo.setCellValueFactory(new PropertyValueFactory<Staff, Byte>("staffId"));
				cCodigo.setMinWidth(100);
				cNombre.setCellValueFactory(new PropertyValueFactory<Staff, String>("firstName"));
				cNombre.setMinWidth(100);
				cApellido.setCellValueFactory(new PropertyValueFactory<Staff, String>("lastName"));
				cApellido.setMinWidth(100);
				cHorario.setCellValueFactory(new PropertyValueFactory<StaffSchedule, Date>("horaInicial"));
				cHorario.setMinWidth(100);
				cButtonEmpleados.setCellValueFactory(new PropertyValueFactory<Staff, Button>(""));
				tbEmpleado.setItems(data);

				
			}
		}		
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	
	/**
	 * Metodo para cargar la tabla inventario
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 2/11/2016<br/>
	 */
	public void cargarTablaInventario(){
		try{
			if(cbTiendas.getSelectionModel().getSelectedIndex()==0){
				mostrarMensaje(AlertType.INFORMATION,null, null, "Por favor seleccione una tienda");
			}else{
				Store store = cbTiendas.getSelectionModel().getSelectedItem();
				
				List<Inventory> lista = boInventario.listarInventarioTienda(store);
				for (Inventory inventory : lista){
					dataI.add(inventory);
					
				cTitulo.setCellValueFactory(new PropertyValueFactory<Inventory, Film>("title"));
				cTitulo.setMinWidth(100);
				cGenero.setCellValueFactory(new PropertyValueFactory<FilmCategory, Category>("name"));
				cGenero.setMinWidth(100);
				tbInventario.setItems(dataI);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		llenarComboCiudad();
		llenarComboEmpleado();
		llenarComboTienda();
		//cargarTablaEmpleados();
		
	}
	
	private void llenarComboCiudad(){
		
		List<City> lista = boCiudad.listarCiudades();
		for (City city : lista){
			cbCiudad.getItems().add(city);
		}
	}
	
	private void llenarComboEmpleado(){
		List<Staff> lista = boEmpleado.listarEmpleados();
		for (Staff staff : lista){
			cbEmpleado.getItems().add(staff);
		}
	}
	
	private void llenarComboTienda(){
		cbTiendas.getItems().add(null);
		List<Store> lista = boTienda.listarTiendas();
		for (Store store : lista){
			cbTiendas.getItems().add(store);
		}
	}
	
	private void empleadosTienda(){
		
	}
	
	/**
	 * Metodo que crea una tienda
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 2/11/2016<br/>
	 */
	@FXML
	public void crear(){
				Address address = new Address();
				Store store = new Store();
				address.setAddress(tfDireccion.getText());
				address.setAddress2(tfDireccion2.getText());
				store.setStaff((Staff) cbEmpleado.getValue());
				address.setPostalCode(tfCodigoPostal.getText());
				address.setDistrict(tfLocalidad.getText());
				address.setCity((City) cbCiudad.getValue());
				address.setPhone(tfTelefono.getText());	
				boDireccion.crear(address);
				boTienda.crear(store);
				notificar("Crear Tienda","La tienda se ha creado exitosamente", TipoNotificacion.INFO);
				
	}
	
	public void editar(){
		
	}
	
	@FXML
	public void buscar(){
		cargarTablaEmpleados();
		cargarTablaInventario();
	}
	
	
	
	
	
	
}
