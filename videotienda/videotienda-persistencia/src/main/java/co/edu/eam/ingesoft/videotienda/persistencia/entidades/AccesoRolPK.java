package co.edu.eam.ingesoft.videotienda.persistencia.entidades;

import java.io.Serializable;

public class AccesoRolPK implements Serializable{

	/*
	 * atributos
	 */
	
	private int rol;
	
	private int acceso;

	/*
	 * Constructores
	 */
	
	public AccesoRolPK(int rol, int acceso) {
		super();
		this.rol = rol;
		this.acceso = acceso;
	}

	public AccesoRolPK() {
	
	}

	/*
	 * Getters and setters
	 */
	
	public int getRol() {
		return rol;
	}

	public void setRol(int rol) {
		this.rol = rol;
	}

	public int getAcceso() {
		return acceso;
	}

	public void setAcceso(int acceso) {
		this.acceso = acceso;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acceso;
		result = prime * result + rol;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccesoRolPK other = (AccesoRolPK) obj;
		if (acceso != other.acceso)
			return false;
		if (rol != other.rol)
			return false;
		return true;
	}
	
	
	
	
	
}
