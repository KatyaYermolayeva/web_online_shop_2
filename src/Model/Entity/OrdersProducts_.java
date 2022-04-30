package Model.Entity;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(OrdersProducts.class)
public class OrdersProducts_ {
    public static volatile SingularAttribute<OrdersProducts, OrdersProductsKey> id;
    public static volatile SingularAttribute<OrdersProducts, Integer> amount;
    public static volatile SingularAttribute<OrdersProducts, Order> order;
    public static volatile SingularAttribute<OrdersProducts, Product> product;
}
