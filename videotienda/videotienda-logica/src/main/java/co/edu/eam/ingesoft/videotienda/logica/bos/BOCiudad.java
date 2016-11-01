package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;

@Component
public class BOCiudad extends BOGenerico<City> {
	
	/**
	 * Metodo que lista todas las ciudades
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 27/10/2016<br/>
	 * @return
	 */
	public List<City> listarCiudades(){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTARCIUDADES);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(City ciudad){
		super.crear(ciudad);
	}
}
