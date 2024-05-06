package enoca.challenge.shopping.service;

import enoca.challenge.shopping.dto.ProductResponse;
import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.Order;
import enoca.challenge.shopping.entity.OrderItem;
import enoca.challenge.shopping.entity.Product;

import java.util.List;

public interface OrderItemService {
    OrderItem createOrderItem(ProductResponse product, Order order);
}
