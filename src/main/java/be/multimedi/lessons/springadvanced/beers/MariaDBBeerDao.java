package be.multimedi.lessons.springadvanced.beers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository("mariaDBImpl")
public class MariaDBBeerDao implements BeerDao {
    private final static String QUERY_ID = "select Name, Alcohol, Price, Stock from Beers Where Id=?";
    private final static String QUERY_ALCOHOL = "select Name, Alcohol, Price, Stock from Beers Where Alcohol=?";
    private final static String UPDATE_STOCK = "Update Beers Set Stock = ? Where Id=?";


    JdbcTemplate template;

    @Override
    public String getBeerById(int id) {
        Map<String, Object> result = template.queryForMap(QUERY_ID, id);
        return mapToBeerString(result);
    }

    @Override
    public void setStock(int id, int stock) {
        template.update(UPDATE_STOCK,stock , id);
    }

    @Override
    public List<String> getBeerByAlcohol(float alcohol) {
        List<Map<String, Object>> resultList = template.queryForList(QUERY_ALCOHOL, alcohol);
        return resultList.stream()
                .map(this::mapToBeerString)
                .collect(Collectors.toList());
    }

    @Autowired
    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    private String mapToBeerString(Map<String, Object> beerMap) {
        return String.format("%s %s %s %s",
                beerMap.get("name"),
                beerMap.get("alcohol"),
                beerMap.get("price"),
                beerMap.get("stock")
        );
    }
}
