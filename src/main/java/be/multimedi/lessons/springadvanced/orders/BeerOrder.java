package be.multimedi.lessons.springadvanced.orders;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "BeerOrders")
public class BeerOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;
    @Column(name = "Name")
    private String name;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name = "BeerOrderId")
    private List<BeerOrderItem> items = new ArrayList<>();

    public BeerOrder() {
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

    public List<BeerOrderItem> getItems() {
        return items;
    }

    public void setItems(List<BeerOrderItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "BeerOrder{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", items=" + items +
                '}';
    }
}
