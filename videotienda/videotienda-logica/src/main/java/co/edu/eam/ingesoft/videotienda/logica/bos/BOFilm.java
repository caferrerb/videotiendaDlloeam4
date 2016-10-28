package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Film;
/**
 * Clase respondable de la logica de la pelicula
 * @author laura posada
 *
 */
@Component
public class BOFilm extends BOGenerico<Film>{

//	@Override
	public void crear(Film entidad) {
		// TODO Auto-generated method stub
		super.crear(entidad);
	}
	
	
	/**
	 * metodo que lista los datos de las peliculas
	 * @return
	 */
	public List<Film> listarPeliculas(String nombre){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_POR_NOMBRE_PELICULA,nombre);
	}
}
