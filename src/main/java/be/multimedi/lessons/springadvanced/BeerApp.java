package be.multimedi.lessons.springadvanced;

import be.multimedi.lessons.springadvanced.beers.Beer;
import be.multimedi.lessons.springadvanced.beers.BeerRepository;
import be.multimedi.lessons.springadvanced.beers.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;
import java.util.List;

@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class BeerApp {
    public final static String USR = "homer";
    public final static String PSW = "password";
    public final static String QUERY_USERS = "select name, passwordbc, enabled from Users where name = ?";
    public final static String QUERY_AUTHORITY = "select name, role from Users where name = ?";

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(BeerApp.class, args);

//        BeerRepository repo = ctx.getBean(BeerRepository.class);
//        BeerService service = ctx.getBean(BeerService.class);
//        System.out.println(repo.getBeerById(4));
//
//        service.orderBeers("an Order2", new int [][] {{4,-10}, {4,-5}, {4,-5}});
//
//        System.out.println(repo.getBeerById(4));

    }

    @Bean
    public String myString() {
        return "hello World";
    }
}
