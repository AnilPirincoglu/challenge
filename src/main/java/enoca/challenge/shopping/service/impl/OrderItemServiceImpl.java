package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.ProductResponse;
import enoca.challenge.shopping.entity.Order;
import enoca.challenge.shopping.entity.OrderItem;
import enoca.challenge.shopping.repository.OrderItemRepository;
import enoca.challenge.shopping.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;
    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public OrderItem createOrderItem(ProductResponse product, Order order) {
        return orderItemRepository.save(new OrderItem(product.name(),product.price(),order));
    }
}
