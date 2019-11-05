package be.multimedi.lessons.springadvanced.orders;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class OrderInfo implements Serializable {
    String name;
    int beerId;
    int amount;

    public OrderInfo() {
    }

    public OrderInfo(String name, int beerId, int amount) {
        this.name = name;
        this.beerId = beerId;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
