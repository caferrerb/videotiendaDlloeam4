package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRolPK;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;

/**
 * Objeto de negocio para todas las operaciones asociadas al AccesoRol.
 *
 * @author Richard Alexander Vanegas Ochoa<br/>
 *         email: Richardvanegas8@gmail.com <br/>
 *         Fecha: 23/10/2015<br/>
 */
@Component
public class BOAccesoRol extends BOGenerico<AccesoRol> {

	/**
	 * Metodo que lista todos los accesos
	 * 
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @param rol,
	 *            rol por el cual se listaran los accesos
	 * @return
	 */
	public List<AccesoRol> listarPorRol(Rol rol) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_ACCESOS_ROL, rol);
	}

	/**
	 * Metodo que lista todos los AccesoRol
	 * 
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @return
	 */
	public List<AccesoRol> listar() {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_TODO_ACCESOS_ROL);
	}

	/**
	 * Metodo que crea un accesoRol
	 * 
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @param entidad,
	 *            AccesoRol a persistir
	 * @return
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(AccesoRol entidad){
		Rol rol = dao.encontrarPorId(Rol.class, entidad.getRol().getId());
		Acceso acc = dao.encontrarPorId(Acceso.class, entidad.getAcceso().getId());
		entidad.setRol(rol);
		entidad.setAcceso(acc);
		AccesoRolPK pk = new AccesoRolPK(rol.getId(), acc.getId());
		AccesoRol bus = dao.encontrarPorId(AccesoRol.class, pk);
		if (bus != null) {
		throw new ExcepcionNegocio("Este rol ya tiene asignada esta pantalla");
		} else {
			super.crear(entidad);
		}
	}

	/**
	 * Metodo que lista todos los accesos
	 * 
	 * @author Richard Alexander Vanegas Ochoa<br/>
	 *         email: richardvanegas8@gmail.com <br/>
	 *         Fecha: 23/10/2016<br/>
	 * @param rol,rol
	 *            una de las primarias AccesoRol a eliminar
	 * @param acceso,acceso
	 *            una de las primarias del AccesoRol a eliminar
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void eliminar(int rol, int acceso) {
		AccesoRolPK pk = new AccesoRolPK();
		pk.setRol(rol);
		pk.setAcceso(acceso);
		dao.eliminar(AccesoRol.class, pk);
	}

}
