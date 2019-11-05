package be.multimedi.lessons.springadvanced.brewers;

import be.multimedi.lessons.springadvanced.beers.Beer;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Brewers")
@XmlRootElement
public class Brewer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;
    @Column(name = "Name")
    private String name;
    @Column(name="Address")
    private String address;
    @Column(name="ZipCode")
    private String zipCode;
    @Column(name = "City")
    private String city;
    @Column(name = "Turnover")
    private int turnover;

    @OneToMany(mappedBy = "brewer")
    private Set<Beer> beers = new HashSet<>();

    public Brewer() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getTurnover() {
        return turnover;
    }

    public void setTurnover(int turnover) {
        this.turnover = turnover;
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
        return "Brewer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", city='" + city + '\'' +
                ", turnover=" + turnover +
//                ", beers=" + beers +
                '}';
    }
}
