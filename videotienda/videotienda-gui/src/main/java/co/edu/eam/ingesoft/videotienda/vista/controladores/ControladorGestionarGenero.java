/**
 * @author Carlos Alfredo Martinez Villada
 * Controlador encargado de todas las funciones de la ventana gestionar genero.
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOGenero;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Category;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

@Controller
public class ControladorGestionarGenero extends BaseController implements Initializable{
	@Autowired
	private BOGenero bo;
	/**
	 * Componentes de la Ventana gestionar genero
	 */
	@FXML
	private TextField tFnombreGenero;
	
	@FXML
	private TextField tFnuevoNombreGenero;
	
	@FXML
	private GridPane nuevoNombre;
	
	/* Combo con todas los Generos */
	@FXML
	private ComboBox<Category> CbGeneros;
	/* Tabla Generos */
	@FXML
	private TableView<Category> TbGeneros;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nuevoNombre.setVisible(false);
		cargarComboGeneros();
	}
	
	/**
	 * Registra la informacion de un genero
	 */
	@FXML
	public void crearGenero(){
		String name = tFnombreGenero.getText();
		if(name.isEmpty()){
			notificar("Falta Informacion", "Por favor ingrese el nombre del genero",  TipoNotificacion.ERROR);
		}else{
			try{
				Category categoria = new Category();
				Date date = new Date();
				long time = date.getTime();
				Timestamp lastUpdate = new Timestamp(time);
				categoria.setLastUpdate(lastUpdate);
				categoria.setName(name);
				bo.crear(categoria);
				notificar("Genero creado exitosamente", "Se ha creado el genero "+name+" correctamente",  TipoNotificacion.INFO);
				limpiarCampos();
				cargarComboGeneros();
			}catch(ExcepcionNegocio ex){
				notificar("Administrador Genero", ex.getMessage(),  TipoNotificacion.ERROR);
				limpiarCampos();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Buscar un Genero
	 */
	@FXML
	public void buscarGenero(){
		String name = tFnombreGenero.getText();
		if(name.isEmpty()){
			notificar("Falta Informacion", "Por favor ingrese el nombre del genero a buscar",  TipoNotificacion.ERROR);
		}else{
			try{
				Category genero = bo.buscarPorNombre(name);
				if(genero != null){
					/* Aqui debo refrescar todas las tablas y el Combobox */
				}else{
					notificar("Sin Resultados", "No se ha encontrado ningun Genero\ncon el nombre "+name,  TipoNotificacion.INFO);
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Editar Informacion de un Genero
	 */
	@FXML
	public void editarGenero(){
		String name = tFnombreGenero.getText();
		if(name.isEmpty()){
			notificar("Busque el Genero", "Por favor busque el Genero a Editar",  TipoNotificacion.ERROR);
		}else{
			nuevoNombre.setVisible(true);
			String nuevoNombre = tFnuevoNombreGenero.getText();
			if(nuevoNombre.isEmpty()){
				notificar("Falta Informacion", "Por favor, ingrese el nuevo nombre de la categoria",  TipoNotificacion.INFO);
			}else{
				try{
					Category genero = bo.buscarPorNombre(name);
					if(genero != null){
						genero.setName(nuevoNombre);
						bo.editar(genero);
						notificar("Genero editado exitosamente", "Se ha editadoo el genero "+name+" correctamente\nPaso a llamarse "+nuevoNombre,  TipoNotificacion.INFO);
						limpiarCampos();
						cargarComboGeneros();
					}else{
						notificar("Administrador Generos", "Usted puede creer... que no he encontrado\nninguna categoria con el nombre "+name, TipoNotificacion.INFO);
					}
				}catch(ExcepcionNegocio ex){
					notificar("Administrador Genero", ex.getMessage(),  TipoNotificacion.ERROR);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * Limpiamos los datos del genero
	 */
	public void limpiarCampos(){
		tFnombreGenero.setText(null);
		tFnuevoNombreGenero.setText(null);
	}
	/**
	 * Carga todos los generos con su informacion en una tabla
	 */
	public void cargarTablaGeneros(){
//		try{
//			List<Category> lista = boReportes.listarTablaRental(s);
//			for (Category genero : lista) {
//				TbGeneros.add(genero);
//				ColumnaAlquilerIdInventario.setCellValueFactory(new PropertyValueFactory<Inventory, Integer>("ID INVENTARIO"));
//				ColumnaAlquilerTitulo.setCellValueFactory(new PropertyValueFactory<Film, String>("TITULO"));
//				ColumnaAlquilerCliente.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("CLIENTE"));
//				TBAlquiler.setItems(listaRenta);
//			}		
//		}catch(Exception e){
//				e.printStackTrace();
//		}
	}
	/**
	 * Carga todos los generos en un Combobox
	 */
	public void cargarComboGeneros(){
		List<Category> lista = bo.listarGeneros();
		if(lista.size() > 0){
			ObservableList<Category> list = FXCollections.observableArrayList(lista);
			CbGeneros.setItems(list);
			SingleSelectionModel<Category> selec = CbGeneros.getSelectionModel();
			selec.select(0);
		}else{
			CbGeneros.setPromptText("No hay Generos");
		}
	}
}
