package Model.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Status class.
 */
@Entity
@Table(name = "STATUSES")
@NamedQueries({
        @NamedQuery(name = "getStatus",
                query = "SELECT s FROM Status s WHERE s.id = :id" ),
        @NamedQuery(name = "getStatusByName",
                query = "SELECT s FROM Status s WHERE s.name = :name" ),
        @NamedQuery(name = "getAllStatuses",
                query = "SELECT s FROM Status s" ),
        @NamedQuery(name = "getOrdersStatus",
                query = "SELECT s FROM Status s WHERE s.id =" +
                        "(SELECT o.status.id FROM Order o WHERE o.id = :id)" )
})

public class Status implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "status")
    private List<Order> orders;

    /**
     * Creates default status.
     */
    public Status() {
        this.id = 0;
        this.name = "";
    }

    /**
     * Creates a status with set parameters.
     * @param id id
     * @param name name
     */
    public Status(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets status' id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets status' name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets status' orders.
     * @return list of orders
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Sets status' id.
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets status' name.
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets status' orders.
     * @param orders
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
