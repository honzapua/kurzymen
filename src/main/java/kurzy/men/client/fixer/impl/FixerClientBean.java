package kurzy.men.client.fixer.impl;

import kurzy.men.client.fixer.api.FixerClient;
import kurzy.men.constant.ApplicationConst;
import kurzy.men.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import kurzy.men.client.fixer.api.dto.ExchangeReferenceDTO;

import java.text.SimpleDateFormat;
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
    public ExchangeReferenceDTO getLatestExchangeReferenceRates() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ApplicationConst.FIXER_RATES_URL, ExchangeReferenceDTO.class);
    }

    @Override
    public ExchangeReferenceDTO getLatestExchangeReferenceRates(String base) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ApplicationConst.FIXER_RATES_BASE_URL, ExchangeReferenceDTO.class, base);
    }

    @Override
    public ExchangeReferenceDTO getHistoricalRates(Date date) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ApplicationConst.FIXER_HISTORIC_RATES_URL, ExchangeReferenceDTO.class, DateUtils.dateToISOString(date));
    }

    @Override
    public ExchangeReferenceDTO getLatestExchangeReferenceRates(String[] symbols) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ApplicationConst.FIXER_RATES_SYMBOLS_URL, ExchangeReferenceDTO.class, new Object[] {symbols});
    }

    @Override
    public List<String> getCurrencies() {
        ExchangeReferenceDTO latestExchangeReferenceRates = getLatestExchangeReferenceRates();
        Set<String> rateSymbols = latestExchangeReferenceRates.getRates().keySet();
        List<String> result = new ArrayList<>(rateSymbols);
        if (!result.contains(latestExchangeReferenceRates.getBase())) {
            result.add(latestExchangeReferenceRates.getBase());
        }
        return result;
    }

}
