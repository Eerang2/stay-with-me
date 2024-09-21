package jwlee.staywithme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StayWithMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(StayWithMeApplication.class, args);
    }

}
