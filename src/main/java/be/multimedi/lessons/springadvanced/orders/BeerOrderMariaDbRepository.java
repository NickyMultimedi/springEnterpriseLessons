package be.multimedi.lessons.springadvanced.orders;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

@Repository("beerOrderRepo")
@Transactional
public class BeerOrderMariaDbRepository implements BeerOrderRepository {
    EntityManagerFactory managerFactory;

    @PersistenceUnit
    public void setManagerFactory(EntityManagerFactory factory) {
        this.managerFactory = factory;
    }

    @Override
    public int saveOrder(BeerOrder order) {
        EntityManager manager = managerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        manager.persist(order);

        transaction.commit();
        manager.close();
        return order.getId();
    }

    @Override
    public BeerOrder getBeerOrderById(int id) {
        EntityManager manager = managerFactory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();
        transaction.begin();

        BeerOrder order = manager.find(BeerOrder.class, id);

        transaction.commit();
        manager.close();
        return order;
    }
}
