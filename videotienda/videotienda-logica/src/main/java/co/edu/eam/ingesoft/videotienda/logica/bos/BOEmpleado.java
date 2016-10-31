package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.City;
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
	
	
	/**
	 * Metodo que lista todos los empleados por tienda
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 29/10/2016<br/>
	 * @return
	 */
	public List<Staff> listarEmpleadosTienda(byte id){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_EMPLEADOS_TIENDA,id);
		
	}
	
	/**
	 * Permite crear un empleado 
	 * @param empleado
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void crearEmpleado(Staff empleado){
		super.crear(empleado);
	}
	
	/**
	 * Permite editar un empleado
	 * @param empleado
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void editarEmpleado(Staff empleado){
		super.editar(empleado);
	}
	
}
