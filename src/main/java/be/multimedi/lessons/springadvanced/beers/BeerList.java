package be.multimedi.lessons.springadvanced.beers;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

@XmlRootElement
public class BeerList implements Serializable {
    List<Beer> beers;

    public BeerList() {
    }

    public BeerList(List<Beer> beers) {
        this.beers = beers;
    }

    public List<Beer> getBeers() {
        return beers;
    }

    public void setBeers(List<Beer> beers) {
        this.beers = beers;
    }
}
