package ar.com.buildingways.agenciapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.joda.time.DateTime;

@Entity
@Table(name = "ACCOUNTS")
public class Account {
	
	private Long userId;
	private User user;
	private int accountNumber;
	private Set<AccountDailyRecord> accountDailyRecords = new HashSet<AccountDailyRecord>(0);
	private int branchNumber;
	private String holder;
	private char directDebit;
	private int accountType;
	private int grossIncomePercentage;
	private Long cbu;
	private String createdBy;
	private DateTime createdDate;
	private String lastModifiedBy;
	private DateTime lastModifiedDate;
	private boolean enabled;
	private boolean deleted;
	
    @Id  
    @GeneratedValue(generator="myGenerator")  
    @GenericGenerator(name="myGenerator", strategy="foreign", parameters=@Parameter(value="user", name = "property")) 
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	@OneToOne(cascade=CascadeType.ALL)  
    @JoinColumn(name="user_id")  
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	@Column(name = "account_number")
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "account")
	public Set<AccountDailyRecord> getAccountDailyRecords() {
		return accountDailyRecords;
	}
	public void setAccountDailyRecords(Set<AccountDailyRecord> accountDailyRecords) {
		this.accountDailyRecords = accountDailyRecords;
	}
	
	@Column(name = "branch_number")
	public int getBranchNumber() {
		return branchNumber;
	}
	public void setBranchNumber(int branchNumber) {
		this.branchNumber = branchNumber;
	}
	
	@Column(name = "holder")
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	
	@Column(name = "direct_debit")
	public char getDirectDebit() {
		return directDebit;
	}
	public void setDirectDebit(char directDebit) {
		this.directDebit = directDebit;
	}
	
	@Column(name = "account_type")
	public int getAccountType() {
		return accountType;
	}
	public void setAccountType(int accountType) {
		this.accountType = accountType;
	}
	
	@Column(name = "gross_income_percentage")
	public int getGrossIncomePercentage() {
		return grossIncomePercentage;
	}
	public void setGrossIncomePercentage(int grossIncomePercentage) {
		this.grossIncomePercentage = grossIncomePercentage;
	}
	
	@Column(name = "cbu")
	public Long getCbu() {
		return cbu;
	}
	public void setCbu(Long cbu) {
		this.cbu = cbu;
	}

	@Column(name = "created_by")
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	@Column(name = "created_date")
	public DateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	@Column(name = "last_modified_by")
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	@Column(name = "last_modified_date")
	public DateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@Column(name = "enabled")
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	@Column(name = "deleted")
	public boolean isDeleted() {
		return deleted;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
