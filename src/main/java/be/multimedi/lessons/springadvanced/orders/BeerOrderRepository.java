package be.multimedi.lessons.springadvanced.orders;

public interface BeerOrderRepository {
    int saveOrder(BeerOrder order);
    BeerOrder getBeerOrderById(int id);
}
