package be.multimedi.lessons.springadvanced.beers;

import be.multimedi.lessons.springadvanced.beers.exceptions.InvalidAmountException;
import be.multimedi.lessons.springadvanced.beers.exceptions.InvalidBeerException;
import be.multimedi.lessons.springadvanced.orders.BeerOrder;
import be.multimedi.lessons.springadvanced.orders.BeerOrderItem;
import be.multimedi.lessons.springadvanced.orders.BeerOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.transaction.TestTransaction;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Service
@SpringBootTest
@Transactional
@DirtiesContext
@WithMockUser(username = "Nick", roles = "ADULT")
class StandardBeerServiceTest {
    BeerService service;
    BeerOrderRepository orderRepo;
    BeerRepository beerRepo;

    @Autowired
    public void setBeerRepo(BeerRepository beerRepo) {
        this.beerRepo = beerRepo;
    }

    @Autowired
    void setOrderRepo(BeerOrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    @Autowired
    void setService(BeerService service) {
        this.service = service;
    }

    @Test
    void orderBeer() {
        int id = service.orderBeer("MyTestOrder", 1, 2);
        assertNotEquals(0, id);

        BeerOrder order = orderRepo.getBeerOrderById(id);
        assertEquals("MyTestOrder", order.getName());

        BeerOrderItem item = order.getItems().get(0);
        assertEquals(2, item.getNumber());

        Beer beer = item.getBeer();
        assertEquals(1, beer.getId());
    }

    @Test
    void orderBeerAdjustsBeerStock() {
        int startStock = beerRepo.getBeerById(1).getStock();
        service.orderBeer("MyTestOrder", 1, 2);
        int endStock = beerRepo.getBeerById(1).getStock();
        assertTrue(TestTransaction.isFlaggedForRollback());
        assertEquals(startStock - 2, endStock);
    }

    @Test
    void orderBeerThrowsExceptions() {
        assertThrows(InvalidAmountException.class, () -> service.orderBeer("MyTestOrder", 1, 200));
        assertThrows(InvalidBeerException.class,() -> service.orderBeer("MyTestOrder", 100, 2));
    }

    @Test
    void orderBeerRollsBackAfterException() {
        int idAfterInvalidAmount = 0;

        try {
            idAfterInvalidAmount = service.orderBeer("MyTestOrder", 1, 200);
        } catch (Exception e) { }

        assertEquals(0, idAfterInvalidAmount);
        assertEquals(100, beerRepo.getBeerById(1).getStock());
    }

    @Test
    void orderBeerRollsBackAfterInvalidBeerException() {
        int idAfterInvalidBeer = 0;

        try {
            idAfterInvalidBeer = service.orderBeer("MyTestOrder", 100, 2);
        } catch (Exception e) {

        }
        assertEquals(0, idAfterInvalidBeer);
        assertEquals(100, beerRepo.getBeerById(1).getStock());
    }

    @Test
//    @Transactional
    void orderBeers() {
        int id = service.orderBeers("MySecondTestOrder", new int[][] {{1,2}, {1,1}});
        assertNotEquals(0, id);

        BeerOrder order = orderRepo.getBeerOrderById(id);
        assertEquals("MySecondTestOrder", order.getName());

        BeerOrderItem item1 = order.getItems().get(0);
        BeerOrderItem item2 = order.getItems().get(1);
        assertEquals(2, item1.getNumber());
        assertEquals(1, item2.getNumber());

        Beer beer1 = item1.getBeer();
        assertEquals(1, beer1.getId());
        Beer beer2 = item2.getBeer();
        assertEquals(1, beer2.getId());
    }

    @Test
    void orderBeersAdjustsBeerStock() {
        int id = service.orderBeers("MySecondTestOrder", new int[][] {{1,2}, {1,1}});
        assertEquals(97, beerRepo.getBeerById(1).getStock());
    }

    @Test
    void orderBeersThrowsExceptions() {
        assertThrows(InvalidAmountException.class, () -> service.orderBeers("MySecondTestOrder", new int[][] {{1,2}, {1,200}}));
        assertThrows(InvalidBeerException.class, () -> service.orderBeers("MySecondTestOrder", new int[][] {{1,2}, {200,1}}));
    }

    @Test
    @Transactional
    void orderBeersRollsBackAfterInvalidAmountExceptions() {
        int startStock = beerRepo.getBeerById(1).getStock();
        try {
            service.orderBeers("MySecondTestOrder", new int[][] {{1,2}, {1,2}, {1, 200}});
        } catch (Exception e) {
            e.printStackTrace();
        }

        TestTransaction.end();
        assertEquals(startStock, beerRepo.getBeerById(1).getStock());
    }

    @Test
    @Commit
    void orderBeersRollsBackAfterInvalidBeerException() {
        int startStock = beerRepo.getBeerById(1).getStock();

        try {
            service.orderBeers("MySecondTestOrder", new int[][] {{1,2}, {1,2}, {100, 2}});
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertThrows(UnexpectedRollbackException.class, TestTransaction::end);
//        assertTrue(TestTransaction.isFlaggedForRollback());

        assertEquals(startStock, beerRepo.getBeerById(1).getStock());
    }
}
