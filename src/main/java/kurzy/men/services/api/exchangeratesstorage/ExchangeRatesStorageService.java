package kurzy.men.services.api.exchangeratesstorage;

import kurzy.men.annotation.ToDo;
import kurzy.men.client.csas.api.dto.CSASExchangeRatesDTO;
import kurzy.men.client.fixer.api.dto.FixerExchangeReferenceDTO;

/**
 * Uklada a nacita data integracnich interfacu fixeru a CSAS z/do DB
 *
 */
@ToDo("Odstranit zavislost klientu na DTO")
public interface ExchangeRatesStorageService {

    void storeExchangeRates(CSASExchangeRatesDTO csasData);

    void storeExchangeRates(FixerExchangeReferenceDTO fixerData);

    @ToDo("remove?")
    CSASExchangeRatesDTO loadCSAS(Long id);

    @ToDo("remove?")
    FixerExchangeReferenceDTO loadFixer(Long id);

}
