package Model.DAO;

import Model.Entity.*;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Product DAO class.
 */
public class ProductDAO extends DAO {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Creates product DAO.
     * @throws DAOException
     */
    public ProductDAO() throws DAOException {
        super();
    }

    /**
     * Gets product by id.
     * @param id id
     * @return product
     * @throws DAOException
     */
    public Product getProduct(int id) throws DAOException {
        Product product = null;
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> query = cb.createQuery(Product.class);
            Root<Product> productRoot = query.from(Product.class);
            query.where(cb.equal(productRoot.get(Product_.id), id));
            TypedQuery<Product> typedQuery = entityManager.createQuery(query);
            product = typedQuery.getSingleResult();
        }
        catch (NoResultException e) {
            product = null;
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain product", e);
        }
        finally {
            entityManager.close();
        }
        return product;
    }

    /**
     * Gets all products.
     * @return list of products
     * @throws DAOException
     */
    public List<Product> getAllProducts() throws DAOException{
        List<Product> products = new ArrayList<Product>();
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Product> query = cb.createQuery(Product.class);
            Root<Product> productRoot = query.from(Product.class);
            TypedQuery<Product> typedQuery = entityManager.createQuery(query);
            products = typedQuery.getResultList();
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain products", e);
        }
        finally {
            entityManager.close();
        }
        return products;
    }

    /**
     * Gets all order's products.
     * @param orderId order's id
     * @return list of products with amounts
     * @throws DAOException
     */
    public Map<Product, Integer> getAllProductsByOrder(int orderId) throws DAOException{
        Map<Product, Integer> products = new HashMap<Product, Integer>();
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            OrderDAO orderDAO = new OrderDAO();
            Order order = orderDAO.getOrder(orderId);
            CriteriaQuery<OrdersProducts> query = cb.createQuery(OrdersProducts.class);
            Root<OrdersProducts> ordersProductsRoot = query.from(OrdersProducts.class);
            query.where(cb.equal(ordersProductsRoot.get(OrdersProducts_.order), order));
            TypedQuery<OrdersProducts> typedQuery = entityManager.createQuery(query);
            List<OrdersProducts> ordersProducts = typedQuery.getResultList();
            for (OrdersProducts op : ordersProducts) {
                products.put(op.getProduct(), op.getAmount());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new DAOException("Failed to obtain order's products", e);
        }
        finally {
            entityManager.close();
        }
        return products;
    }

    /**
     * Deletes product.
     * @param id product's id
     * @throws DAOException
     */
    public void deleteProduct(int id) throws DAOException{
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Product product = entityManager.find(Product.class, id);
            entityManager.remove(product);
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to delete product", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Adds new product.
     * @param product new product
     * @throws DAOException
     */
    public void addProduct(Product product) throws DAOException{
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(product);
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to add product", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Edits product's information.
     * @param product new product's information
     * @throws DAOException
     */
    public void editProduct(Product product) throws DAOException {
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(product);
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to update product", e);
        }
        finally {
            entityManager.close();
        }
    }
}
