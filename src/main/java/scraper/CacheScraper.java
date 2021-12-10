package scraper;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CacheScraper implements Scraper{
    @Override @SneakyThrows
    public Home parse(String url) {

        Connection connection = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        Statement statement = connection.createStatement();

        String query = String.format("select count(*) as count from homes where url='%s'", url);
        System.out.println("Connecting to DB...");
        ResultSet rs = statement.executeQuery(query);
        int count = rs.getInt("count");
        if (count == 0) {
            DefaultScraper defaultScraper = new DefaultScraper();
            Home home = defaultScraper.parse(url);
            String insertedQuery = String.format("insert into homes(url, price, beds, baths, garage) values('%s', '%d', '%f', '%f', '%f')", url, home.getPrice(), home.getBeds(), home.getBaths(), home.getGarages());
            statement.executeUpdate(insertedQuery);
            System.out.println("Retrieving info from website...");
            return home;
        } else {
            String selectQuery = String.format("select * from homes where url='%s'", url);
            ResultSet result = statement.executeQuery(selectQuery);
            Home home = new Home(rs.getInt("price"), Double.parseDouble(rs.getString("beds").replaceAll(",", ".")), Double.parseDouble(rs.getString("baths").replaceAll(",", ".")), Double.parseDouble(rs.getString("garage").replaceAll(",", ".")));
            System.out.println("Retrieving info from DB...");
            return home;
        }
    }
}
