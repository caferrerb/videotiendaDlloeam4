package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Customer;

@Component
public class BOCustomer extends BOGenerico<Customer>{
	@Override
	public Customer buscar(Object pk) {
		return super.buscar(pk);
		
	}
	
	public List<Customer> listaBucarCliente(int idCliente){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTAR_DATOS_CLIENTE, idCliente);
	}

}
