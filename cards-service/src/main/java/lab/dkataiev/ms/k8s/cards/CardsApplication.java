package lab.dkataiev.ms.k8s.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RefreshScope
@ComponentScans({
        @ComponentScan("lab.dkataiev.ms.k8s.cards.config"),
        @ComponentScan("lab.dkataiev.ms.k8s.cards.repository"),
        @ComponentScan("lab.dkataiev.ms.k8s.cards.controller")
})
@EnableJpaRepositories("lab.dkataiev.ms.k8s.cards.repository")
@EntityScan("lab.dkataiev.ms.k8s.cards.model")
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }

}
