package lab.dkataiev.ms.k8s.loans.controller;

import lab.dkataiev.ms.k8s.loans.model.Loan;
import lab.dkataiev.ms.k8s.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    private final LoansRepository loansRepository;

    @Autowired
    public LoansController(LoansRepository loansRepository) {
        this.loansRepository = loansRepository;
    }

    @GetMapping("/c/{cId}")
    public Iterable<Loan> findByCustomerId(@PathVariable("cId") Long cId){
        return loansRepository.findByCustomerIdOrderByStartDtDesc(cId);
    }

}
