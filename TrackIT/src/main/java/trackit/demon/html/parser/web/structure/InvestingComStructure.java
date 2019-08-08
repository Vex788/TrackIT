package trackit.demon.html.parser.web.structure;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

@Data
@NoArgsConstructor
public class InvestingComStructure {

    private String urlTemplate = "https://www.google.com/search?q=%s+%s";

    private String buildUrl(String v1, String v2) {
        v1 = v1.toLowerCase();
        v2 = v2.toLowerCase();

        if (v1.equals(v2)) { return null; }

        return String.format(urlTemplate, v1, v2);
    }

    private String deleteAlphabet(String text) {
        return text.replaceAll("[^0-9]", "");
    }
    // v[0] - site title, v[1] - current price
    public String[] getCurrentPrice(String v1, String v2) throws IOException {
        String[] siteData = new String[2];
        String url = buildUrl(v1, v2);
        Document doc = Jsoup.connect(url)
                .header("Accept-Encoding", "gzip, deflate")
                .userAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0")
                .maxBodySize(0)
                .timeout(600000)
                .get();

        String temp = doc.select(".dDoNo").text();

        try {
            siteData[0] = doc.select(".vk_gy").get(0).text() + " " + temp;
            siteData[1] = temp.replace(",", ".").substring(0, temp.indexOf(" "));
        } catch (IndexOutOfBoundsException ex) {
            siteData = null;
        }

        return siteData;
    }
}
