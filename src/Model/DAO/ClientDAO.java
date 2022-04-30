package Model.DAO;

import Model.Entity.Client;
import Model.Entity.Client_;
import Model.Exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * Client DAO class.
 */
public class ClientDAO extends DAO {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Creates client DAO.
     * @throws DAOException
     */
    public ClientDAO() throws DAOException {
        super();
    }

    /**
     * Gets client by id.
     * @param id id
     * @return client
     * @throws DAOException
     */
    public Client getClient(int id) throws DAOException {
        Client client = null;
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Client> query = cb.createQuery(Client.class);
            Root<Client> clientRoot = query.from(Client.class);
            query.where(cb.equal(clientRoot.get(Client_.id), id));
            TypedQuery<Client> typedQuery = entityManager.createQuery(query);
            client = typedQuery.getSingleResult();
        }
        catch (NoResultException e) {
            client = null;
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain clients", e);
        }
        finally {
            entityManager.close();
        }
        return client;
    }

    /**
     * Gets client by username.
     * @param username username
     * @return client
     * @throws DAOException
     */
    public Client getClientByUsername(String username) throws DAOException {
        Client client = null;
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Client> query = cb.createQuery(Client.class);
            Root<Client> clientRoot = query.from(Client.class);
            query.where(cb.equal(clientRoot.get(Client_.username), username));
            TypedQuery<Client> typedQuery = entityManager.createQuery(query);
            client = typedQuery.getSingleResult();
        }
        catch (NoResultException e) {
            client = null;
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain clients", e);
        }
        finally {
            entityManager.close();
        }
        return client;
    }

    /**
     * Gets client by username and password.
     * @param username username
     * @param password password
     * @return client
     * @throws DAOException
     */
    public Client getClientByUsernameAndPassword(String username, String password) throws DAOException {
        Client client = null;
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Client> query = cb.createQuery(Client.class);
            Root<Client> clientRoot = query.from(Client.class);
            Predicate usernamePredicate = cb.equal(clientRoot.get(Client_.username), username);
            Predicate passwordPredicate = cb.equal(clientRoot.get(Client_.password), password);
            Predicate finalPredicate = cb.and(usernamePredicate, passwordPredicate);
            query.where(finalPredicate);
            TypedQuery<Client> typedQuery = entityManager.createQuery(query);
            client = typedQuery.getSingleResult();
        }
        catch (NoResultException e) {
            client = null;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Failed to obtain clients", e);
        }
        finally {
            entityManager.close();
        }
        return client;
    }

    /**
     * Gets all clients.
     * @return list of clients
     * @throws DAOException
     */
    public List<Client> getAllClients() throws DAOException{
        List<Client> clients = new ArrayList<Client>();
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Client> query = cb.createQuery(Client.class);
            Root<Client> clientRoot = query.from(Client.class);
            TypedQuery<Client> typedQuery = entityManager.createQuery(query);
            clients = typedQuery.getResultList();
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain clients", e);
        }
        finally {
            entityManager.close();
        }
        return clients;
    }

    /**
     * Deletes client.
     * @param id client's id
     * @throws DAOException
     */
    public void deleteClient(int id) throws DAOException{
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Client client = entityManager.find(Client.class, id);
            entityManager.remove(client);
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to delete client", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Adds new client.
     * @param client new client
     * @throws DAOException
     */
    public void addClient(Client client) throws DAOException{
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(client);
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to add client", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Edits client's information.
     * @param client new client's information
     * @throws DAOException
     */
    public void editClient(Client client) throws DAOException {
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(client);
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to update client", e);
        }
        finally {
            entityManager.close();
        }
    }
}
