package kurzy.men;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hlavni spustitelna trida
 */
@Configuration
@SpringBootApplication
@EnableJpaRepositories
//@EnableCaching
@EnableScheduling
@EnableAsync
@ImportResource("classpath:spring-mail-config.xml")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
