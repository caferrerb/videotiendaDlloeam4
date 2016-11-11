package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Acceso;

/**
 * Objeto de negocio para todas las operaciones asociadas al Acceso.
 *
 * @author Richard Alexander Vanegas Ochoa<br/>
 *         email: Richardvanegas8@gmail.com <br/>
 *         Fecha: 23/10/2015<br/>
 */
@Component
public class BOAcceso extends BOGenerico<Acceso>{

	/**
	 * Metodo que lista todos los accesos
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @return
	 */
	public List<Acceso> listar(){
		
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_PANTALLAS);
	}
}
