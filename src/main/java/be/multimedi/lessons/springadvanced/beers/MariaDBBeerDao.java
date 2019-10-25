package be.multimedi.lessons.springadvanced.beers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository("mariaDBImpl")
public class MariaDBBeerDao implements BeerDao {
    private final static String QUERY_ID = "select Name, Alcohol, Price, Stock from Beers Where Id=?";
    private final static String UPDATE_STOCK = "Update Beers Set Stock = ? Where Id=?";

    JdbcTemplate template;

    @Override
    public String getBeerById(int id) {
        String beer = "";
        Map<String, Object> result = template.queryForMap(QUERY_ID, id);
        beer = String.format("%s %s %s %s",
                result.get("name"),
                result.get("alcohol"),
                result.get("price"),
                result.get("stock")
                );
        return beer;
    }

    @Override
    public void setStock(int id, int stock) {
        template.update(UPDATE_STOCK,stock , id);
    }

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }
}
