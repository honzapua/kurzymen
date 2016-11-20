package kurzy.men.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * * Balik domen reprezentuje data v databazi
 */
@Entity
public class FixerExchangeReference implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "base", length = 3, nullable = false)
    @NotNull
    private String base;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    @NotNull
    private Date date;

    @Column(name = "last_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // potrebuju datum ALE i cas!
    @NotNull
    private Date lastUpdated;

    @OneToMany(mappedBy = "fixerExchangeReference", cascade = CascadeType.ALL)
    //
    private List<FixerExchangeRate> fixerExchangeRates = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<FixerExchangeRate> getFixerExchangeRates() {
        return fixerExchangeRates;
    }

    public void setFixerExchangeRates(List<FixerExchangeRate> fixerExchangeRates) {
        this.fixerExchangeRates = fixerExchangeRates;
    }
}
