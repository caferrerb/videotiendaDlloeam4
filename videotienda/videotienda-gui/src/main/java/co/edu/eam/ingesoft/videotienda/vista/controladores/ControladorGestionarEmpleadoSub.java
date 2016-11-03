package co.edu.eam.ingesoft.videotienda.vista.controladores;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import co.edu.eam.ingesoft.videotienda.logica.bos.BOEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.bos.BOHoraioEmpleado;
import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.eam.ingesoft.videotienda.vista.util.BaseController;
import co.edu.eam.ingesoft.videotienda.vista.util.TipoNotificacion;
import co.edu.uniquindio.videotienda.dtos.DayEnum;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer.Status;

@Controller
public class ControladorGestionarEmpleadoSub extends BaseController implements Initializable {

	@Autowired
	private BOHoraioEmpleado boHorarioEmp;

	@Autowired
	private BOEmpleado boEmpleado;

	@FXML
	private ComboBox<DayEnum> CBDia;

	@FXML
	private ComboBox<String> horaIni;

	@FXML
	private ComboBox<String> horaFin;

	@FXML
	private Button BTAgregarHorario;

	@FXML
	private TextField idEmpleado;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		llenarDia();
		llenarHoras();
	}

	/**
	 * LLena el comboBox Dia
	 */
	public void llenarDia() {
		DayEnum[] dias = boHorarioEmp.listaDias();
		for (DayEnum dia : dias) {
			CBDia.getItems().add(dia);
		}
	}

	public void llenarHoras() {
		String[] hora = boHorarioEmp.listaHoras();

		horaIni.getItems().addAll(hora);
		horaFin.getItems().addAll(hora);
	}

	@FXML
	public void agregarHorario() {
		try {
			StaffSchedule horario = new StaffSchedule();
			System.out.println("---------------------------------------------------------" + idEmpleado.getText()
					+ CBDia.getSelectionModel().getSelectedItem() + horaFin.getSelectionModel().getSelectedItem()
					+ horaIni.getSelectionModel().getSelectedItem());

			Staff empleado = boEmpleado.buscar((byte) Integer.parseInt(idEmpleado.getText()));

			horario.setEmpleado(empleado);

			DayEnum di= CBDia.getSelectionModel().getSelectedItem();
			horario.setDia(di);
			horario.setHoraFinal(Integer.parseInt(horaFin.getSelectionModel().getSelectedItem()));
			horario.setHoraInicial(Integer.parseInt(horaIni.getSelectionModel().getSelectedItem()));

			boHorarioEmp.crearHorario(horario);

			notificar("gestionar Horario", "Horario agregado con exito", TipoNotificacion.INFO);
		} catch (ExcepcionNegocio e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
