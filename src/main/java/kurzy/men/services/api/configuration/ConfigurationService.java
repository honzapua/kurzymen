package kurzy.men.services.api.configuration;

/**
 * Poskytuje konfiguracni volby, ktere jsou sice staticke (podobne jako konstanty), ale chceme si zachovat moznost menit jejich hodnoty bez rekompilace celeho ekosystemu.
 */
public interface ConfigurationService {
    /**
     * Vraci hodnoty konfigurace
     * @param option
     * @return
     */
    String getOption(ConfigurationOption option);

}
