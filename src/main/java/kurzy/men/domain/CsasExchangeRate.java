package kurzy.men.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Entita reprezentuje tabulky.
 *
 * Reprezentuje data integracniho kanalu CSAS
 *
 */
@Entity
@Table(name = "CSAS_EXCHANGE_RATES")
public class CsasExchangeRate implements Serializable {

    @EmbeddedId //slozeny primarni klic
    private CsasExchangeRatePK csasExchangeRatePK;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_reference_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false) //sloupec ktery foreign key
    private CsasExchangeReference csasExchangeReference;

    public CsasExchangeRatePK getCsasExchangeRatePK() {
        return csasExchangeRatePK;
    }

    public void setCsasExchangeRatePK(CsasExchangeRatePK csasExchangeRatePK) {
        this.csasExchangeRatePK = csasExchangeRatePK;
    }

    public CsasExchangeReference getCsasExchangeReference() {
        return csasExchangeReference;
    }

    public void setCsasExchangeReference(CsasExchangeReference csasExchangeReference) {
        this.csasExchangeReference = csasExchangeReference;
    }
}
