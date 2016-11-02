/**
 * @author Carlos Alfredo Martinez Villada
 * Bo encargado de la logica de negocio de Category (Genero).
 */
package co.edu.eam.ingesoft.videotienda.logica.bos;
import java.util.List;
import org.springframework.stereotype.Component;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Category;

@Component
public class BOGenero extends BOGenerico<Category>{
	/**
	 * Lista con todos los generos registrados en la bd
	 * @return lista con todos los generos
	 */
	public List<Category> listarGeneros(){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_GENEROS);
	}
}
