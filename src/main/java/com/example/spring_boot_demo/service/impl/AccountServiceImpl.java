package com.example.spring_boot_demo.service.impl;

import com.example.spring_boot_demo.dto.AccountDto;
import com.example.spring_boot_demo.entity.Account;
import com.example.spring_boot_demo.mapper.AccountMapper;
import com.example.spring_boot_demo.repository.AccountRepository;
import com.example.spring_boot_demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Account Service Implementation
 */
@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    /**
     * AccountServiceImpl constructor
     *
     * @param accountRepository
     */
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Create Account
     *
     * @param accountDto
     * @return
     */
    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account newAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(newAccount);
    }

    /**
     * Get by Account Id
     *
     * @param id
     * @return AccountDto
     */
    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("No Account found for this Id"));
        return AccountMapper.mapToAccountDto(account);
    }

    /**
     * Deposit balance in Account
     *
     * @param id
     * @param amount
     * @return AccountDto
     */
    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("No Account found for this Id"));
        double newBalance = account.getBalance() + amount;
        account.setBalance(newBalance);
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(updatedAccount);
    }

    /**
     * Withdraw balance from Account
     *
     * @param id
     * @param amount
     * @return AccountDto
     */
    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("No Account found for this Id"));
        double balance = account.getBalance();
        if (balance < amount)
            throw new RuntimeException("Insufficient Balance");
        account.setBalance(balance - amount);
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(updatedAccount);
    }

    /**
     * Get all Accounts
     *
     * @return List<AccountDto>
     */
    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        return allAccounts.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
    }

}
