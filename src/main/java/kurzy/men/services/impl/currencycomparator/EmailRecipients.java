package kurzy.men.services.impl.currencycomparator;

import kurzy.men.constant.ApplicationConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Zpristupnuje seznam adresatu z configuracniho resourcu recipients.txt.template
 * Singleton si pri prvnim pouziti natahne recipients.txt
 * Email je kazdy na jedne radce, neosekavaji se bile znaky,
 * Sekvence eol konce radky neni soucasti (jedina se odsekne),
 * Prazdne radky se preskakuji (Opet bez orezavani bilych znaku),
 * Radek se povazuje za komentar zacina-li znakem #
 */
public final class EmailRecipients {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConst.LOGGER_CURRENCY_COMPARATOR_SERVICE);

    private static final String RECIPIENTS_TXT_RESOURCE_NAME = "recipients.txt";

    private static final String COMMENT_SIGN = "#";

    private static final List<String> RECIPIENTS = loadRecipients();

    public static List<String> getRecipients() {
        return RECIPIENTS;
    }

    private static List<String> loadRecipients() {
        logger.info("About to load recipients from resource");
        try (InputStream is = EmailRecipients.class.getResourceAsStream(RECIPIENTS_TXT_RESOURCE_NAME);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            List<String> result = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty() || line.startsWith(COMMENT_SIGN)) {
                    continue;
                }
                logger.info("Adding '{}'", line);
                result.add(line);
            }
            logger.info("Loaded {} recipients", result.size());
            return Collections.unmodifiableList(result);
        } catch (IOException ex) {
            final String message = "Failed to load recipients from resource";
            logger.error(message, ex);
            throw new RuntimeException(message, ex);
        }
    }

    private EmailRecipients() {
    }
}
