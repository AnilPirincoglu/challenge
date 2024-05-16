package enoca.challenge.shopping.controller;

import enoca.challenge.shopping.dto.response.CartResponse;
import enoca.challenge.shopping.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{cartId}")
    public CartResponse getCart(@PathVariable Long cartId) {
        return cartService.getCart(cartId);
    }

    @PostMapping("/update/{cartId}")
    public CartResponse updateCart(@PathVariable Long cartId) {
        return cartService.updateCart(cartId);
    }

    @PostMapping("empty/{cartId}")
    public CartResponse emptyCart(@PathVariable Long cartId) {
        return cartService.emptyCart(cartId);
    }

    @PostMapping("add/{cartId}/{productId}")
    public CartResponse addProductToCart(@PathVariable Long productId,
                                         @PathVariable Long cartId) {
        return cartService.addProductToCart(productId, cartId);
    }

    @PostMapping("remove/{cartId}/{productId}")
    public CartResponse removeProductFromCart(@PathVariable Long productId,
                                              @PathVariable Long cartId) {
        return cartService.removeProductFromCart(productId, cartId);
    }
}