package be.multimedi.lessons.springadvanced.orders;

import be.multimedi.lessons.springadvanced.beers.BeerRepository;
import be.multimedi.lessons.springadvanced.beers.BeerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

@RestController
@RequestMapping("/orders")
public class RestBeerOrderService {
    BeerRepository beerRepository;
    BeerOrderRepository orderRepository;
    BeerService beerService;

    @Autowired
    public void setBeerRepository(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Autowired
    public void setOrderRepository(BeerOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Autowired
    public void setBeerService(BeerService beerService) {
        this.beerService = beerService;
    }

    @PostMapping(
            consumes = {
                    MediaType.APPLICATION_JSON_UTF8_VALUE,
                    MediaType.APPLICATION_XML_VALUE
            }
    )
    public ResponseEntity placeOrder(@RequestBody OrderInfo order, HttpServletRequest request) {
        int orderId = this.beerService.orderBeer(order.name, order.beerId, order.amount);
        if (beerRepository.getBeerById(orderId) == null) {
            return ResponseEntity.badRequest().build();
        }
        URI uri = URI.create(request.getRequestURL() + "/" + orderId);
        return ResponseEntity.created(uri).build();
    }


}
