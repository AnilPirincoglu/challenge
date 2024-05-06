package enoca.challenge.shopping.util;

import enoca.challenge.shopping.dto.OrderItemResponse;
import enoca.challenge.shopping.entity.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemConverter {
    public static OrderItemResponse orderItemToResponse(OrderItem orderItem){
        return new OrderItemResponse(orderItem.getName(), orderItem.getPrice());
    }

    public static List<OrderItemResponse> orderItemToResponseList(List<OrderItem> orderItems){
        return new ArrayList<>(orderItems.stream().map(OrderItemConverter::orderItemToResponse).toList());
    }
}
