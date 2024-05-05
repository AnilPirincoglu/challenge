package enoca.challenge.shopping.service;

import enoca.challenge.shopping.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(Long cart_id);
    List<OrderResponse> getAllOrdersForCustomer(Long customer_id);
}
