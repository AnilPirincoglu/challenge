package enoca.challenge.shopping.service;

import enoca.challenge.shopping.entity.Cart;
import enoca.challenge.shopping.entity.OrderItem;
import enoca.challenge.shopping.entity.Product;

import java.util.List;

public interface OrderItemService {
    OrderItem createOrderItem(Product product);
}
