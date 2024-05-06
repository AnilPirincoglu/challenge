package enoca.challenge.shopping.util;

import enoca.challenge.shopping.dto.CartResponse;
import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.Order;

import java.util.ArrayList;

public class CartConverter {
    public static CartResponse cartToResponse(Cart cart) {
        return new CartResponse(cart.getCustomer().getEmail(),
                ProductConverter
                        .productsToResponseList(cart.getProducts()),
                cart.getTotalPrice());
    }

}
