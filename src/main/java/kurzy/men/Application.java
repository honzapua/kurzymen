package kurzy.men;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Hlavni spustitelna trida
 */
@Configuration
@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@ImportResource("classpath:spring-mail-config.xml")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
