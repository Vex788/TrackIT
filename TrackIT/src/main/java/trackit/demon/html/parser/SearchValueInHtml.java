package trackit.demon.html.parser;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Element;
import trackit.demon.html.parser.web.structure.InvestingComStructure;
import trackit.demon.html.parser.web.structure.WebShopStructure;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
public class SearchValueInHtml {
    private String siteTitle;
    private String priceValue;
    private Collection<Element> elementCollection = new ArrayList<Element>();

    public boolean getData(String url, String v1, String v2) {
        try {
            String[] data = null;

            if (url != null && !url.isEmpty()) {
                WebShopStructure wss = new WebShopStructure();

                data = wss.getCurrentPrice(url);
            }
            if (v1 != null && !v1.isEmpty() &&
                v2 != null && !v2.isEmpty()) {
                InvestingComStructure ics = new InvestingComStructure();

                data = ics.getCurrentPrice(v1, v2);
            }

            boolean operation = false;

            if (data != null) {
                siteTitle = data[0];
                priceValue = data[1];
                operation = true;
            }

            return operation;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
