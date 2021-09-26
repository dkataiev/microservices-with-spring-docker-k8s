package lab.dkataiev.ms.k8s.loans.controller;

import lab.dkataiev.ms.k8s.loans.config.LoansServiceConfig;
import lab.dkataiev.ms.k8s.loans.model.Loan;
import lab.dkataiev.ms.k8s.loans.model.Properties;
import lab.dkataiev.ms.k8s.loans.repository.LoansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    private final LoansRepository loansRepository;
    private final LoansServiceConfig loansServiceConfig;

    @Autowired
    public LoansController(LoansRepository loansRepository, LoansServiceConfig loansServiceConfig) {
        this.loansRepository = loansRepository;
        this.loansServiceConfig = loansServiceConfig;
    }

    @GetMapping("/c/{cId}")
    public Iterable<Loan> findByCustomerId(@PathVariable("cId") Long cId) {
        return loansRepository.findByCustomerIdOrderByStartDtDesc(cId);
    }

    @GetMapping("/properties")
    public Properties getProperties(){
        return Properties.builder()
                .msg(loansServiceConfig.getMsg())
                .buildVersion(loansServiceConfig.getBuildVersion())
                .mailDetails(loansServiceConfig.getMailDetails())
                .activeBranches(loansServiceConfig.getActiveBranches())
                .build();
    }

}
