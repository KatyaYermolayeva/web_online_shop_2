package Model.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Product class.
 */
@Entity
@Table(name = "PRODUCTS")
@NamedQueries({
        @NamedQuery(name = "getProduct",
                query = "SELECT p FROM Product p WHERE p.id = :id" ),
        @NamedQuery(name = "getAllProducts",
                query = "SELECT p FROM Product p" ),
})
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @OneToMany(mappedBy = "product")
    private List<OrdersProducts> orders;

    /**
     * Creates default product.
     */
    public Product() {
        this.id = 0;
        this.name = "";
        this.description = "";
        this.price = 0.0;
    }

    /**
     * Creates a product with set parameters.
     * @param id id
     * @param name name
     * @param description description
     * @param price price
     */
    public Product(int id, String name, String description, Double price)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Gets product's id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets product's name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets product's description.
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets product's price.
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Gets product's orders
     * @return list of orders
     */
    public List<OrdersProducts> getOrders() {
        return orders;
    }

    /**
     * Sets product's id.
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets product's name.
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets product's description.
     * @param description new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets product's price.
     * @param price new price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
