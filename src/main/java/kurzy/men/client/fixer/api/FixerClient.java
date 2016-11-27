package kurzy.men.client.fixer.api;

import kurzy.men.client.fixer.api.dto.FixerExchangeReferenceDTO;

import java.util.Date;
import java.util.List;

/**
 * Reprezentace sluzby fixeru. Je to client
 */
public interface FixerClient {
    FixerExchangeReferenceDTO getLatestExchangeReferenceRates();
    FixerExchangeReferenceDTO getLatestExchangeReferenceRates(String base);
    FixerExchangeReferenceDTO getHistoricalRates(Date date);
    FixerExchangeReferenceDTO getHistoricalRates(Date date, String base);
    FixerExchangeReferenceDTO getLatestExchangeReferenceRates(String[] symbols);

    /**
     * pomocna metoda a zjednoduseni prace v klientovi je navic oproti API fixer.io.
     * Nemeni povahu klienta.
     * @return seznam podporovanych men fixeru
     */
    List<String> getCurrencies();

}