package trackit.demon.html_parser;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Collection;

@Data
@NoArgsConstructor
public class SearchValueInHtml {
    private String priceValue;
    private String xPath;
    private Collection<Element> elementCollection = new ArrayList<>();

    private boolean searchPriceValue(Elements elements, boolean recordSimilarResults) {
        for (Element element : elements) {
            if (recordSimilarResults) {
                elementCollection.add(element);
                //System.out.println("-----\n" + element.ownText().replace(",", ".") + "\n-----");
            }
            if (element.className().replaceAll("\\s+", "").contains("price") &&
                    !element.className().replaceAll("\\s+", "").contains("old")) {
                this.priceValue = element.ownText().replaceAll("[^0-9]", "");
                this.xPath = element.cssSelector();

                //System.out.println(priceValue);
                //System.out.println(xPath);
            }
        }

        return this.priceValue != null && this.xPath != null ? true : false;
    }

    private void setElementData(Element element) {
        this.priceValue = element.ownText().replaceAll("[^0-9]", "");
        this.xPath = element.cssSelector();

        System.out.println(priceValue);
        System.out.println(xPath);
    }

    private String searchCurrentPriceValue(Document document, String xPath) {
        String result = null;

        result = document.select(xPath).text().replaceAll("[^0-9]", "");

        return result;
    }

    public boolean getData(String url, String searchValue) {
        try {
            Document doc = Jsoup.connect(url).get();
            // search input price
            Elements elementsMatchingOwnText = doc.getElementsMatchingOwnText(searchValue);

            boolean status = searchPriceValue(elementsMatchingOwnText, true);

            if (status) {
                xPath = elementCollection.toArray()[0].toString();
            }

            System.out.println("Operation status: " + status);
            return status;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
