package enoca.challenge.shopping.service;

import enoca.challenge.shopping.dto.ProductResponse;
import enoca.challenge.shopping.entity.Product;

public interface ProductService {
    ProductResponse getProduct(Long productId);

    ProductResponse createProduct(Product product);

    ProductResponse updateProduct(Product product);

    ProductResponse deleteProduct(Long productId);

    Product findProduct(Long productId);
}

