package kurzy.men.schedulers.impl;

import kurzy.men.constant.ApplicationConst;
import kurzy.men.services.api.currencycomparator.CurrencyComparatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Spousti business logiku kazdou hodinu. V pripade ApplicationConst.ENABLE_SCHEDULER = true;
 */
@Component
public class CurrencyComparatorSchedulerBean {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationConst.LOGGER_CURRENCY_COMPARATOR_SCHEDULER);

    @Autowired
    private CurrencyComparatorService currencyComparatorService;

    @Scheduled(fixedDelay = 1 * 1000 * 60 * 60) //kazdou hodinu
    public void schedule(){
        if(!ApplicationConst.ENABLE_SCHEDULER){
            logger.warn("Scheduler is disabled; skipping");
            return;
        }

        logger.info("Launching currency comparator JOB");
        currencyComparatorService.compareAndReport();
        logger.info("Currency comparator JOB completed");
    }
}
