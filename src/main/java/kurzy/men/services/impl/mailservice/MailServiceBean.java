package kurzy.men.services.impl.mailservice;

import kurzy.men.constant.ApplicationConst;
import kurzy.men.services.api.configuration.ConfigurationOption;
import kurzy.men.services.api.configuration.ConfigurationService;
import kurzy.men.services.api.mailservice.MailService;
import kurzy.men.services.api.mailservice.dto.MailDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
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

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConst.LOGGER_MAIL_SERVICE);

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
        logger.info("About to send Email");
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
            logger.info("Sending Email");
            this.mailSender.send(preparator);
            logger.info("Email sent");
        } catch (MailException ex) {
            String message ="FAILED to send mail!";
            logger.error(message);
            throw new RuntimeException(message, ex);
        }

    }
}
