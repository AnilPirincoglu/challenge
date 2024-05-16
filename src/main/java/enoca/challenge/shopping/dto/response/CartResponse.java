package enoca.challenge.shopping.dto.response;

import java.util.List;

public record CartResponse(String customerEmail, List<ProductResponse> products, Double totalPrice) {
}
