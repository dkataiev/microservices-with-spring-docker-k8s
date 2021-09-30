package lab.dkataiev.ms.k8s.loans.controller;

import lab.dkataiev.ms.k8s.loans.config.LoansServiceConfig;
import lab.dkataiev.ms.k8s.loans.model.Customer;
import lab.dkataiev.ms.k8s.loans.model.Loan;
import lab.dkataiev.ms.k8s.loans.model.Properties;
import lab.dkataiev.ms.k8s.loans.repository.LoansRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class LoansController {

    public static final String CORRELATION_ID = "k8s-bank-correlation-id";

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

    @PostMapping("/c")
    public Iterable<Loan> findByCustomer(@RequestHeader(CORRELATION_ID) String correlationId,
                                         @RequestBody Customer customer) {
        log.info("Getting loans details for customer #{} started...", customer.getCustomerId());
        List<Loan> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(customer.getCustomerId());
        log.info("Getting loans details for customer #{} done.", customer.getCustomerId());
        return loans;
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
