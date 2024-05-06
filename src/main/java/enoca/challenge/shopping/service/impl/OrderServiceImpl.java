package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.CartResponse;
import enoca.challenge.shopping.dto.OrderResponse;
import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.Order;
import enoca.challenge.shopping.entity.Product;
import enoca.challenge.shopping.exception.GlobalException;
import enoca.challenge.shopping.repository.OrderRepository;
import enoca.challenge.shopping.service.CartService;
import enoca.challenge.shopping.service.CustomerService;
import enoca.challenge.shopping.service.OrderItemService;
import enoca.challenge.shopping.service.OrderService;
import enoca.challenge.shopping.util.CartConverter;
import enoca.challenge.shopping.util.OrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private CartService cartService;
    private CustomerService customerService;
    private OrderItemService orderItemService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, CartService cartService, CustomerService customerService, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.customerService = customerService;
        this.orderItemService = orderItemService;
    }

    @Override
    public OrderResponse placeOrder(Long cart_id) {
        CartResponse cart = cartService.getCart(cart_id);
        Order order = createOrder(cart);
        order.getOrderItems().addAll(stockControl(cart_id)
                .products()
                .stream()
                .map(productResponse ->
                        orderItemService.createOrderItem(productResponse,order))
                .toList());
        OrderResponse orderResponse = OrderConverter
                .orderToResponse(orderRepository.save(order));
        cartService.emptyCart(cart_id);
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrdersForCustomer(Long customer_id) {
        return OrderConverter.orderToResponseList(
                customerService.findCustomer(customer_id)
                        .getOrders());
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow(()->
                new GlobalException("Order with given id is not exist: " + id, HttpStatus.NOT_FOUND)
        );
    }

    @Override
    public Order createOrder(CartResponse cartResponse) {
        return orderRepository.save(new Order(cartResponse.totalPrice(),
                customerService.findByEmail(cartResponse.customerEmail()),
                new ArrayList<>()));
    }

    private CartResponse stockControl(Long cart_id) {
        Cart cart = cartService.findCart(cart_id);
        for (Product product : cart.getProducts()) {
            if (product.getStockQuantity() <= 0)
                throw new GlobalException(product.getName() + " is out of stock!", HttpStatus.BAD_REQUEST);
        }
        cart.getProducts()
                .forEach(product -> product.setStockQuantity(product.getStockQuantity() - 1));
        return CartConverter.cartToResponse(cart);
    }
}
