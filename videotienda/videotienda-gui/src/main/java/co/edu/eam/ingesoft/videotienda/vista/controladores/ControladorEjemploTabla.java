package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

@Controller
public class ControladorEjemploTabla extends BaseController implements Initializable {

	@Autowired
	private BOFilm boFilm;
	
	@Autowired
	private DataSource ds;

	@FXML
	private TableColumn<Film, String> colNom;

	@FXML
	private TableColumn<Film, Integer> colLong;

	@FXML
	private TableColumn<Film, String> colLeng;
	
	@FXML
	private TableColumn<Film,String> colBot;

	@FXML
	private TableView<Film> tablaPeliculas;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		configurarTAbla();
		llenarTabla();

	}

	/**
	 * 
	 * Método para llenar la tabla <br>
	 * 
	 * 
	 * @author Camilo Andres Ferrer Bustos<br>
	 * 
	 * @date 6/11/2016
	 * @version 1.0
	 *
	 */
	private void llenarTabla() {

		List<Film> lista = boFilm.listarTodos();
		ObservableList<Film> listaTabla = FXCollections.observableArrayList();
		for (Film film : lista) {
			listaTabla.add(film);
		}
		tablaPeliculas.setItems(listaTabla);
	}

	/**
	 * 
	 * Método que configura la tabla <br>
	 * 
	 * 
	 * @author Camilo Andres Ferrer Bustos<br>
	 * 
	 * @date 6/11/2016
	 * @version 1.0
	 *
	 */
	private void configurarTAbla() {
		colNom.setCellValueFactory(new PropertyValueFactory<>("title"));
		colLeng.setCellValueFactory(new Callback<CellDataFeatures<Film, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Film, String> data) {
				return new SimpleObjectProperty<>(data.getValue().getLanguage1().getName());
			}
		});
		colLong.setCellValueFactory(new PropertyValueFactory<>("length"));

		Callback<TableColumn<Film, String>, TableCell<Film, String>> cellFactory = new Callback<TableColumn<Film, String>, TableCell<Film, String>>() {

			@Override
			public TableCell<Film, String> call(TableColumn<Film, String> param) {
				// TODO Auto-generated method stub
				TableCell<Film, String> cell = new TableCell<Film, String>() {
					final Button btn = new Button("Ver");

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
		colBot.setCellFactory(cellFactory);
	}
	@FXML
	public void generarReporte(){
		
		try {
			GeneradorReporte reporter=new GeneradorReporte(ds.getConnection());
			Map<String, Object> params=new HashMap<>();
			params.put("idcat", 1);
			reporter.generarReporte(params, "/reportes/peliculascat.jrxml", "PeliculasXCategoria");
		} catch (Exception e) {
			notificar("Ejemplo", "Error generando el reporte", TipoNotificacion.ERROR);
		}
	}

}
