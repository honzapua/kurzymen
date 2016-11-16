package kurzy.men.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by honzapua on 16.11.2016.
 */
@Entity
@Table(name = "CSAS_EXCHANGE_RATES")
public class CsasExchangeRate implements Serializable {

    @EmbeddedId
    private CsasExchangeRatePK csasExchangeRatePK;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "exchange_reference_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
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
