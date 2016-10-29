package co.edu.eam.ingesoft.videotienda.vista.controladores;




import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

@Controller
public class ControladorGestionarEmpleado extends BaseController implements Initializable {

	@FXML
	private TextField TfidTienda;
	@FXML
	private TextField TfPrimerNombre;	
	@FXML
	private TextField TfSegundoNombre;
	@FXML
	private TextField TfEmail;
	@FXML
	private TextField TfDireccion;
	@FXML
	private CheckBox CheckActivo;
	@FXML
	private DatePicker DtFechaCreacion;
	@FXML
	private DatePicker DtFechaActualizacion;
	@FXML
	private ImageView PhFoto;
	@FXML
	private ComboBox<Store> comboBoxSelecTienda;
	@FXML
	private TableView<StaffSchedule> TbHorario;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

	@FXML
	public void crearEmpleado(){
		JOptionPane.showMessageDialog(null, "Crear");
	}

	@FXML
	public void editar(){
		
	}
	
	@FXML
	public void buscar(){
		
	}


}
