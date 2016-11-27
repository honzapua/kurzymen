package kurzy.men.client.fixer.impl;

import kurzy.men.client.fixer.api.FixerClient;
import kurzy.men.client.fixer.api.dto.FixerExchangeReferenceDTO;
import kurzy.men.constant.ApplicationConst;
import kurzy.men.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * Implementace klienta podle rozhrani Fixeru. Interface fixer client
 */
@Service
class FixerClientBean implements FixerClient {

    private static final Logger logger = LoggerFactory.getLogger(FixerClientBean.class);

    @Override
    public FixerExchangeReferenceDTO getLatestExchangeReferenceRates() {
        if (logger.isDebugEnabled()) {
            logger.debug("About to obtain latest exchange rates ");
        }
        RestTemplate restTemplate = new RestTemplate();
        FixerExchangeReferenceDTO result = restTemplate.getForObject(ApplicationConst.FIXER_RATES_URL, FixerExchangeReferenceDTO.class);
        if (logger.isDebugEnabled()) {
            logger.debug("Got latest exchange rates {}", result);
        }
        return result;
    }

    @Override
    public FixerExchangeReferenceDTO getLatestExchangeReferenceRates(String base) {
        if (logger.isDebugEnabled()) {
            logger.debug("About to obtain '{}' based latest exchange rates ", base);
        }
        RestTemplate restTemplate = new RestTemplate();
        FixerExchangeReferenceDTO result = restTemplate.getForObject(ApplicationConst.FIXER_RATES_BASE_URL, FixerExchangeReferenceDTO.class, base);
        if (logger.isDebugEnabled()) {
            logger.debug("Got result for base '{}': {}", base, result);
        }
        return result;
    }

    @Override
    public FixerExchangeReferenceDTO getHistoricalRates(Date date) {
        if (logger.isDebugEnabled()) {
            logger.debug("About to obtain result for historical rates {}", date);
        }
        RestTemplate restTemplate = new RestTemplate();
        FixerExchangeReferenceDTO result = restTemplate.getForObject(ApplicationConst.FIXER_HISTORIC_RATES_URL, FixerExchangeReferenceDTO.class, DateUtils.dateToISOString(date));
        if (logger.isDebugEnabled()) {
            logger.debug("Got result for historical date {}: {}", date, result);
        }
        return result;
    }

    @Override
    public FixerExchangeReferenceDTO getHistoricalRates(Date date, String base) {
        if (logger.isDebugEnabled()) {
            logger.debug("About to obtain result for historical rates {} based on {}", date, base);
        }
        RestTemplate restTemplate = new RestTemplate();

        if (logger.isDebugEnabled()) {
            logger.debug("Got result for historical rates {}: based {}", date, base);
        }
        return restTemplate.getForObject(ApplicationConst.FIXER_HISTORIC_RATES_WITH_BASE_URL, FixerExchangeReferenceDTO.class, DateUtils.dateToISOString(date), base);
    }

    @Override
    public FixerExchangeReferenceDTO getLatestExchangeReferenceRates(String[] symbols) {
        if (logger.isDebugEnabled()) {
            logger.debug("About to obtain Exchange Reference Rates {}", Arrays.toString(symbols));
        }
        RestTemplate restTemplate = new RestTemplate();
        if (logger.isDebugEnabled()) {
            logger.debug("Got result for Exchange Reference Rates {}", Arrays.toString(symbols));
        }
        return restTemplate.getForObject(ApplicationConst.FIXER_RATES_SYMBOLS_URL, FixerExchangeReferenceDTO.class, new Object[]{symbols});
    }

    @Override
    public List<String> getCurrencies() {
        if (logger.isDebugEnabled()) {
            logger.debug("About to obtain result for getCurrencies");
        }
        FixerExchangeReferenceDTO latestExchangeReferenceRates = getLatestExchangeReferenceRates();
        Set<String> rateSymbols = latestExchangeReferenceRates.getRates().keySet();
        List<String> result = new ArrayList<>(rateSymbols);
        if (!result.contains(latestExchangeReferenceRates.getBase())) {
            result.add(latestExchangeReferenceRates.getBase());
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Got result for getCurrencies");
        }
        return result;
    }

}
