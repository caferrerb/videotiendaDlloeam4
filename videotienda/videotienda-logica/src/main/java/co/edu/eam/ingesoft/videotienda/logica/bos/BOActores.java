/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	
		Actor actor = buscar(entidad.getActorId());
		if(actor == null){
			super.crear(entidad);
		}else{
			
		}
	}

}
