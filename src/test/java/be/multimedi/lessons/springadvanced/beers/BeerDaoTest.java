package be.multimedi.lessons.springadvanced.beers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@DirtiesContext
class BeerDaoTest {
    private static final String TEST_BEER = "TestBeer 7.0 2.75 100";

    @Autowired
    BeerDao dao;

    @Test
    void testGetBeerById() {
        String beer = dao.getBeerById(1);
        assertEquals(TEST_BEER, beer);
    }

    @Test
    void getBeerByIdReturnsString() {
        assertEquals(dao.getBeerById(1).getClass(), String.class);
    }

    @Test
    void getBeerByIdFindsRecord() {
        assertNotNull(dao.getBeerById(1));
    }

    @Test
    void testSetStock() {
        dao.setStock(1,20);
        String testBeerWithAdjustedStock = TEST_BEER.replaceAll("100", "20");
        assertEquals(testBeerWithAdjustedStock, dao.getBeerById(1));
    }

    @Test
    void testGetBeerByAlcohol() {
        assertEquals(TEST_BEER, dao.getBeerByAlcohol(7.0f).get(0));
    }

    @Test
    void getBeerByAlcoholFindsRecords() {
        assertNotNull(dao.getBeerByAlcohol(7.0f));
    }

    @Test
    void getBeerByAlcoholReturnsStringBeerList() {
        assertEquals(ArrayList.class, dao.getBeerByAlcohol(7.0f).getClass());
        assertEquals(String.class, dao.getBeerByAlcohol(7.0f).get(0).getClass());

    }
}
