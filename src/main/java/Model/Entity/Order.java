package Model.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Order class.
 */
@Entity
@Table(name = "ORDERS")
@NamedQueries({
        @NamedQuery(name = "getOrder",
                query = "SELECT o FROM Order o WHERE o.id = :id" ),
        @NamedQuery(name = "getAllOrders",
                query = "SELECT o FROM Order o" ),
        @NamedQuery(name = "getAllDelayedOrders",
                query = "SELECT o FROM Order o " +
                        "WHERE o.deliveryDate <= :deliveryDate AND o.status = " +
                        "(SELECT s FROM Status s WHERE s.name = 'awaiting delivery')" ),
        @NamedQuery(name = "getAllClientsOrders",
                query = "SELECT o FROM Order o WHERE o.client = :client" ),
        @NamedQuery(name = "getAllOrdersByStatus",
                query = "SELECT o FROM Order o WHERE o.status = :status" ),
        @NamedQuery(name = "getAllOrdersByDate",
                query = "SELECT o FROM Order o WHERE o.deliveryDate = :deliveryDate" )
})
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    @Column(name = "DELIVERY_DATE", nullable = false)
    private Date deliveryDate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "STATUS_ID", nullable = false)
    private Status status;

    @OneToMany(mappedBy = "order", orphanRemoval = true)
    private List<OrdersProducts> products;

    /**
     * Creates default order.
     */
    public Order() {
        this.id = 0;
        this.deliveryDate = Date.valueOf("2000-01-01");
    }

    /**
     * Creates order with set parameters.
     * @param id id
     * @param client client
     * @param deliveryDate delivery date
     * @param status status
     */
    public Order(int id, Client client, Date deliveryDate, Status status) {
        this.id = id;
        this.client = client;
        this.deliveryDate = deliveryDate;
        this.status = status;
    }

    /**
     * Gets order'd id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets order's client.
     * @return client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Gets order's delivery date.
     * @return delivery date
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Gets order's status.
     * @return status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Gets order's products.
     * @return list of products
     */
    public List<OrdersProducts> getProducts() {
        return products;
    }

    /**
     * Sets order's id.
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets order's client.
     * @param client new client
     */
    public void setClient(Client client) {
        this.client = client;
    }

    /**
     * Sets order's delivery date.
     * @param deliveryDate new delivery date
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * Sets order's status.
     * @param status new status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Sets order's products.
     * @param products list of products
     */
    public void setProducts(List<OrdersProducts> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", deliveryDate=" + deliveryDate +
                ", status=" + status +
                ", products=" + products +
                '}';
    }
}
