package scraper;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class DefaultScraper implements Scraper{
    private static final String priceSelector = ".detail__info-xlrg";
    private static final String bedSelector = ".nhs_BedsInfo";
    private static final String bathSelector = ".nhs_BathsInfo";
    private static final String garageSelector = ".nhs_GarageInfo";

    @Override @SneakyThrows
    public Home parse(String url) {
        Document doc = Jsoup.connect(url).get();
        int price = getPrice(doc);
        double beds = getBed(doc);
        double baths = getBath(doc);
        double garages = getGarage(doc);
        return new Home(price, beds, baths, garages);
    }
    private static int getPrice(Document doc) {
        Element priceTag = doc.selectFirst(priceSelector);
        String priceText = priceTag.text().replaceAll("[^0-9]", "");
        return Integer.parseInt(priceText);
    }
    private static double getBed(Document doc) {
        Element bedsTag = doc.selectFirst(bedSelector);
        String bedsText = bedsTag.text().replaceAll("[^0-9.,]", "");
        return Double.parseDouble(bedsText);
    }
    private static double getBath(Document doc) {
        Element bathTag = doc.selectFirst(bathSelector);
        String bathText = bathTag.text().replaceAll("[^0-9.,]", "");
        return Double.parseDouble(bathText);
    }
    private static double getGarage(Document doc) {
        Element garageTag = doc.selectFirst(garageSelector);
        String garageText = garageTag.text().replaceAll("[^0-9.,]", "");
        return Double.parseDouble(garageText);
    }

}
