package enoca.challenge.shopping.util;

import enoca.challenge.shopping.dto.response.CartResponse;
import enoca.challenge.shopping.entity.Cart;

public class CartConverter {
    public static CartResponse cartToResponse(Cart cart) {
        return new CartResponse(cart.getCustomer().getEmail(),
                ProductConverter
                        .productsToResponseList(cart.getProducts()),
                cart.getTotalPrice());
    }

}
