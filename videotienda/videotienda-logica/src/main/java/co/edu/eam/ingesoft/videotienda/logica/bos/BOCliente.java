package co.edu.eam.ingesoft.videotienda.logica.bos;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;

@Component
public class BOCliente extends BOGenerico<Customer> {

	/**
	 * Permite trasladar la ciudad a un cliente
	 * @param ciudad Ciudad a la cual se desea trasladar al cliente
	 * @param c el cliente
	 */
	public void trasladarCiudad (City ciudad, Customer c){
		c.getAddress().setCity(ciudad);
	}
	
}
