package kurzy.men.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by honzapua on 12.11.2016.
 */
@Entity
@Table(name = "exchange_rates")
public class ExchangeRate implements Serializable {

    @EmbeddedId
    private ExchangeRatePK exchangeRatePK;
    /**
     * //Eager vs Lazy. jen hlavni Entity a kdyz se sahne zavola se getter
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_reference_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private ExchangeReference exchangeReference;

    public ExchangeRatePK getExchangeRatePK() {
        return exchangeRatePK;
    }

    public void setExchangeRatePK(ExchangeRatePK exchangeRatePK) {
        this.exchangeRatePK = exchangeRatePK;
    }

    public ExchangeReference getExchangeReference() {
        return exchangeReference;
    }

    public void setExchangeReference(ExchangeReference exchangeReference) {
        this.exchangeReference = exchangeReference;
    }
}
