package co.edu.eam.ingesoft.videotienda.persistencia.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

/**
 * 
 * Clase encargada de proveer los servicios básicos para la capa de
 * persistencia.
 * 
 * @author Camilo Andres Ferrer Bustos.
 */
@SuppressWarnings({ "unchecked" })
public class DAOGenerico implements Serializable {

	/**
	 * Constante de serialización.
	 */
	private static final long serialVersionUID = -3106939302290740868L;

	/**
	 * Entitymanager.
	 */

	private EntityManager entityManager;

	/**
	 * Usuario en sesion.
	 */
	private String usuario;

	/**
	 * Constructor de la clase.
	 * 
	 * @param entityManager
	 *            , manejador de entidades.
	 */
	public DAOGenerico(EntityManager entityManager) {
		super();
		this.entityManager = entityManager;
	}

	/**
	 * Constructor de la clase sin parametros.
	 * 
	 * @author <br>
	 * 
	 * @version 1.0
	 */
	public DAOGenerico() {

	}

	/**
	 * 
	 * Método encargado de persistir una instancia de la entidad <T> en el
	 * sistema.
	 * 
	 * @param instancia
	 *            a persistir
	 */
	public void persistir(Object instancia) {
		
		// Se almacena la entidad.
		entityManager.persist(instancia);

	}

	/**
	 * Método encargado de encontrar un registro mediante su id de
	 * 
	 * @param id
	 * @return instancia del registro con el identificador indicado
	 */
	public <T> T encontrarPorId(Class<T> clase, Object pk) {
		return entityManager.find(clase, pk);
	}

	/**
	 * 
	 * Método encargado de obtener la referencia para la entidad.
	 * 
	 * @param id
	 *            del registro para ser referenciado en bd
	 * @return referencia al objeto por medio de la instancia de la entidad
	 */
	public <T> T obtenerReferencia(Class<T> clase, Object id) {
		return entityManager.getReference(clase, id);
	}

	/**
	 * Método encargado de listar todos los registros de la entidad <T>.
	 * 
	 * @return listado con todos los registros
	 */
	public <T> List<T> listarTodos(Class<T> clase) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clase);
		criteriaQuery.from(clase);
		TypedQuery<T> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}

	/**
	 * Método encargado de eliminar un registro en la base de datos con el id
	 * especificado. la eliminacion es logica, ya que solo se desactiva la
	 * entidad.
	 * 
	 * @param id
	 *            del registro a ser destruido
	 */
	public <T extends Object> T eliminar(Class<T> clase, Object pk) {
		Object instance = encontrarPorId(clase, pk);
		if (instance != null) {
			eliminar(instance);

			return (T) instance;
		}
		return null;
	}

	/**
	 * Metodo para eliminar fisicamente una entidad.
	 * 
	 * 
	 * @author <br>
	 *         Camilo Andrés Ferrer Bustos <br>
	 *         Email: @.com<br>
	 * @date 28/04/2013 22:42:49
	 * @version 1.0
	 * 
	 * @param entidad
	 */
	public void eliminar(Object entidad) {
		entityManager.remove(entidad);
	}

	/**
	 * 
	 * Método encargado de realizar un update en el sistema con la entidad
	 * actualizada proporcionada.
	 * 
	 * @param updatedInstance
	 *            edidad con la información actualizada
	 * @return registro actualizado
	 */
	public <T> T actualizar(T updatedInstance) {

		

		T obj = entityManager.merge(updatedInstance);

		return obj;
	}

	/**
	 * Metodo para ejecutar una NamedQuery con parametros.
	 * 
	 * @author <br>
	 *         Camilo Andr�s Ferrer Bustos <br>
	 *         Email: @.com<br>
	 * @date 2/05/2013 10:00:13
	 * @version 1.0
	 * 
	 * @param nombreNamedQuery
	 *            , nombre de la NamedQuery.
	 * @param params
	 *            , parametros. @return, la lista de entidades.
	 */
	public <T> List<T> ejecutarNamedQuery(String nombreNamedQuery, Object... params) {
		Query q = entityManager.createNamedQuery(nombreNamedQuery);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				Object object = params[i];
				q.setParameter( (i + 1), object);

			}

		}
		return q.getResultList();

	}

	/**
	 * Metodo para ejecutar una NamedQuery con parametros y limite.
	 * 
	 * @author <br>
	 *         Camilo Andr�s Ferrer Bustos <br>
	 *         Email: @.com<br>
	 * @date 2/05/2013 10:00:13
	 * @version 1.0
	 * 
	 * @param nombreNamedQuery
	 *            , nombre de la NamedQuery.
	 * @param params
	 *            , parametros. @return, la lista de entidades.
	 */
	public Query queryFromNamedQuery(String nombreNamedQuery, Object... params) {
		Query q = entityManager.createNamedQuery(nombreNamedQuery);
		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				Object object = params[i];
				q.setParameter(i + 1, object);

			}

		}

		return q;

	}

	/**
	 * Metodo para ejecutar una NamedQuery con parametros y limite.
	 * 
	 * @author <br>
	 *         Camilo Andr�s Ferrer Bustos <br>
	 *         Email: @.com<br>
	 * @date 2/05/2013 10:00:13
	 * @version 1.0
	 * 
	 * @param nombreNamedQuery
	 *            , nombre de la NamedQuery.
	 * @param params
	 *            , parametros. @return, la lista de entidades.
	 * @param start,
	 *            inicio de la paginacion
	 * @param end,
	 *            fin de la paginacion
	 */
	public Query queryFromNamedQuery(String nombreNamedQuery, int start, int end, Object... params) {
		Query q = queryFromNamedQuery(nombreNamedQuery, params);

		q.setFirstResult(start);
		q.setMaxResults(end - start + 1);

		return q;

	}

	/**
	 * Metodo para ejecutar una NamedQuery con parametros y limite.
	 * 
	 * @author <br>
	 *         Camilo Andr�s Ferrer Bustos <br>
	 *         Email: @.com<br>
	 * @date 2/05/2013 10:00:13
	 * @version 1.0
	 * 
	 * @param nombreNamedQuery
	 *            , nombre de la NamedQuery.
	 * @param params
	 *            , parametros. @return, la lista de entidades.
	 * @param start,
	 *            inicio de la paginacion
	 * @param end,
	 *            fin de la paginacion
	 */
	public int countQueryFromNamedQuery(String nombreNamedQuery, Object... params) {
		Query q = queryFromNamedQuery(nombreNamedQuery, params);

		// TODO: corregir esto..............
		return q.getResultList().size();

	}

	/**
	 * Metodo para ejecutar una namedQuery.
	 * 
	 * @param nombreNamedQuery
	 * @param params
	 * @return
	 */
	public <T> List<T> ejecutarNamedQuery(String query, Map<String, Object> params) {

		Query q = entityManager.createNamedQuery(query);

		if (params != null) {
			for (Entry<String, Object> par : params.entrySet()) {
				q.setParameter(par.getKey(), par.getValue());
			}
		}

		List<T> resultList = q.getResultList();

		return resultList;
	}

	/**
	 * Metodo para ejecutar una Query.
	 * 
	 * @param nombreNamedQuery
	 * @param params
	 * @return
	 */
	public <T> List<T> ejecutarQuery(String query, Map<String, Object> params) {

		Query q = entityManager.createQuery(query);

		if (params != null) {
			for (Entry<String, Object> par : params.entrySet()) {
				q.setParameter(par.getKey(), par.getValue());
			}
		}

		List<T> resultList = q.getResultList();

		return resultList;
	}

	/**
	 * Metodo para ejecutar una Query Nativa.
	 * 
	 * @param nombreNamedQuery
	 * @param params
	 * @return
	 */
	public <T> List<T> ejecutarNativeQuery(String query, Object... params) {

		Query q = entityManager.createNativeQuery(query);

		if (params != null) {
			for (int i = 0; i < params.length; i++) {
				Object par = params[i];
				q.setParameter(i + 1, par);

			}
		}

		List<T> resultList = q.getResultList();

		return resultList;
	}

    /**
     * Metodo para ejecutar una namedQuery, usando paginacion.
     * 
     * @param queryName
     * @param start
     *            Primea fila a obtener, iniciando en 0
     * @param limit
     *            Cantidad de registros a obtener
     * @return
     */
    public <T> List<T> ejecutarNamedQuery(String queryName, int start, int limit) {

        Query q = entityManager.createNamedQuery(queryName);
        q.setFirstResult(start);
        q.setMaxResults(limit);

        List<T> resultList = q.getResultList();

        return resultList;
    }

	/**
	 * Forzar el envio a la BD del cambio.
	 * 
	 * 
	 * 
	 * @date 12/09/2013
	 * @version 1.0
	 * 
	 */
	public void flush() {
		entityManager.flush();
	}

	/**
	 * setter del atributo usuario.
	 * 
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
