package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.UsuarioRol;

@Component
public class BOUsuarioRol extends BOGenerico<UsuarioRol>{

	/**
	 * Metodo que lista los usuarios de un rol determinado
	 * @author Richard Vanegas<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @return
	 */
	public List<Rol> listarUsuarioPorRol(int rol){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_USUARIO_POR_ROL,rol);
		
	}
	
}
