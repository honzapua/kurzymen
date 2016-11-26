package kurzy.men.client.csas.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Toto je obalka jednotlivych Ratu, vychazi ze struktury API CSAS
 */
public class CSASExchangeRatesDTO implements Serializable { // prenasi data musi byt Seriazable

    private List<CSASExchangeRateDTO> rates = new ArrayList<>();

    public List<CSASExchangeRateDTO> getRates() {
        return rates;
    }

    public void setRates(List<CSASExchangeRateDTO> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "CSASExchangeRatesDTO{" +
                "rates=" + rates +
                '}';
    }
}
