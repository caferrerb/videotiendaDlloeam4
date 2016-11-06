package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Acceso;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRolPK;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Usuario;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.UsuarioRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.UsuarioRolPK;

@Component
public class BOAccesoRol extends BOGenerico<AccesoRol> {

	public List<AccesoRol> listarPorRol(Rol rol) {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_ACCESOS_ROL, rol);
	}

	public List<AccesoRol> listar() {
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_TODO_ACCESOS_ROL);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(AccesoRol entidad) {
		Rol rol = dao.encontrarPorId(Rol.class,entidad.getRol().getId());
		Acceso acc = dao.encontrarPorId(Acceso.class, entidad.getAcceso().getId());
		entidad.setRol(rol);
		entidad.setAcceso(acc);
		super.crear(entidad);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void eliminar(int rol,int acceso){
		AccesoRolPK pk = new AccesoRolPK();
		pk.setRol(rol);
		pk.setAcceso(acceso);
		dao.eliminar(AccesoRol.class, pk);
	}
	
}
