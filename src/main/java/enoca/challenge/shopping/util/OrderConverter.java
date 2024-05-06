package enoca.challenge.shopping.util;

import enoca.challenge.shopping.dto.OrderResponse;
import enoca.challenge.shopping.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderConverter {
    public static OrderResponse orderToResponse(Order order) {
        return new OrderResponse(order.getCustomer().getEmail(),
                OrderItemConverter.orderItemToResponseList(order.getOrderItems()),
                order.getTotalPrice());
    }

    public static List<OrderResponse> orderToResponseList(List<Order> orders) {
        return new ArrayList<>(orders.stream()
                .map(OrderConverter::orderToResponse)
                .toList());
    }
}
