package kurzy.men.services.impl.configuration;

import kurzy.men.annotation.ToDo;
import kurzy.men.constant.ApplicationConst;
import kurzy.men.services.api.configuration.ConfigurationOption;
import kurzy.men.services.api.configuration.ConfigurationService;
import org.springframework.stereotype.Service;

/**
 * V soucasne dobe je resena implementace z ApplicationConst
 */
@ToDo("Vyresit ukladani voleb, patrne spring singleton")
@Service
public class ConfigurationServiceBean implements ConfigurationService {

    @Override
    public String getOption(ConfigurationOption option) {
        if (option == null) {
            throw new RuntimeException("option can't be null");
        }

        switch (option) {
            case DEFAULT_FIXER_BASE:
                return ApplicationConst.DEFAULT_FIXER_BASE;
            case APPLICATION_FROM_ADDRESS:
                return ApplicationConst.APPLICATION_FROM_ADDRESS;
            default:
                throw new RuntimeException(String.format("Unsupported option '%s'.", option.name()));
        }
    }

}
