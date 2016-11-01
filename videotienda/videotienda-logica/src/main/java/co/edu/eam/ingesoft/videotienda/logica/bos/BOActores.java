/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import javax.management.Notification;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;

/**
 * @author GAR-T
 *
 */
@Component
public class BOActores extends BOGenerico<Actor> {

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(Actor entidad) {		
			super.crear(entidad);
		}
	


	/**
	 * metodo para listar autores
	 * 
	 * @return autores registrados
	 */
	public List<Actor> listarAutores() {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_AUTORES);
	}
}
