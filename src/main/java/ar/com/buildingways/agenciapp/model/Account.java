package ar.com.buildingways.agenciapp.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ACCOUNTS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
		  		  property = "userId")
public class Account {
	
	private int userId;
	private User user;
	private int accountNumber;
	@JsonBackReference
	private Set<AccountDailyRecord> accountDailyRecords = new HashSet<AccountDailyRecord>(0);
	private int branchNumber;
	private String holder;
	private char directDebit;
	private String accountType;
	private double grossIncomePercentage;
	private String cbu;
	private String createdBy;
	private DateTime createdDate;
	private String lastModifiedBy;
	private DateTime lastModifiedDate;
	private boolean enabled;
	private boolean deleted;
	
	public Account() {}
	
	public Account(int userId, User user, int accountNumber, Set<AccountDailyRecord> accountDailyRecords,
			int branchNumber, String holder, char directDebit, String accountType, double grossIncomePercentage,
			String cbu, String createdBy, DateTime createdDate, String lastModifiedBy, DateTime lastModifiedDate,
			boolean enabled, boolean deleted) {
		super();
		this.userId = userId;
		this.user = user;
		this.accountNumber = accountNumber;
		this.accountDailyRecords = accountDailyRecords;
		this.branchNumber = branchNumber;
		this.holder = holder;
		this.directDebit = directDebit;
		this.accountType = accountType;
		this.grossIncomePercentage = grossIncomePercentage;
		this.cbu = cbu;
		this.createdBy = createdBy;
		this.createdDate = createdDate;
		this.lastModifiedBy = lastModifiedBy;
		this.lastModifiedDate = lastModifiedDate;
		this.enabled = enabled;
		this.deleted = deleted;
	}

	public Account(int accountNumber, int branchNumber, String holder, char directDebit, String accountType, double grossIncomePercentage,
			String cbu, String createdBy, DateTime createdDate, String lastModifiedBy,
			DateTime lastModifiedDate, boolean enabled, boolean deleted) {
 		this.accountNumber = accountNumber;
 		this.branchNumber = branchNumber;
 		this.holder = holder;
 		this.directDebit = directDebit;
 		this.accountType = accountType;
 		this.grossIncomePercentage = grossIncomePercentage;
 		this.cbu = cbu;
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
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	@OneToOne(cascade = CascadeType.ALL)  
    @PrimaryKeyJoinColumn  
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "account_number", columnDefinition = "numeric(15)")
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@OneToMany(mappedBy = "account")
	public Set<AccountDailyRecord> getAccountDailyRecords() {
		return accountDailyRecords;
	}
	public void setAccountDailyRecords(Set<AccountDailyRecord> accountDailyRecords) {
		this.accountDailyRecords = accountDailyRecords;
	}
	
	@Column(name = "branch_number", columnDefinition = "numeric(8)")
	public int getBranchNumber() {
		return branchNumber;
	}
	public void setBranchNumber(int branchNumber) {
		this.branchNumber = branchNumber;
	}
	
	@Column(name = "holder", columnDefinition = "varchar(50)")
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	
	@Column(name = "direct_debit", columnDefinition = "char(1)")
	public char getDirectDebit() {
		return directDebit;
	}
	public void setDirectDebit(char directDebit) {
		this.directDebit = directDebit;
	}
	
	@Column(name = "account_type", columnDefinition = "varchar(20)")
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	@Column(name = "gross_income_percentage", columnDefinition = "decimal(5,2)")
	public double getGrossIncomePercentage() {
		return grossIncomePercentage;
	}
	public void setGrossIncomePercentage(double grossIncomePercentage) {
		this.grossIncomePercentage = grossIncomePercentage;
	}
	
	@Column(name = "cbu", columnDefinition = "varchar(50)")
	public String getCbu() {
		return cbu;
	}
	public void setCbu(String cbu) {
		this.cbu = cbu;
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
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(getUserId(), account.getUserId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");

	    result.append(this.getClass().getName() + " CUENTA {" + NEW_LINE);
	    result.append(" Nº de cuenta: " + this.getAccountNumber() + NEW_LINE);
	    result.append(" Sucursal: " + this.getBranchNumber() + NEW_LINE);
	    result.append(" Titular: " + this.getHolder() + NEW_LINE );
	    result.append(" Adherido al débito: " + this.getDirectDebit() + NEW_LINE );
	    result.append(" Tipo de cuenta: " + this.getAccountType() + NEW_LINE );
	    result.append(" % retención IIBB: " + this.getGrossIncomePercentage() + NEW_LINE );
	    result.append(" CBU: " + this.getCbu() + NEW_LINE );
	    result.append(" Registros diarios de estado de cuenta: " + this.getAccountDailyRecords().toString() + NEW_LINE );
	    result.append("}");

	    return result.toString();
	}
}
