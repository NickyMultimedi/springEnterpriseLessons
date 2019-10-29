package be.multimedi.lessons.springadvanced.beers;

import be.multimedi.lessons.springadvanced.beers.Beer;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.RepositoryDefinition;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface BeerRepository {
    Beer getBeerById(int id);
    List<Beer> getBeerByAlcohol(double alcohol);
    void updateBeer(Beer b);
}
