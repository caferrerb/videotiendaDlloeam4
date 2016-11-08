package co.edu.eam.ingesoft.videotienda.logica.bos;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.eam.ingesoft.videotienda.logica.excepciones.ExcepcionNegocio;
import co.edu.eam.ingesoft.videotienda.persistencia.dao.DAOGenerico;

/**
 * 
 * BO Generico de l cual deben heredar los demas.<br>
 * 
 * @author Camilo Andres Ferrer Bustos<br>
 * 
 * 
 * @date 15/10/2016
 * @version 1.0
 * @param <T>
 */
public class BOGenerico<T> {

	/**
	 * Entitymanager.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * DAO Generico.
	 */
	protected DAOGenerico dao;

	/**
	 * 
	 * Método que inicializa el BO <br>
	 * 
	 * @author Camilo Andres Ferrer Bustos<br>
	 *         caferrerb@gmail.com<br>
	 * 
	 * @date 15/10/2016
	 * @version 1.0
	 *
	 */
	@PostConstruct
	public void init() {
		System.out.println("iniciando el BO"+entityManager);
		dao = new DAOGenerico(entityManager);
	}

	/**
	 * 
	 * Método que crea una entidad <br>
	 * 
	 * @author Camilo Andres Ferrer Bustos<br>
	 *         caferrerb@gmail.com<br>
	 * 
	 * @date 15/10/2016
	 * @version 1.0
	 *
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void crear(T entidad){
		dao.persistir(entidad);
	}

	/**
	 * 
	 * Método que edita una entidad <br>
	 * 
	 * @author Camilo Andres Ferrer Bustos<br>
	 *         caferrerb@gmail.com<br>
	 * 
	 * @date 15/10/2016
	 * @version 1.0
	 *
	 */
	@Transactional(propagation = Propagation.REQUIRED)
	public void editar(T entidad) {
		dao.actualizar(entidad);
	}

	/**
	 * 
	 * Método que crea una entidad <br>
	 * 
	 * @author Camilo Andres Ferrer Bustos<br>
	 *         caferrerb@gmail.com<br>
	 * 
	 * @date 15/10/2016
	 * @version 1.0
	 *
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public void eliminar(Object pk) {
		dao.eliminar(getClase(), pk);
	}
	
	/**
	 * 
	 * Método que busca una entidad <br>
	 * 
	 * @author Camilo Andres Ferrer Bustos<br>
	 *         caferrerb@gmail.com<br>
	 * 
	 * @date 15/10/2016
	 * @version 1.0
	 *
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED)
	public T buscar(Object pk) {
		return dao.encontrarPorId(getClase(), pk);
	}
	/**
	 * 
	 * Método que listar todos los regiustrode  una entidad <br>
	 * 
	 * @author Camilo Andres Ferrer Bustos<br>
	 *         caferrerb@gmail.com<br>
	 * 
	 * @date 15/10/2016
	 * @version 1.0
	 *
	 */
	public List<T> listarTodos(){
		return dao.ejecutarQuery("SELECT o FROM "+getClase().getSimpleName()+" o",null);
	}

	/**
	 * 
	 * Método que obtiene la clase del BO <br>
	 * 
	 * @author Camilo Andres Ferrer Bustos<br>
	 *         caferrerb@gmail.com<br>
	 * 
	 * @date 15/10/2016
	 * @version 1.0
	 *
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getClase() {
		Class<T> persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		
		return persistentClass;
	}

}
