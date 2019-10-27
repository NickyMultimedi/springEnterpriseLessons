package be.multimedi.lessons.springadvanced.beers;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

//@ExtendWith(SpringExtension.class)
@SpringBootTest
class BeerRepositoryTest {
    @Autowired
    BeerRepository repo;

    @Test
    void getBeerById() {
        Beer repoBeer = repo.getBeerById(1);
        assertEquals("TestBeer", repoBeer.getName());
    }

    @Test
    void getBeerByIdFindsBeer() {
        Beer repoBeer = repo.getBeerById(1);
        assertNotNull(repoBeer);
        assertEquals(Beer.class, repoBeer.getClass());
    }

    @Test
    void updateBeer() {
        Beer repoBeer = repo.getBeerById(1);
        repoBeer.setName("TestedBeer");
        repo.updateBeer(repoBeer);
        assertEquals(repoBeer, repo.getBeerById(1));
    }

    @Test
    void getBeerByAlcohol() {
        List<Beer> testBeers = repo.getBeerByAlcohol(7.0);
        Beer testBeer = testBeers.get(0);
        assertEquals(repo.getBeerById(1), testBeer);
    }

    @Test
    void getBeerByAlcoholFindsListOfBeers() {
        List<Beer> testBeers = repo.getBeerByAlcohol(7.0);

        assertNotNull(testBeers);

        Beer testBeer = testBeers.get(0);
        assertNotNull(testBeer);
        assertEquals(Beer.class, testBeer.getClass());
    }
}
