package kurzy.men.services.api.mailservice.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Do body se uklada text zformatovany v plain text takze entery, tabulatory. Pozor kazdy mail client parsuje text trosku jinak.
 * Created by honzapua on 20.11.2016.
 */
public class MailDTO implements Serializable {
    private List<String> recipients = new ArrayList<>();
    private String subject;
    private String body;

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
