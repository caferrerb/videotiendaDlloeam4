package co.edu.eam.ingesoft.videotienda.persistencia.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.edu.uniquindio.videotienda.dtos.DayEnum;

@Entity
@Table(name="StaffSchedule")
public class StaffSchedule {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_HORARIO")
	private int idStaffSchedule;
	
	@JoinColumn(name="EMPLEADO")
	@ManyToOne
	private Staff empleado;
	
	@Column(name="dia")
	private DayEnum dia;
	
	@Temporal(TemporalType.TIME)
	@Column(name="HORA_INICIAL")
	private Date horaInicial;
	
	@Temporal(TemporalType.TIME)
	@Column(name="HORA_FINAL")
	private Date horaFinal;
	
	public StaffSchedule() {
		
	}

	public StaffSchedule(int idStaffSchedule, Staff empleado, DayEnum dia, Date horaInicial, Date horaFinal) {
		super();
		this.idStaffSchedule = idStaffSchedule;
		this.empleado = empleado;
		this.dia = dia;
		this.horaInicial = horaInicial;
		this.horaFinal = horaFinal;
	}

	/**
	 * @return the idStaffSchedule
	 */
	public int getIdStaffSchedule() {
		return idStaffSchedule;
	}

	/**
	 * @param idStaffSchedule the idStaffSchedule to set
	 */
	public void setIdStaffSchedule(int idStaffSchedule) {
		this.idStaffSchedule = idStaffSchedule;
	}

	/**
	 * @return the empleado
	 */
	public Staff getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Staff empleado) {
		this.empleado = empleado;
	}

	/**
	 * @return the dia
	 */
	public DayEnum getDia() {
		return dia;
	}

	/**
	 * @param dia the dia to set
	 */
	public void setDia(DayEnum dia) {
		this.dia = dia;
	}

	/**
	 * @return the horaInicial
	 */
	public Date getHoraInicial() {
		return horaInicial;
	}

	/**
	 * @param horaInicial the horaInicial to set
	 */
	public void setHoraInicial(Date horaInicial) {
		this.horaInicial = horaInicial;
	}

	/**
	 * @return the horaFinal
	 */
	public Date getHoraFinal() {
		return horaFinal;
	}

	/**
	 * @param horaFinal the horaFinal to set
	 */
	public void setHoraFinal(Date horaFinal) {
		this.horaFinal = horaFinal;
	}

	

}
