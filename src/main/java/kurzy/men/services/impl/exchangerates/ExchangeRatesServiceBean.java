package kurzy.men.services.impl.exchangerates;

import kurzy.men.client.csas.api.ExchangeRateClient;
import kurzy.men.client.csas.api.dto.CSASExchangeRateDTO;
import kurzy.men.client.csas.api.dto.CSASExchangeRatesDTO;
import kurzy.men.client.fixer.api.FixerClient;
import kurzy.men.client.fixer.api.dto.FixerExchangeReferenceDTO;
import kurzy.men.constant.ApplicationConst;
import kurzy.men.services.api.dto.ExchangeRateDTO;
import kurzy.men.services.api.dto.ExchangeRatesDTO;
import kurzy.men.services.api.exchangerates.ExchangeRatesService;
import kurzy.men.services.api.exchangeratesstorage.ExchangeRatesStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Implementace Normalizece u men ktere uvadeji na 100 jednotek. Potrebujeme 1:1
 *
 * Do Beany by se dokumentace psat nemela, ale stacit by melo inheritDoc
 * {@inheritDoc}
 */

@Service
public class ExchangeRatesServiceBean implements ExchangeRatesService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConst.LOGGER_EXCHANGE_RATES);

    @Autowired
    private ExchangeRateClient csasClient;

    @Autowired
    private FixerClient fixerClient;

    @Autowired
    private ExchangeRatesStorageService exchangeRatesStorageService;

    @Override
    public ExchangeRatesDTO getExchangeRates() {
        logger.info("About obtain and normalize exchange rates");

        logger.info("About to get CSAS data");
        CSASExchangeRatesDTO csasData = csasClient.getCurrentRates();
        logger.info("About to get fixer data");
        FixerExchangeReferenceDTO fixerData = fixerClient.getLatestExchangeReferenceRates(ApplicationConst.DEFAULT_FIXER_BASE);
        logger.info("About to store all data");
        store(csasData, fixerData);
        logger.info("About normalize data");
        return normalizeData(csasData, fixerData);
    }

    @Override
    public ExchangeRatesDTO getExchangeRates(Date date) {
        CSASExchangeRatesDTO csasData = csasClient.getHistoricalExchangeRates(date);
        FixerExchangeReferenceDTO fixerData = fixerClient.getHistoricalRates(date, ApplicationConst.DEFAULT_FIXER_BASE);
        store(csasData, fixerData);
        return normalizeData(csasData, fixerData);
    }
    /**
     * Transformuje sporitelni data <k,v> k = kod meny, v = beana dat pro konkretni menu. Vytvarime neco index v databazi. Kdyz by nebyl index, dochazelo by k FullScan a to by bylo pomale.
     * @param csasData
     * @param fixerData
     * @return
     */
    private ExchangeRatesDTO normalizeData(CSASExchangeRatesDTO csasData, FixerExchangeReferenceDTO fixerData){
        Map<String, CSASExchangeRateDTO> data = new HashMap<>();
        for (CSASExchangeRateDTO dto: csasData.getRates()){
            data.put(dto.getShortName(), dto);
        }

        return normalizeData(data, fixerData);
    }

    /**
     * Normalizuje hodnoty sporitelny, ktera ma amount, ktery fixer nema. Je potreba udelat vypocet ohledne 100 yenu, forintu apod.
     * @param csasData
     * @param fixerData
     * @return
     */

    private ExchangeRatesDTO normalizeData(Map<String, CSASExchangeRateDTO> csasData, FixerExchangeReferenceDTO fixerData){
        ExchangeRatesDTO result = new ExchangeRatesDTO();
        for (Map.Entry<String, Double> entry: fixerData.getRates().entrySet()){
            final String fixerCurrency = entry.getKey();
            //zpracovavame data, ktera mame z obou zdroju
            if(!csasData.containsKey(fixerCurrency)){
                continue;
            }

            final Double fixerRate = entry.getValue();
            final CSASExchangeRateDTO csasRate = csasData.get(fixerCurrency);
            ExchangeRateDTO rate = new ExchangeRateDTO();
            rate.setFixerRate(1 / fixerRate);
            rate.setFixerValidFrom(fixerData.getDate());
            rate.setCsasValidFrom(csasRate.getValidFrom());
            rate.setCsasRate(csasRate.getCurrMid() / csasRate.getAmount());
            rate.setCurrency(fixerCurrency);

            result.getRates().add(rate);
            result.getRatesByCurrency().put(fixerCurrency, rate);
        }
        return result;
    }

    private void store(CSASExchangeRatesDTO csasData, FixerExchangeReferenceDTO fixerData){
        exchangeRatesStorageService.storeExchangeRates(csasData);
        exchangeRatesStorageService.storeExchangeRates(fixerData);
    }
}
