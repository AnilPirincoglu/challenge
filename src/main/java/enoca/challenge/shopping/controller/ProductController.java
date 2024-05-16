package enoca.challenge.shopping.controller;

import enoca.challenge.shopping.dto.request.ProductRequest;
import enoca.challenge.shopping.dto.response.ProductResponse;
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

    @GetMapping("/{productId}")
    public ProductResponse getProduct(@PathVariable Long productId) {
        return productService.getProduct(productId);
    }

    @PostMapping("/create")
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @PostMapping("/update/{productId}")
    public ProductResponse updateProduct(@PathVariable Long productId,
                                         @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(productId, productRequest);
    }

    @DeleteMapping("/delete/{productId}")
    public ProductResponse deleteProduct(@PathVariable Long productId) {
        return productService.deleteProduct(productId);
    }
}
