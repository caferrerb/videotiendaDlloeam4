/**
 * 
 */
package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.util.List;

import org.springframework.stereotype.Component;

import co.edu.eam.ingesoft.videotienda.persistencia.dao.ConstantesNamedQueries;
import co.edu.eam.ingesoft.videotienda.persistencia.entidades.Language;

/**
 * @author TOSHIBAP55W
 *
 */
@Component
public class BOLanguage  extends BOGenerico<Language>{

	
	public List<Language> listarLenguajes(){
		return dao.ejecutarNamedQuery(ConstantesNamedQueries.CONSULTA_LISTAR_LENGUAJES);
	}
	
}
