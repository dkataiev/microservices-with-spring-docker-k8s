package lab.dkataiev.ms.k8s.cards.controller;

import lab.dkataiev.ms.k8s.cards.config.CardsServiceConfig;
import lab.dkataiev.ms.k8s.cards.model.Card;
import lab.dkataiev.ms.k8s.cards.model.Properties;
import lab.dkataiev.ms.k8s.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class CardsController {

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

    @GetMapping("/properties")
    public Properties getProperties(){
        return Properties.builder()
                .msg(cardsServiceConfig.getMsg())
                .buildVersion(cardsServiceConfig.getBuildVersion())
                .mailDetails(cardsServiceConfig.getMailDetails())
                .activeBranches(cardsServiceConfig.getActiveBranches())
                .build();
    }
}
