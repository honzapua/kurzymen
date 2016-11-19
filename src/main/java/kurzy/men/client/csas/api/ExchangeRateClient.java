package kurzy.men.client.csas.api;

import kurzy.men.client.csas.api.dto.CSASExchangeRatesDTO;
import java.util.Date;

/**
 * Interface klienta podle oficialni specifikace CSAS
 */
public interface ExchangeRateClient {

    CSASExchangeRatesDTO getCurrentRates();
    CSASExchangeRatesDTO getHistoricalExchangeRates(Date date);

}
