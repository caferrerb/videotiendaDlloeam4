package co.edu.eam.ingesoft.videotienda.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_ACCESO")
public class Acceso implements Serializable{

	/*
	 * Atributos
	 */
	
	public static final String LISTAR_ACCESOS_ROL = "Acceso.listarAccesosRol";
	
	@Id
	@Column(name="ID_ACCESO", length=12, nullable=false)
	private int id;
	
	@Column(name="PANTALLA", length=200, nullable=false)
    private String pantalla;
    
	@Column(name="NOMBRE", length=60, nullable=false)
    private String nombre;
	
	
	
	/*
	 * Constructor 
	 */
	

	public Acceso(int id, String url, String nombre) {
		super();
		this.id = id;
		this.pantalla = url;
		this.nombre = nombre;
	}

	public Acceso() {
	
	}
	
	/*
	 * Getters and setters
	 */

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPantalla() {
		return pantalla;
	}

	public void setPantalla(String pantalla) {
		this.pantalla = pantalla;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
}
