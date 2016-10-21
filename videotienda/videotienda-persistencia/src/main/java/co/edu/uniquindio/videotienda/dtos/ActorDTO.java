package co.edu.uniquindio.videotienda.dtos;

public class ActorDTO {
	
	private String nombre;
	private String apellido;
	
	public ActorDTO() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	public ActorDTO(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}



	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	
	

}
