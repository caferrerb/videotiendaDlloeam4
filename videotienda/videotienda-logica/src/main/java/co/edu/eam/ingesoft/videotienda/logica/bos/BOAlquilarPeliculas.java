package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;

@Component
public class BOAlquilarPeliculas extends BOGenerico<Rental> {

	/**
	 * lista de los prestamos de un cliente
	 * 
	 * @param c
	 *            el cliente el cual se busca
	 * @return la lista
	 */
	public List<Rental> listarPrestamoCliente(Customer c) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_PRESTAMOS_CLIENTE, c);

	}

	/**
	 * trae la fecha de entraga de una pelicula
	 * 
	 * @param f
	 *            la película
	 * @return la fecha de entrega
	 */
	public List<Rental> fechaEntrePelicula(String f) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_FECHA_ENTREGA_PELICULA, f);
	}

	/**
	 * metodo que lista las peliculas por su nombre
	 * 
	 * @return
	 */
	public List<Film> listarPeliculasNombres() {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_PELICULAS_NOMBRES);
	}

	/**
	 * 
	 * @param f
	 * @return
	 */
	public Rental fechaEntregaPelicula(String f) {

		List<Rental> lista = fechaEntrePelicula(f);
		return lista.get(0);

	}

}
