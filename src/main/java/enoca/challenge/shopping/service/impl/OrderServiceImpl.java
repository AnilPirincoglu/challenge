package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.response.CartResponse;
import enoca.challenge.shopping.dto.response.OrderResponse;
import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.Order;
import enoca.challenge.shopping.entity.OrderItem;
import enoca.challenge.shopping.entity.Product;
import enoca.challenge.shopping.exception.GlobalException;
import enoca.challenge.shopping.repository.OrderRepository;
import enoca.challenge.shopping.service.CartService;
import enoca.challenge.shopping.service.CustomerService;
import enoca.challenge.shopping.service.OrderService;
import enoca.challenge.shopping.util.CartConverter;
import enoca.challenge.shopping.util.OrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private CartService cartService;
    private CustomerService customerService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CartService cartService, CustomerService customerService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.customerService = customerService;
    }

    @Transactional
    @Override
    public OrderResponse placeOrder(Long cartId) {
        Order order = createOrder(cartId);
        stockControl(cartId).products().forEach(productResponse ->
                order.addOrderItem(new OrderItem(productResponse.name(),
                                productResponse.price(),
                                order)
                ));
        OrderResponse orderResponse = OrderConverter
                .orderToResponse(orderRepository.save(order));
        cartService.emptyCart(cartId);
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrdersForCustomer(Long customerId) {
        return OrderConverter.orderToResponseList(
                customerService.findCustomer(customerId)
                        .getOrders());
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(() ->
                new GlobalException("Order with given id is not exist: " + orderId, HttpStatus.NOT_FOUND)
        );
    }

    private Order createOrder(Long cartId) {
        CartResponse cartResponse = cartService.getCart(cartId);
        return orderRepository.save(new Order(cartResponse.totalPrice(),
                customerService.findByEmail(cartResponse.customerEmail()),
                new ArrayList<>()));
    }

    private CartResponse stockControl(Long cartId) {
        Cart cart = cartService.findCart(cartId);
        for (Product product : cart.getProducts()) {
            if (product.getStockQuantity() <= 0)
                throw new GlobalException(product.getName() + " is out of stock!", HttpStatus.BAD_REQUEST);
        }
        cart.getProducts()
                .forEach(product -> product.setStockQuantity(product.getStockQuantity() - 1));
        return CartConverter.cartToResponse(cart);
    }
}
