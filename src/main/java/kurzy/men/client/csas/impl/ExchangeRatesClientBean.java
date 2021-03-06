package kurzy.men.client.csas.impl;

import kurzy.men.client.csas.api.ExchangeRateClient;
import kurzy.men.client.csas.api.dto.CSASExchangeRateDTO;
import kurzy.men.client.csas.api.dto.CSASExchangeRatesDTO;
import kurzy.men.constant.ApplicationConst;
import kurzy.men.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;

/**
 * implementace klienta  pomoci metod REST template (springovina)
 */
@Service
public class ExchangeRatesClientBean implements ExchangeRateClient {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeRatesClientBean.class);

    private static final String CSAS_HISTORIC_EXCHANGE_RATES_URL = "https://api.csas.cz/sandbox/webapi/api/v1/exchangerates/{date}";

    private static final String CSAS_CURRENT_RATES_URL = "https://api.csas.cz/sandbox/webapi/api/v1/exchangerates";

    /**
     * {@inheritDoc}
     */
    @Override
    public CSASExchangeRatesDTO getCurrentRates() {
        if (logger.isDebugEnabled()){
            logger.debug("About to get current exchange rates");
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.put("WEB-API-key", Arrays.asList(new String[] {
                WebApiKeyConfiguration.getWebApiKey()}));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<CSASExchangeRateDTO[]> response = restTemplate.exchange(CSAS_CURRENT_RATES_URL, HttpMethod.GET, entity, CSASExchangeRateDTO[].class);
        CSASExchangeRatesDTO dto = new CSASExchangeRatesDTO();
        dto.setRates(Arrays.asList(response.getBody()));

        if (logger.isDebugEnabled()){
            logger.debug("Got result current exchange rates {}", dto);
        }
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CSASExchangeRatesDTO getHistoricalExchangeRates(Date date) {
        if (logger.isDebugEnabled()){
            logger.debug("About to get historical exchange rates");
        }
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.put("WEB-API-key", Arrays.asList(new String[] {
                WebApiKeyConfiguration.getWebApiKey()}));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        //Provolava rest vraci kurzy k datu napsanem za lomitkem URL (v konstante).
        ResponseEntity<CSASExchangeRateDTO[]> response = restTemplate.exchange(
                CSAS_HISTORIC_EXCHANGE_RATES_URL,
                HttpMethod.GET, entity, CSASExchangeRateDTO[].class,
                DateUtils.dateToISOString(date));
        CSASExchangeRatesDTO dto = new CSASExchangeRatesDTO();
        dto.setRates(Arrays.asList(response.getBody()));

        if (logger.isDebugEnabled()){
            logger.debug("Got result historical exchange rates {}", dto);
        }
        return dto;
    }

}
