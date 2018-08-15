package ar.com.buildingways.agenciapp.model;

import ar.com.buildingways.agenciapp.model.audit.BaseAudit;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "STORES")
public class Store extends BaseAudit {

    private Long userId;
    private User user;
    private String address;
    private String tradeName;
    private Long cuit;
    private int commissionAgent;
    private int terminalQuantity;

    public Store() {
    }

    public Store(String address, User user, String tradeName, long cuit, int commissionAgent,
                 int terminalQuantity, String createdBy, DateTime createdDate, String lastModifiedBy, DateTime lastModifiedDate,
                 boolean enabled, boolean deleted) {
        this.address = address;
        this.user = user;
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
    @GeneratedValue(generator = "myGenerator")
    @GenericGenerator(name = "myGenerator", strategy = "foreign", parameters = @Parameter(value = "user", name = "property"))
    @Column(name = "user_id", unique = true, nullable = false, columnDefinition = "numeric(8)")
    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @OneToOne
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Store)) return false;
        Store store = (Store) o;
        return Objects.equals(getUserId(), store.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId());
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String NEW_LINE = System.getProperty("line.separator");

        result.append("********** DETALLES **********" + NEW_LINE);
        result.append("Domicilio: " + this.getAddress() + NEW_LINE);
        result.append("Titular: " + this.getUser().getFirstName() + " " + this.getUser().getLastName() + NEW_LINE);
        result.append("Nombre de fantasía: " + this.getTradeName() + NEW_LINE);
        result.append("CUIT: " + this.getCuit() + NEW_LINE);
        result.append("Comisionista: " + this.getCommissionAgent() + NEW_LINE);
        result.append("Cantidad de terminales: " + this.getTerminalQuantity());

        return result.toString();
    }
}
