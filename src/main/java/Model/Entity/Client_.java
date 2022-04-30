package Model.Entity;

import Model.Role;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Client.class)
public class Client_ {
    public static volatile SingularAttribute<Client, Integer> id;
    public static volatile SingularAttribute<Client, String> username;
    public static volatile SingularAttribute<Client, String> email;
    public static volatile SingularAttribute<Client, String> password;
    public static volatile SingularAttribute<Client, Role> role;
    public static volatile ListAttribute<Client, Order> orders;
}
