package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Sale;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;

@Component
public class BOReportes extends BOGenerico<Store>{

	
	/**
	 * lista las tiendas que hay en la base de datos
	 * @return la lista con las tiendas
	 */
	public List<Store>listarTiendas(){	
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_TIENDAS);
	}
	
	/**
	 * consulta que trae una lista con los datos de la tabla renta de la ventana reportes
	 * @param s la tienda a la que se le van a buscar los datos
	 * @return la lista
	 */
	public List<Rental>listarTablaRental(Store s){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_DATOS_RENTA,s);
	}
	
	/**
	 * consulta que trae una lista con los datos de la tabla venta de la ventana reportes
	 * @param s la tienda a la que se le van a buscar los datos
	 * @return la lista
	 */
	public List<Sale>listarTablaVenta(Store s){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_DATOS_VENTA, s);
	}
}
