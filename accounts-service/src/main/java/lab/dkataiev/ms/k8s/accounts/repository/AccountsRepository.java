package lab.dkataiev.ms.k8s.accounts.repository;

import lab.dkataiev.ms.k8s.accounts.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByCustomerId(Long customerId);

}
