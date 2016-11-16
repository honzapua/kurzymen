package kurzy.men.client.csas.api.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Kopiruji promene presne podle API i kdyz ne vsechny vyuziju, ale v budoucnu to muze zkomplikovat.
 */
public class ExchangeRateDTO implements Serializable {

    private String shortName;
    private Date validFrom;
    private String name;
    private String country;
    private Long amount;
    private Double valBuy;
    private Double valSell;
    private Double valMid;
    private Double currBuy;
    private Double currSell;
    private Double currMid;
    private Double move;
    private Double cnbMid;
    private String version;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Double getValBuy() {
        return valBuy;
    }

    public void setValBuy(Double valBuy) {
        this.valBuy = valBuy;
    }

    public Double getValSell() {
        return valSell;
    }

    public void setValSell(Double valSell) {
        this.valSell = valSell;
    }

    public Double getValMid() {
        return valMid;
    }

    public void setValMid(Double valMid) {
        this.valMid = valMid;
    }

    public Double getCurrBuy() {
        return currBuy;
    }

    public void setCurrBuy(Double currBuy) {
        this.currBuy = currBuy;
    }

    public Double getCurrSell() {
        return currSell;
    }

    public void setCurrSell(Double currSell) {
        this.currSell = currSell;
    }

    public Double getCurrMid() {
        return currMid;
    }

    public void setCurrMid(Double currMid) {
        this.currMid = currMid;
    }

    public Double getMove() {
        return move;
    }

    public void setMove(Double move) {
        this.move = move;
    }

    public Double getCnbMid() {
        return cnbMid;
    }

    public void setCnbMid(Double cnbMid) {
        this.cnbMid = cnbMid;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
