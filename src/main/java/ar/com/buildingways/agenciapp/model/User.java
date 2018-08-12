package ar.com.buildingways.agenciapp.model;

import ar.com.buildingways.agenciapp.model.audit.BaseAudit;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.NaturalId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "USERS",
        uniqueConstraints = {@UniqueConstraint(columnNames = "id"),
                @UniqueConstraint(columnNames = "username")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class User extends BaseAudit {

    private Long id;
    @NaturalId
    private Long username;
    private String password;
    @JsonManagedReference
    private Set<Role> roles;
    private Store store;
    private Account account;

    public User() {
    }

    public User(Long username, String createdBy, DateTime createdDate) {
        this.username = username;
        this.createdBy = createdBy;
        this.createdDate = createdDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, columnDefinition = "numeric(8)")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "username", unique = true, nullable = false, columnDefinition = "numeric(8)")
    public Long getUsername() {
        return username;
    }

    public void setUsername(Long username) {
        this.username = username;
    }

    @Column(name = "password", columnDefinition = "varchar(60)")
    @Transient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(getUsername(), user.getUsername());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append("********** USUARIO **********" + NEW_LINE);
        result.append("Nombre de usuario (legajo): " + this.getUsername() + NEW_LINE);
        result.append("Roles: " + this.getRoles().toString() + NEW_LINE);
        result.append("Creado por: " + this.getCreatedBy() + NEW_LINE);
        result.append("Fecha de creación: " + this.getCreatedDate().toString("dd/MM/yyyy HH:mm:ss") + NEW_LINE);
        result.append("Modificado por: " + this.getLastModifiedBy() + NEW_LINE);
        result.append("Fecha de modificación: " + this.getLastModifiedDate().toString("dd/MM/yyyy HH:mm:ss") + NEW_LINE);
        result.append("Habilitado: " + this.isEnabled() + NEW_LINE);
        result.append("Eliminado: " + this.isDeleted() + NEW_LINE);
        result.append(this.getStore().toString() + NEW_LINE);
        result.append(this.getAccount().toString() + NEW_LINE);

        return result.toString();
    }

}