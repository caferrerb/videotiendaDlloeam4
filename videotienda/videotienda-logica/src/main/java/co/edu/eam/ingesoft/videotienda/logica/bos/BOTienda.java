package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;

/**
 * Clase responsable de la logica de la tienda
 * @author Jhoan Sebastian Salazar Henao<br/>
 *         email: jsebastian48@hotmail.com <br/>
 *         Fecha: 27/10/2016<br/>
 */
@Component
public class BOTienda extends BOGenerico<Store>{

	/**
	 * Metodo que lista todas las tiendas
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 29/10/2016<br/>
	 * @return
	 */
	public List<Store> listarTiendas(){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_TIENDAS);
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(Store store){
		super.crear(store);
	}
	

}
