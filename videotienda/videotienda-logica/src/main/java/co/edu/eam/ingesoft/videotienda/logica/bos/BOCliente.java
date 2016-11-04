package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Address;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;

@Component
public class BOCliente extends BOGenerico<Customer> {

	/**
	 * Lista las películas rentadas de un cliente
	 * @param c Cliente al cual se le listarán las películas
	 * @return las lista de películas del cliente
	 */
	public List<Film> peliculasCliente (Customer c){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_PELICULAS_CLIENTE,c);
	}
	
	/**
	 * Permite trasladar la ciudad a un cliente
	 * @param cliente Cliente al cual se le va a trasladar la ciudad
	 * @param ciudad Ciudad a la que se desea trasladar
	 * @param dir La nueva dirección
	 * @throws ExcepcionNegocio Si falla alguna operación
	 */
	@org.springframework.transaction.annotation.Transactional(propagation=Propagation.REQUIRED)
	public void trasladar (Customer cliente, City ciudad, String dir) throws ExcepcionNegocio{
		List<Film> peliculasCliente = peliculasCliente(cliente);
		if (!peliculasCliente.isEmpty()) {
			throw new ExcepcionNegocio("No se puede trasladar porque tiene películas prestadas");
		} else {
			if (cliente == null) {
				throw new ExcepcionNegocio("Debe buscar el cliente para hacer el traslado");
			} else {
				if (cliente.getAddress().getCity().getCity().equals(ciudad.getCity())) {
					throw new ExcepcionNegocio("La ciudad actual y la de traslado deben ser diferentes");
				} else {
					Address add = cliente.getAddress();
					add.setAddress(dir);
					add.setCity(ciudad);
					cliente.setAddress(add);
					editar(cliente);
				}
			}
		}
	}
	
}
