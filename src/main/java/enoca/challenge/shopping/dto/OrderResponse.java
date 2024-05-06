package enoca.challenge.shopping.dto;

import java.util.List;

public record OrderResponse(String customerEmail, List<OrderItemResponse> products, Double totalPrice) {
}
