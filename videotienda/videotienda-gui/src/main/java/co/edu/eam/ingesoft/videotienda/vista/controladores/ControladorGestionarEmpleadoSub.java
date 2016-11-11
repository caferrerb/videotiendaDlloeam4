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
	private Button BTActualizar;
	
	@FXML
	private TextField idEmpleado;

	private StaffSchedule horario;
	
	private int idHorario;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		horario=(StaffSchedule) obtenerValor("horario");
		
		if(horario != null){
			idEmpleado.setText(Integer.toString(horario.getEmpleado().getStaffId()));
			CBDia.setValue(horario.getDia());
			horaIni.setValue(Integer.toString(horario.getHoraInicial()));
			horaFin.setValue(Integer.toString(horario.getHoraFinal()));
			idHorario=horario.getIdStaffSchedule();
			//BTAgregarHorario.
		}
		
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
	public void agregarHorario()throws Exception {
		try {
			StaffSchedule horario = new StaffSchedule();

			Staff empleado = boEmpleado.buscar((byte) Integer.parseInt(idEmpleado.getText()));

			horario.setEmpleado(empleado);

			DayEnum di = CBDia.getSelectionModel().getSelectedItem();
			horario.setDia(di);
			horario.setHoraFinal(Integer.parseInt(horaFin.getSelectionModel().getSelectedItem()));
			horario.setHoraInicial(Integer.parseInt(horaIni.getSelectionModel().getSelectedItem()));

			boHorarioEmp.crearHorario(horario);

			notificar("gestionar Horario", "Horario agregado con exito", TipoNotificacion.INFO);
		}catch (ExcepcionNegocio ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarHorario (){
		try {
			
			StaffSchedule horario = boHorarioEmp.buscar(idHorario);

			//Staff empleado = boEmpleado.buscar(Byte.parseByte(idEmpleado.getText()));

			horario.setEmpleado(horario.getEmpleado());
			horario.setIdStaffSchedule(horario.getIdStaffSchedule());
			DayEnum di = CBDia.getSelectionModel().getSelectedItem();
			horario.setDia(di);
			horario.setHoraFinal(Integer.parseInt(horaFin.getSelectionModel().getSelectedItem()));
			horario.setHoraInicial(Integer.parseInt(horaIni.getSelectionModel().getSelectedItem()));

			boHorarioEmp.editarHorario(horario);

			notificar("gestionar Horario", "Horario Actualizado con exito", TipoNotificacion.INFO);
		}catch (ExcepcionNegocio ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
