package net.javaproject.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaproject.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
