package br.com.letscode.ecommerce;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class ECommerceApplication {

	/*@Bean
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


		};
	}*/


	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}


	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


}
