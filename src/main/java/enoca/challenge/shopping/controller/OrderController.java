package enoca.challenge.shopping.controller;

import enoca.challenge.shopping.dto.response.OrderResponse;
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

    @PostMapping("/{cartId}")
    public OrderResponse placeOrder(@PathVariable Long cartId) {
        return orderService.placeOrder(cartId);
    }
    @GetMapping("/{customerId}")
    public List<OrderResponse> getAllOrdersForCustomer(@PathVariable Long customerId) {
        return orderService.getAllOrdersForCustomer(customerId);
    }
}
