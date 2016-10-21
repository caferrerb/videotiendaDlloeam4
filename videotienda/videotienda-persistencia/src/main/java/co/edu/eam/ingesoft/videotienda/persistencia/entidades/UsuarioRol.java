package co.edu.eam.ingesoft.videotienda.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name="T_USUARIO_ROL")
@IdClass(UsuarioRolPK.class)
public class UsuarioRol implements Serializable{

	/*
	 * Atributos
	 */
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ID_ROL", nullable = false, updatable = false, insertable = false)
	private Rol rol;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", nullable = false, updatable = false, insertable = false)
	private Usuario usuario;

	/*
	 * Constructor
	 */
	
	public UsuarioRol(Rol rol, Usuario usuario) {
		super();
		this.rol = rol;
		this.usuario = usuario;
	}

	public UsuarioRol() {
		
	}

	/*
	 * Getters and setters
	 */
	
	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
	
	
}
