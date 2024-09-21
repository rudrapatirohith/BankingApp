package net.javaproject.banking.service.implementation;

import org.springframework.stereotype.Service;

import net.javaproject.banking.dto.AccountDto;
import net.javaproject.banking.entity.Account;
import net.javaproject.banking.mapper.AccountMapper;
import net.javaproject.banking.repository.AccountRepository;
import net.javaproject.banking.service.AccountService;

@Service  //to automatically create springbean for the class
public class AccountServiceImplimentation implements AccountService{

    private AccountRepository accountRepository;
    
    // as we have only pne constructor we dont use @autowire as spring will automatically inject the dependencies
    public AccountServiceImplimentation(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto); // converting to account(entity)
        Account savedAccount = accountRepository.save(account); // saving the account entity to database using repository 
        return AccountMapper.mapToAccountDto(savedAccount); // converting account(entity) back to dto
    }

}
