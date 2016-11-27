package kurzy.men.services.api.currencycomparator;

/**
 * Vlastni sluzba, ktera provadi Bussiness logiku
 * Jestli je rozdil v kurzech  &gt; (vetsi nez) 0.01 alespon v jedne mene, tak odesle mail
 */
public interface CurrencyComparatorService {

    /**
     * Stahuje data, vybira meny podle business pozadavku, formatuje email a odesila jej
     */
    void compareAndReport();
}
