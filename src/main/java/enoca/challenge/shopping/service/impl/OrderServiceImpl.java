package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.OrderResponse;
import enoca.challenge.shopping.entity.Cart;
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

    @Override
    public OrderResponse placeOrder(Long cart_id) {
        OrderResponse orderResponse = OrderConverter.orderToResponse(
                orderRepository.save(
                        CartConverter.cartToOrder(
                                stockControl(cart_id))));
        cartService.emptyCart(cart_id);
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllOrdersForCustomer(Long customer_id) {
        return OrderConverter.orderToResponseList(
                customerService.findCustomer(customer_id)
                        .getOrders());
    }

    private Cart stockControl(Long cart_id) {//TODO Mantık Hatası Var
        Cart cart = cartService.findCart(cart_id);
        for (Product product : cart.getProducts()) {
            if (product.getStockQuantity() <= 0)
                throw new GlobalException(product.getName() + " is out of stock!", HttpStatus.BAD_REQUEST);
        }
        cart.getProducts()
                .forEach(product -> product.setStockQuantity(product.getStockQuantity() - 1));
        return cart;
    }
}
