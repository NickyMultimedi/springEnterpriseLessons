package be.multimedi.lessons.springadvanced.beers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/beers")
public class RestBeerService {
    BeerRepository beerRepo;

    @Autowired
    public void setBeerRepo(BeerRepository beerRepo) {
        this.beerRepo = beerRepo;
    }

    @GetMapping(value = "{id}",
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResponseEntity<Beer> getBeer(@PathVariable("id") int id) {
        Beer beer = beerRepo.getBeerById(id);
        boolean beerExists = beer != null;

        if (!beerExists) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(beer, HttpStatus.OK);
        }
    }

    @GetMapping(
            params = {"alcohol"},
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResponseEntity<BeerList> getBeersByAlcohol(@RequestParam("alcohol") double alcohol) {
        List<Beer> beers = beerRepo.getBeerByAlcohol(alcohol);
        if (beers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            BeerList beerList = new BeerList(beers);
            return new ResponseEntity<>(beerList, HttpStatus.OK);
        }
    }

    @GetMapping(
            produces = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            },
            params = {"name"}
    )
    public ResponseEntity<BeerList> getBeersByName(@RequestParam("name") String name) {
        List<Beer> beers = beerRepo.findByNameLikeOrderByName(name);
        if (beers == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BeerList beerList = new BeerList(beers);
        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }
}
