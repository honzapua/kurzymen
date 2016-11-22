package kurzy.men.services.api.reportformatter;

import kurzy.men.services.api.mailservice.dto.MailDTO;
import kurzy.men.services.api.reportformatter.dto.ReportDataDTO;

/**
 * Vytvari report ze vstupnich dat ve formatu pro mail service.
 */
public interface ReportFormatterService {

    MailDTO format(ReportDataDTO data);


}
