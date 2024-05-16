package enoca.challenge.shopping.util;

import enoca.challenge.shopping.dto.request.ProductRequest;
import enoca.challenge.shopping.dto.response.ProductResponse;
import enoca.challenge.shopping.entity.Product;


import java.util.List;

public class ProductConverter {
    public static ProductResponse productToResponse(Product product) {
        return new ProductResponse(product.getName(), product.getPrice(), product.getStockQuantity());
    }

    public static List<ProductResponse> productsToResponseList(List<Product> products) {
        return products.stream().map(ProductConverter::productToResponse).toList();
    }

    public static Product requestToProduct(ProductRequest productRequest) {
        return new Product(productRequest.name(),
                productRequest.price(),
                productRequest.stock(),
                null);
    }

    public static Product requestToProduct(Product product, ProductRequest productRequest) {
        if (productRequest.price() != null)
            product.setPrice(productRequest.price());

        if (productRequest.name() != null)
            product.setName(productRequest.name());

        if (productRequest.stock() != null)
            product.setStockQuantity(productRequest.stock());

        return product;
    }
}
