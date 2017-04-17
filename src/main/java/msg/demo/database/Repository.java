package msg.demo.database;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.sql.DataSource;

import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 * Repository class for encapsulating database operations.
 * 
 * @author Serban Petrescu
 *
 */
public class Repository {

	private final EntityManagerFactory emf;

	public Repository() {
		EntityManagerFactory emf;
		try {
			InitialContext ctx = new InitialContext();
			Map<String, Object> properties = new HashMap<>();
			properties.put(PersistenceUnitProperties.NON_JTA_DATASOURCE,
					(DataSource) ctx.lookup("java:comp/env/jdbc/DefaultDB"));
			emf = Persistence.createEntityManagerFactory("demo", properties);
		} catch (NamingException e) {
			emf = null;
		}
		this.emf = emf;
	}

	/**
	 * Retrieves all entities of the given type from the database.
	 * 
	 * @param clazz
	 *            The type of the entities.
	 * @return The complete list of entities.
	 */
	public <T> List<T> selectAll(Class<T> clazz) {
		EntityManager em = emf.createEntityManager();
		CriteriaQuery<T> criteria = em.getCriteriaBuilder().createQuery(clazz);
		criteria.select(criteria.from(clazz));
		List<T> result = em.createQuery(criteria).getResultList();
		em.close();
		return result;
	}

	/**
	 * Creates a new entity in the database.
	 * 
	 * @param entity
	 *            The entity which should be created.
	 */
	public void create(Object entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(entity);
		em.getTransaction().commit();
		em.close();
	}

}
