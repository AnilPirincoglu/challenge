package enoca.challenge.shopping.service;

import enoca.challenge.shopping.dto.CartResponse;
import enoca.challenge.shopping.entity.Cart;

public interface CartService {
    CartResponse getCart(Long id);

    CartResponse updateCart(Cart cart);

    CartResponse emptyCart(Long id);

    CartResponse addProductToCart(Long product_id, Long cart_id);

    CartResponse removeProductFromCart(Long product_id, Long cart_id);
    Cart createCart();
    Cart findCart(Long id);

}
