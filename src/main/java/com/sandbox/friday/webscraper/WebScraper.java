package com.sandbox.friday.webscraper;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.sandbox.friday.model.Item;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class WebScraper {

    public static final String HAS_PIC_AND_WITHIN_40K_MILES = "&hasPic=1&search_distance=40000&postal=19003";
    public static final String CRAIGSLIST_PHILLY_CARS_AND_TRUCKS = "https://philadelphia.craigslist.org/search/cta?query=";
    public static final String CRAIGSLIST_PHILLY_ELECTRONICS = "https://philadelphia.craigslist.org/d/for-sale/search/ela?query=";

    private List<Item> results = new ArrayList<>();

    public List<Item> scrape(String searchQuery) {
        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        try {
            String searchUrl = CRAIGSLIST_PHILLY_CARS_AND_TRUCKS.concat(URLEncoder.encode(searchQuery, "UTF-8")).concat(HAS_PIC_AND_WITHIN_40K_MILES);
            HtmlPage page = client.getPage(searchUrl);
            List<HtmlElement> items = page.getByXPath("//li[@class='result-row']") ;

            if(items.isEmpty()) {
                System.out.println("No results found. Size=".concat(String.valueOf(items.size())));
            } else {
                items.forEach(result -> {
                    HtmlAnchor itemAnchor = result.getFirstByXPath(".//div/h3/a[@class='result-title hdrlnk']");
                    HtmlElement spanPrice = result.getFirstByXPath(".//a/span[@class='result-price']");

                    Item item = new Item();

                    item.setPrice(spanPrice == null ? BigDecimal.ZERO : new BigDecimal(formatPrice(spanPrice.asText())));
                    item.setTitle(itemAnchor.asText());
                    item.setUrl(itemAnchor.getHrefAttribute());
                    System.out.println("item.toString() = " + item.toString());
                    results.add(item);
                });

                return results;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return results;
    }

    private String formatPrice(String spanPrice) {
        return spanPrice.replaceAll("[$,]", "");
    }
}
