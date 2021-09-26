package lab.dkataiev.ms.k8s.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScans({
        @ComponentScan("lab.dkataiev.ms.k8s.loans.config"),
        @ComponentScan("lab.dkataiev.ms.k8s.loans.controller"),
        @ComponentScan("lab.dkataiev.ms.k8s.loans.repository")
})
@EnableJpaRepositories("lab.dkataiev.ms.k8s.loans.repository")
@EntityScan("lab.dkataiev.ms.k8s.loans.model")
public class LoansApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

}
