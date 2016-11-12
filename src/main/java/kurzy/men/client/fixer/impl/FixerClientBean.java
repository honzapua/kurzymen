package kurzy.men.client.fixer.impl;

import kurzy.men.client.fixer.api.FixerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import kurzy.men.client.fixer.api.dto.ExchangeReferenceDTO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by honzapua on 4.11.2016.
 */
@Service
class FixerClientBean implements FixerClient {

    @Override
    public ExchangeReferenceDTO getLatestExchangeReferenceRates() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://api.fixer.io/latest", ExchangeReferenceDTO.class);
    }

    @Override
    public ExchangeReferenceDTO getLatestExchangeReferenceRates(String base) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://api.fixer.io/latest?base={base}", ExchangeReferenceDTO.class, base);
    }

    @Override
    public ExchangeReferenceDTO getHistoricalRates(Date date) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://api.fixer.io/{date}", ExchangeReferenceDTO.class, dateToISOString(date));
    }

    @Override
    public ExchangeReferenceDTO getLatestExchangeReferenceRates(String[] symbols) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject("http://api.fixer.io/latest?symbols={symbols}", ExchangeReferenceDTO.class, new Object[] {symbols});

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

    private static String dateToISOString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}
