package enoca.challenge.shopping.controller;

import enoca.challenge.shopping.dto.CartResponse;
import enoca.challenge.shopping.entity.Cart;
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

    @GetMapping("/{id}")
    public CartResponse getCart(@PathVariable Long id) {
        return cartService.getCart(id);
    }

    @PostMapping("/")
    public CartResponse updateCart(@RequestBody Cart cart) {
        return cartService.updateCart(cart);
    }

    @PostMapping("empty/{id}")
    public CartResponse emptyCart(@PathVariable Long id) {
        return cartService.emptyCart(id);
    }

    @PostMapping("add/{product_id}/{cart_id}")
    public CartResponse addProductToCart(@PathVariable Long product_id,
                                         @PathVariable Long cart_id) {
        return cartService.addProductToCart(product_id, cart_id);
    }

    @PostMapping("remove/{product_id}/{cart_id}")
    public CartResponse removeProductFromCart(@PathVariable Long product_id,
                                              @PathVariable Long cart_id) {
        return cartService.removeProductFromCart(product_id, cart_id);
    }
}