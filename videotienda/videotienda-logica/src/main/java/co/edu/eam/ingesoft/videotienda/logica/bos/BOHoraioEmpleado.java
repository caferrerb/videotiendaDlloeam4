package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.uniquindio.videotienda.dtos.DayEnum;

@Component
public class BOHoraioEmpleado extends BOGenerico<StaffSchedule> {

	/**
	 * Crea un horario para el empleado
	 * 
	 * @param horario
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void crearHorario(StaffSchedule horario) throws Exception {
		List<StaffSchedule> listaH = listaHorario(horario.getEmpleado());
		if (horario.getHoraInicial() < horario.getHoraFinal()) {

			int hora = 0;
			int horaB = 0;

			for (int i = 0; i < listaH.size(); i++) {
				hora += listaH.get(i).getHoraFinal() - listaH.get(i).getHoraInicial();
				horaB = hora + (horario.getHoraFinal() - horario.getHoraInicial());
			}
			if (horaB < 48) {
				super.crear(horario);
			} else {
				throw new ExcepcionNegocio(
						"Usted ya excedio el limite de horas ha trabajar" + "\nLa suma de horas esta dando: " + horaB);
			}
		} else {
			throw new ExcepcionNegocio("La hora final no puede ser menos a la hora inicial o igual");
		}
	}
	
	
	/**
	 * Crea un horario para el empleado
	 * 
	 * @param horario
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void editarHorario(StaffSchedule horario) throws Exception {
		List<StaffSchedule> listaH = listaHorario(horario.getEmpleado());
		if (horario.getHoraInicial() < horario.getHoraFinal()) {

			int hora = 0;
			int horaB = 0;

			for (int i = 0; i < listaH.size(); i++) {
				hora += listaH.get(i).getHoraFinal() - listaH.get(i).getHoraInicial();
				horaB = hora + (horario.getHoraFinal() - horario.getHoraInicial());
			}
			if (horaB < 48) {
				super.editar(horario);
			} else {
				throw new ExcepcionNegocio(
						"Usted ya excedio el limite de horas ha trabajar" + "\nLa suma de horas esta dando: " + horaB);
			}
		} else {
			throw new ExcepcionNegocio("La hora final no puede ser menos a la hora inicial o igual");
		}
	}
	
	

	/**
	 * Trae una lista de horario del empleado buscado
	 * 
	 * @return listara del hroario del empleado
	 */
	public List<StaffSchedule> listaHorario(Staff Empleado) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_HORARIO_EMPLEADO, Empleado);
	}

	/**
	 * Carga la enumeracion de dias
	 * 
	 * @author CAMILO
	 * @return
	 */
	public DayEnum[] listaDias() {
		DayEnum[] dias = DayEnum.values();
		for (int i = 0; i < dias.length; i++) {
			return dias;
		}
		return null;
	}

	/**
	 * Horas de trabajo de los empleados
	 * 
	 * @return
	 */
	public String[] listaHoras() {
		String[] hora = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24" };
		return hora;
	}

	/**
	 * Lista horario empleado
	 * @return
	 */
	public List<StaffSchedule> ListaHorarios(){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_HORARIO);
	}
	
}
