package br.com.letscode.ecommerce.domain.controllers;

import br.com.letscode.ecommerce.domain.models.ProductEntity;
import br.com.letscode.ecommerce.domain.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;


@RequestMapping("/api/products")
@RestController
public class ProductController {

    private ProductRepository productRepository;

    public ProductController (ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping()
    public List<ProductEntity> index() {
        List<ProductEntity> products = this.productRepository.findAll();

        return products;
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductEntity store (@RequestBody ProductEntity product){

        product.setCreatedAt(ZonedDateTime.now());
        product.setUpdatedAt(ZonedDateTime.now());

        return  this.productRepository.save(product);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.productRepository.findById(id)
                .map( productExists -> {
                    this.productRepository.delete(productExists);
                    return productExists;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

    }

    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody ProductEntity product, @PathVariable Long id) {

        this.productRepository
                .findById(id)
                .map(productExists -> {
                    product.setId(productExists.getId());
                    product.setCreatedAt(productExists.getCreatedAt());
                    product.setUpdatedAt(ZonedDateTime.now());
                    this.productRepository.save(product);
                    return productExists;
                }).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

    }
}
