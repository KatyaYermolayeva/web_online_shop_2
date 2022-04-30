package Model.Entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Status.class)
public class Status_ {
    public static volatile SingularAttribute<Status, Integer> id;
    public static volatile SingularAttribute<Status, String> name;
    public static volatile ListAttribute<Status, Order> orders;
}
