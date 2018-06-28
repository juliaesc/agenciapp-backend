package ar.com.buildingways.agenciapp.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.NaturalId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "USERS",
	   uniqueConstraints = { @UniqueConstraint(columnNames = "id"),
			   				 @UniqueConstraint(columnNames = "username")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
		  		  property = "id")
public class User {
	
	private int id;
	@NaturalId
	private int username;
	private String password;
	@JsonManagedReference
	private Role role;
	private UserDetails userDetails;
	private Account account;
	private String createdBy;
	private DateTime createdDate;
	private String lastModifiedBy;
	private DateTime lastModifiedDate;
	private boolean enabled;
	private boolean deleted;
	
	public User() {}
	
	public User(int username, String createdBy, DateTime createdDate) {
		this.username = username;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "numeric(8)")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "username", unique = true, nullable = false, columnDefinition = "numeric(8)")
	public int getUsername() {
		return username;
	}
	public void setUsername(int username) {
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

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "role_id", nullable = false, columnDefinition = "numeric(8)")
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	@OneToOne(cascade=CascadeType.ALL, mappedBy="user")
    public UserDetails getUserDetails(){  
        return userDetails;  
    }  
    public void setUserDetails(UserDetails userDetails){  
        this.userDetails = userDetails;  
    }
    
    @OneToOne(cascade=CascadeType.ALL, mappedBy="user")
    public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Column(name = "created_by", columnDefinition = "varchar(20)")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "created_date", columnDefinition = "datetime")
	public DateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "last_modified_by", nullable = true, columnDefinition = "varchar(20)")
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	@Column(name = "last_modified_date", nullable = true, columnDefinition = "datetime")
	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Column(name = "enabled", columnDefinition = "tinyint")
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Column(name = "deleted", nullable = true, columnDefinition = "tinyint")
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
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

	    result.append(this.getClass().getName() + " USUARIO {" + NEW_LINE);
	    result.append(" Nommbre de usuario (legajo): " + this.getUsername() + NEW_LINE);
	    result.append(" Rol: " + this.getRole().getName() + NEW_LINE);
	    result.append(" Detalles: " + this.getUserDetails().toString() + NEW_LINE );
	    result.append(" Cuenta: " + this.getAccount().toString() + NEW_LINE);
	    result.append(" Creado por: " + this.getCreatedBy() + NEW_LINE);
	    result.append(" Fecha de creación: " + this.getCreatedDate() + NEW_LINE);
	    result.append(" Modificado por: " + this.getLastModifiedBy() + NEW_LINE);
	    result.append(" Fecha de modificación: " + this.getLastModifiedDate() + NEW_LINE);
	    result.append(" Habilitado: " + this.isEnabled() + NEW_LINE);
	    result.append(" Eliminado: " + this.isDeleted() + NEW_LINE);
	    result.append("}");

	    return result.toString();
	}
	
}