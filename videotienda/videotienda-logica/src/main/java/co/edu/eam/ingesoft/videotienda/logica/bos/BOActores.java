/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;

/**
 * @author GAR-T
 *
 */
@Component
public class BOActores extends BOGenerico<Actor> {

	/**
	 * metodo para listar autores
	 * @return autores registrados
	 */
	public List<Actor> listarAutores(){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_AUTORES);
	}
}
