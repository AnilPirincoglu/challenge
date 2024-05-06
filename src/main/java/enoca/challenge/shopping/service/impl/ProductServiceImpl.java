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
        findProduct(product.getId());
        return ProductConverter
                .productToResponse(
                        productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Product product) {
        findProduct(product.getId());
        return ProductConverter
                .productToResponse(
                        productRepository.save(product));
    }

    @Override
    public ProductResponse deleteProduct(Long id) {
        var product = getProduct(id);
        productRepository.deleteById(id);
        return product;
    }

    public Product findProduct(Long id){
        return productRepository.findById(id)
                .orElseThrow(() ->
                        new GlobalException("Product with given id is not exist: " + id,
                                HttpStatus.NOT_FOUND));
    }
}
