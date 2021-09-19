package lab.dkataiev.ms.k8s.loans.repository;

import java.util.List;

import lab.dkataiev.ms.k8s.loans.model.Loan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoansRepository extends CrudRepository<Loan, Long> {


    List<Loan> findByCustomerIdOrderByStartDtDesc(long customerId);

}
