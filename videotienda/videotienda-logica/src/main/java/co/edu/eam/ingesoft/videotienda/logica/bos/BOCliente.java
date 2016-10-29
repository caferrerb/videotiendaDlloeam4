package co.edu.eam.ingesoft.videotienda.logica.bos;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;

@Component
public class BOCliente extends BOGenerico<Customer> {

	public void trasladarCiudad (City ciudad, Customer c){
		c.getAddress().setCity(ciudad);
	}
	
}
