package trackit.demon.html.parser.web.structure;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

@Data
@NoArgsConstructor
public class WebShopStructure {

    private String[] urls = new String[] {
            "https://m.rozetka.com",
            "https://rozetka.com",
            "https://www.citrus.",
            "https://stylus.",
            "https://comfy.",
            "https://estore.",
            "https://icases.org.",
            "https://allo.",
            "https://www.itbox.",
            "https://eldorado.",
            "https://www.foxtrot.com"
    };

    private String[] selectors = new String[] {
            ".float-lt .g-price-uah",
            ".detail-price-uah span:nth-of-type(1)",
            "#buy-block .price:nth-of-type(2) span",
            ".part-wrap .regular-price",
            ".product-card__actions [itemprop='price']",
            ".product-type-data .special-price .price",
            ".product__price-value",
            "[class='new-price price-sym-7'] .price",
            "[itemprop='price']",
            ".price-value",
            ".flex-wrapper .price__relevant .numb"
    };

    private String getSelector(String url) {
        for (int i = 0; i < urls.length; i++) {
            if (url.startsWith(urls[i])) {
                return selectors[i];
            }
        }
        return null;
    }

    private String deleteAlphabet(String text) {
        return text.replaceAll("[^0-9]", "");
    }
    // v[0] - site title, v[1] - current price
    public String[] getCurrentPrice(String url) throws IOException {
        String[] siteData = new String[2];
        Document doc = Jsoup.connect(url)
                .header("Accept-Encoding", "gzip, deflate")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                .maxBodySize(0)
                .timeout(600000)
                .get();

        Elements elements = doc.select(getSelector(doc.location()));

        if (elements != null && elements.size() > 0) {
            if (doc.title().contains(".")) {
                siteData[0] = doc.title().substring(0, doc.title().indexOf("."));
            } else {
                siteData[0] = doc.title();
            }
            siteData[1] = deleteAlphabet(elements.get(0).ownText()).replace(",", ".");
        }
        if (siteData[1] == null || siteData[1].isEmpty()) {
            siteData = null;
        }

        return siteData;
    }
}
