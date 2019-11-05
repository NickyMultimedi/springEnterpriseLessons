package be.multimedi.lessons.springadvanced.categories;

import be.multimedi.lessons.springadvanced.beers.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findById(int id);
    Category findByBeersContaining(Beer beer);
}
