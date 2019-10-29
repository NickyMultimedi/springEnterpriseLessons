package be.multimedi.lessons.springadvanced.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BeerOrderRepository extends JpaRepository<BeerOrder, Integer> {
    default int saveOrder(BeerOrder order) {
        save(order);
        return order.getId();
    }
    default BeerOrder getBeerOrderById(int id) {
        return findById(id).orElse(null);
    }
}
