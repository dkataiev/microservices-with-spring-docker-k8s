package lab.dkataiev.ms.k8s.loans.repository;

import lab.dkataiev.ms.k8s.loans.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LoansRepository extends CrudRepository<Loan, Long> {


    List<Loan> findByCustomerIdOrderByStartDtDesc(long customerId);

}
