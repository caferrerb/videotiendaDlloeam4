package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rental;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Sale;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;

@Component
public class BOReportes extends BOGenerico<Store> {

	/**
	 * lista las tiendas que hay en la base de datos
	 * 
	 * @return la lista con las tiendas
	 */
	public List<Store> listarTiendas() {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_TIENDAS);
	}

	/**
	 * consulta que trae una lista con los datos de la tabla renta de la ventana
	 * reportes
	 * 
	 * @param s
	 *            la tienda a la que se le van a buscar los datos
	 * @return la lista
	 */
	public List<Rental> listarTablaRental(Store s) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_DATOS_RENTA, s);
	}

	/**
	 * consulta que trae una lista con los datos de la tabla venta de la ventana
	 * reportes
	 * 
	 * @param s
	 *            la tienda a la que se le van a buscar los datos
	 * @return la lista
	 */
	public List<Sale> listarTablaVenta(Store s) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_DATOS_VENTA, s);
	}

	/**
	 * Consulta que retorna el valor total de las rentas
	 * 
	 * @param r
	 *            la renta
	 * @return el total o si esta vacia retorna 0
	 */
	public double totalPorPrestamo(Date fechUn, Date fechDos) {
		List<Double> lis = dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_TOTALRENTAS, fechUn, fechDos);
		if (lis.get(0) != null) {
			return lis.get(0);
		} else {
			return 0;
		}
	}

	/**
	 * Consulta que retorna el valor total de las ventas
	 * 
	 * @param s
	 *            las Ventas
	 * @return el total o si esta vacia retorna 0
	 */
	public double totalPorVentas(Date fechUno, Date fechaDos) {
		List<Double> lista = dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_TOTALVENTAS, fechUno, fechaDos);
		if (lista.get(0) != null) {
			return lista.get(0);
		} else {
			return 0;
		}
	}
}
