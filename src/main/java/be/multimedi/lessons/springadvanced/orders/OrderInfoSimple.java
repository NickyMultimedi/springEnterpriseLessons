package be.multimedi.lessons.springadvanced.orders;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.StringJoiner;

@XmlRootElement
public class OrderInfoSimple implements Serializable {
    private String name;
    private int beerId;
    private int beerAmount;

    public OrderInfoSimple() {
    }

    public OrderInfoSimple(String name) {
        this.name = name;
    }

    public OrderInfoSimple(String name, int beerId, int beerAmount) {
        this.name = name;
        this.beerId = beerId;
        this.beerAmount = beerAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBeerId() {
        return beerId;
    }

    public void setBeerId(int beerId) {
        this.beerId = beerId;
    }

    public int getBeerAmount() {
        return beerAmount;
    }

    public void setBeerAmount(int beerAmount) {
        this.beerAmount = beerAmount;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderInfoSimple.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("beerId=" + beerId)
                .add("beerAmount=" + beerAmount)
                .toString();
    }
}
