package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRolPK;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.UsuarioRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.UsuarioRolPK;

/**
 * Objeto de negocio para todas las operaciones asociadas al UsuarioRol.
 *
 * @author Richard Alexander Vanegas Ochoa<br/>
 *         email: Richardvanegas8@gmail.com <br/>
 *         Fecha: 23/10/2015<br/>
 */
@Component
public class BOUsuarioRol extends BOGenerico<UsuarioRol> {

	/**
	 * Metodo que crea un usuario rol
	 *
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: Richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2015<br/>
	 * @param entidad,
	 *            UsuarioRol que se va a persistir
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(UsuarioRol entidad) {
		Rol rol = dao.encontrarPorId(Rol.class, entidad.getRol().getId());
		Usuario us = dao.encontrarPorId(Usuario.class, entidad.getUsuario().getUsuario());
		entidad.setRol(rol);
		entidad.setUsuario(us);
		UsuarioRolPK pk = new UsuarioRolPK(rol.getId(), us.getUsuario());
		UsuarioRol bus = dao.encontrarPorId(UsuarioRol.class, pk);
		if (bus != null) {
			throw new ExcepcionNegocio("Este usuario ya tiene este rol asignado");
		} else {
			super.crear(entidad);
		}
		super.crear(entidad);
	}

	/**
	 * Metodo que lista los usuarios de un rol determinado
	 * 
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @param rol,
	 *            rol por el cual que listaran los usuarios
	 * @return
	 */
	public List<Rol> listarUsuarioPorRol(int rol) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_USUARIO_POR_ROL, rol);

	}

	/**
	 * Metodo que lista todos los roles de un usuario determinado
	 * 
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @param usuario,
	 *            usuario por el cual se listaran los roles
	 * @return
	 */
	public List<Rol> listarRolesPorUsuario(String usuario) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_ROLES_POR_USUARIO, usuario);

	}

	/**
	 * Metodo que elimina un rol de un usuario
	 * 
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @param rol,una
	 *            de las primarias UsuarioRol a eliminar
	 * @param usuario,una
	 *            de las primarias del UsuarioRol a eliminar
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void eliminar(int rol, String usuario) {
		UsuarioRolPK pk = new UsuarioRolPK();
		pk.setRol(rol);
		pk.setUsuario(usuario);
		dao.eliminar(UsuarioRol.class, pk);
	}

}
