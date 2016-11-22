package kurzy.men.client.csas.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * package private - vyuziva jen implementace klienta, zverejnuji jen to co ma byt videt.
 * nejdi po ni dedit (final) je to Singleton a vola se pri kazdem provolani toho.
 */
final class WebApiKeyConfiguration {

    private static final String PROPERTIES_FILES_RESOURCE_NAME = "webapikey.properties"; //resource na classpath=> samostatny soubor
    private static final String WEB_API_KEY_PROPERTY_NAME = "webapikey"; //nazev vlastni property
    private static final String WEB_API_KEY = loadWebAPIKey(); //vlastni hodnota z mailu sporitelny

    public static String getWebApiKey(){
        return WEB_API_KEY;
    } //vystavuje se ven

    private static String loadWebAPIKey(){ //zvola se jednou
        try (InputStream is = WebApiKeyConfiguration.class.getResourceAsStream(PROPERTIES_FILES_RESOURCE_NAME)){//na class path
            Properties properties = new Properties();
            properties.load(is);
            return properties.getProperty(WEB_API_KEY_PROPERTY_NAME);
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private WebApiKeyConfiguration() {
    }
}
