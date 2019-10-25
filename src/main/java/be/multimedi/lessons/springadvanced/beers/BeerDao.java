package be.multimedi.lessons.springadvanced.beers;

public interface BeerDao {
    String getBeerById(int id);
    void setStock(int id, int stock);
}
