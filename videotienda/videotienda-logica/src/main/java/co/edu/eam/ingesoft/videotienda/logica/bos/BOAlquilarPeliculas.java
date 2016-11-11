package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.GregorianCalendar;
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
import co.edu.uniquindio.videotienda.dtos.PrestamoDTO;

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
	 * lista de los prestamos de un cliente
	 * 
	 * @param c
	 *            el cliente el cual se busca
	 * @return la lista
	 */
	public List<String> listarPrestaClientes(Customer c) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_PRESTAMOS_CLIENTE, c);

	}

	/**
	 * 
	 * @param f
	 * @return
	 */
	private Inventory inventarioPelicula(Film f) {
		List<Inventory> lista = dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTA_INVENTARIO_PELICULA, f);
		return lista.get(0);
	}

	/**
	 * 
	 * @return
	 */
	private Staff empleado() {
		List<Staff> lista = dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAREMPLEADOS);
		return lista.get(0);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<PrestamoDTO> listarLosPrestamosCliente(int c) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_INFO_PRESTAMOS, c);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<Rental> listarPrestamosDeUnCLiente(int c) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_PELICULAS_CLIENTE, c);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<Rental> listarFechaEntregaPrestamoCliente(int c) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_FECHAS_CLIENTE, c);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public List<String> listarInfoPrestamos(String c) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_INFO_PRESTAMOS, c);
	}

	/**
	 * 
	 * @param idCliente
	 * @param f
	 * @param fechaEntrega
	 * @throws ExcepcionNegocio
	 */
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
		List<Rental> listaP = listarPrestamosDeUnCLiente(idCliente);
		List<Rental> listaF = listarFechaEntregaPrestamoCliente(idCliente);
		if (lista.size() == 0) {
			if (listaP.size() < 5) {
                for (int i = 0; i < listaF.size(); i++) {
					if(fechaActual.after(listaF.get(i).getReturnDate())){
						throw new ExcepcionNegocio(" El cliente tiene un prestamo con la fecha de entrega vencida ");
					}
					
				}
               crear(prestamos);
			} else {
				throw new ExcepcionNegocio(
						" El clienta al cual se le va a realizar el prestamo, ya tiene el limite de prestamos");
			}
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
	 * @param t
	 * @return
	 */
	public List<Rental> listarPelicualsPrestadas(String t) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_PELICULAS_PRESTADAS, t);
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
