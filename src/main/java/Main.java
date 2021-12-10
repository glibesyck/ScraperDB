import scraper.CacheScraper;
import scraper.Home;

public class Main {
    public static void main(String[] args) {
        CacheScraper cacheScraper = new CacheScraper();
        String url = "https://www.newhomesource.com/specdetail/164-victoria-peak-loop-dripping-springs-tx-78620/2108551";
        Home home = cacheScraper.parse(url);
        System.out.println(home.toString());
    }

}
