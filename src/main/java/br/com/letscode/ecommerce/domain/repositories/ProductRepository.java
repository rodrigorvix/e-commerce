package br.com.letscode.ecommerce.domain.repositories;

import br.com.letscode.ecommerce.domain.models.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
