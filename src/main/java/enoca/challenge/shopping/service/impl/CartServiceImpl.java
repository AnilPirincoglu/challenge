package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.CartResponse;
import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.Product;
import enoca.challenge.shopping.exception.GlobalException;
import enoca.challenge.shopping.repository.CartRepository;
import enoca.challenge.shopping.service.CartService;
import enoca.challenge.shopping.service.ProductService;
import enoca.challenge.shopping.util.CartConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;
    private ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    @Override
    public CartResponse getCart(Long cartId) {
        return CartConverter
                .cartToResponse(findCart(cartId));
    }

    @Override
    public CartResponse updateCart(Long cartId) {
        Cart cart = findCart(cartId);
        cart.setTotalPrice(calculateTotalPrice(cart));
        return CartConverter
                .cartToResponse(cartRepository.save(cart));
    }

    @Override
    public CartResponse emptyCart(Long cartId) {
        Cart cart = findCart(cartId);
        cart.setProducts(new ArrayList<>());
        cart.setTotalPrice(0d);
        cartRepository.save(cart);
        return CartConverter
                .cartToResponse(cart);
    }

    @Override
    public CartResponse addProductToCart(Long productId, Long cartId) {
        Cart cart = findCart(cartId);
        cart.addProduct(productService
                .findProduct(productId));
        cart.setTotalPrice(calculateTotalPrice(cart));
        return CartConverter
                .cartToResponse(cartRepository.save(cart));
    }

    @Override
    public CartResponse removeProductFromCart(Long productId, Long cartId) {
        Cart cart = findCart(cartId);
        if (!cart.getProducts().remove(productService.findProduct(productId)))
            throw new GlobalException("This product is not in your cart!", HttpStatus.NOT_FOUND);
        cart.setTotalPrice(calculateTotalPrice(cart));
        return CartConverter
                .cartToResponse(cartRepository.save(cart));
    }

    @Override
    public Cart findCart(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() ->
                        new GlobalException("Cart with given id is not exist : " + cartId,
                                HttpStatus.BAD_REQUEST));
    }

    private Double calculateTotalPrice(Cart cart) {
        double total = 0;
        for (Product product : cart.getProducts()) {
            total = total + product.getPrice();
        }
        return total;
    }

}
