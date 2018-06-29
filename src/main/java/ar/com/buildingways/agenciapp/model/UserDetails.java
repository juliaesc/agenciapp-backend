package ar.com.buildingways.agenciapp.model;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;
import org.joda.time.DateTime;

@Entity
@Table(name = "USER_DETAILS")
public class UserDetails {

	private int userId;
	private User user;
	private String address;
	private String email;
	private String storeOwner;
	private String tradeName;
	private Long cuit;
	private int commissionAgent;
	private int terminalQuantity;
	private String createdBy;
	private DateTime createdDate;
	private String lastModifiedBy;
	private DateTime lastModifiedDate;
	private boolean enabled;
	private boolean deleted;

	public UserDetails() {}
	
 	public UserDetails(String address, String email, String storeOwner, String tradeName, long cuit, int commissionAgent,
			int terminalQuantity, String createdBy, DateTime createdDate, String lastModifiedBy, DateTime lastModifiedDate,
			boolean enabled, boolean deleted) {
 		this.address = address;
 		this.email = email;
 		this.storeOwner = storeOwner;
 		this.tradeName = tradeName;
 		this.cuit = cuit;
 		this.commissionAgent = commissionAgent;
 		this.terminalQuantity = terminalQuantity;
 		this.createdBy = createdBy;
 		this.createdDate = createdDate;
 		this.lastModifiedBy = lastModifiedBy;
 		this.lastModifiedDate = lastModifiedDate;
 		this.enabled = enabled;
 		this.deleted = deleted;
 	}

 	@Id  
    @GeneratedValue(generator="myGenerator")  
    @GenericGenerator(name="myGenerator", strategy="foreign", parameters=@Parameter(value="user", name = "property")) 
	@Column (name = "user_id", unique = true, nullable = false, columnDefinition = "numeric(8)")
	public int getUserId() {
		return this.userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)  
    @PrimaryKeyJoinColumn  
    public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "address", columnDefinition = "varchar(120)")
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name = "email", columnDefinition = "varchar(30)")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "store_owner", columnDefinition = "varchar(50)")
	public String getStoreOwner() {
		return storeOwner;
	}
	public void setStoreOwner(String storeOwner) {
		this.storeOwner = storeOwner;
	}
	
	@Column(name = "trade_name", nullable = true, columnDefinition = "varchar(80)")
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	
	@Column(name = "cuit", columnDefinition = "numeric(15)")
	public Long getCuit() {
		return cuit;
	}
	public void setCuit(Long cuit) {
		this.cuit = cuit;
	}
		
	@Column(name = "commission_agent", columnDefinition = "numeric(3)")
	public int getCommissionAgent() {
		return commissionAgent;
	}
	public void setCommissionAgent(int commissionAgent) {
		this.commissionAgent = commissionAgent;
	}
	
	@Column(name = "terminal_quantity", columnDefinition = "numeric(3)")
	public int getTerminalQuantity() {
		return terminalQuantity;
	}
	public void setTerminalQuantity(int terminalQuantity) {
		this.terminalQuantity = terminalQuantity;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetails)) return false;
        UserDetails userDetails = (UserDetails) o;
        return Objects.equals(getUserId(), userDetails.getUserId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");

	    result.append("--- DETALLES ---" + NEW_LINE);
	    result.append("Domicilio: " + this.getAddress() + NEW_LINE);
	    result.append("Email: " + this.getEmail() + NEW_LINE);
	    result.append("Razón social: " + this.getStoreOwner() + NEW_LINE );
	    result.append("Nombre de fantasía: " + this.getTradeName() + NEW_LINE);
	    result.append("CUIT: " + this.getCuit() + NEW_LINE);
	    result.append("Comisionista: " + this.getCommissionAgent() + NEW_LINE);
	    result.append("Cantidad de terminales: " + this.getTerminalQuantity());

	    return result.toString();
	}
}
