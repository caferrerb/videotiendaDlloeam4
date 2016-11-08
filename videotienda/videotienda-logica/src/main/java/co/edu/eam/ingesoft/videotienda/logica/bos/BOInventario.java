
package co.edu.eam.ingesoft.videotienda.logica.bos;


import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;

@Component
public class BOInventario extends BOGenerico<Inventory> {


	public List<Inventory> listarInventarioTienda(Store store){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_INVENTARIO_TIENDA,store);
	}
	

}
