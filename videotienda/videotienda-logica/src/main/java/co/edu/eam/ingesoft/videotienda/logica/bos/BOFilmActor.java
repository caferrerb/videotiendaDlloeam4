/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.FilmActor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.FilmActorPK;

/**
 * @author TOSHIBAP55W
 *
 */
@Component
public class BOFilmActor extends BOGenerico<FilmActor> {

	public List<FilmActor> listarFilmesActores(int idFilm) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_FILM_ACTOR_POR_ID_FILM, idFilm);
	}

	public List<FilmActor> listarFilmActoresPorActor(Actor ac) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_FILMACTOR_POR_ACTOR, ac);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(FilmActor entidad) {

		FilmActor fiAc = buscar(entidad.getId());

		if (fiAc != null) {

			throw new ExcepcionNegocio(
					"Este actor ya tiene un personaje asignado en la pelicula con ID= ''" + entidad.getId() + "''.");
		}
		super.crear(entidad);
	}

	// @Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void editar(FilmActor entidadActual, FilmActor entidadNueva) {

		FilmActor fiAc = buscar(entidadActual.getId());

		if (fiAc != null) {
			super.eliminar(entidadActual.getId());
			super.crear(entidadNueva);
		}

	}

}
