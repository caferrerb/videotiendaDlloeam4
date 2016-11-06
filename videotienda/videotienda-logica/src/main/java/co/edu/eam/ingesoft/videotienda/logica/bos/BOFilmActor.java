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
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.FilmActor;

/**
 * @author TOSHIBAP55W
 *
 */
@Component
public class BOFilmActor extends BOGenerico<FilmActor>{

	
	public List<FilmActor> listarFilmesActores(int idFilm){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_FILM_ACTOR_POR_ID_FILM,idFilm);
	}
	
	public List<FilmActor> listarFilmActoresPorActor(Actor ac){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_FILMACTOR_POR_ACTOR,ac);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(FilmActor entidad){
		
		super.crear(entidad);
	}
	
	
}
