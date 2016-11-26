package kurzy.men.services.impl.currencycomparator;

import com.sun.javafx.binding.StringFormatter;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Math.abs;

/**
 *
 * Created by honzapua on 20.11.2016.
 */
@Service
public class CurrencyComparatorServiceBean implements CurrencyComparatorService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConst.LOGGER_CURRENCY_COMPARATOR_SERVICE);

    @Autowired
    private ExchangeRatesService exchangeRatesService;

    @Autowired
    private ReportFormatterService reportFormatterService;

    @Autowired
    private MailService mailService;

    @Override
    public void compareAndReport() {
        logger.info("About to get normalized exchange rates");
        ExchangeRatesDTO exchangeRates = exchangeRatesService.getExchangeRates();
        logger.info("Preparing data for e-mail");
        ReportDataDTO reportDataDTO = toReport(exchangeRates);
        //je tam alespon jeden zaznam? Jestli jo reportuj a posilej
        if (!reportDataDTO.getEntries().isEmpty()){
            logger.info("Report data complete; about to send {}", reportDataDTO);
            MailDTO mail = reportFormatterService.format(reportDataDTO);
            logger.info("About to send e-mail {}", mail);
            mailService.sendMail(mail);// ODESILAC mailu!!!
        }
    }

    private ReportDataDTO toReport(ExchangeRatesDTO rates) {
        ReportDataDTO result = new ReportDataDTO();
        result.setCaption("Rozdily kurzu"); //subject mailu
        result.getRecipients().addAll(EmailRecipients.getRecipients());

        for (ExchangeRateDTO rate : rates.getRates()) {
            final double difference = abs(rate.getCsasRate() - rate.getFixerRate());
            if(difference > ApplicationConst.CURRENCY_RATE_DIFFERENCE) {
                ReportDataEntryDTO reportDataEntryDTO = new ReportDataEntryDTO();
                reportDataEntryDTO.setCsasValue(rate.getCsasRate());
                reportDataEntryDTO.setFixerValue(rate.getFixerRate());
                reportDataEntryDTO.setValueDifference(difference);
                reportDataEntryDTO.setDescription(String.format("%s", rate.getCurrency()));
                result.getEntries().add(reportDataEntryDTO);
            }
        }

        return result;
    }
}
