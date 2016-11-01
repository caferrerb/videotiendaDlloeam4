package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.uniquindio.videotienda.dtos.DayEnum;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.media.MediaPlayer.Status;

@Controller
public class ControladorGestionarEmpleadoSub extends BaseController implements Initializable{

	@FXML
	private ComboBox<String> CBDia;
	

	public void llenarDia(){		
		CBDia.getItems().addAll("Holas");
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		JOptionPane.showMessageDialog(null, "Holasssss");
		
	}
	

}
