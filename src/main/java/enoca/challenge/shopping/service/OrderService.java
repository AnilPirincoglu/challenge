package enoca.challenge.shopping.service;

import enoca.challenge.shopping.dto.OrderResponse;
import enoca.challenge.shopping.entity.Order;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(Long cartId);
    List<OrderResponse> getAllOrdersForCustomer(Long customerId);
    Order getOrder(Long id);

}
