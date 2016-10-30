package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;

@Component
public class BOEmpleado extends BOGenerico<Staff>{

	/**
	 * Metodo que lista todos los empleados
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 27/10/2016<br/>
	 * @return
	 */
	public List<Staff> listarEmpleados(){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAREMPLEADOS);
	}
	
	
	
	
}
