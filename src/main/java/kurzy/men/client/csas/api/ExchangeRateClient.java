package kurzy.men.client.csas.api;

import kurzy.men.client.csas.api.dto.ExchangeRatesDTO;
import java.util.Date;

/**
 * Interface klienta podle oficialni specifikace CSAS
 */
public interface ExchangeRateClient {

    ExchangeRatesDTO getCurrentRates();
    ExchangeRatesDTO getHistoricalExchangeRates(Date date);

}
