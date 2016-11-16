package kurzy.men.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by honzapua on 16.11.2016.
 */
@Entity
@Table(name = "CSAS_EXCHANGE_REFERENCES")
public class CsasExchangeReference implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "last_updated", nullable = false)
    @Temporal(TemporalType.TIMESTAMP) // potrebuju datum ALE i cas!
    @NotNull
    private Date lastUpdated;

    @OneToMany(mappedBy = "csasExchangeReference", cascade = CascadeType.ALL)
    private List<CsasExchangeRate> csasExchangeRates = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<CsasExchangeRate> getCsasExchangeRates() {
        return csasExchangeRates;
    }

    public void setCsasExchangeRates(List<CsasExchangeRate> csasExchangeRates) {
        this.csasExchangeRates = csasExchangeRates;
    }
}
