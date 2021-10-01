package lab.dkataiev.ms.k8s.accounts;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@RefreshScope
@EnableFeignClients
@ComponentScans({
        @ComponentScan("lab.dkataiev.ms.k8s.accounts.config"),
        @ComponentScan("lab.dkataiev.ms.k8s.accounts.repository"),
        @ComponentScan("lab.dkataiev.ms.k8s.accounts.controller"),
})
@EnableJpaRepositories("lab.dkataiev.ms.k8s.accounts.repository")
@EntityScan("lab.dkataiev.ms.k8s.accounts.model")
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

    @Bean
    public TimedAspect timedAspect(MeterRegistry registry){
        return new TimedAspect(registry);
    }

}
