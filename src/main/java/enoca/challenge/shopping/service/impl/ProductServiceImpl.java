package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.request.ProductRequest;
import enoca.challenge.shopping.dto.response.ProductResponse;
import enoca.challenge.shopping.entity.Product;
import enoca.challenge.shopping.exception.GlobalException;
import enoca.challenge.shopping.repository.ProductRepository;
import enoca.challenge.shopping.service.ProductService;
import enoca.challenge.shopping.util.ProductConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse getProduct(Long productId) {
        return ProductConverter
                .productToResponse(findProduct(productId));
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
        checkProduct(productRequest);
        return ProductConverter.productToResponse(
                productRepository.save(
                        ProductConverter.requestToProduct(productRequest)
                ));
    }

    @Override
    public ProductResponse updateProduct(Long productId, ProductRequest productRequest) {
        Product product = findProduct(productId);

        if (!product.getName().equals(productRequest.name()))
            checkProduct(productRequest);

        return ProductConverter.productToResponse(
                productRepository.save(ProductConverter.requestToProduct(product, productRequest)));
    }

    @Override
    public ProductResponse deleteProduct(Long productId) {
        ProductResponse productResponse = getProduct(productId);
        productRepository.deleteById(productId);
        return productResponse;
    }

    @Override
    public Product findProduct(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new GlobalException("Product with given id is not exist: " + productId,
                                HttpStatus.NOT_FOUND));
    }

    private void checkProduct(ProductRequest productRequest) {
        if (productRepository.findByName(productRequest.name()).isPresent())
            throw new GlobalException("This product is already exist : " + productRequest.name(),
                    HttpStatus.BAD_REQUEST);
    }
}
