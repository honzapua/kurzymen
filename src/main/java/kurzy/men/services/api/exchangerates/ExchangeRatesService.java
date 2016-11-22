package kurzy.men.services.api.exchangerates;

import kurzy.men.services.api.dto.ExchangeRatesDTO;

import java.util.Date;

/**
 * Normalizece u men, ktere se uvadeji na 100 jednotek. Potrebujeme 1:1
 * Pri kazdem provolani se ulozi cerstva data pro oba intefaces CSAS, fixer do DB
 */
public interface ExchangeRatesService {

    ExchangeRatesDTO getExchangeRates();
    ExchangeRatesDTO getExchangeRates(Date date);


}
