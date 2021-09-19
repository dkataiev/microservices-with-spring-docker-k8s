package lab.dkataiev.ms.k8s.cards.repository;

import lab.dkataiev.ms.k8s.cards.model.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardsRepository extends CrudRepository<Card, Long> {

    Iterable<Card> findAllByCustomerId(Long customerId);
}
