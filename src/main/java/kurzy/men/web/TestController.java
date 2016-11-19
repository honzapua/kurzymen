package kurzy.men.web;

import kurzy.men.client.csas.api.ExchangeRateClient;
import kurzy.men.client.csas.api.dto.CSASExchangeRateDTO;
import kurzy.men.client.csas.api.dto.CSASExchangeRatesDTO;
import kurzy.men.client.fixer.api.FixerClient;
import kurzy.men.client.fixer.api.dto.FixerExchangeReferenceDTO;
import kurzy.men.domain.*;
import kurzy.men.repositories.CsasExchangeReferenceRepository;
import kurzy.men.repositories.FixerExchangeReferenceRepository;
import kurzy.men.services.api.dto.ExchangeRatesDTO;
import kurzy.men.services.api.exchangerates.ExchangeRatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * MVC architektura Controler
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private FixerClient fixerClient;

    @Autowired
    private FixerExchangeReferenceRepository exchangeReferenceRepository;

    @Autowired
    private ExchangeRateClient csasClient;

    @Autowired
    private CsasExchangeReferenceRepository csasExchangeReferenceRepository;

    @Autowired
    private ExchangeRatesService exchangeRatesService;

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
        FixerExchangeReference er = new FixerExchangeReference();
        er.setBase(dto.getBase());
        er.setDate(dto.getDate());
        er.setLastUpdated(new Date());

        er = exchangeReferenceRepository.save(er);

        //entrySet je iterator klice s hodnotou mena:rate
        for (Map.Entry<String, Double> entry: dto.getRates().entrySet()){
            FixerExchangeRate rate = new FixerExchangeRate();
            FixerExchangeRatePK pk = new FixerExchangeRatePK();//slozeny PK jiny objekt
            pk.setCode(entry.getKey()); // date z klienta cpeme
            pk.setRate(entry.getValue());
            pk.setExchangeReferenceId(er.getId());
            rate.setFixerExchangeRatePK(pk);
            er.getFixerExchangeRates().add(rate);
        }

        exchangeReferenceRepository.save(er);

        return dto;
    }

    @RequestMapping(value = "/csaslist")
    public CSASExchangeRatesDTO csasList() {
        CSASExchangeRatesDTO dto = csasClient.getCurrentRates();

        CsasExchangeReference er = new CsasExchangeReference();
        er.setLastUpdated(new Date());
        er = csasExchangeReferenceRepository.save(er);

        for (CSASExchangeRateDTO rate : dto.getRates()) {
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

        return dto;
    }

    /**
     * Nenacita data z fixeru, ale z nasi DB.
     * @return
     */

    @RequestMapping(value = "/fixerdb")
    public FixerExchangeReferenceDTO getFixerDb(@RequestParam(value = "id", required = false) Long id) {
        FixerExchangeReference er = exchangeReferenceRepository.getOne(id != null ? id : 2L);

        FixerExchangeReferenceDTO result = new FixerExchangeReferenceDTO();

        result.setBase(er.getBase());
        result.setDate(er.getDate());
        for (FixerExchangeRate rate : er.getFixerExchangeRates()) {
            result.getRates().put(rate.getFixerExchangeRatePK().getCode(), rate.getFixerExchangeRatePK().getRate());
        }

        return result;
    }

    @RequestMapping(value = "/csasdb")
    public CSASExchangeRatesDTO getCsasDb(@RequestParam(value = "id", required = false) Long id) {
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

}
