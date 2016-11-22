package kurzy.men.services.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Sjednocene kurzy interfacu CSAS a fixeru do jednotho DTO => Data 2 sluzeb. Zapouzdruje v ExchangeRateDTO
 */
public class ExchangeRatesDTO implements Serializable {


    /*
     * oba fieldy maji inicializaci, nebude se resit null v budoucnu
     */
    /**
     * Mapuje vlastni data (Cela beana) podle kodu meny. Kdyz chci cilene konkretni menu
     */
    private Map<String, ExchangeRateDTO> ratesByCurrency = new HashMap<>();
    /**
     * List obsahuje vsechny meny v nahodnem poradi. Kdyz chci prochazet vsechny meny
     */
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

