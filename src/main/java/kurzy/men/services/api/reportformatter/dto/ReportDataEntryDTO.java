package kurzy.men.services.api.reportformatter.dto;

import java.io.Serializable;

/**
 * Created by honzapua on 20.11.2016.
 */
public class ReportDataEntryDTO implements Serializable {
    private String description;
    private Double fixerValue;
    private Double csasValue;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getFixerValue() {
        return fixerValue;
    }

    public void setFixerValue(Double fixerValue) {
        this.fixerValue = fixerValue;
    }

    public Double getCsasValue() {
        return csasValue;
    }

    public void setCsasValue(Double csasValue) {
        this.csasValue = csasValue;
    }
}