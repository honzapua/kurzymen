package kurzy.men.services.impl.configuration;

import kurzy.men.annotation.ToDo;
import kurzy.men.constant.ApplicationConst;
import kurzy.men.services.api.configuration.ConfigurationOption;
import kurzy.men.services.api.configuration.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * V soucasne dobe je resena implementace z ApplicationConst
 * Technicky modul
 */
@ToDo("Vyresit ukladani voleb, patrne spring singleton")
@Service
public class ConfigurationServiceBean implements ConfigurationService {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationConst.LOGGER_COMMON);

    @Override
    public String getOption(ConfigurationOption option) {
        logger.info("About to get value for option {}", option);
        if (option == null) {
            final String message = "option can't be null";
            logger.error(message);
            throw new RuntimeException(message);
        }

        switch (option) {
            case DEFAULT_FIXER_BASE:
                return ApplicationConst.DEFAULT_FIXER_BASE;
            case APPLICATION_FROM_ADDRESS:
                return ApplicationConst.APPLICATION_FROM_ADDRESS;
            default:
                final String message = String.format("Unsupported option '%s'.", option.name());
                logger.error(message);
                throw new RuntimeException(message);
        }
    }

}
