package kurzy.men.client.csas.impl;

import kurzy.men.client.csas.api.ExchangeRateClient;
import kurzy.men.client.csas.api.dto.ExchangeRateDTO;
import kurzy.men.client.csas.api.dto.ExchangeRatesDTO;
import kurzy.men.constant.ApplicationConst;
import kurzy.men.utils.DateUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;

/**
 * implementace klienta  metod REST template
 */
@Service
public class ExchangeRatesClientBean implements ExchangeRateClient {

    /**
     * Aktualni kurzy men
     * @return vraci kurz k atualnimu datu
     */
    @Override
    public ExchangeRatesDTO getCurrentRates() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.put("WEB-API-key", Arrays.asList(new String[] {
                WebApiKeyConfiguration.getWebApiKey()}));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<ExchangeRateDTO[]> response = restTemplate.exchange(ApplicationConst.CSAS_CURRENT_RATES_URL, HttpMethod.GET, entity, ExchangeRateDTO[].class);
        ExchangeRatesDTO dto = new ExchangeRatesDTO();
        dto.setRates(Arrays.asList(response.getBody()));
        return dto;
    }

    /**
     *
     * @param date
     * @return vraci kurzy k datu napsanym za lomitkem URL
     */
    @Override
    public ExchangeRatesDTO getHistoricalExchangeRates(Date date) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.put("WEB-API-key", Arrays.asList(new String[] {
                WebApiKeyConfiguration.getWebApiKey()}));
        HttpEntity<Object> entity = new HttpEntity<>(headers);
        ResponseEntity<ExchangeRateDTO[]> response = restTemplate.exchange(ApplicationConst.CSAS_HISTORIC_EXCHANGE_RATES_URL, HttpMethod.GET, entity, ExchangeRateDTO[].class, DateUtils.dateToISOString(date));
        ExchangeRatesDTO dto = new ExchangeRatesDTO();
        dto.setRates(Arrays.asList(response.getBody()));
        return dto;
    }

}
