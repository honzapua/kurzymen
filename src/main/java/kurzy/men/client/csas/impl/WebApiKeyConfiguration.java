package kurzy.men.client.csas.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * package private, nejdi po ni dedit je to Singleton a provola se jen jednou na zacatku
 */
final class WebApiKeyConfiguration {

    private static final String PROPERTIES_FILES_RESOURCE_NAME = "webapikey.properties";
    private static final String WEB_API_KEY_PROPERTY_NAME = "webapikey";
    private static final String WEB_API_KEY = loadWebAPIKey();

    public static String getWebApiKey(){
        return WEB_API_KEY;
    }

    private static String loadWebAPIKey(){
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
