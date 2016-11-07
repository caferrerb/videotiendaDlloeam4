package co.edu.eam.ingesoft.videotienda.vista.controladores;




import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOFilm;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Category;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

@Controller
public class ControladorGestionarVenta extends BaseController implements Initializable {

	@Autowired
	private BOFilm boPelicula;	
	@FXML
	private TextField jtfTitulo;	
	@FXML
	private TableView<Film> jttablacontenidoPelicula;	
	@FXML
	private TableColumn<Film, String> jcolumnaGenero;	
	@FXML
	private TableColumn<Film, String> jcolumnaTitulo;	
	@FXML
	private TableColumn<Film, Integer> jcolumnaLong;	
	@FXML
	private TableColumn<Film, Double> jcolumnaPrecio;	
	@FXML
	private TableColumn jcolumnaboton;	
	@FXML
	private ObservableList<Film> data = FXCollections.observableArrayList();
	
	
	@FXML
	public void buscarPelicula(){
		System.out.println("buscando...");
		try {
			data.clear();
			String nomPelicula = jtfTitulo.getText();
			List<Film> pelicula = boPelicula.listarPeliculas(nomPelicula);
			if(pelicula.isEmpty()){
				notificar("¡ERROR!", "No se encuentran peliculas registradas con este nombre",  TipoNotificacion.ERROR);
			}else{
			for ( int i = 0; i < pelicula.size(); i++) {
				
					final Film f=pelicula.get(i);
					data.add(pelicula.get(i));
					jcolumnaGenero.setCellValueFactory(new PropertyValueFactory<Film, String>(""));
					jcolumnaGenero.setMinWidth(100);
					jcolumnaTitulo.setCellValueFactory(new PropertyValueFactory<Film, String>("title"));
					jcolumnaTitulo.setMinWidth(100);
					jcolumnaLong.setCellValueFactory(new PropertyValueFactory<Film, Integer>("length"));
					jcolumnaLong.setMinWidth(100);
					jcolumnaPrecio.setCellValueFactory(new PropertyValueFactory<Film, Double>("rentalRate"));
					jcolumnaPrecio.setMinWidth(100);
					
//					jcolumnaboton.setCellFactory(new Callback<TableColumn<Film, Boolean>, TableCell<Film, Boolean>>() {
//
//						public TableCell<Film, Boolean> call(TableColumn<Film, Boolean> p) {
//							return new ButtonCell(jttablacontenidoPelicula,f);
//						}
//
//					});
					
					jttablacontenidoPelicula.setItems(data);
					
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}

	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
	
	public void listarPeliculas(){
		
	}
	
	private class ButtonCell extends TableCell<Film, Boolean> {

		// boton a mostrar
		final Button cellButton = new Button("Vender");
		private Film film;
		ButtonCell(final TableView tblView,Film f) {
			this.film=f;
			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					guardarEnSesion("peliculavender", film);
					abrirVentana("/fxml/venderPelicula.fxml", ControladorvenderPelicula.class);
					int num = getTableRow().getIndex();
					
//					}
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
