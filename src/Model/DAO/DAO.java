package Model.DAO;

import Model.Exception.DAOException;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * DAO class.
 */
public class DAO {
    private static final String ENTITY_MANAGER_FACTORY_NAME =
            "simpleFactory2";
    protected EntityManagerFactory factory;

    /**
     * Creates DAO.
     * @throws DAOException
     */
    public DAO() throws DAOException {
        try {
            factory = Persistence.createEntityManagerFactory(ENTITY_MANAGER_FACTORY_NAME);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            throw new DAOException("Can't create entity manager factory ", e);
        }
    }

    /**
     * Gets entity manager factory.
     * @return factory
     */
    public EntityManagerFactory getFactory() {
        return factory;
    }
}
