package kurzy.men.services.api.currencycomparator;

/**
 * Vlastni sluzba, ktera provadi Bussiness logiku
 * Jestli je rozdil v kurzech  > 0.01 alespon v jedne mene, tak odesle mail
 */
public interface CurrencyComparatorService {
    void compareAndReport();
}