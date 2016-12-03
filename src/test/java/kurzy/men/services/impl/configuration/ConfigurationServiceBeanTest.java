package kurzy.men.services.impl.configuration;

import kurzy.men.constant.ApplicationConst;
import kurzy.men.services.api.configuration.ConfigurationOption;
import kurzy.men.services.api.configuration.ConfigurationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by honzapua on 2.12.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigurationServiceBeanTest {
    @Autowired
    private ConfigurationService configurationService;

    @Test(expected = RuntimeException.class)
    public void doesNotAcceptNull() {
        configurationService.getOption(null);
    }

    @Test
    public void applicationFromAddressNotNull() {
        String option = configurationService.getOption(ConfigurationOption.APPLICATION_FROM_ADDRESS);
        Assert.assertNotNull(option);
    }

    @Test
    public void defaultFixerBaseCZK(){
        String actual = configurationService.getOption(ConfigurationOption.DEFAULT_FIXER_BASE);
        String expected = "CZK";
        Assert.assertEquals(actual, expected);
    }


}
