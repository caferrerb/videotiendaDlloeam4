package co.edu.uniquindio.videotienda.dtos;

import java.io.Serializable;
import java.util.Date;

public class PrestamosClienteDTO implements Serializable {

	private String titulo;
	private Date fechaPrestamo;
	private Date fechaEntrega;

	
	public PrestamosClienteDTO() {
	}


	/**
	 * @param titulo
	 * @param fechaPrestamo
	 * @param fechaEntrega
	 */
	public PrestamosClienteDTO(String titulo, Date fechaPrestamo, Date fechaEntrega) {
		super();
		this.titulo = titulo;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaEntrega = fechaEntrega;
	}


	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}


	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	/**
	 * @return the fechaPrestamo
	 */
	public Date getFechaPrestamo() {
		return fechaPrestamo;
	}


	/**
	 * @param fechaPrestamo the fechaPrestamo to set
	 */
	public void setFechaPrestamo(Date fechaPrestamo) {
		this.fechaPrestamo = fechaPrestamo;
	}


	/**
	 * @return the fechaEntrega
	 */
	public Date getFechaEntrega() {
		return fechaEntrega;
	}


	/**
	 * @param fechaEntrega the fechaEntrega to set
	 */
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	
	
}
