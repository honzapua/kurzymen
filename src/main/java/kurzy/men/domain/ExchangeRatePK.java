package kurzy.men.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Slozeny primarni klic v JAVE nejde udelat jinak nez pres @Embeddable
 */
@Embeddable
public class ExchangeRatePK implements Serializable {

    @Column(name = "code", length = 3, nullable = false)
    @NotNull
    private String code;

    @Column(name = "rate", nullable = false)
    @NotNull
    private Double rate;

    @Column(name = "exchange_reference_id", nullable = false)
    @NotNull
    private Long exchangeReferenceId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Long getExchangeReferenceId() {
        return exchangeReferenceId;
    }

    public void setExchangeReferenceId(Long exchangeReferenceId) {
        this.exchangeReferenceId = exchangeReferenceId;
    }
}
