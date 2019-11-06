package be.multimedi.lessons.springadvanced.beers;

import be.multimedi.lessons.springadvanced.brewers.Brewer;
import be.multimedi.lessons.springadvanced.categories.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringJoiner;

@Entity
@Table(name="Beers")
@NamedQuery(name = "findByAlcohol", query = "select b from Beer b where b.alcohol=:alcohol")
@XmlRootElement
public class Beer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;
    @Column(name="Name")
    private String name;
    @Column(name="Price")
    private double price;
    @Column(name="Stock")
    private int stock;
    @Column(name="Alcohol")
    private double alcohol;
    @Version
    @Column(name = "Version")
    private int version;

    @ManyToOne
    @JoinColumn(name="BrewerId")
    private Brewer brewer;

    @ManyToOne
    @JoinColumn(name = "CategoryId")
    private Category category;

    public Beer() {
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Brewer getBrewer() {
        return brewer;
    }

    public void setBrewer(Brewer brewer) {
        this.brewer = brewer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    @Override
//    public String toString() {
//        return "Beer{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", price=" + price +
//                ", stock=" + stock +
//                ", alcohol=" + alcohol +
////                ", version=" + version +
//                ", brewer=" + brewer.getName() +
//                ", category=" + category.getName() +
//                '}';
//    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Beer.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("stock=" + stock)
                .add("alcohol=" + alcohol)
//                .add("version=" + version)
//                .add("brewer=" + brewer.getName())
//                .add("category=" + category.getName())
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beer beer = (Beer) o;
        return getId() == beer.getId() &&
                Double.compare(beer.getPrice(), getPrice()) == 0 &&
                getStock() == beer.getStock() &&
                Double.compare(beer.getAlcohol(), getAlcohol()) == 0 &&
                Objects.equals(getName(), beer.getName()) &&
                Objects.equals(getBrewer().getName(), beer.getBrewer().getName()) &&
                Objects.equals(getCategory().getName(), beer.getCategory().getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPrice(), getStock(), getAlcohol(), getBrewer().getName(), getCategory().getName());
    }
}
