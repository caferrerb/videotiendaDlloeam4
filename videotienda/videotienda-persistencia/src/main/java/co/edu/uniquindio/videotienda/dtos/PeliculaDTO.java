package co.edu.uniquindio.videotienda.dtos;

public class PeliculaDTO {
	
	private int longitud;
	private String idioma;
	private String nombre;
	
	
	public PeliculaDTO(int longitud, String idioma, String nombre) {
		super();
		this.longitud = longitud;
		this.idioma = idioma;
		this.nombre = nombre;
	}
	public int getLongitud() {
		return longitud;
	}
	public void setLongitud(int longitud) {
		this.longitud = longitud;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
