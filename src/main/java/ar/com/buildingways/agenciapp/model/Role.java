package ar.com.buildingways.agenciapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ROLES")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
		  		  property = "id")
public class Role {
	
	private Long id;
	private String name;
	private String description;
	@JsonBackReference
	private Set<User> users = new HashSet<User>(0);
	
	public Role() {}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false, columnDefinition = "numeric(8)")
	public Long getId() {
		return id;
	}	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="name", columnDefinition = "varchar(15)")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="description", columnDefinition = "varchar(50)")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToMany(mappedBy = "roles")
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");

	    result.append("--- ROL ---" + NEW_LINE);
	    result.append("Nombre: " + this.getName() + NEW_LINE);
	    result.append("Descripción: " + this.getDescription() + NEW_LINE);
	    String users = " Usuarios con este rol: ";
	    for (User user : this.getUsers()) {
			users+=user.getUsername();
			result.append(", ");
		}
	    users = users.substring(0, users.length() - 2);
	    result.append(users + NEW_LINE);

	    return result.toString();
	}
}