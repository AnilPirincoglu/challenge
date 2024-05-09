package enoca.challenge.shopping.service.impl;

import enoca.challenge.shopping.dto.ProductResponse;
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
    public ProductResponse getProduct(Long id) {
        return ProductConverter
                .productToResponse(findProduct(id));
    }

    @Override
    public ProductResponse createProduct(Product product) {
        if (productRepository.findByName(product.getName()).isPresent())
            throw new GlobalException("This product is already exist : " + product.getName(),
                    HttpStatus.BAD_REQUEST);
        return ProductConverter
                .productToResponse(productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Product product) {
        findProduct(product.getId());
        return ProductConverter
                .productToResponse(productRepository.save(product));
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
}
