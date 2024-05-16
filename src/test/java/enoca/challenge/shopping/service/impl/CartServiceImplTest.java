package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.response.CartResponse;
import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.Customer;
import enoca.challenge.shopping.exception.GlobalException;
import enoca.challenge.shopping.repository.CartRepository;
import enoca.challenge.shopping.service.CartService;
import enoca.challenge.shopping.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CartServiceImplTest {

    private CartService cartService;
    private Cart cart;
    @MockBean
    private ProductService productService;
    @MockBean
    private CartRepository cartRepository;

    @BeforeEach
    void setUp() {
        cartService = new CartServiceImpl(cartRepository,productService);
        cart = new Cart(0D,
                new Customer("testname","testlastname","test@test.com",null,null),
                new ArrayList<>());
    }

    @AfterEach
    void tearDown() {
    }

    @DisplayName("Find Cart Successfully")
    @Test
    void findCartSuccess() {
        given(cartRepository.findById(cart.getId())).willReturn(Optional.of(cart));
        Cart cart1 = cartService.findCart(cart.getId());
        assertNotNull(cart1);
        assertEquals(cart1, cart);
    }

    @DisplayName("Find Cart Failure")
    @Test
    void findCartFailure() {
        given(cartRepository.findById(cart.getId())).willReturn(Optional.empty());
        assertThatThrownBy(()->cartService.findCart(cart.getId()))
                .isInstanceOf(GlobalException.class)
                .hasMessageContaining("Cart with given id is not exist : " + cart.getId());
    }

    @Test
    void getCart() {
        given(cartRepository.findById(cart.getId())).willReturn(Optional.of(cart));
        CartResponse cartResponse = cartService.getCart(cart.getId());
        assertEquals(cartResponse.customerEmail(),cart.getCustomer().getEmail());
    }

    @Test
    void updateCart() {
    }

    @Test
    void emptyCart() {
    }

    @Test
    void addProductToCart() {
    }

    @Test
    void removeProductFromCart() {
    }


}