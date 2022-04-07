package br.com.letscode.ecommerce.domain.repositories;

import br.com.letscode.ecommerce.domain.models.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
