package kurzy.men.client.csas.api;

import kurzy.men.client.csas.api.dto.CSASExchangeRatesDTO;
import java.util.Date;

/**
 * Interface klienta podle oficialni specifikace CSAS. Metody, ktere poskytuje
 */
public interface ExchangeRateClient {

    /**
     * Aktualni kurzy men
     * @return vraci kurz k atualnimu datu
     */
    CSASExchangeRatesDTO getCurrentRates();

    /**
     * @param date
     * @return vraci kurzy k datu dle parametru date
     */
    CSASExchangeRatesDTO getHistoricalExchangeRates(Date date);

}
