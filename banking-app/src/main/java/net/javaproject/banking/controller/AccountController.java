package net.javaproject.banking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
        AccountDto accountDto = accountService.getAccountById(id); // calls the service layer to fetch the account by its id.
        return ResponseEntity.ok(accountDto); // internally writes http status as 200 ok
    }

    // DepositAmount Rest API
    @PutMapping("/{id}/deposit")   // for put req and api url
    // i used @pathvariable to bind the url with the id in method argument, i used @reqbody to convert json to java map object so that it can read the value
    public ResponseEntity<AccountDto> depositAmount(@PathVariable Long id, @RequestBody Map<String, Double> request){
        
        Double amount = request.get("amount");  // ia m taking the valye of the amounnt by using its key
        AccountDto accountDto = accountService.deposit(id, amount); //here the deposit method in acc serv imp will be called and the update logic will work and give the final updated data in repsosne with ok 
        return ResponseEntity.ok(accountDto);
    }


    // Withdraw Rest API
    @PutMapping("/{id}/withdraw")   // for put req and api url
        // i used @pathvariable to bind the url with the id in method argument, i used @reqbody to convert json to java map object so that it can read the value
    public ResponseEntity<AccountDto> withdrawAmount(@PathVariable Long id, @RequestBody Map<String, Double> request){

        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }


    // Get All Accounts Rest API
    @GetMapping
    public ResponseEntity<List<AccountDto>> getAllAccounts(){

        List<AccountDto> accounts = accountService.getAllAccounts();
        return ResponseEntity.ok(accounts);
    }


    //Delete Account Rest API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id){   // as i am showing string in repsonse i gave spring here in responseentity
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account is Deleted Successfully!");
    }
}
