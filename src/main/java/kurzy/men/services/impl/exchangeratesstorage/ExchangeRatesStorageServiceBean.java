package kurzy.men.services.impl.exchangeratesstorage;

import kurzy.men.client.csas.api.dto.CSASExchangeRateDTO;
import kurzy.men.client.csas.api.dto.CSASExchangeRatesDTO;
import kurzy.men.client.fixer.api.dto.FixerExchangeReferenceDTO;
import kurzy.men.domain.*;
import kurzy.men.repositories.CsasExchangeReferenceRepository;
import kurzy.men.repositories.FixerExchangeReferenceRepository;
import kurzy.men.services.api.exchangeratesstorage.ExchangeRatesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by honzapua on 19.11.2016.
 */
@Service
public class ExchangeRatesStorageServiceBean implements ExchangeRatesStorageService {

    @Autowired
    private FixerExchangeReferenceRepository exchangeReferenceRepository;

    @Autowired
    private CsasExchangeReferenceRepository csasExchangeReferenceRepository;

    @Override
    public void storeExchangeRates(CSASExchangeRatesDTO csasData) {
        CsasExchangeReference er = new CsasExchangeReference();
        er.setLastUpdated(new Date());
        er = csasExchangeReferenceRepository.save(er);

        for (CSASExchangeRateDTO rate : csasData.getRates()) {
            CsasExchangeRatePK pk = new CsasExchangeRatePK();

            pk.setExchangeReferenceId(er.getId());
            pk.setAmount(rate.getAmount());
            pk.setCnbMid(rate.getCnbMid());
            pk.setCountry(rate.getCountry());
            pk.setCurrBuy(rate.getCurrBuy());
            pk.setCurrMid(rate.getCurrMid());
            pk.setCurrSell(rate.getCurrSell());
            pk.setMove(rate.getMove());
            pk.setName(rate.getName());
            pk.setShortName(rate.getShortName());
            pk.setValBuy(rate.getValBuy());
            pk.setValidFrom(rate.getValidFrom());
            pk.setValMid(rate.getValMid());
            pk.setValSell(rate.getValSell());
            pk.setVersion(rate.getVersion());

            CsasExchangeRate r = new CsasExchangeRate();
            r.setCsasExchangeRatePK(pk);

            er.getCsasExchangeRates().add(r);
        }

        csasExchangeReferenceRepository.save(er);
    }

    @Override
    public void storeExchangeRates(FixerExchangeReferenceDTO fixerData) {
        FixerExchangeReference er = new FixerExchangeReference();
        er.setBase(fixerData.getBase());
        er.setDate(fixerData.getDate());
        er.setLastUpdated(new Date());

        er = exchangeReferenceRepository.save(er);

        //entrySet je iterator klice s hodnotou mena:rate
        for (Map.Entry<String, Double> entry: fixerData.getRates().entrySet()){
            FixerExchangeRate rate = new FixerExchangeRate();
            FixerExchangeRatePK pk = new FixerExchangeRatePK();//slozeny PK jiny objekt
            pk.setCode(entry.getKey()); // date z klienta cpeme
            pk.setRate(entry.getValue());
            pk.setExchangeReferenceId(er.getId());
            rate.setFixerExchangeRatePK(pk);
            er.getFixerExchangeRates().add(rate);
        }

        exchangeReferenceRepository.save(er);
    }

    @Override
    public CSASExchangeRatesDTO loadCSAS(Long id) {
        CsasExchangeReference er = csasExchangeReferenceRepository.getOne(id != null ? id : 1L);

        CSASExchangeRatesDTO result = new CSASExchangeRatesDTO();

        for (CsasExchangeRate r : er.getCsasExchangeRates()) {
            CsasExchangeRatePK pk = r.getCsasExchangeRatePK();

            CSASExchangeRateDTO d = new CSASExchangeRateDTO();

            d.setVersion(pk.getVersion());
            d.setValSell(pk.getValSell());
            d.setValMid(pk.getValMid());
            d.setAmount(pk.getAmount());
            d.setCnbMid(pk.getCnbMid());
            d.setCountry(pk.getCountry());
            d.setCurrBuy(pk.getCurrBuy());
            d.setCurrMid(pk.getCurrMid());
            d.setCurrSell(pk.getCurrSell());
            d.setMove(pk.getMove());
            d.setName(pk.getName());
            d.setShortName(pk.getShortName());
            d.setValidFrom(pk.getValidFrom());
            d.setValBuy(pk.getValBuy());

            result.getRates().add(d);
        }

        return result;
    }

    @Override
    public FixerExchangeReferenceDTO loadFixer(Long id) {
        FixerExchangeReference er = exchangeReferenceRepository.getOne(id != null ? id : 2L);

        FixerExchangeReferenceDTO result = new FixerExchangeReferenceDTO();

        result.setBase(er.getBase());
        result.setDate(er.getDate());
        for (FixerExchangeRate rate : er.getFixerExchangeRates()) {
            result.getRates().put(rate.getFixerExchangeRatePK().getCode(), rate.getFixerExchangeRatePK().getRate());
        }

        return result;
    }

}
