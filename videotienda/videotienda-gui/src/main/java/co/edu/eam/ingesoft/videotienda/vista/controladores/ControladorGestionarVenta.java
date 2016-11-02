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
			String nomPelicula = jtfTitulo.getText();
			List<Film> pelicula = boPelicula.listarPeliculas(nomPelicula);
			for (int i = 0; i < pelicula.size(); i++) {
				data.add(pelicula.get(i));
				jcolumnaGenero.setCellValueFactory(new PropertyValueFactory<Film, String>(""));
				jcolumnaGenero.setMinWidth(100);
				jcolumnaTitulo.setCellValueFactory(new PropertyValueFactory<Film, String>("title"));
				jcolumnaTitulo.setMinWidth(100);
				jcolumnaLong.setCellValueFactory(new PropertyValueFactory<Film, Integer>("length"));
				jcolumnaLong.setMinWidth(100);
				jcolumnaPrecio.setCellValueFactory(new PropertyValueFactory<Film, Double>("rentalRate"));
				jcolumnaPrecio.setMinWidth(100);
				
				jcolumnaboton.setCellFactory(new Callback<TableColumn<Film, Boolean>, TableCell<Film, Boolean>>() {

					public TableCell<Film, Boolean> call(TableColumn<Film, Boolean> p) {
						return new ButtonCell(jttablacontenidoPelicula);
					}

				});
				
				jttablacontenidoPelicula.setItems(data);
				
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

		ButtonCell(final TableView tblView) {

			cellButton.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent t) {
					abrirVentana("/fxml/venderPelicula.fxml", ControladorvenderPelicula.class);
//					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/venderPelicula.fxml"));
//					Parent root1;
//					try {
//						root1 = (Parent) fxmlLoader.load();
//						Stage stage = new Stage();
//						stage.initModality(Modality.APPLICATION_MODAL);
//						stage.setTitle("VENDER PELICULA");
//						stage.setScene(new Scene(root1));
//						stage.show();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
					int num = getTableRow().getIndex();
					
					// borramos el objeto obtenido de la fila
//					Film r = data.get(num);
//					if(boPelicula.listarPeliculas(r.getTitle()).size()!= 0){
//						notificar("Eliminar Rol", "No se puede eliminar el rol," + "ya que alguien lo tiene asignado",
//								TipoNotificacion.ERROR);
//
//					} else {
//						System.out.println("i dont know");
//						boRol.eliminar(r.getId());
//						roles.remove(num);
//						notificar("Eliminar Rol", "Rol eliminado con exito!", TipoNotificacion.INFO);
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
