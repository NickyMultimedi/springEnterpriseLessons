package be.multimedi.lessons.springadvanced;

import be.multimedi.lessons.springadvanced.beers.BeerDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class BeerApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BeerApp.class, args);

        BeerDao dao = ctx.getBean("mariaDBImpl",BeerDao.class);

        System.out.println(dao.getBeerById(10));

        dao.setStock(10, 200);
        System.out.println(dao.getBeerById(10));

        List<String> beers = dao.getBeerByAlcohol(5.0f);
        beers.stream().forEach(System.out::println);

    }

    @Bean
    public String myString() {
        return "hello World";
    }
}
