package be.multimedi.lessons.springadvanced.beers;

public interface BeerService {
    int orderBeer(String name, int beerId, int amount);
    int orderBeers(String name, int[][] order);
}
