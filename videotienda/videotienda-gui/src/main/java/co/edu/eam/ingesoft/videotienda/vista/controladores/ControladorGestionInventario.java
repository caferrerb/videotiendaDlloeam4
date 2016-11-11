/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.vista.controladores;


import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOInventario;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOTienda;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Sale;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.GeneradorReporte;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Callback;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;



/**
 * @author XgioserX
 *
 */
@Controller
public class ControladorGestionInventario extends BaseController implements Initializable{

	@Autowired
	private BOInventario boInventario;
	@Autowired
	private BOTienda boTienda;
	@Autowired
	private BOFilm boFilm;
	@FXML
	private TextField JTFTituloDarAlta;
	@FXML
	private TextField JTFIdPeliculaDarAlta;
	@FXML
	private TextArea JTARazonDarAlta;
	@FXML
	private TextField JTFGenero;
	@FXML
	private TextField JTFTitulo;
	@FXML
	private TextField JTFIdPelicula;
	@FXML
	private ComboBox<Store> JCBTienda;
	
	@FXML
	private TableView<Film> jPTienda;
	
	@FXML
	private TableColumn<Film, String> colTitulo;
	
	@FXML
	private TableColumn<Film, Integer> colInventario;
	
	@FXML
	private TableColumn<Film, Button> colDarAlta;
	
	
	@FXML
	private Pane JPDarDeBaja;

	@FXML
	private final ObservableList<Film> data = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		llenarComboTiendas();
		
	}

	/**
	 * llena el combo con las tiendas
	 */

	private void llenarComboTiendas() {
		List<Store> lista = boTienda.listarTiendas();
		for (Store store : lista) {
			JCBTienda.getItems().add(store);
		}
	}
	
	
	/**
	 * evento de accion del combo
	 */
	@FXML
	public void eventoAccionCombo() {
		JCBTienda.setOnAction((event) -> {
			Store i = JCBTienda.getSelectionModel().getSelectedItem();
			llenarTablaPeliculas(i);

		});
	}
	
	/**
	 * llena la tabla venta
	 * @param s la tienda a la que se le van a obtener los datos
	 */
	@FXML
	public void llenarTablaPeliculas(Store s) {
		List<Film> lista = boInventario.listarTablaPeliculas(s);
		for (int i = 0; i < lista.size(); i++) {
			data.add(lista.get(i));
		}
		jPTienda.setItems(data);
	}
	@FXML
	private void mostrarPanelDarBaja() {
		JPDarDeBaja.setVisible(true);
		
	}
	@FXML
	private void confirmarDarAlta() {
	
	
	}
	@FXML
	private void cancelarDarAlta() {
		JPDarDeBaja.setVisible(false);
		
	
	}
	
	
	
	@FXML
	public void buscarPelicula()throws Exception{
		if(JTFIdPelicula.getText().isEmpty()){
			notificar("Ingrese", "Ingrese el Id de la pelicula que desea buscar", TipoNotificacion.ERROR);
		}else{
			int idPelicula=Integer.parseInt(JTFIdPelicula.getText());
			Film pelicula=boFilm.buscar(JTFIdPelicula);
			if(pelicula!=null){
				JTFTitulo.setText(pelicula.getTitle());
				JTFGenero.setText(pelicula.getCategory().getName());
				
			}else{
				notificar("¡ERROR!", "La pelicula con el ID= ''"+JTFIdPelicula+"'' (NO) se encuentra registrada",  TipoNotificacion.ERROR);

			}

		}
	}
	
	

	//private int cuentaCantidad(){
		
		
	//}
	
	private void configurarTabla() {
		colTitulo.setCellValueFactory(new PropertyValueFactory<>("title"));
		colInventario.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

		Callback<TableColumn<Film, String>, TableCell<Film, String>> cellFactory = new Callback<TableColumn<Film, String>, TableCell<Film, String>>() {

			@Override
			public TableCell<Film, String> call(TableColumn<Film, String> param) {
				// TODO Auto-generated method stub
				TableCell<Film, String> cell = new TableCell<Film, String>() {
					final Button btn = new Button("Dar De Alta");

					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						}else{
							btn.setOnAction(new EventHandler<ActionEvent>() {
								
								@Override
								public void handle(ActionEvent event) {
									
									Film f=getTableView().getItems().get(getIndex());
									guardarEnSesion("pelicula", f);
									abrirVentana("/fxml/verpeli.fxml", ControladorVerPeliEjemplo.class);
									
								}
							});
							setGraphic( btn );
                            setText( null );
						}
						
					}
				};
				return cell;
			}
		};
		
	}
	

	
}
