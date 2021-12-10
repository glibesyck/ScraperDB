package scraper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CacheScraperTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Test
    public void parse() {
        String firstURL = "https://www.newhomesource.com/specdetail/164-victoria-peak-loop-dripping-springs-tx-78620/2108551";
        CacheScraper cacheScraper = new CacheScraper();
        System.setOut(new PrintStream(outputStreamCaptor));
        Home firstHome = cacheScraper.parse(firstURL);
        assertEquals(outputStreamCaptor.toString().trim(), "Connecting to DB...\nRetrieving info from DB...");
    }
}