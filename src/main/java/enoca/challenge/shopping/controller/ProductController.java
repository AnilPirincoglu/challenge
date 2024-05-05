package enoca.challenge.shopping.controller;

import enoca.challenge.shopping.dto.ProductResponse;
import enoca.challenge.shopping.entity.Product;
import enoca.challenge.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ProductResponse getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }

    @PostMapping("/create")
    public ProductResponse createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PostMapping("/update")
    public ProductResponse updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public ProductResponse deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }
}
