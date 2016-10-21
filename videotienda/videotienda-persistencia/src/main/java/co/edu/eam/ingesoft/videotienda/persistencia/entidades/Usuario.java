package co.edu.eam.ingesoft.videotienda.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Clase para el manejo del usuario con sus datos basicos
 * 
 * @author Giraldo
 */

@Entity
@Table(name = "T_USUARIO")
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({@NamedQuery(name=Usuario.BUSCAR_POR_USERNAME, query="SELECT u FROM Usuario u WHERE u.usuario = ?1 ")})

public class Usuario implements Serializable {

	/* Atributos */
	
	public static final String BUSCAR_POR_USERNAME = "Usuario.buscarUser";

	@Id
	@Column(name = "USUARIO", length = 45, nullable = false, unique=true)
	protected String usuario;

	@Column(name = "PASS", length = 45, nullable = false)
	protected String pass;

	/* Constructor */

	public Usuario(int id, String nombre, String apellido, String usuario, String pass) {
		super();
		
		
		this.usuario = usuario;
		this.pass = pass;
	}

	public Usuario() {

	}

	

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
