package Model.Entity;

import Model.Role;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Client class.
 */
@Entity
@Table(name = "CLIENTS")
@NamedQueries({
        @NamedQuery(name = "getClient",
                query = "SELECT c FROM Client c WHERE c.id = :id" ),
        @NamedQuery(name = "getAllClients",
                query = "SELECT c FROM Client c" )
})
public class Client implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "client")
    private List<Order> orders;

    /**
     * Creates default client.
     */
    public Client() {
        this.id = 0;
        this.username = "";
        this.email = "";
        this.password = "";
        this.role = Role.USER;
    }

    /**
     * Creates a client with set parameters.
     * @param id id
     * @param username username
     * @param email email
     * @param password password
     * @param role role
     */
    public Client(int id, String username, String email, String password, Role role)
    {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets client's id.
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Gets client's username.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets client's email.
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets client's orders.
     * @return list of orders
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * Gets client's password.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets client's role.
     * @return role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets client's id.
     * @param id new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Sets client's username.
     * @param username new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets client's email.
     * @param email new email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets client's orders.
     * @param orders new list of orders
     */
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    /**
     * Sets client's password.
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Sets client's role.
     * @param role new role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", orders=" + orders +
                '}';
    }
}
