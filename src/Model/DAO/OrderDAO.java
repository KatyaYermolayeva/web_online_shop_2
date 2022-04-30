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
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Order DAO class.
 */
public class OrderDAO extends DAO {
    private static final Logger logger = LogManager.getLogger();

    /**
     * Creates order DAO.
     * @throws DAOException
     */
    public OrderDAO() throws DAOException {
        super();
    }

    /**
     * Gets order by id.
     * @param id id
     * @return order
     * @throws DAOException
     */
    public Order getOrder(int id) throws DAOException {
        Order order = null;
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> orderRoot = query.from(Order.class);
            query.where(cb.equal(orderRoot.get(Order_.id), id));
            TypedQuery<Order> typedQuery = entityManager.createQuery(query);
            order = typedQuery.getSingleResult();
        }
        catch (NoResultException e) {
            order = null;
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain order", e);
        }
        finally {
            entityManager.close();
        }
        return order;
    }

    /**
     * Gets all orders.
     * @return list of orders
     * @throws DAOException
     */
    public List<Order> getAllOrders() throws DAOException{
        List<Order> orders = new ArrayList<Order>();
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> orderRoot = query.from(Order.class);
            TypedQuery<Order> typedQuery = entityManager.createQuery(query);
            orders = typedQuery.getResultList();
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain orders", e);
        }
        finally {
            entityManager.close();
        }
        return orders;
    }

    /**
     * Gets all delayed orders.
     * @return list of orders
     * @throws DAOException
     */
    public List<Order> getAllDelayedOrders() throws DAOException{
        List<Order> orders = new ArrayList<Order>();
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            StatusDAO statusDAO = new StatusDAO();
            Status status = statusDAO.getStatus("awaiting delivery");
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> orderRoot = query.from(Order.class);
            query.where(cb.equal(orderRoot.get(Order_.status), status));
            Predicate deliveryDatePredicate = cb.lessThan(orderRoot.get(Order_.deliveryDate), Date.valueOf(LocalDate.now()));
            query.where(cb.and(deliveryDatePredicate));
            TypedQuery<Order> typedQuery = entityManager.createQuery(query);
            orders = typedQuery.getResultList();
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain orders", e);
        }
        finally {
            entityManager.close();
        }
        return orders;
    }

    /**
     * Gets all client's orders.
     * @param clientId client's id
     * @return list of orders
     * @throws DAOException
     */
    public List<Order> getAllOrdersByClient(int clientId) throws DAOException{
        List<Order> orders = new ArrayList<Order>();
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            ClientDAO clientDAO = new ClientDAO();
            Client client = clientDAO.getClient(clientId);
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> orderRoot = query.from(Order.class);
            query.where(cb.equal(orderRoot.get(Order_.client), client));
            TypedQuery<Order> typedQuery = entityManager.createQuery(query);
            orders = typedQuery.getResultList();
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain orders", e);
        }
        finally {
            entityManager.close();
        }
        return orders;
    }

    /**
     * Gets all orders of certain status.
     * @param statusId status' id
     * @return list of orders
     * @throws DAOException
     */
    public List<Order> getAllOrdersByStatus(int statusId) throws DAOException{
        List<Order> orders = new ArrayList<Order>();
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            StatusDAO statusDAO = new StatusDAO();
            Status status = statusDAO.getStatus(statusId);
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> orderRoot = query.from(Order.class);
            query.where(cb.equal(orderRoot.get(Order_.status), status));
            TypedQuery<Order> typedQuery = entityManager.createQuery(query);
            orders = typedQuery.getResultList();
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain orders", e);
        }
        finally {
            entityManager.close();
        }
        return orders;
    }

    /**
     * Gets all orders with certain delivery date.
     * @param deliveryDate delivery date
     * @return list of orders
     * @throws DAOException
     */
    public List<Order> getAllOrdersByDeliveryDate(Date deliveryDate) throws DAOException{
        List<Order> orders = new ArrayList<Order>();
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Order> query = cb.createQuery(Order.class);
            Root<Order> orderRoot = query.from(Order.class);
            query.where(cb.equal(orderRoot.get(Order_.deliveryDate), deliveryDate));
            TypedQuery<Order> typedQuery = entityManager.createQuery(query);
            orders = typedQuery.getResultList();
        }
        catch (Exception e) {
            throw new DAOException("Failed to obtain orders", e);
        }
        finally {
            entityManager.close();
        }
        return orders;
    }

    /**
     * Deletes order.
     * @param id order's id
     * @throws DAOException
     */
    public void deleteOrder(int id) throws DAOException{
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Order order = entityManager.find(Order.class, id);
            if (order.getStatus().getName().equals("completed")
                    || order.getStatus().getName().equals("cancelled")) {
                entityManager.remove(order);
            }
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to delete order", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Adds new order.
     * @param order new order
     * @throws DAOException
     */
    public void addOrder(Order order) throws DAOException{
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            order.setClient(entityManager.find(Client.class, order.getClient().getId()));
            order.setStatus(entityManager.find(Status.class, order.getStatus().getId()));
            entityManager.persist(order);
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to add order", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Completes order.
     * @param id order's id
     * @return true if successful
     * @throws DAOException
     */
    public boolean completeOrder(int id) throws DAOException {
        EntityManager entityManager = null;
        try{
            boolean result = false;
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Order order = getOrder(id);
            if (order.getStatus().getName().equals("delivered")) {
                result = true;
                StatusDAO statusDAO = new StatusDAO();
                Status status = statusDAO.getStatus("completed");
                order.setStatus(status);
                entityManager.merge(order);
            }
            entityTransaction.commit();
            return result;
        }
        catch (Exception e) {
            throw new DAOException("Failed to complete order", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Finalizes order.
     * @param id order's id
     * @return true if successful
     * @throws DAOException
     */
    public boolean finalizeOrder(int id) throws DAOException {
        EntityManager entityManager = null;
        try{
            boolean result = false;
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Order order = getOrder(id);
            if (order.getStatus().getName().equals("pending")) {
                result = true;
                StatusDAO statusDAO = new StatusDAO();
                Status status = statusDAO.getStatus("awaiting delivery");
                order.setStatus(status);
                entityManager.merge(order);
            }
            entityTransaction.commit();
            return result;
        }
        catch (Exception e) {
            throw new DAOException("Failed to finalize order", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Edits order's information
     * @param order new order's information
     * @throws DAOException
     */
    public void editOrder(Order order) throws DAOException {
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            Order realOrder = getOrder(order.getId());
            if (realOrder.getClient().equals(order.getClient())
                    && realOrder.getStatus().equals(order.getStatus())) {
                EntityTransaction entityTransaction = entityManager.getTransaction();
                entityTransaction.begin();
                entityManager.merge(order);
                entityTransaction.commit();
            }
            else throw new IllegalArgumentException("Illegal update");
        }
        catch (Exception e) {
            throw new DAOException("Failed to update order", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Adds product to order.
     * @param productId product's id
     * @param orderId order's id
     * @param amount amount
     * @throws DAOException
     */
    public void addProductToOrder(int productId, int orderId, int amount) throws DAOException {
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProduct(productId);
            Order order = getOrder(orderId);
            OrdersProducts ordersProducts = new OrdersProducts();
            ordersProducts.setAmount(amount);
            ordersProducts.setOrder(order);
            ordersProducts.setProduct(product);
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(ordersProducts);
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to add product to order", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Deletes product from order.
     * @param productId product's id
     * @param orderId order's id
     * @throws DAOException
     */
    public void deleteProductFromOrder(int productId, int orderId) throws DAOException {
        EntityManager entityManager = null;
        try{
            entityManager = factory.createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            Order order = entityManager.find(Order.class, orderId);
            List<OrdersProducts> products = order.getProducts();
            products = products.stream().filter(p -> p.getProductId() != productId).collect(Collectors.toList());
            order.setProducts(products);
            entityTransaction.commit();
        }
        catch (Exception e) {
            throw new DAOException("Failed to delete product from order", e);
        }
        finally {
            entityManager.close();
        }
    }

    /**
     * Checks if order is pending.
     * @param id order's id
     * @return true if order is pending
     * @throws DAOException
     */
    public boolean isPending(int id) throws DAOException {
        Order order = getOrder(id);
        if (order == null) {
            throw new DAOException("Order not found");
        }
        return order.getStatus().getName().equals("pending");
    }

    /**
     * Checks if order is delivered.
     * @param id order's id
     * @return true if order is delivered
     * @throws DAOException
     */
    public boolean isDelivered(int id) throws DAOException {
        Order order = getOrder(id);
        if (order == null) {
            throw new DAOException("Order not found");
        }
        return order.getStatus().getName().equals("delivered");
    }
}
