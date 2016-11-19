package kurzy.men.client.csas.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by honzapua on 13.11.2016.
 */
public class CSASExchangeRatesDTO implements Serializable { // prenasi data musi byzt Seriazable

    private List<CSASExchangeRateDTO> rates = new ArrayList<>();

    public List<CSASExchangeRateDTO> getRates() {
        return rates;
    }

    public void setRates(List<CSASExchangeRateDTO> rates) {
        this.rates = rates;
    }
}
