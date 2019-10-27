package be.multimedi.lessons.springadvanced.orders;

import be.multimedi.lessons.springadvanced.beers.Beer;
import be.multimedi.lessons.springadvanced.beers.BeerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerOrderMariaDbRepositoryTest {
    @Autowired
    BeerOrderRepository repo;

    @Autowired
    BeerRepository beerRepo;

    @Test
    void saveOrder() {
        BeerOrder testOrder = new BeerOrder();
        testOrder.setName("TestBeerOrder2");
        BeerOrderItem item = new BeerOrderItem();
        item.setNumber(2);
        item.setBeer(beerRepo.getBeerById(1));
        List<BeerOrderItem> items = new ArrayList<>();
        items.add(item);
        testOrder.setItems(items);
        int id = repo.saveOrder(testOrder);

        assertEquals(testOrder, repo.getBeerOrderById(id));
    }

    @Test
    void getBeerOrderById() {
        BeerOrder order = repo.getBeerOrderById(1);
        assertEquals("TestOrder", order.getName());
    }
}
