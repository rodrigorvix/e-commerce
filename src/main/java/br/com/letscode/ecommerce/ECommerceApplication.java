package br.com.letscode.ecommerce;

import br.com.letscode.ecommerce.domain.models.OrderEntity;
import br.com.letscode.ecommerce.domain.models.UserEntity;
import br.com.letscode.ecommerce.domain.repositories.OrderRepository;
import br.com.letscode.ecommerce.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;

@SpringBootApplication
public class ECommerceApplication {

	@Bean
	public CommandLineRunner init(@Autowired UserRepository users,
								  @Autowired OrderRepository orders) {
		return args -> {
			System.out.println("Salvando usuários");

			UserEntity u = new UserEntity();
			u.setName("Fulando");
			u.setUsername("fulaninho");
			u.setBirthDate(LocalDate.of(1968,12,18));
			u.setCreatedAt(ZonedDateTime.now());
			u.setUpdatedAt(ZonedDateTime.now());

			users.save(u);

			UserEntity u2 = new UserEntity();
			u2.setName("Teste");
			u2.setUsername("testando");
			u2.setBirthDate(LocalDate.of(1992,4,03));
			u2.setCreatedAt(ZonedDateTime.now());
			u2.setUpdatedAt(ZonedDateTime.now());

			users.save(u2);


			UserEntity u3 = new UserEntity();
			u3.setName("Zé");
			u3.setUsername("zezinho");
			u3.setBirthDate(LocalDate.of(1998,07,11));
			u3.setCreatedAt(ZonedDateTime.now());
			u3.setUpdatedAt(ZonedDateTime.now());

			users.save(u3);

			OrderEntity o = new OrderEntity();
			o.setAmount(BigDecimal.valueOf(10.20));
			o.setCreatedAt(ZonedDateTime.now());
			o.setUpdatedAt(ZonedDateTime.now());
			o.setUser(u2);

			orders.save(o);

		/*	OrderEntity o2 = new OrderEntity();
			o2.setAmount(BigDecimal.valueOf(20.40));
			o2.setCreatedAt(ZonedDateTime.now());
			o2.setUpdatedAt(ZonedDateTime.now());
			o2.setUser(u2);

			orders.save(o2);*/

			orders.findByUser(u2).forEach(System.out::println);


		};
	}



	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}


}
