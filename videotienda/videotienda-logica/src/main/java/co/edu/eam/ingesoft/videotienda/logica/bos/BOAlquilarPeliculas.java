package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;

@Component
public class BOAlquilarPeliculas extends BOGenerico<Rental> {

	/**
	 * lista de los prestamos de un cliente
	 * 
	 * @param c
	 *            el cliente el cual se busca
	 * @return la lista
	 */
	public List<Object[]> listarPrestamoCliente(int c) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_PRESTAMOS_CLIENTE, c);

	}
	
	/**
	 * lista de los prestamos de un cliente
	 * 
	 * @param c
	 *            el cliente el cual se busca
	 * @return la lista
	 */
	public List<Rental> listarPrestaClientes(int c) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_PRESTAMOS_CLIENTE, c);

	}
	
	private Inventory inventarioPelicula (Film f){
		List<Inventory> lista = dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTA_INVENTARIO_PELICULA, f);
		return lista.get(0);
	}
	
	private Staff empleado(){
		List<Staff> lista = dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAREMPLEADOS);
		return lista.get(0);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void registrarPrestamo(int idCliente, Film f, LocalDate fechaEntrega) throws ExcepcionNegocio {

		Rental prestamos = new Rental();
		Customer cliente = new Customer();
		cliente.setCustomerId(idCliente);
		prestamos.setCustomer(cliente);
		Date fechaActual = new Date();
		fechaActual.getDate();
		prestamos.setRentalDate(fechaActual);
		Date fechaEntre = Date.from(fechaEntrega.atStartOfDay(ZoneId.systemDefault()).toInstant());
		prestamos.setReturnDate(fechaEntre);
		prestamos.setLastUpdate(fechaActual);
	    prestamos.setInventory(inventarioPelicula(f));
		prestamos.setStaff(empleado());

		List<Rental> lista = listarPrestamosRepetidos(f);
		System.out.println(lista.size() + " La lista");
		if (lista.size() == 0) {
			crear(prestamos);
		} else {
			throw new ExcepcionNegocio(" Esta pelicula ya esta prestada ");
		}

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
	 * @param idPrestamo
	 * @return
	 */
	public List<Rental> listarPrestamosRepetidos(Film f) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_PRESTAMOS_REPETIDOS, f);
	}

}
