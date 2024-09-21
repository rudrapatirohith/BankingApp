package net.javaproject.banking.mapper;

import net.javaproject.banking.dto.AccountDto;
import net.javaproject.banking.entity.Account;

public class AccountMapper {

    // mapping dto to entity
    public static Account mapToAccount(AccountDto accountDto){
        Account account= new Account(
        accountDto.getId(),
        accountDto.getAccountHolderName(),
        accountDto.getBalance()
        );
        return account;
    }

    // mapping entity to dto
    public static AccountDto mapToAccountDto(Account account){
        AccountDto accountDto = new AccountDto(
        account.getId(),
        account.getAccountHolderName(),
        account.getBalance()
        );
        return accountDto;
    }
}
