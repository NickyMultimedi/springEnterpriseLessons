package be.multimedi.lessons.springadvanced.beers;

import be.multimedi.lessons.springadvanced.beers.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.stream.Stream;

@Transactional
public interface BeerRepository extends JpaRepository<Beer, Integer> {

    default Beer getBeerById(int id) {
        return findById(id).orElse(null);
    }

    @Query(name = "findByAlcohol")
    List<Beer> getBeerByAlcohol(double alcohol);

    @Transactional
    default void updateBeer(Beer b) {
        save(b);
    }

    public List<Beer> findByAlcoholOrderByNameAsc(float alcohol);
    public List<Beer> findBeerByAlcoholBetween(float min, float max);
    public List<Beer> findBeerByAlcoholGreaterThan(float min);
    public List<Beer> findBeerByAlcoholLessThan(float max);
    public List<Beer> findByAlcoholOrName(float alcohol, String name);
    public List<Beer> findByNameLikeOrderByName(String name);
    public List<Beer> findFirst10ByAlcohol(float alcohol);
    public Stream<Beer> findByStockLessThan(int stock);

    @Transactional
    @Modifying
    @Query("update Beer b set b.price=b.price*?1")
    public int updatePrice(float perc);
}
