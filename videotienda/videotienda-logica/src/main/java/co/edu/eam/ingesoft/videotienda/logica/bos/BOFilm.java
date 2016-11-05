package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Category;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
/**
 * Clase respondable de la logica de la pelicula
 * @author laura posada
 *
 */
@Component
public class BOFilm extends BOGenerico<Film>{

//	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(Film entidad){
		
		super.crear(entidad);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Film buscarPelicula(int id) {
		
		return super.buscar(id);
	}
	
	/**
	 * metodo que lista los datos de las peliculas
	 * @return
	 */
	public List<Film> listarPeliculas(String nombre){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_POR_NOMBRE_PELICULA,"%"+nombre+"%");
	}
	
	public List<Category> listarCategorias(){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_CATEGORIAS_FILM);
	}

	public List<Film> listarPeliculasPorActor(int id){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_FILM_POR_ACTOR);
	}
	/**
	 * metodo que lista las peliculas por su nombre
	 * @return
	 */
	public List<Film> listarPeliculasNombres(){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_PELICULAS_NOMBRES);
	}

	
}
