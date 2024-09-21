package net.javaproject.banking.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaproject.banking.dto.AccountDto;
import net.javaproject.banking.service.AccountService;

@RestController  //makes this class as spring mvc controller class. This tells Spring that this class will handle incoming HTTP requests.
@RequestMapping("/api/accounts") // base url for all the apis within the accountController class
public class AccountController {
    private AccountService accountService;

//AccountService is injected into the controller via the constructor
    // as we have only one constructor we dont use @autowire as spring will automatically inject the dependencies
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    
    // Add Account Rest API
    @PostMapping // for Post req method
    // below @requestbody will convert json to java object automatically
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
        return new ResponseEntity<>(accountService.createAccount(accountDto),HttpStatus.CREATED);  // first arg will return svaed account, second one is http status 
    }

    // Get Account Rest API
    @GetMapping("/{id}") // for Get Req Method
    // to bind value of id with method argument we use @pathvaribale
    public ResponseEntity<AccountDto> getAccountById(@PathVariable Long id){
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto); // internally writes http status as 200 ok
    }
}
