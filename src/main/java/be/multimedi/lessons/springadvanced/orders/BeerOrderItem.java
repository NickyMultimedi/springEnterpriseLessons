package be.multimedi.lessons.springadvanced.orders;

import be.multimedi.lessons.springadvanced.beers.Beer;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "BeerOrderItems")
@XmlRootElement
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeerOrderItem that = (BeerOrderItem) o;
        return getId() == that.getId() &&
                getNumber() == that.getNumber() &&
                Objects.equals(getBeer().getName(), that.getBeer().getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumber(), getBeer().getName());
    }
}
