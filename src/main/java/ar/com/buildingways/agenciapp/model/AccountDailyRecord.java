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
	
	public AccountDailyRecord(Account account, String game, Integer drawNumber, DateTime dueDate,
			double debt, double credit, double interest, String state, String currency, String type) {
		this.account = account;
		this.game = game;
		this.drawNumber = drawNumber;
		this.dueDate = dueDate;
		this.debt = debt;
		this.credit = credit;
		this.interest = interest;
		this.state = state;
		this.currency = currency;
		this.type = type;
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
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AccountDailyRecord)) return false;
        AccountDailyRecord accountDailyRecord = (AccountDailyRecord) o;
        return Objects.equals(getAccount().getUserId(), accountDailyRecord.getAccount().getUserId());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getAccount().getUserId());
    }

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
	    String NEW_LINE = System.getProperty("line.separator");

	    result.append(this.getClass().getName() + " LIQUIDACIONES {" + NEW_LINE);
	    result.append(" Tipo: " + this.getType() + NEW_LINE );
	    result.append(" Juego: " + this.getGame() + NEW_LINE );
	    result.append(" Nº sorteo: " + this.getDrawNumber() + NEW_LINE );
	    result.append(" Moneda: " + this.getCurrency() + NEW_LINE);
	    result.append(" Débitos: " + this.getDebt() + NEW_LINE);
	    result.append(" Créditos: " + this.getCredit() + NEW_LINE );
	    result.append(" Intereses: " + this.getInterest() + NEW_LINE );
	    result.append(" Fecha de vencimiento: " + this.getDueDate() + NEW_LINE );
	    result.append(" Estado: " + this.getState() + NEW_LINE );

	    result.append("}");

	    return result.toString();
	}
	
}