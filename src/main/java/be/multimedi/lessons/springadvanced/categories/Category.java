package be.multimedi.lessons.springadvanced.categories;

import be.multimedi.lessons.springadvanced.beers.Beer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="Categories")
@XmlRootElement
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Category")
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Beer> beers = new HashSet<Beer>();

    public Category() {

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

    public Set<Beer> getBeers() {
        return beers;
    }

    @XmlTransient
    @JsonIgnore
    public void setBeers(Set<Beer> beers) {
        this.beers = beers;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", beers=" + beers +
                '}';
    }
}
