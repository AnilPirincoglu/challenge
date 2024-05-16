package enoca.challenge.shopping.dto.response;

import enoca.challenge.shopping.dto.response.OrderItemResponse;

import java.util.List;

public record OrderResponse(String customerEmail, List<OrderItemResponse> products, Double totalPrice) {
}
