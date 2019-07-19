package trackit.demon.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class SiteData {
    private String siteUrl;
    private String xmlPathToPriceElement;
    private double startedPrice;
    private double finishPrice;
    private boolean increase;

    public SiteData(String siteUrl, String xmlPathToPriceElement, double startedPrice, double finishPrice) {
        this.siteUrl = siteUrl;
        this.xmlPathToPriceElement = xmlPathToPriceElement;
        this.startedPrice = startedPrice;
        this.finishPrice = finishPrice;
    }

    public SiteData(String siteUrl, String xmlPathToPriceElement, double startedPrice, boolean increase) {
        this.siteUrl = siteUrl;
        this.xmlPathToPriceElement = xmlPathToPriceElement;
        this.startedPrice = startedPrice;
        this.increase = increase;
    }
}
