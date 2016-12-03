package kurzy.men.services.impl.exchangerates;

import kurzy.men.client.csas.api.ExchangeRateClient;
import kurzy.men.client.csas.api.dto.CSASExchangeRateDTO;
import kurzy.men.client.csas.api.dto.CSASExchangeRatesDTO;
import kurzy.men.client.fixer.api.FixerClient;
import kurzy.men.client.fixer.api.dto.FixerExchangeReferenceDTO;
import kurzy.men.constant.ApplicationConst;
import kurzy.men.services.api.dto.ExchangeRatesDTO;
import kurzy.men.services.api.exchangerates.ExchangeRatesService;
import kurzy.men.services.api.exchangeratesstorage.ExchangeRatesStorageService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Pokus o mockovani klientu a testovani logiky
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExchangeRatesServiceBeanTest {

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @MockBean
    private ExchangeRateClient csasClient;

    @MockBean
    private FixerClient fixerClient;

    @MockBean
    private ExchangeRatesStorageService exchangeRatesStorageService;

    @Test
    public void returnsCommonCurrency(){
        BDDMockito.given(csasClient.getCurrentRates())
                .willReturn(createCsasEUR());
        BDDMockito.given(fixerClient.getLatestExchangeReferenceRates(ApplicationConst.DEFAULT_FIXER_BASE))
                .willReturn(createFixerEUR());

        ExchangeRatesDTO actual = exchangeRatesService.getExchangeRates();
        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.getRates().size());
        Assert.assertEquals("EUR", actual.getRates().get(0).getCurrency());
        Assert.assertEquals(1D, actual.getRates().get(0).getFixerRate(), 0D);
        Assert.assertEquals(4D, actual.getRates().get(0).getCsasRate(), 0D);
    }

    @Test
    public void returnsNoCurrencyIfDistinctInClientsSets(){
        BDDMockito.given(csasClient.getCurrentRates())
                .willReturn(createCsasGBP());
        BDDMockito.given(fixerClient.getLatestExchangeReferenceRates(ApplicationConst.DEFAULT_FIXER_BASE))
                .willReturn(createFixerEUR());
        ExchangeRatesDTO actual = exchangeRatesService.getExchangeRates();
        Assert.assertNotNull(actual);
        Assert.assertEquals(0, actual.getRates().size());
    }

    @Test
    public void csasNormalizesAmount(){
        BDDMockito.given(csasClient.getCurrentRates())
                .willReturn(createCsasEURDifferentAmount());
        BDDMockito.given(fixerClient.getLatestExchangeReferenceRates(ApplicationConst.DEFAULT_FIXER_BASE))
                .willReturn(createFixerEUR());
        ExchangeRatesDTO actual = exchangeRatesService.getExchangeRates();
        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.getRates().size());
        Assert.assertEquals("EUR", actual.getRates().get(0).getCurrency());
        Assert.assertEquals(1D, actual.getRates().get(0).getFixerRate(), 0D);
        Assert.assertEquals(4D, actual.getRates().get(0).getCsasRate(), 0D);
    }

    protected CSASExchangeRatesDTO createCsasEUR() {
        CSASExchangeRatesDTO result = new CSASExchangeRatesDTO();

        CSASExchangeRateDTO rate = new CSASExchangeRateDTO();
        rate.setAmount(1L);
        rate.setCnbMid(2D);
        rate.setCountry("EUR");
        rate.setCurrBuy(3D);
        rate.setCurrMid(4D);
        rate.setCurrSell(5D);

        rate.setMove(6D);
        rate.setName("EUR");
        rate.setShortName("EUR");
        rate.setValBuy(7D);
        rate.setValidFrom(new Date());
        rate.setValMid(8D);
        rate.setValSell(9D);
        rate.setVersion("1.0");

        result.getRates().add(rate);

        return result;
    }

    protected CSASExchangeRatesDTO createCsasGBP() {
        CSASExchangeRatesDTO result = new CSASExchangeRatesDTO();

        CSASExchangeRateDTO rate = new CSASExchangeRateDTO();
        rate.setAmount(1L);
        rate.setCnbMid(2D);
        rate.setCountry("GBP");
        rate.setCurrBuy(3D);
        rate.setCurrMid(4D);
        rate.setCurrSell(5D);

        rate.setMove(6D);
        rate.setName("GBP");
        rate.setShortName("GBP");
        rate.setValBuy(7D);
        rate.setValidFrom(new Date());
        rate.setValMid(8D);
        rate.setValSell(9D);
        rate.setVersion("1.0");

        result.getRates().add(rate);

        return result;
    }

    protected CSASExchangeRatesDTO createCsasEURDifferentAmount() {
        CSASExchangeRatesDTO result = new CSASExchangeRatesDTO();

        CSASExchangeRateDTO rate = new CSASExchangeRateDTO();
        rate.setAmount(10L);
        rate.setCnbMid(2D);
        rate.setCountry("EUR");
        rate.setCurrBuy(3D);
        rate.setCurrMid(40D);
        rate.setCurrSell(5D);

        rate.setMove(6D);
        rate.setName("EUR");
        rate.setShortName("EUR");
        rate.setValBuy(7D);
        rate.setValidFrom(new Date());
        rate.setValMid(8D);
        rate.setValSell(9D);
        rate.setVersion("1.0");

        result.getRates().add(rate);

        return result;
    }

    protected FixerExchangeReferenceDTO createFixerEUR() {
        FixerExchangeReferenceDTO result = new FixerExchangeReferenceDTO();

        result.setBase("CZK");
        result.setDate(new Date());

        result.getRates().put("EUR", 1D);

        return result;
    }

}
