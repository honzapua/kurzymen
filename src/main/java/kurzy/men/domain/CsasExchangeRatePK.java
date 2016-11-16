package kurzy.men.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by honzapua on 16.11.2016.
 */
@Embeddable
public class CsasExchangeRatePK implements Serializable {

    @Column(name = "short_name", length = 3, nullable = false)
    @NotNull
    private String shortName;

    @Column(name ="valid_from", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date validFrom;

    @Column(name = "name", nullable = false)
    @NotNull
    private String name;

    @Column(name = "country", nullable = false)
    @NotNull
    private String country;

    @Column(name = "amout", nullable = false)
    @NotNull
    private Long amount;

    @Column(name = "val_buy", nullable = false)
    @NotNull
    private Double valBuy;

    @Column(name = "val_sel", nullable = false)
    @NotNull
    private Double valSell;

    @Column(name = "val_mid", nullable = false)
    @NotNull
    private Double valMid;

    @Column(name = "curr_buy", nullable = false)
    @NotNull
    private Double currBuy;

    @Column(name = "curr_sell", nullable = false)
    @NotNull
    private Double currSell;

    @Column(name = "curr_mid", nullable = false)
    @NotNull
    private Double currMid;

    @Column(name = "move", nullable = false)
    @NotNull
    private Double move;

    @Column(name = "cnb_mid", nullable = false)
    @NotNull
    private Double cnbMid;

    @Column(name = "version", nullable = false)
    @NotNull
    private String version;

    @Column(name = "exchange_reference_id", nullable = false)
    @NotNull
    private Long exchangeReferenceId;

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

    public Long getExchangeReferenceId() {
        return exchangeReferenceId;
    }

    public void setExchangeReferenceId(Long exchangeReferenceId) {
        this.exchangeReferenceId = exchangeReferenceId;
    }
}
