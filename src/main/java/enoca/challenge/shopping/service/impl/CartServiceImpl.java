package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.CartResponse;
import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.Product;
import enoca.challenge.shopping.exception.GlobalException;
import enoca.challenge.shopping.repository.CartRepository;
import enoca.challenge.shopping.service.CartService;
import enoca.challenge.shopping.util.CartConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public CartResponse getCart(Long id) {
        return CartConverter
                .cartToResponse(findCart(id));
    }

    @Override
    public CartResponse updateCart(Cart cart) {
        hasItId(cart);
        cart.setTotalPrice(calculateTotalPrice(cart));
        return CartConverter
                .cartToResponse(cartRepository.save(cart));
    }

    @Override
    public CartResponse emptyCart(Long id) {
        Cart cart = findCart(id);
        cart.setProducts(new ArrayList<>());
        cart.setTotalPrice(0d);
        cartRepository.save(cart);
        return CartConverter
                .cartToResponse(cart);
    }

    private void hasItId(Cart cart) {
        if (cart.getId() == null)
            throw new GlobalException("Id field must not be null!", HttpStatus.BAD_REQUEST);
    }

    public Cart findCart(Long id) {
        return cartRepository.findById(id)
                .orElseThrow(() ->
                        new GlobalException("Cart with given id is not exist : " + id,
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
