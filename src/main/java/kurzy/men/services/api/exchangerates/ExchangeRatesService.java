package kurzy.men.services.api.exchangerates;

import kurzy.men.services.api.dto.ExchangeRateDTO;
import kurzy.men.services.api.dto.ExchangeRatesDTO;

import java.util.Date;

/**
 * Normalizece  u men ktere uvadeji na 100 jednotek. Potrebujeme 1:1
 */
public interface ExchangeRatesService {

    ExchangeRatesDTO getExchangeRates();
    ExchangeRatesDTO getExchangeRates(Date date);


}
