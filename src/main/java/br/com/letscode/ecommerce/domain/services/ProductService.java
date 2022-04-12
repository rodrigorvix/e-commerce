package br.com.letscode.ecommerce.domain.services;

import br.com.letscode.ecommerce.domain.models.ProductEntity;
import br.com.letscode.ecommerce.domain.repositories.ProductRepository;
import br.com.letscode.ecommerce.domain.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.ZonedDateTime;
import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }


    public List<ProductEntity> getProducts() {
        List<ProductEntity> products = this.productRepository.findAll();

        return products;
    }

    public ProductEntity createProduct(ProductEntity product) {
        product.setCreatedAt(ZonedDateTime.now());
        product.setUpdatedAt(ZonedDateTime.now());

        return  this.productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        this.productRepository.findById(id)
                .map( productExists -> {
                    this.productRepository.delete(productExists);
                    return productExists;
                })
                .orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    public void updateProduct(ProductEntity product, Long id) {
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
