package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;

/**
 * Clase responsable de la logica de la tienda
 * @author Cristian Sinisterra<br/>
 * 		   email:cristiansinisterra@hotmail.com<br/>
 *		   Fecha 04/11/2016
 */
@Component
public class BORental extends BOGenerico<Store> {
	
	/**
	 * Consulta que trae una lista con los datos de la tabla de peliculas rentadas
	 * @param store la tienda a la cual se la van a buscar las peliculas rentadas
	 * @return la lista de peliculas rentadas de dicha tienda
	 */
	public List<Rental>listarTablaRental(Store store){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_TABLA_PELICULA_RENTADAS,store);
	}
	

}
