package be.multimedi.lessons.springadvanced.orders;

import be.multimedi.lessons.springadvanced.beers.Beer;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "BeerOrderItems")
public class BeerOrderItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Number")
    private int number;

    @ManyToOne
    @JoinColumn(name = "BeerId")
    private Beer beer;

    public BeerOrderItem() {
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    @Override
    public String toString() {
        return "BeerOrderItem{" +
                "id=" + id +
                ", number=" + number +
                ", beer=" + beer +
                '}';
    }
}
