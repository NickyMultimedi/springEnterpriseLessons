package be.multimedi.lessons.springadvanced.brewers;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrewerRepository extends JpaRepository<Brewer, Integer> {

    Brewer findBrewerById(int id);
}
