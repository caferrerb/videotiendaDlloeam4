package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.UsuarioRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.UsuarioRolPK;

@Component
public class BOUsuarioRol extends BOGenerico<UsuarioRol>{

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(UsuarioRol entidad) {
		Rol rol=dao.encontrarPorId(Rol.class, entidad.getRol().getId());
		Usuario us=dao.encontrarPorId(Usuario.class, entidad.getUsuario().getUsuario());
		entidad.setRol(rol);
		entidad.setUsuario(us);
		super.crear(entidad);
	}
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
	
	
	/**
	 * Metodo que lista todos los roles de un usuario determinado
	 * @author Richard Vanegas<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @return
	 */
	public List<Rol> listarRolesPorUsuario(String usuario){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_ROLES_POR_USUARIO,usuario);
		
	}
	
	public void eliminar(int rol,String usuario){
		UsuarioRolPK pk = new UsuarioRolPK();
		pk.setRol(rol);
		pk.setUsuario(usuario);
		dao.eliminar(UsuarioRol.class, pk);
	}
	
}
