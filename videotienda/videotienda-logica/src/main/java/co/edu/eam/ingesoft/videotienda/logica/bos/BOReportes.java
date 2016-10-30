package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
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
	
	
}
