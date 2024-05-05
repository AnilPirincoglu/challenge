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
        haveIProduct(product);
        return ProductConverter
                .productToResponse(
                        productRepository.save(product));
    }

    @Override
    public ProductResponse updateProduct(Product product) {
        hasItId(product);
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

    private void haveIProduct(Product product) {//TODO 2.0 yaklaşım doğru mu?
        if (productRepository.findByName(product.getName()).isPresent())
            throw new GlobalException("This product already created : " + product.getName(),
                    HttpStatus.BAD_REQUEST);
    }

    private void hasItId(Product product) {
        if (product.getId() == null)
            throw new GlobalException("Id field must not be null!", HttpStatus.BAD_REQUEST);
    }
}
