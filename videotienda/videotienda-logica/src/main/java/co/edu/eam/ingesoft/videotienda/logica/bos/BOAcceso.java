package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Acceso;

@Component
public class BOAcceso extends BOGenerico<Acceso>{

	public List<Acceso> listar(){
		
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_PANTALLAS);
	}
	
}
