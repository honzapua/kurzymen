package kurzy.men.services.api.reportformatter;

import kurzy.men.services.api.mailservice.dto.MailDTO;
import kurzy.men.services.api.reportformatter.dto.ReportDataDTO;

/**
 * Created by honzapua on 20.11.2016.
 */
public interface ReportFormatterService {

    MailDTO format(ReportDataDTO data);


}
