package co.edu.eam.ingesoft.videotienda.persistencia.entidades;

import java.io.Serializable;

public class UsuarioRolPK implements Serializable {

	/*
	 * Atributos
	 */
	
	private int rol;
	
	private String usuario;

	/*
	 * Constructor
	 */
	
	public UsuarioRolPK(int rol, String usuario) {
		super();
		this.rol = rol;
		this.usuario = usuario;
	}

	public UsuarioRolPK() {
		
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

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rol;
		result = prime * result + usuario.hashCode();
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
		UsuarioRolPK other = (UsuarioRolPK) obj;
		if (rol != other.rol)
			return false;
		if (usuario != other.usuario)
			return false;
		return true;
	}
	

	
	
	
}
