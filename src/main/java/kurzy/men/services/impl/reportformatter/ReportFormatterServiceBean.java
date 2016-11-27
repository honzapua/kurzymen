package kurzy.men.services.impl.reportformatter;

import kurzy.men.constant.ApplicationConst;
import kurzy.men.services.api.mailservice.dto.MailDTO;
import kurzy.men.services.api.reportformatter.ReportFormatterService;
import kurzy.men.services.api.reportformatter.dto.ReportDataDTO;
import kurzy.men.services.api.reportformatter.dto.ReportDataEntryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by honzapua on 20.11.2016.
 */
@Service
public class ReportFormatterServiceBean implements ReportFormatterService{

    public static final Logger logger = LoggerFactory.getLogger(ApplicationConst.LOGGER_REPORTING);

    /**
     * Pouziva caption na subject mailu
     * @param data vstupni data reportu
     * @return strukturu dat pro {@linkplain kurzy.men.services.api.mailservice.MailService MailService}
     */
    @Override
    public MailDTO format(ReportDataDTO data) {
        logger.info("About to create email from Reporting data");
        MailDTO result = new MailDTO();
        result.setSubject(data.getCaption());
        result.getRecipients().addAll(data.getRecipients());
        // konkatenace retezcu
        StringBuilder sb = new StringBuilder();
        sb.append("Report kurzu men: ");
        sb.append("\n\n");

        for(ReportDataEntryDTO entry: data.getEntries()){
            sb.append(" - CSAS: ").append(entry.getCsasValue()).append(", Fixer: ").append(String.format("%.3f", entry.getFixerValue())).append(String.format(", rozdil: %.3f. (%s)",entry.getValueDifference(), entry.getDescription()));
            sb.append("\n");

        }

        result.setBody(sb.toString());
        logger.info("Email was formatted successfully");
        return result;
    }
}
