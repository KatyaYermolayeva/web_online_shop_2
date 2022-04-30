package Model.Entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDERS_PRODUCTS")
@NamedQueries({
        @NamedQuery(name = "getAllOrdersProducts",
                query = "SELECT op FROM OrdersProducts op WHERE op.order.id = :id" )
})
@NamedNativeQueries({
        @NamedNativeQuery(name = "addOrdersProduct",
                query = "INSERT INTO ORDERS_PRODUCTS(ORDER_ID, PRODUCT_ID, AMOUNT)" +
                        " VALUES(?, ?, ?)"),
        @NamedNativeQuery(name = "deleteOrdersProduct",
                query = "DELETE FROM ORDERS_PRODUCTS " +
                        "WHERE ORDER_ID = ? AND PRODUCT_ID = ?" )
})
public class OrdersProducts {
    @EmbeddedId
    private OrdersProductsKey id;

    @Column(name = "AMOUNT")
    private int amount;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;


    /**
     * Gets amount.
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Gets product's id.
     * @return product's id
     */
    public int getProductId() {
        return id.getProductId();
    }

    /**
     * Gets order's id.
     * @return order's id
     */
    public int getOrderId() {
        return id.getOrderId();
    }

    /**
     * Gets order's product.
     * @return product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Gets order.
     * @return order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets amount.
     * @param amount amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Sets order.
     * @param order order
     */
    public void setOrder(Order order) {
        this.order = order;
    }

    /**
     * Sets product.
     * @param product product
     */
    public void setProduct(Product product) {
        this.product = product;
    }
}
