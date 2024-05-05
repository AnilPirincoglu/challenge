package enoca.challenge.shopping.controller;

import enoca.challenge.shopping.dto.OrderResponse;
import enoca.challenge.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{id}")
    public OrderResponse placeOrder(@PathVariable Long id) {
        return orderService.placeOrder(id);
    }
    @GetMapping("/{id}")
    public List<OrderResponse> getAllOrdersForCustomer(@PathVariable Long id) {
        return orderService.getAllOrdersForCustomer(id);
    }
}
