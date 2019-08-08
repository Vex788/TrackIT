package trackit.demon.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
public class SiteData {
    private String siteTitle = null;
    private String siteUrl = null;
    private String currencyCodes = null;
    private double startedPrice = 0.0d;
    private double finishPrice = 0.0d;
    private boolean increase = false;
}
