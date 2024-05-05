package enoca.challenge.shopping.util;

import enoca.challenge.shopping.dto.ProductResponse;
import enoca.challenge.shopping.entity.Product;


import java.util.List;

public class ProductConverter {
    public static ProductResponse productToResponse(Product product){
        return new ProductResponse(product.getName(), product.getPrice(), product.getStockQuantity());
    }

    public static List<ProductResponse> productsToResponseList(List<Product> products){
        return products.stream().map(ProductConverter::productToResponse).toList();
    }
}
