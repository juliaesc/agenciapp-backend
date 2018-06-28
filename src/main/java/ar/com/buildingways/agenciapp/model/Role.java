package ar.com.buildingways.agenciapp.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ROLES")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
		  		  property = "id")
public class Role {
	
	private int id;
	private String name;
	private String description;
	@JsonBackReference
	private Set<User> users = new HashSet<User>(0);

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique = true, nullable = false, columnDefinition = "numeric(8)")
	public int getId() {
		return id;
	}	
	public void setId(int id) {
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
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
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

	    result.append(this.getClass().getName() + " ROLE {" + NEW_LINE);
	    result.append(" Nombre: " + this.getName() + NEW_LINE);
	    result.append(" Descripción: " + this.getDescription() + NEW_LINE);
	    String users = " Usuarios con este rol: ";
	    for (User user : this.getUsers()) {
			users+=user.getUsername();
			result.append(", ");
		}
	    users = users.substring(0, users.length() - 2);
	    result.append(users + NEW_LINE);
	    result.append("}");

	    return result.toString();
	}
}