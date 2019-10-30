package be.multimedi.lessons.springadvanced.beers;

import be.multimedi.lessons.springadvanced.beers.exceptions.InvalidAmountException;
import be.multimedi.lessons.springadvanced.beers.exceptions.InvalidBeerException;
import be.multimedi.lessons.springadvanced.orders.BeerOrder;
import be.multimedi.lessons.springadvanced.orders.BeerOrderItem;
import be.multimedi.lessons.springadvanced.orders.BeerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("beerService")
@Transactional
public class StandardBeerService implements BeerService {
    BeerOrderRepository beerOrderRepository;
    BeerRepository beerRepository;

    @Autowired
    public void setBeerOrderRepository(BeerOrderRepository beerOrderRepository) {
        this.beerOrderRepository = beerOrderRepository;
    }

    @Autowired
    public void setBeerRepository(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    @Transactional
    @Secured({"ROLE_ADULT"})
    public int orderBeer(String name, int beerId, int amount) throws InvalidAmountException, InvalidBeerException {
        return this.orderBeers(name, new int[][] {{beerId,amount}});
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    @Secured({"ROLE_ADULT"})
    public int orderBeers(String name, int[][] orderDetails) {
        int[] beerIds = Arrays.stream(orderDetails).mapToInt((arr) -> arr[0]).toArray();
        int[] amounts = Arrays.stream(orderDetails).mapToInt((arr) -> arr[1]).toArray();
        List<BeerOrderItem> items = createOrderItems(beerIds, amounts);

        BeerOrder order = createBeerOrder(name, items);

        return beerOrderRepository.saveOrder(order);
    }

    private List<BeerOrderItem> createOrderItems(int[] beerIds, int[] amounts) throws InvalidBeerException, InvalidAmountException {

        List<BeerOrderItem> items = new ArrayList<>();

        for (int i = 0; i < beerIds.length; i++) {

            int id = beerIds[i];
            Beer beer = getBeer(id);

            int amount = amounts[i];

            adjustStockIfAvailable(beer, amount);

            BeerOrderItem item = createOrderItem(beer, amount);

            items.add(item);
        }


        return items;
    }

    private BeerOrderItem createOrderItem(Beer beer, int amount) {
        BeerOrderItem item = new BeerOrderItem();
        item.setNumber(amount);
        item.setBeer(beer);
        return item;
    }

    private BeerOrder createBeerOrder(String name, List<BeerOrderItem> items) {
        BeerOrder order = new BeerOrder();
        order.setName(name);
        order.setItems(items);

        return order;
    }

    private Beer getBeer(int id) throws InvalidBeerException {
        return Optional
                .ofNullable(beerRepository.getBeerById(id))
                .orElseThrow(() -> new InvalidBeerException(String.format("Beer with id %s doens't exist", id)));
    }

    private void checkAvailability(Beer beer, int amount) throws InvalidAmountException {
        if (amount > beer.getStock()) {
            throw new InvalidAmountException("Not enough beer in stock");
        }
    }

    private void adjustStockIfAvailable(Beer beer, int amount) throws InvalidAmountException {
        checkAvailability(beer,amount);
        beer.setStock(beer.getStock() - amount);
    }
}
