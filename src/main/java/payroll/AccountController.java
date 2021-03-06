package payroll;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class AccountController {

    private final AccountRepository repository;

    AccountController(AccountRepository repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/accounts")
    List<Account> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/accounts")
    Account newAccount(@RequestBody Account newAccount) {
        return repository.save(newAccount);
    }

    // Single item

    @GetMapping("/accounts/{id}")
    Account one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @PutMapping("/accounts/{id}")
    Account replaceAccount(@RequestBody Account newAccount, @PathVariable Long id) {

        return repository.findById(id)
                .map(account -> {
                    account.setName(newAccount.getName());
                    account.setBalance(newAccount.getBalance());
                    return repository.save(account);
                })
                .orElseGet(() -> {
                    newAccount.setId(id);
                    return repository.save(newAccount);
                });
    }

//    @PostMapping("/accounts/{id}/transaction")
//    Account postTransaction(@PathVariable Long id, @RequestBody String transactionType, @RequestBody Double transactionAmount) {
//        return repository.findById(id)
//                .map(account -> {
//                    if(transactionType.equals("deposit")) {
//                        account.setBalance(account.getBalance() + transactionAmount);
//                        return repository.save(account);
//                    } else if (transactionType.equals("withdrawal")) {
//                        account.setBalance(account.getBalance() - transactionAmount);
//                        return repository.save(account);
//                    }
//
//                });
//    }

    @DeleteMapping("/accounts/{id}")
    void deleteAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }
}