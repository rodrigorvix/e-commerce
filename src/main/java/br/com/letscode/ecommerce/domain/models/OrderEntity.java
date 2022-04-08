package br.com.letscode.ecommerce.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity(name = "Orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private Long id;

        @Column(name = "amount", precision = 20, scale = 2)
        private BigDecimal amount;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private UserEntity user;

        @Column(name = "created_at")
        private ZonedDateTime createdAt;

        @Column(name = "update_at")
        private ZonedDateTime updatedAt;

}