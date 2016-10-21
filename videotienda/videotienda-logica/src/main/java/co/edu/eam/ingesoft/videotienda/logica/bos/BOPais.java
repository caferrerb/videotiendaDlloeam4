package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Country;

/**
 * 
 * Clase responsable de la logica de los paiese<br>
 * 
 * @author Camilo Andres Ferrer Bustos<br>
 *         caferrer
 * 
 * @date 21/10/2016
 * @version 1.0
 */
@Component
public class BOPais extends BOGenerico<Country> {

	/**
	 * 
	 * Método que lista todos los paises<br>
	 * 
	 * @author Camilo Andres Ferrer Bustos<br>
	 *         caferrer
	 * @date 21/10/2016
	 * @version 1.0
	 * @return
	 *
	 */
	public List<Country> listarPaises() {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTARPAISES);
	}
}
