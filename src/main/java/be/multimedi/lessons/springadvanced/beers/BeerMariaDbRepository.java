package be.multimedi.lessons.springadvanced.beers;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository("beerRepo")
@Primary
public class BeerMariaDbRepository implements BeerRepository {
    EntityManagerFactory managerFactory;

    @PersistenceUnit
    public void setManagerFactory(EntityManagerFactory managerFactory) {
        this.managerFactory = managerFactory;
    }

    @Override
    public Beer getBeerById(int id) {
        EntityManager manager = managerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        Beer foundBeer = manager.find(Beer.class, id);

        transaction.commit();
        manager.close();

        return foundBeer;
    }

    @Override
    public List<Beer> getBeerByAlcohol(double alcohol) {
        EntityManager manager = managerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        TypedQuery<Beer> query = manager.createNamedQuery("findByAlcohol", Beer.class);
        query.setParameter(1, alcohol);

        transaction.begin();

        List<Beer> beers = query.getResultList();

        transaction.commit();
        manager.close();
        return beers;
    }

    @Override
    public void updateBeer(Beer beer) {
        EntityManager manager = managerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        manager.merge(beer);

        transaction.commit();
        manager.close();
    }
}
