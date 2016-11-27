package kurzy.men.services.api.configuration;

/**
 * Poskytuje konfiguracni volby, ktere jsou sice staticke (podobne jako konstanty), ale chceme si zachovat moznost menit jejich hodnoty bez rekompilace celeho ekosystemu.
 */
public interface ConfigurationService {
    /**
     * Vraci hodnoty konfigurace
     * @param option prvek ze seznamu konfiguracnich voleb Enum
     * @return hodnota konfiguracni volby
     */
    String getOption(ConfigurationOption option);

}
