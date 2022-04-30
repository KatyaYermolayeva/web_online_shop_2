package Model.Entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import java.sql.Date;

@StaticMetamodel(Order.class)
public class Order_ {
    public static volatile SingularAttribute<Order, Integer> id;
    public static volatile SingularAttribute<Order, Client> client;
    public static volatile SingularAttribute<Order, Status> status;
    public static volatile SingularAttribute<Order, Date> deliveryDate;
    public static volatile ListAttribute<Order, Product> products;
}
