package br.com.letscode.ecommerce.domain.repositories;

import br.com.letscode.ecommerce.domain.models.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Long> {
}
