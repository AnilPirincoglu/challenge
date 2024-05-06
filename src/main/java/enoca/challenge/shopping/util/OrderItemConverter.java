package enoca.challenge.shopping.util;

import enoca.challenge.shopping.dto.OrderItemResponse;
import enoca.challenge.shopping.entity.Order;
import enoca.challenge.shopping.entity.OrderItem;
import enoca.challenge.shopping.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderItemConverter {
    public static OrderItemResponse orderItemToResponse(OrderItem orderItem){
        return new OrderItemResponse(orderItem.getName(), orderItem.getPrice());
    }

    public static List<OrderItemResponse> orderItemToResponseList(List<OrderItem> orderItems){
        return new ArrayList<>(orderItems.stream().map(OrderItemConverter::orderItemToResponse).toList());
    }

    public static OrderItem productToOrderItem(Product product) {
        return new OrderItem(product.getName(), product.getPrice(),new Order());
    }

    public static List<OrderItem> productToOrderItemList(List<Product> products) {
        return new ArrayList<>(products.stream().map(OrderItemConverter::productToOrderItem).toList());
    }
}
