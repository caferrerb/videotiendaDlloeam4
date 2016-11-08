package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;

/**
 * Objeto de negocio para todas las operaciones asociadas al Usuario.
 *
 * @author Richard Alexander Vanegas Ochoa<br/>
 *         email: Richardvanegas8@gmail.com <br/>
 *         Fecha: 23/10/2015<br/>
 */
@Component
public class BOUsuario extends BOGenerico<Usuario> {
	
	/**
	 * Metodo que busca un usuario
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @param usu usuario a logearse
	 * @return
	 */
	public List<Usuario> buscarEntidad(Usuario usu){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_VALIDAR_USUARIO,usu);
	}
}
