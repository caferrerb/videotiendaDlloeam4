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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
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
	
	/* Tabla Generos */
	@FXML
	private TableView<Category> TbGeneros;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
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
		
	}
	
	/**
	 * Editar Informacion de un Genero
	 */
	@FXML
	public void editarGenero(){
		
	}
	/**
	 * Limpiamos los datos del genero
	 */
	public void limpiarCampos(){
		tFnombreGenero.setText(null);
	}
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
}
