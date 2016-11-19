package kurzy.men.services.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Sjednocene kurzy interfacu CSAS a fixeru do jednotho DTO => Data 2 sluzeb, ale jenom pro jednu konkretni menu. To cele zapouzdreno v ExchangeRatesDTO
 */
public class ExchangeRatesDTO implements Serializable {


    /**
     * oba fieldy ma inicializaci nebude se resit null
     */
    private Map<String, ExchangeRateDTO> ratesByCurrency = new HashMap<>();
    private List<ExchangeRateDTO> rates = new ArrayList<>();

    public Map<String, ExchangeRateDTO> getRatesByCurrency() {
        return ratesByCurrency;
    }

    public void setRatesByCurrency(Map<String, ExchangeRateDTO> ratesByCurrency) {
        this.ratesByCurrency = ratesByCurrency;
    }

    public List<ExchangeRateDTO> getRates() {
        return rates;
    }

    public void setRates(List<ExchangeRateDTO> rates) {
        this.rates = rates;
    }
}

