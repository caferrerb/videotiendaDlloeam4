package co.edu.uniquindio.videotienda.dtos;

import java.io.Serializable;

public class PrestamoDTO implements Serializable {
	
	private String titulo;
	private String nombreTienda;
	private int idPrestamos;
	
	public PrestamoDTO() {
	}
	
	

	/**
	 * @param titulo
	 * @param nombreTienda
	 */
	public PrestamoDTO(String nombreTienda, String titulo,int idpre) {
		super();
		this.titulo = titulo;
		this.nombreTienda = nombreTienda;
		this.idPrestamos=idpre;
	}



	/**
	 * @return the idPrestamos
	 */
	public int getIdPrestamos() {
		return idPrestamos;
	}



	/**
	 * @param idPrestamos the idPrestamos to set
	 */
	public void setIdPrestamos(int idPrestamos) {
		this.idPrestamos = idPrestamos;
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
	 * @return the nombreTienda
	 */
	public String getNombreTienda() {
		return nombreTienda;
	}

	/**
	 * @param nombreTienda the nombreTienda to set
	 */
	public void setNombreTienda(String nombreTienda) {
		this.nombreTienda = nombreTienda;
	}
	
	
	
	

}
