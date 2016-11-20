package kurzy.men.services.api.reportformatter.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by honzapua on 20.11.2016.
 */
public class ReportDataDTO implements Serializable {
    private String caption;
    private List<ReportDataEntryDTO> entries = new ArrayList<>();
    private List<String> recipients = new ArrayList<>();

    public List<String> getRecipients() {
        return recipients;
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public List<ReportDataEntryDTO> getEntries() {
        return entries;
    }

    public void setEntries(List<ReportDataEntryDTO> entries) {
        this.entries = entries;
    }
}
