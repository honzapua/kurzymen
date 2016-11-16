package kurzy.men.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Balik domen reprezentuje data v databazi
 */
@Entity
@Table(name = "FIXER_EXCHANGE_RATES")
public class FixerExchangeRate implements Serializable {

    @EmbeddedId
    private FixerExchangeRatePK fixerExchangeRatePK;
    /**
     * Eager vs Lazy. jen hlavni Entity a kdyz se sahne zavola se getter
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_reference_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private FixerExchangeReference fixerExchangeReference;

    public FixerExchangeRatePK getFixerExchangeRatePK() {
        return fixerExchangeRatePK;
    }

    public void setFixerExchangeRatePK(FixerExchangeRatePK fixerExchangeRatePK) {
        this.fixerExchangeRatePK = fixerExchangeRatePK;
    }

    public FixerExchangeReference getFixerExchangeReference() {
        return fixerExchangeReference;
    }

    public void setFixerExchangeReference(FixerExchangeReference fixerExchangeReference) {
        this.fixerExchangeReference = fixerExchangeReference;
    }
}
