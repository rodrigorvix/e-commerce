package br.com.letscode.ecommerce.domain.controllers;

import br.com.letscode.ecommerce.domain.models.ProductEntity;
import br.com.letscode.ecommerce.domain.repositories.ProductRepository;
import br.com.letscode.ecommerce.domain.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;


@RequestMapping("/api/products")
@RestController
public class ProductController {

    private ProductService productService;

    public ProductController (ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<ProductEntity> getProducts() {

        return this.productService.getProducts();

    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity createProduct (@RequestBody ProductEntity product){

        return this.productService.createProduct(product);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id) {

        this.productService.deleteProduct(id);

    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@RequestBody ProductEntity product, @PathVariable Long id) {

        this.productService.updateProduct(product, id);

    }
}
