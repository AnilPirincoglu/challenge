package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.entity.OrderItem;
import enoca.challenge.shopping.entity.Product;
import enoca.challenge.shopping.repository.OrderItemRepository;
import enoca.challenge.shopping.service.OrderItemService;
import enoca.challenge.shopping.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private OrderItemRepository orderItemRepository;
    private OrderService orderService;
    @Autowired
    public OrderItemServiceImpl(OrderItemRepository orderItemRepository) {
        this.orderItemRepository = orderItemRepository;
    }


    @Override
    public OrderItem createOrderItem(Product product) {
        OrderItem orderItem = new OrderItem();
        orderItem.setName(product.getName());
        orderItem.setPrice(product.getPrice());
        return orderItemRepository.save(orderItem);
    }
}
