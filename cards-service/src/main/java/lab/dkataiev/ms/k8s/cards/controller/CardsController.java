package lab.dkataiev.ms.k8s.cards.controller;

import lab.dkataiev.ms.k8s.cards.config.CardsServiceConfig;
import lab.dkataiev.ms.k8s.cards.model.Card;
import lab.dkataiev.ms.k8s.cards.model.Customer;
import lab.dkataiev.ms.k8s.cards.model.Properties;
import lab.dkataiev.ms.k8s.cards.repository.CardsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController("/")
public class CardsController {

    public static final String CORRELATION_ID = "k8s-bank-correlation-id";

    private final CardsRepository cardsRepository;
    private final CardsServiceConfig cardsServiceConfig;

    @Autowired
    public CardsController(CardsRepository cardsRepository, CardsServiceConfig cardsServiceConfig) {
        this.cardsRepository = cardsRepository;
        this.cardsServiceConfig = cardsServiceConfig;
    }

    @GetMapping("/c/{cId}")
    public Iterable<Card> findByCustomerId(@PathVariable("cId") Long cId) {
        return cardsRepository.findAllByCustomerId(cId);
    }

    @PostMapping("/c")
    public Iterable<Card> findByCustomer(@RequestHeader(CORRELATION_ID) String correlationId,
                                         @RequestBody Customer customer) {
        log.info("Getting cards details for customer #{} started...", customer.getCustomerId());
        Iterable<Card> cards = cardsRepository.findAllByCustomerId(customer.getCustomerId());
        log.info("Getting cards details for customer #{} done.", customer.getCustomerId());
        return cards;
    }

    @GetMapping("/properties")
    public Properties getProperties() {
        return Properties.builder()
                .msg(cardsServiceConfig.getMsg())
                .buildVersion(cardsServiceConfig.getBuildVersion())
                .mailDetails(cardsServiceConfig.getMailDetails())
                .activeBranches(cardsServiceConfig.getActiveBranches())
                .build();
    }
}
