package kurzy.men.constant;

/**
 * Veskery Konstanty davaji se do API modulu vidi sem 100%. Lidem nedavat nic navic. Jen co potrebuji
 */
public final class ApplicationConst {

    public static final long DEFAULT_SERIAL_VERSION_UID = 1L;

    public static final String DEFAULT_FIXER_BASE;

    public static final String FIXER_RATES_URL = "http://api.fixer.io/latest";

    public static final String FIXER_RATES_BASE_URL = "http://api.fixer.io/latest?base={base}";

    public static final String FIXER_HISTORIC_RATES_URL = "http://api.fixer.io/{date}";

    public static final String FIXER_HISTORIC_RATES_WITH_BASE_URL = "http://api.fixer.io/{date}?base={base}";

    public static final String FIXER_RATES_SYMBOLS_URL = "http://api.fixer.io/latest?symbols={symbols}";

    public static final String CSAS_CURRENT_RATES_URL = "https://api.csas.cz/sandbox/webapi/api/v1/exchangerates";

    public static final String CSAS_HISTORIC_EXCHANGE_RATES_URL = "https://api.csas.cz/sandbox/webapi/api/v1/exchangerates/{date}";

    public static final String APPLICATION_FROM_ADDRESS = "info@kurzymen.cz"; //gmail nam to prepise

    static {
        DEFAULT_FIXER_BASE = "CZK"; //fixer na rozdil CSAS musime prepnout na CZK
    }

    private ApplicationConst() {
    }
}
