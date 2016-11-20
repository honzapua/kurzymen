package kurzy.men.services.api.mailservice;

import kurzy.men.services.api.mailservice.dto.MailDTO;

/**
 * Service na odesilani emailu cte z MailDTO a adresa odesilatele se bere z ConfigurationService
 * Created by honzapua on 20.11.2016.
 */
public interface MailService {
    /**
     * Odesila maily. Seznam podporovanych vlastnosti. subject, telo zpravy pouze text/plain, vice prijemcu, bez priloh
     * @param mail vlastni zprava k odeslani
     */
    void sendMail(MailDTO mail);

}
