package lab.dkataiev.ms.k8s.accounts.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lab.dkataiev.ms.k8s.accounts.config.AccountsServiceConfig;
import lab.dkataiev.ms.k8s.accounts.model.Account;
import lab.dkataiev.ms.k8s.accounts.model.Card;
import lab.dkataiev.ms.k8s.accounts.model.Customer;
import lab.dkataiev.ms.k8s.accounts.model.CustomerDetails;
import lab.dkataiev.ms.k8s.accounts.model.Loan;
import lab.dkataiev.ms.k8s.accounts.model.Properties;
import lab.dkataiev.ms.k8s.accounts.repository.AccountsRepository;
import lab.dkataiev.ms.k8s.accounts.service.client.CardsFeignClient;
import lab.dkataiev.ms.k8s.accounts.service.client.LoansFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController("/")
public class AccountsController {

    private final AccountsRepository accountsRepository;
    private final AccountsServiceConfig accountsServiceConfig;

    private final CardsFeignClient cardsClient;
    private final LoansFeignClient loansClient;

    @Autowired
    public AccountsController(AccountsRepository accountsRepository, AccountsServiceConfig accountsServiceConfig,
                              CardsFeignClient cardsClient, LoansFeignClient loansClient) {
        this.accountsRepository = accountsRepository;
        this.accountsServiceConfig = accountsServiceConfig;
        this.cardsClient = cardsClient;
        this.loansClient = loansClient;
    }

    @GetMapping("/c/{cId}")
    public Account findByCustomerId(@PathVariable("cId") Long cId) {
        return accountsRepository.findByCustomerId(cId).orElseThrow();
    }

    @GetMapping("/properties")
    public Properties getProperties() {
        return Properties.builder()
                .msg(accountsServiceConfig.getMsg())
                .buildVersion(accountsServiceConfig.getBuildVersion())
                .mailDetails(accountsServiceConfig.getMailDetails())
                .activeBranches(accountsServiceConfig.getActiveBranches())
                .build();
    }

    @PostMapping("/details")
    @CircuitBreaker(name="customerDetailsApp", fallbackMethod = "customerDetailsFallback")
    public CustomerDetails getCustomerDetails(@RequestBody Customer customer) {
        Account account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow();
        List<Loan> loansDetails = loansClient.getLoansDetails(customer);
        List<Card> cardsDetails = cardsClient.getCardsDetails(customer);
        return CustomerDetails.builder()
                .account(account)
                .cards(cardsDetails)
                .loans(loansDetails)
                .build();
    }

    private CustomerDetails customerDetailsFallback(Customer customer, Throwable t){
        Account account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow();
        List<Loan> loans = loansClient.getLoansDetails(customer);
        return CustomerDetails.builder()
                .account(account)
                .loans(loans)
                .build();
    }

}
