package enoca.challenge.shopping.service;

import enoca.challenge.shopping.dto.ProductResponse;
import enoca.challenge.shopping.entity.Product;

public interface ProductService {
    ProductResponse getProduct(Long id);

    ProductResponse createProduct(Product product);

    ProductResponse updateProduct(Product product);

    ProductResponse deleteProduct(Long id);

    Product findProduct(Long id);
}

