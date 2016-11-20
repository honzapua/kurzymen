package kurzy.men.web;

import kurzy.men.annotation.Remove;
import kurzy.men.client.csas.api.ExchangeRateClient;
import kurzy.men.client.csas.api.dto.CSASExchangeRatesDTO;
import kurzy.men.client.fixer.api.FixerClient;
import kurzy.men.client.fixer.api.dto.FixerExchangeReferenceDTO;
import kurzy.men.services.api.dto.ExchangeRatesDTO;
import kurzy.men.services.api.exchangerates.ExchangeRatesService;
import kurzy.men.services.api.exchangeratesstorage.ExchangeRatesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * MVC architektura Controler
 */
@RestController
@RequestMapping(value = "/test")
@Remove // oznaceno na budouci smazani
public class TestController {

    @Autowired
    private FixerClient fixerClient;

    @Autowired
    private ExchangeRateClient csasClient;

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Autowired
    private ExchangeRatesStorageService exchangeRatesStorageService;

    @RequestMapping(value = "/service")
    public ExchangeRatesDTO callService() {
        return exchangeRatesService.getExchangeRates();
    }

    /**
     * Dostava z fixeru ulozi do DB a vrati na frontEnd
     * @return dto fixer je client nas
     */
    @RequestMapping(value = "/fixerlist")
    public FixerExchangeReferenceDTO getRates() {
        FixerExchangeReferenceDTO dto = fixerClient.getLatestExchangeReferenceRates();
        exchangeRatesStorageService.storeExchangeRates(dto);
        return dto;
    }

    @RequestMapping(value = "/csaslist")
    public CSASExchangeRatesDTO csasList() {
        CSASExchangeRatesDTO dto = csasClient.getCurrentRates();
        exchangeRatesStorageService.storeExchangeRates(dto);
        return dto;
    }

    /**
     * Nenacita data z fixeru, ale z nasi DB.
     * @return
     */

    @RequestMapping(value = "/fixerdb")
    public FixerExchangeReferenceDTO getFixerDb(@RequestParam(value = "id", required = false) Long id) {
        return exchangeRatesStorageService.loadFixer(id);
    }

    @RequestMapping(value = "/csasdb")
    public CSASExchangeRatesDTO getCsasDb(@RequestParam(value = "id", required = false) Long id) {
        return exchangeRatesStorageService.loadCSAS(id);
    }

}
