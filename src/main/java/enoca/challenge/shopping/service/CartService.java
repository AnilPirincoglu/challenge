package enoca.challenge.shopping.service;

import enoca.challenge.shopping.dto.CartResponse;
import enoca.challenge.shopping.entity.Cart;

public interface CartService {
    CartResponse getCart(Long cartId);

    CartResponse updateCart(Long cartId);

    CartResponse emptyCart(Long cartId);

    CartResponse addProductToCart(Long productId, Long cartId);

    CartResponse removeProductFromCart(Long productId, Long cartId);

    Cart findCart(Long cartId);

}
