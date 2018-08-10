package ar.com.buildingways.agenciapp.model;

import ar.com.buildingways.agenciapp.model.audit.BaseAudit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ACCOUNTS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
		  		  property = "userId")
public class Account extends BaseAudit {
	
	private Long userId;
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

	public Account() {}
	
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
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
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

	    result.append("--- CUENTA ---" + NEW_LINE);
	    result.append("Nº de cuenta: " + this.getAccountNumber() + NEW_LINE);
	    result.append("Sucursal: " + this.getBranchNumber() + NEW_LINE);
	    result.append("Titular: " + this.getHolder() + NEW_LINE);
	    result.append("Adherido al débito: " + this.getDirectDebit() + NEW_LINE);
	    result.append("Tipo de cuenta: " + this.getAccountType() + NEW_LINE);
	    result.append("% retención IIBB: " + this.getGrossIncomePercentage() + NEW_LINE);
	    result.append("CBU: " + this.getCbu() + NEW_LINE);
	    result.append(this.getAccountDailyRecords().toString() + NEW_LINE);

	    return result.toString();
	}
}
