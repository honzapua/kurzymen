package kurzy.men.client.fixer.impl;

import kurzy.men.client.fixer.api.FixerClient;
import kurzy.men.client.fixer.api.dto.FixerExchangeReferenceDTO;
import kurzy.men.constant.ApplicationConst;
import kurzy.men.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Implementace klienta podle rozhrani Fixeru. Interface fixer client
 */
@Service
class FixerClientBean implements FixerClient {

    @Override
    public FixerExchangeReferenceDTO getLatestExchangeReferenceRates() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ApplicationConst.FIXER_RATES_URL, FixerExchangeReferenceDTO.class);
    }

    @Override
    public FixerExchangeReferenceDTO getLatestExchangeReferenceRates(String base) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ApplicationConst.FIXER_RATES_BASE_URL, FixerExchangeReferenceDTO.class, base);
    }

    @Override
    public FixerExchangeReferenceDTO getHistoricalRates(Date date) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ApplicationConst.FIXER_HISTORIC_RATES_URL, FixerExchangeReferenceDTO.class, DateUtils.dateToISOString(date));
    }

    @Override
    public FixerExchangeReferenceDTO getHistoricalRates(Date date, String base) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ApplicationConst.FIXER_HISTORIC_RATES_WITH_BASE_URL, FixerExchangeReferenceDTO.class, DateUtils.dateToISOString(date), base);
    }

    @Override
    public FixerExchangeReferenceDTO getLatestExchangeReferenceRates(String[] symbols) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ApplicationConst.FIXER_RATES_SYMBOLS_URL, FixerExchangeReferenceDTO.class, new Object[] {symbols});
    }

    @Override
    public List<String> getCurrencies() {
        FixerExchangeReferenceDTO latestExchangeReferenceRates = getLatestExchangeReferenceRates();
        Set<String> rateSymbols = latestExchangeReferenceRates.getRates().keySet();
        List<String> result = new ArrayList<>(rateSymbols);
        if (!result.contains(latestExchangeReferenceRates.getBase())) {
            result.add(latestExchangeReferenceRates.getBase());
        }
        return result;
    }

}
