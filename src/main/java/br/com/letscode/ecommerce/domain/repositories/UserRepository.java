package br.com.letscode.ecommerce.domain.repositories;

import br.com.letscode.ecommerce.domain.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
