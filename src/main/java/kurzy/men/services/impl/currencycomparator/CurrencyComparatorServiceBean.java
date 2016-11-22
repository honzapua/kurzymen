package kurzy.men.services.impl.currencycomparator;

import kurzy.men.constant.ApplicationConst;
import kurzy.men.services.api.currencycomparator.CurrencyComparatorService;
import kurzy.men.services.api.dto.ExchangeRateDTO;
import kurzy.men.services.api.dto.ExchangeRatesDTO;
import kurzy.men.services.api.exchangerates.ExchangeRatesService;
import kurzy.men.services.api.mailservice.MailService;
import kurzy.men.services.api.mailservice.dto.MailDTO;
import kurzy.men.services.api.reportformatter.ReportFormatterService;
import kurzy.men.services.api.reportformatter.dto.ReportDataDTO;
import kurzy.men.services.api.reportformatter.dto.ReportDataEntryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Math.abs;

/**
 *
 * Created by honzapua on 20.11.2016.
 */
@Service
public class CurrencyComparatorServiceBean implements CurrencyComparatorService {
    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Autowired
    private ReportFormatterService reportFormatterService;

    @Autowired
    private MailService mailService;

    @Override
    public void compareAndReport() {
        ExchangeRatesDTO exchangeRates = exchangeRatesService.getExchangeRates();
        ReportDataDTO reportDataDTO = toReport(exchangeRates);
        //je tam alespon jeden zaznam? Jestli jo reportuj a posilej
        if (!reportDataDTO.getEntries().isEmpty()){
            MailDTO mail = reportFormatterService.format(reportDataDTO);
            mailService.sendMail(mail);
        }
    }

    private ReportDataDTO toReport(ExchangeRatesDTO rates) {
        ReportDataDTO result = new ReportDataDTO();
        result.setCaption("Rozdily kurzu");
        result.getRecipients().add("jan.smidrkal@gmail.com ");

        for (ExchangeRateDTO rate : rates.getRates()) {
            if(abs(rate.getCsasRate() - rate.getFixerRate()) > ApplicationConst.CURRENCY_RATE_DIFFERENCE) {
                ReportDataEntryDTO reportDataEntryDTO = new ReportDataEntryDTO();
                reportDataEntryDTO.setCsasValue(rate.getCsasRate());
                reportDataEntryDTO.setFixerValue(rate.getFixerRate());
                reportDataEntryDTO.setDescription("Zde se doplni Description");
                result.getEntries().add(reportDataEntryDTO);
            }
        }

        return result;
    }
}
