package kurzy.men.services.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Sjednocene kurzy interfacu CSAS a fixeru do jednotho DTO tj.&nbsp;Data 2 sluzeb, ale jenom pro jednu konkretni menu. To cele zapouzdreno v ExchangeRatesDTO
 */
public class ExchangeRateDTO implements Serializable{

    private String currency;
    private Double fixerRate;
    private Double csasRate;
    private Date fixerValidFrom;
    private Date csasValidFrom;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getFixerRate() {
        return fixerRate;
    }

    public void setFixerRate(Double fixerRate) {
        this.fixerRate = fixerRate;
    }

    public Double getCsasRate() {
        return csasRate;
    }

    public void setCsasRate(Double csasRate) {
        this.csasRate = csasRate;
    }

    public Date getFixerValidFrom() {
        return fixerValidFrom;
    }

    public void setFixerValidFrom(Date fixerValidFrom) {
        this.fixerValidFrom = fixerValidFrom;
    }

    public Date getCsasValidFrom() {
        return csasValidFrom;
    }

    public void setCsasValidFrom(Date csasValidFrom) {
        this.csasValidFrom = csasValidFrom;
    }
}
