package br.com.letscode.ecommerce.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Entity(name = "Products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "id")
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "description")
        private String description;

        @Column(name = "price", precision = 20, scale = 2)
        private BigDecimal price;

        @Column(name = "created_at")
        private ZonedDateTime createdAt;

        @Column(name = "update_at")
        private ZonedDateTime updatedAt;
}
