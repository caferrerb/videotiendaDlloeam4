package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;

/**
 * Objeto de negocio para todas las operaciones asociadas al Rol.
 *
 * @author Richard Alexander Vanegas Ochoa<br/>
 *         email: Richardvanegas8@gmail.com <br/>
 *         Fecha: 23/10/2015<br/>
 */
@Component
public class BORol extends BOGenerico<Rol>{
	
	/**
	 * Metodo que lista todos los roles
	 * @author Richard Vanegas<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @return
	 */
	public List<Rol> listar(){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_ROLES);
		
	}
	
}
