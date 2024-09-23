package net.javaproject.banking.service.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import net.javaproject.banking.dto.AccountDto;
import net.javaproject.banking.entity.Account;
import net.javaproject.banking.mapper.AccountMapper;
import net.javaproject.banking.repository.AccountRepository;
import net.javaproject.banking.service.AccountService;

@Service  //to automatically create springbean for the class
public class AccountServiceImplimentation implements AccountService{

    private AccountRepository accountRepository;
    
    // as we have only one constructor we dont use @autowire as spring will automatically inject the dependencies
    public AccountServiceImplimentation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto); // converting to account(entity)
        Account savedAccount = accountRepository.save(account); // saving the account entity to database using repository 
        return AccountMapper.mapToAccountDto(savedAccount); // converting account(entity) back to dto
    }


    @Override
    public AccountDto getAccountById(Long id) { 
        Account account = accountRepository
                            .findById(id)  //search for an Account entity in the database by its ID.
                            .orElseThrow(()->new RuntimeException("Account does not Exist"));
        return AccountMapper.mapToAccountDto(account); //Once the Account entity is fetched from the database, it is converted into an AccountDto using the mapper class.
    }


    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
        .findById(id)  //search for an Account entity in the database by its ID.
        .orElseThrow(()->new RuntimeException("Account does not Exist"));
    
        double total = account.getBalance()+amount;
        account.setBalance(total);
        Account savedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(savedAccount);
    }


    @Override
    public AccountDto withdraw(Long id, double amount) {
       Account account = accountRepository
       .findById(id)
       .orElseThrow(()-> new RuntimeException("Account does not exist"));

       if(account.getBalance()<amount){
        throw new RuntimeException("Insufficient Amount");
       }
       double total = account.getBalance()-amount;
       account.setBalance(total);
       Account savedAccount = accountRepository.save(account);
       return AccountMapper.mapToAccountDto(savedAccount);
    }


    @Override
    public List<AccountDto> getAllAccounts() {
     
        List<Account> accounts = accountRepository.findAll(); // get all account entities form db and store in accpount objects

        return accounts.stream() // convert list of acc entites into a stream for processing
        .map((account)->AccountMapper.mapToAccountDto(account))  // converting it to accountdto object
        .collect(Collectors.toList()); // collects mapped accountdto obj into a list
    }


    @Override
    public void deleteAccount(Long id) {
    
        @SuppressWarnings("unused")
        Account account = accountRepository
        .findById(id)
        .orElseThrow(()-> new RuntimeException("Account does not exist"));

        accountRepository.deleteById(id);
    }


    

}
