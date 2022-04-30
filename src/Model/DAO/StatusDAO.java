package Model.DAO;

import Model.Entity.Status;
import Model.Entity.Status_;
import Model.Exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Status DAO class.
 */
public class StatusDAO extends DAO {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Creates status DAO.
     * @throws DAOException
     */
    public StatusDAO() throws DAOException {
        super();
    }

    /**
     * Gets status by id.
     * @param id id
     * @return status
     * @throws DAOException
     */
    public Status getStatus(int id) throws DAOException {
        Status status = null;
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Status> query = cb.createQuery(Status.class);
            Root<Status> statusRoot = query.from(Status.class);
            query.where(cb.equal(statusRoot.get(Status_.id), id));
            TypedQuery<Status> typedQuery = entityManager.createQuery(query);
            status = typedQuery.getSingleResult();
        }
        catch (NoResultException e) {
            status = null;
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain status", e);
        }
        finally {
            entityManager.close();
        }
        return status;
    }

    /**
     * Gets status by name.
     * @param name name
     * @return status
     * @throws DAOException
     */
    public Status getStatus(String name) throws DAOException {
        Status status = null;
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Status> query = cb.createQuery(Status.class);
            Root<Status> statusRoot = query.from(Status.class);
            query.where(cb.equal(statusRoot.get(Status_.name), name));
            TypedQuery<Status> typedQuery = entityManager.createQuery(query);
            status = typedQuery.getSingleResult();
        }
        catch (NoResultException e) {
            status = null;
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain status", e);
        }
        finally {
            entityManager.close();
        }
        return status;
    }

    /**
     * Gets all available statuses.
     * @return list of statuses
     * @throws DAOException
     */
    public List<Status> getAllStatuses() throws DAOException{
        List<Status> statuses = new ArrayList<Status>();
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Status> query = cb.createQuery(Status.class);
            Root<Status> statusRoot = query.from(Status.class);
            TypedQuery<Status> typedQuery = entityManager.createQuery(query);
            statuses = typedQuery.getResultList();
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain statuses", e);
        }
        finally {
            entityManager.close();
        }
        return statuses;
    }

    /**
     * Deletes status.
     * @param id status' id
     * @throws DAOException
     */
    public void deleteStatus(int id) throws DAOException{
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Status status = entityManager.find(Status.class, id);
            entityManager.remove(status);
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to delete status", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Adds new status.
     * @param status new status
     * @throws DAOException
     */
    public void addStatus(Status status) throws DAOException{
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(status);
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to add status", e);
        }
        finally {
            entityManager.close();
        }
    }
}
