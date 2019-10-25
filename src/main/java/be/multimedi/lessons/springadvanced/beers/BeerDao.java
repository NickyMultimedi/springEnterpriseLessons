package be.multimedi.lessons.springadvanced.beers;

import java.util.List;

public interface BeerDao {
    String getBeerById(int id);
    void setStock(int id, int stock);
    List<String> getBeerByAlcohol(float alcohol);
}
