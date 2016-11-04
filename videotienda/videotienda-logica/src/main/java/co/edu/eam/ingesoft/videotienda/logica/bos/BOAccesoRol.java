package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.AccesoRol;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Rol;

@Component
public class BOAccesoRol extends BOGenerico<AccesoRol>{

public List<AccesoRol> listarPorRol(Rol rol){
	
	return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_ACCESOS_ROL,rol);
}


public List<AccesoRol> listar(){
	
	return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_TODO_ACCESOS_ROL);
}


}
