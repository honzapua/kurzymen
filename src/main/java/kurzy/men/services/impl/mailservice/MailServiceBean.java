package kurzy.men.services.impl.mailservice;

import kurzy.men.services.api.configuration.ConfigurationOption;
import kurzy.men.services.api.configuration.ConfigurationService;
import kurzy.men.services.api.mailservice.MailService;
import kurzy.men.services.api.mailservice.dto.MailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by honzapua on 20.11.2016.
 * JavaMail
 */
@Service
public class MailServiceBean implements MailService{

    /**
     * Doplnuje se Diky Dependency Injection podle springove configurace spring-mail-config.xml
     * Normalne se to delalo @Autowired
     * v XML zkonfigurovano pro Gmail
     * @ImportResource("classpath:spring-mail-config.xml") v hlavni tride Application
     */
    private JavaMailSender mailSender;

    @Autowired
    private ConfigurationService configurationService;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void sendMail(MailDTO mail) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {

            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                for (String to : mail.getRecipients()) {
                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                }
                mimeMessage.setFrom(new InternetAddress(
                        configurationService.getOption(ConfigurationOption.APPLICATION_FROM_ADDRESS)));
                mimeMessage.setSubject(mail.getSubject());
                mimeMessage.setText(mail.getBody());
            }

        };

        try {
            this.mailSender.send(preparator);
        } catch (MailException ex) {
            throw new RuntimeException("Failed to send mail.", ex);
        }

    }
}
