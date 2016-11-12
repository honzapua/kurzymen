package kurzy.men.web;

import kurzy.men.client.fixer.api.FixerClient;
import kurzy.men.client.fixer.api.dto.ExchangeReferenceDTO;
import kurzy.men.domain.ExchangeRate;
import kurzy.men.domain.ExchangeRatePK;
import kurzy.men.domain.ExchangeReference;
import kurzy.men.repositories.ExchangeReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by honzapua on 12.11.2016.
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private FixerClient fixerClient;

    @Autowired
    private ExchangeReferenceRepository exchangeReferenceRepository;

    /**
     * Dostava z fixeru ulozi do DB a vrati na frontEnd
     * @return dto fixer je client nas
     */

    @RequestMapping(value = "/list")
    public ExchangeReferenceDTO getRates() {
        ExchangeReferenceDTO dto = fixerClient.getLatestExchangeReferenceRates();
        ExchangeReference er = new ExchangeReference();
        er.setBase(dto.getBase());
        er.setDate(dto.getDate());
        er.setLastUpdated(new Date());

        er = exchangeReferenceRepository.save(er);

        //entrySet je iterator klice s hodnotou mena:rate
        for (Map.Entry<String, Double> entry: dto.getRates().entrySet()){
            ExchangeRate rate = new ExchangeRate();
            ExchangeRatePK pk = new ExchangeRatePK();//slozeny PK jiny objekt
            pk.setCode(entry.getKey()); // date z klienta cpeme
            pk.setRate(entry.getValue());
            pk.setExchangeReferenceId(er.getId());
            rate.setExchangeRatePK(pk);
            er.getExchangeRates().add(rate);
        }

        exchangeReferenceRepository.save(er);

        return dto;
    }

    /**
     * Nenacita data z fixeru, ale z nasi DB.
     * @return
     */

    @RequestMapping(value = "/db")
    public ExchangeReferenceDTO getFromDb() {
        ExchangeReference er = exchangeReferenceRepository.getOne(2L);

        ExchangeReferenceDTO result = new ExchangeReferenceDTO();

        result.setBase(er.getBase());
        result.setDate(er.getDate());
        for (ExchangeRate rate : er.getExchangeRates()) {
            result.getRates().put(rate.getExchangeRatePK().getCode(), rate.getExchangeRatePK().getRate());
        }

        return result;
    }
}
