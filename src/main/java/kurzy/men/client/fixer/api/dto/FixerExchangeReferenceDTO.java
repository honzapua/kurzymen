package kurzy.men.client.fixer.api.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by honzapua on 4.11.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FixerExchangeReferenceDTO implements Serializable {
    private String base;
    private Date date;
    private Map<String, Double> rates = new HashMap<>(); // Map je interface HashMap implementace konkretni

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

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "FixerExchangeReferenceDTO{" +
                "base='" + base + '\'' +
                ", date=" + date +
                ", rates=" + rates +
                '}';
    }
}
