package be.multimedi.lessons.springadvanced.beers;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Repository("beerRepo")
@Primary
public class BeerMariaDbRepository implements BeerRepository {
    EntityManager manager;

    @PersistenceContext
    public void setEntityManager(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    @Transactional
    public Beer getBeerById(int id) {
        return manager.find(Beer.class, id);
    }

    @Override
    @Transactional
    public List<Beer> getBeerByAlcohol(double alcohol) {
        TypedQuery<Beer> query = manager.createNamedQuery("findByAlcohol", Beer.class);
        query.setParameter(1, alcohol);

        List<Beer> beers = query.getResultList();
        return beers;
    }

    @Override
    @Transactional
    public void updateBeer(Beer beer) {
        manager.merge(beer);
    }
}
