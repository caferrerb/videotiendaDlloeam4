package co.edu.uniquindio.videotienda.dtos;

import javafx.beans.property.SimpleStringProperty;

public class AccesoRolDTO {

	private SimpleStringProperty descripcion;
	
	private SimpleStringProperty nombre;

	
	public AccesoRolDTO() {
		super();
	}


	public AccesoRolDTO(String descripcion, String nombre) {
		super();
		this.descripcion = new SimpleStringProperty(descripcion);
		this.nombre = new SimpleStringProperty(nombre);
	}


	public SimpleStringProperty getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(SimpleStringProperty descripcion) {
		this.descripcion = descripcion;
	}


	public SimpleStringProperty getNombre() {
		return nombre;
	}


	public void setNombre(SimpleStringProperty nombre) {
		this.nombre = nombre;
	}

	
	
}
