package enoca.challenge.shopping.dto;

import enoca.challenge.shopping.entity.OrderItem;

import java.util.List;

public record OrderResponse(String customerEmail, List<OrderItemReponse> products, Double totalPrice) {
}
