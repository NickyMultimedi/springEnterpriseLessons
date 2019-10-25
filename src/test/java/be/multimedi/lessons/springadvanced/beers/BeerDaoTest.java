package be.multimedi.lessons.springadvanced.beers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class BeerDaoTest {
    @Autowired
    BeerDao dao;

    @Test
    void testGetBeerById() {
        String beer = dao.getBeerById(1);
        assertEquals("TestBeer 7.0 2.75 100", beer);
    }

    @Test
    void getBeerByIdReturnsString() {
        assertEquals(dao.getBeerById(1).getClass(), String.class);
    }

    @Test
    void getBeerByIdReturnsNotNull() {
        assertNotNull(dao.getBeerById(1));
    }
}
