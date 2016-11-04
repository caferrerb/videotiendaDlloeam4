package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;

@Component
public class BOUsuario extends BOGenerico<Usuario> {

	
<<<<<<< HEAD
//	public List<Usuario> buscarEntidad(Usuario usu){
//		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_VALIDAR_USUARIO,usu);
//	}
	
//	public Usuario buscarEntidad(Usuario usu){
//		return (Usuario) dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_VALIDAR_USUARIO,usu);
//	}
	
=======
	public List<Usuario> buscarEntidad(Usuario usu){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_VALIDAR_USUARIO,usu);
	}
>>>>>>> branch 'master' of https://github.com/caferrerb/videotiendaDlloeam4.git
}
