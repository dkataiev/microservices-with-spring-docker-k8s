package lab.dkataiev.ms.k8s.cards.controller;

import lab.dkataiev.ms.k8s.cards.model.Card;
import lab.dkataiev.ms.k8s.cards.repository.CardsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class CardsController {

    private final CardsRepository cardsRepository;

    @Autowired
    public CardsController(CardsRepository cardsRepository) {
        this.cardsRepository = cardsRepository;
    }

    @GetMapping("/c/{cId}")
    public Iterable<Card> findByCustomerId(@PathVariable("cId") Long cId) {
        return cardsRepository.findAllByCustomerId(cId);
    }

}
