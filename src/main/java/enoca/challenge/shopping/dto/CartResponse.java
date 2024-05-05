package enoca.challenge.shopping.dto;

import java.util.List;

public record CartResponse(String customerEmail, List<ProductResponse> products, Double totalPrice) {
}
