package lab.dkataiev.ms.k8s.accounts.controller;

import lab.dkataiev.ms.k8s.accounts.config.AccountsServiceConfig;
import lab.dkataiev.ms.k8s.accounts.model.Account;
import lab.dkataiev.ms.k8s.accounts.model.Properties;
import lab.dkataiev.ms.k8s.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController("/")
public class AccountsController {

    private final AccountsRepository accountsRepository;
    private final AccountsServiceConfig accountsServiceConfig;

    @Autowired
    public AccountsController(AccountsRepository accountsRepository, AccountsServiceConfig accountsServiceConfig) {
        this.accountsRepository = accountsRepository;
        this.accountsServiceConfig = accountsServiceConfig;
    }

    @GetMapping("/c/{cId}")
    public Account findByCustomerId(@PathVariable("cId") Long cId) {
        return accountsRepository.findByCustomerId(cId).get();
    }

    @GetMapping("/properties")
    public Properties getProperties(){
        return Properties.builder()
                .msg(accountsServiceConfig.getMsg())
                .buildVersion(accountsServiceConfig.getBuildVersion())
                .mailDetails(accountsServiceConfig.getMailDetails())
                .activeBranches(accountsServiceConfig.getActiveBranches())
                .build();
    }

}
