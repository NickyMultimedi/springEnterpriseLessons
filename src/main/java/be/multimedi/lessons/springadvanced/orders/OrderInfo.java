package be.multimedi.lessons.springadvanced.orders;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

@XmlRootElement
public class OrderInfo implements Serializable {
    String name;
    Map<Integer, Integer> amountsPerBeer;

    public OrderInfo() {
    }

    public OrderInfo(String name, int beerId, int amount) {
        this.name = name;
        amountsPerBeer = new HashMap<>();
        amountsPerBeer.put(beerId, amount);
    }

    public OrderInfo(String name, Map<Integer, Integer> amountsPerBeer) {
        this.name = name;
        this.amountsPerBeer = amountsPerBeer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Integer> getAmountsPerBeer() {
        return amountsPerBeer;
    }

    public void setAmountsPerBeer(Map<Integer, Integer> amountsPerBeer) {
        this.amountsPerBeer = amountsPerBeer;
    }

    public int[] getAmounts() {
        return amountsPerBeer.keySet().stream().mapToInt(k -> k).toArray();
    }

    public int[] getBeerIds() {
        return amountsPerBeer.values().stream().mapToInt(v -> v).toArray();
    }

    public int[][] getAmountsPerBeerMultiArray() {
        int orderSize = amountsPerBeer.size();
        int[][] amountsPerBeerArray = new int[orderSize][2];

        int i = 0;
        for (int beerId : this.amountsPerBeer.keySet()) {
            amountsPerBeerArray[i][0] = beerId;
            amountsPerBeerArray[i][1] = amountsPerBeer.get(beerId);
            i++;
        }

        return amountsPerBeerArray;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OrderInfo.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("amountsPerBeer=" + amountsPerBeer)
                .toString();
    }
}
