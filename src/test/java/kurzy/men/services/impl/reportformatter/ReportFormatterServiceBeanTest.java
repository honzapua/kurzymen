package kurzy.men.services.impl.reportformatter;

import kurzy.men.services.api.mailservice.dto.MailDTO;
import kurzy.men.services.api.reportformatter.ReportFormatterService;
import kurzy.men.services.api.reportformatter.dto.ReportDataDTO;
import kurzy.men.services.api.reportformatter.dto.ReportDataEntryDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by honzapua on 27.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportFormatterServiceBeanTest {

    @Autowired
    private ReportFormatterService reportFormatterService;

    @Test(expected = NullPointerException.class)
    public void doesNotAcceptNull() throws Exception {
        reportFormatterService.format(null);
    }

    @Test
    public void formats() throws Exception {
        MailDTO expected = createExpected();
        MailDTO actual = reportFormatterService.format(createInputData());

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    private ReportDataDTO createInputData() throws Exception{
        ReportDataDTO result = new ReportDataDTO();
        result.setCaption("caption");
        result.getRecipients().add("r1");
        result.getRecipients().add("r2");

        ReportDataEntryDTO entry = new ReportDataEntryDTO();
        entry.setDescription("d1");
        entry.setValueDifference(1D);
        entry.setCsasValue(2D);
        entry.setFixerValue(3D);
        result.getEntries().add(entry);

        entry = new ReportDataEntryDTO();
        entry.setDescription("d2");
        entry.setValueDifference(4D);
        entry.setCsasValue(5D);
        entry.setFixerValue(6D);

        result.getEntries().add(entry);
        return result;
    }

    private MailDTO createExpected() throws Exception{
        MailDTO result = new MailDTO();
        result.setSubject("caption");
        result.getRecipients().add("r1");
        result.getRecipients().add("r2");

        //Stringbuilder vyhodny na nekonecne spojovani
        StringBuilder sb = new StringBuilder();
        sb.append("Report kurzu men: ");
        sb.append("\n\n");

        sb.append(" - CSAS: ").append(2D).append(", Fixer: ").append(String.format("%.3f", 3D)).append(String.format(", rozdil: %.3f. (%s)",1D, "d1"));
        sb.append("\n");
        sb.append(" - CSAS: ").append(5D).append(", Fixer: ").append(String.format("%.3f", 6D)).append(String.format(", rozdil: %.3f. (%s)",4D, "d2"));
        sb.append("\n");
        result.setBody(sb.toString());

        return result;
    }

}
