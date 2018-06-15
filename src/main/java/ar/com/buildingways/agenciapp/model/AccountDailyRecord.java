package ar.com.buildingways.agenciapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "ACCOUNT_DAILY_RECORDS")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
  				  property = "id")
public class AccountDailyRecord {
 
	private Long id;
	@JsonManagedReference
	private Account account;
	private String currency;
	private double debt;
	private double credit;
	private double interest;
	private DateTime dueDate;
	private String state;
	private String game;
	private int drawNumber;
	private String type;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "numeric(10)")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(cascade = CascadeType.ALL) 
	@JoinColumn(name = "account_id", nullable = false, columnDefinition = "numeric(8)")
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	@Column(name = "currency", columnDefinition = "varchar(10)")
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@Column(name = "debt", nullable = true, columnDefinition = "decimal(8,2)")
	public double getDebt() {
		return debt;
	}
	public void setDebt(double debt) {
		this.debt = debt;
	}
	
	@Column(name = "credit", nullable = true, columnDefinition = "decimal(8,2)")
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	@Column(name = "interest", nullable = true, columnDefinition = "decimal(8,2)")
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	
	@Column(name = "due_date", nullable = true, columnDefinition = "datetime")
	public DateTime getDueDate() {
		return dueDate;
	}
	public void setDueDate(DateTime dueDate) {
		this.dueDate = dueDate;
	}
	
	@Column(name = "state", columnDefinition = "varchar(30)")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "game", columnDefinition = "varchar(20)")
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	
	@Column(name = "draw_number", columnDefinition = "numeric(6)")
	public int getDrawNumber() {
		return drawNumber;
	}
	public void setDrawNumber(int drawNumber) {
		this.drawNumber = drawNumber;
	}
	
	@Column(name = "type", columnDefinition = "varchar(30)")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}