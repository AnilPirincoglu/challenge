package enoca.challenge.shopping.service;

import enoca.challenge.shopping.dto.CartResponse;
import enoca.challenge.shopping.dto.OrderResponse;
import enoca.challenge.shopping.entity.Order;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(Long cart_id);
    List<OrderResponse> getAllOrdersForCustomer(Long customer_id);

    Order getOrder(Long id);
    Order createOrder(CartResponse cartResponse);
}
