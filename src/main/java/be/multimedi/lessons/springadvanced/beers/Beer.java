package be.multimedi.lessons.springadvanced.beers;

import be.multimedi.lessons.springadvanced.brewers.Brewer;
import be.multimedi.lessons.springadvanced.categories.Category;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Beers")
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

    @Override
    public String toString() {
        return "Beer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                ", alcohol=" + alcohol +
                ", version=" + version +
                ", brewer=" + brewer +
                ", category=" + category +
                '}';
    }
}
