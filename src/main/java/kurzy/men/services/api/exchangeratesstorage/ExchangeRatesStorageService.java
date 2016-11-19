package kurzy.men.services.api.exchangeratesstorage;

import kurzy.men.client.csas.api.dto.CSASExchangeRatesDTO;
import kurzy.men.client.fixer.api.dto.FixerExchangeReferenceDTO;

/**
 * Created by honzapua on 19.11.2016.
 */
public interface ExchangeRatesStorageService {

    void storeExchangeRates(CSASExchangeRatesDTO csasData);

    void storeExchangeRates(FixerExchangeReferenceDTO fixerData);

    CSASExchangeRatesDTO loadCSAS(Long id);

    FixerExchangeReferenceDTO loadFixer(Long id);

}
