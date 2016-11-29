package kurzy.men.services.api.reportformatter;

import kurzy.men.services.api.mailservice.dto.MailDTO;
import kurzy.men.services.api.reportformatter.dto.ReportDataDTO;

/**
 * Vytvari report ze vstupnich dat ve formatu pro mail service.
 */
public interface ReportFormatterService {
    /**
     * formatuje vysledna data s rozdilem kurzu vetsi nez podminka 0.01
     * @param data data ke zforatovani
     * @return email pripraveny k odeslani
     */
    MailDTO format(ReportDataDTO data);


}