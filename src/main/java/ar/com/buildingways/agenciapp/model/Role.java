package ar.com.buildingways.agenciapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Role {
	
	private int id;
	private String name;
	private String description;
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
}