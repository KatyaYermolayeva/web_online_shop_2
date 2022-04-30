package Model.Entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrdersProductsKey implements Serializable {
    @Column(name = "ORDER_ID")
    private int orderId;

    @Column(name = "PRODUCT_ID")
    private int productId;

    /**
     * Gets product's id.
     * @return product's id
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Gets order's id.
     * @return order's id
     */
    public int getOrderId() {
        return orderId;
    }
}
