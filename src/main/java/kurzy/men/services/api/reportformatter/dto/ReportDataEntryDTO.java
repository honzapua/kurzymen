package kurzy.men.services.api.reportformatter.dto;

import java.io.Serializable;

/**
 * Data jsou v kolekci a Kolekce je v ReportDataDTO
 */
public class ReportDataEntryDTO implements Serializable {
    private String description;
    private Double fixerValue;
    private Double csasValue;
    private Double valueDifference;

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

    public Double getValueDifference() {
        return valueDifference;
    }

    public void setValueDifference(Double valueDifference) {
        this.valueDifference = valueDifference;
    }

    @Override
    public String toString() {
        return "ReportDataEntryDTO{" +
                "description='" + description + '\'' +
                ", fixerValue=" + fixerValue +
                ", csasValue=" + csasValue +
                ", valueDifference=" + valueDifference +
                '}';
    }
}
