package kurzy.men.services.api.reportformatter.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Vstupni data pro formatovac emailu. Potrebujeme vedet, ktere meny maji
 * pozadavany rozdil v kurzech. Je jich dynamicky pocet, proto pouzita kolekce List
 */
public class ReportDataDTO implements Serializable {
    private String caption; //nazev obecne - u nas subject mailu
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

    @Override
    public String toString() {
        return "ReportDataDTO{" +
                "caption='" + caption + '\'' +
                ", entries=" + entries +
                ", recipients=" + recipients +
                '}';
    }
}
