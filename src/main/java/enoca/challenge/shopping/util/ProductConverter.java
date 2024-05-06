package enoca.challenge.shopping.util;

import enoca.challenge.shopping.dto.ProductResponse;
import enoca.challenge.shopping.entity.Order;
import enoca.challenge.shopping.entity.OrderItem;
import enoca.challenge.shopping.entity.Product;


import java.util.ArrayList;
import java.util.List;

public class ProductConverter {
    public static ProductResponse productToResponse(Product product) {
        return new ProductResponse(product.getName(), product.getPrice(), product.getStockQuantity());
    }

    public static List<ProductResponse> productsToResponseList(List<Product> products) {
        return products.stream().map(ProductConverter::productToResponse).toList();
    }

    public static OrderItem productToOrderItem(Product product, Order order) {
        return new OrderItem(product.getName(), product.getPrice(), order);
    }

    public static List<OrderItem> productToOrderItemList(List<Product> products, Order order) {
        return new ArrayList<>(products.stream().map(product -> productToOrderItem(product, order)).toList());
    }
}
