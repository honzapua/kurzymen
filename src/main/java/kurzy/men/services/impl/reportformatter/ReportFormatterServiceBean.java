package kurzy.men.services.impl.reportformatter;

import kurzy.men.services.api.mailservice.dto.MailDTO;
import kurzy.men.services.api.reportformatter.ReportFormatterService;
import kurzy.men.services.api.reportformatter.dto.ReportDataDTO;
import kurzy.men.services.api.reportformatter.dto.ReportDataEntryDTO;
import org.springframework.stereotype.Service;

/**
 * Created by honzapua on 20.11.2016.
 */
@Service
public class ReportFormatterServiceBean implements ReportFormatterService{

    /**
     * Pouziva caption na subject mailu
     * @param data
     * @return
     */
    @Override
    public MailDTO format(ReportDataDTO data) {
        MailDTO result = new MailDTO();
        result.setSubject(data.getCaption());
        result.getRecipients().addAll(data.getRecipients());
        // konkatenace retezcu
        StringBuilder sb = new StringBuilder();
        sb.append("Report kurzu men: ");
        sb.append("\n\n");

        for(ReportDataEntryDTO entry: data.getEntries()){
            sb.append(" - CSAS: ").append(entry.getCsasValue()).append(", Fixer: ").append(entry.getFixerValue()).append(String.format(". (%s)", entry.getDescription()));
            sb.append("\n");

        }

        result.setBody(sb.toString());
        return result;
    }
}
