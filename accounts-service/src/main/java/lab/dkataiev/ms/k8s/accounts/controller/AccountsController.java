package lab.dkataiev.ms.k8s.accounts.controller;

import lab.dkataiev.ms.k8s.accounts.model.Account;
import lab.dkataiev.ms.k8s.accounts.repository.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class AccountsController {

    private final AccountsRepository accountsRepository;

    @Autowired
    public AccountsController(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @GetMapping("/c/{cId}")
    public Account findByCustomerId(@PathVariable("cId") Long cId){
        return accountsRepository.findByCustomerId(cId).get();
    }

}
