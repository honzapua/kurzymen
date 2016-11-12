package kurzy.men.client.fixer.api;

import kurzy.men.client.fixer.api.dto.ExchangeReferenceDTO;

import java.util.Date;
import java.util.List;

/**
 * Created by honzapua on 4.11.2016.
 */
public interface FixerClient {
    ExchangeReferenceDTO getLatestExchangeReferenceRates();
    ExchangeReferenceDTO getLatestExchangeReferenceRates(String base);
    ExchangeReferenceDTO getHistoricalRates(Date date);
    ExchangeReferenceDTO getLatestExchangeReferenceRates(String[] symbols);

    /**
     * pomocna metoda a zjednoduseni prace v klientovi je navic oproti API fixer.io.
     * Nemeni povahu klienta.
     * @return vrati
     */
    List<String> getCurrencies();

}