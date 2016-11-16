package kurzy.men.client.csas.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by honzapua on 13.11.2016.
 */
public class ExchangeRatesDTO implements Serializable { // prenasi data musi byzt Seriazable

    private List<ExchangeRateDTO> rates = new ArrayList<>();

    public List<ExchangeRateDTO> getRates() {
        return rates;
    }

    public void setRates(List<ExchangeRateDTO> rates) {
        this.rates = rates;
    }
}
