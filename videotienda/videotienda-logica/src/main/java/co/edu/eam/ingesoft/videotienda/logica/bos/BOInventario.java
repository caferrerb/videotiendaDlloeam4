<<<<<<< HEAD
/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Staff;

/**
 * @author XgioserX
 *
 */
public class BOInventario {

	
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
=======
package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Inventory;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Store;

@Component
public class BOInventario extends BOGenerico<Inventory> {

	/**
	 * Metodo que lista el inventario de la tienda
	 * @author Jhoan Sebastian Salazar Henao<br/>
	 *         email: jsebastian48@hotmail.com <br/>
	 *         Fecha: 2/11/2016<br/>
	 * @return
	 */
	public List<Inventory> listarInventarioTienda(Store store){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_INVENTARIO_TIENDA,store);
	}
	
>>>>>>> branch 'master' of https://github.com/caferrerb/videotiendaDlloeam4.git
}
