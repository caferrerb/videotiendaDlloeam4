package co.edu.eam.ingesoft.videotienda.logica.bos;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Address;

@Component
public class BODireccion extends BOGenerico<Address>{

	
	@Transactional(propagation = Propagation.REQUIRED)
	public void crearDireccion(Address direccion) {
		super.crear(direccion);
	}
	
	
	
}
