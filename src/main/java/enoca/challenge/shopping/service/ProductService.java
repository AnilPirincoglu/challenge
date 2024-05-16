package enoca.challenge.shopping.service;

import enoca.challenge.shopping.dto.request.ProductRequest;
import enoca.challenge.shopping.dto.response.ProductResponse;
import enoca.challenge.shopping.entity.Product;

public interface ProductService {
    ProductResponse getProduct(Long productId);

    ProductResponse createProduct(ProductRequest productRequest);

    ProductResponse updateProduct(Long productId, ProductRequest productRequest);

    ProductResponse deleteProduct(Long productId);

    Product findProduct(Long productId);
}

