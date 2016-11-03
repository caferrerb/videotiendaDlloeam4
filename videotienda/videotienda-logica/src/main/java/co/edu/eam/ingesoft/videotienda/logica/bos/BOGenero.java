/**
 * @author Carlos Alfredo Martinez Villada
 * Bo encargado de la logica de negocio de Category (Genero).
 */
package co.edu.eam.ingesoft.videotienda.logica.bos;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Actor;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Category;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;

@Component
public class BOGenero extends BOGenerico<Category>{
	/**
	 * Registrar informacion de una categoria en la base de datos
	 * @throws ExcepcionNegocio 
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(Category entidad){
		List<Category> genero = dao.ejecutarNamedQuery(ConstantesNamedQueries.BUSCAR_GENERO_POR_NOMBRE, entidad.getName());
		if(genero.size() > 0){
			throw new ExcepcionNegocio("El genero "+entidad.getName()+" ya existe");
		}else{
			super.crear(entidad);
		}
	}
	/**
	 * Editar la informacion de un genero
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void editar(Category genero){
		Category b = buscarPorNombre(genero.getName());
		if(b != null){
			throw new ExcepcionNegocio("Ya hay un genero con el nombre "+genero.getName());
		}else{
			super.editar(genero);
		}
	}
	/**
	 * Buscar un genero por nombre
	 * @return la categoria encontrada de lo contrario null
	 */
	public Category buscarPorNombre(String name){
		List<Category> lista = dao.ejecutarNamedQuery(ConstantesNamedQueries.BUSCAR_GENERO_POR_NOMBRE, name);
		if(lista.size() > 0){
			return lista.get(0);
		}else{
			return null;
		}
	}
	/**
	 * Lista con todos los generos registrados en la bd
	 * @return lista con todos los generos
	 */
	public List<Category> listarGeneros(){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_GENEROS);
	}
	
	/**
	 * Lista con todas las peliculas de un determinado genero
	 * @return lista con todas las peliculas que tengan un determinado genero
	 */
	public List<Film> listarPeliculasPorGenero(Category c){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_FILM_POR_CATEGORIA, c);
	}
	
	/**
	 * Lista con todos los actores que hallan trabajado en peliculas de un determinado genero
	 * @return lista con todas los actores que hallan trabajado en peliculas de un determinado genero
	 */
	public List<Actor> listarActoresPorGenero(Category c){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_ACTOR_POR_CATEGORIA, c);
	}
}
