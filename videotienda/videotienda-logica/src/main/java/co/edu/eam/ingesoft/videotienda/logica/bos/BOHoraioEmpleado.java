package co.edu.eam.ingesoft.videotienda.logica.bos;


import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.entidades.StaffSchedule;
import co.edu.uniquindio.videotienda.dtos.DayEnum;

@Component
public class BOHoraioEmpleado extends BOGenerico<StaffSchedule> {

	
	/**
	 * Crea un horario para el empleado
	 * @param horario
	 */
	public void crearHorario(StaffSchedule horario){
		super.crear(horario);
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

	public String[] listaHoras() {
		String[] hora = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
				"18", "19", "20", "21", "22", "23", "24" };
		return hora;
	}

}
