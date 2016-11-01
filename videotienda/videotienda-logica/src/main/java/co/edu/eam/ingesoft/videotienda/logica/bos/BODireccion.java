package co.edu.eam.ingesoft.videotienda.logica.bos;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Address;

@Component
public class BODireccion extends BOGenerico<Address>{

	/**
	 * crea una direecion para.
	 * Actualmente se utiliza para poder registrar un empleado
	 * @param direccion
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void crearDireccion(Address direccion) {
		super.crear(direccion);
	}
	
	/**
	 * Edita una direccion 
	 * @param direccion
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void editarDireccion(Address direccion) {
		super.editar(direccion);
	}
	
}
