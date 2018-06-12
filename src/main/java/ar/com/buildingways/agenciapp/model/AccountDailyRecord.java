package ar.com.buildingways.agenciapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

@Entity
@Table(name = "ACCOUNT_DAILY_RECORDS")
public class AccountDailyRecord {
 
	private Long id;
	private Account account;
	private String currency;
	private double debt;
	private double credit;
	private double interest;
	private DateTime dueDate;
	private String state;
	private String game;
	private int drawNumber;
	
	public AccountDailyRecord() {}
	
	public AccountDailyRecord(Account account, String currency, double debt, double credit, 
			double interest, DateTime dueDate, String state) {
		this.account = account;
		this.currency = currency;
		this.debt = debt;
		this.credit = credit;
		this.interest = interest;
		this.dueDate = dueDate;
		this.state = state;
	}
	
	public AccountDailyRecord(Account account, String currency, double debt, String game, int drawNumber,
			double interest, DateTime dueDate, String state) {
		this.account = account;
		this.currency = currency;
		this.debt = debt;
		this.game = game;
		this.drawNumber = drawNumber;
		this.interest = interest;
		this.dueDate = dueDate;
		this.state = state;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false, columnDefinition = "numeric(10)")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
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
	
	@Column(name = "debt", nullable = true, columnDefinition = "decimal(6,2)")
	public double getDebt() {
		return debt;
	}
	public void setDebt(double debt) {
		this.debt = debt;
	}
	
	@Column(name = "credit", nullable = true, columnDefinition = "decimal(6,2)")
	public double getCredit() {
		return credit;
	}
	public void setCredit(double credit) {
		this.credit = credit;
	}
	
	@Column(name = "interest", nullable = true, columnDefinition = "decimal(6,2)")
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
	
	@Column(name = "state", columnDefinition = "varchar(15)")
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@Column(name = "game", columnDefinition = "varchar(10)")
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
	
}