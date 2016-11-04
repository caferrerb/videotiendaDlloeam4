
package co.edu.eam.ingesoft.videotienda.logica.bos;


import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;

@Component
public class BOInventario extends BOGenerico<Inventory> {

	/**
	 * Metodo que lista el inventario de la tienda
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 2/11/2016<br/>
	 * @return
	 */
	public List<Inventory> listarInventarioTienda(Store store){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_INVENTARIO_TIENDA,store);
	}
	

}
