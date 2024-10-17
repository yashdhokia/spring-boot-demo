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

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account newAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(newAccount);
    }

    @Override
    public AccountDto getAccountById(Long id) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("No Account found for this Id"));
        return AccountMapper.mapToAccountDto(account);
    }

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

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account = accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("No Account found for this Id"));
        double balance = account.getBalance();
        if(balance < amount)
            throw new RuntimeException("Insufficient Balance");
        account.setBalance(balance - amount);
        Account updatedAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto(updatedAccount);
    }

    @Override
    public List<AccountDto> getAllAccounts() {
        List<Account> allAccounts = accountRepository.findAll();
        return allAccounts.stream().map(AccountMapper::mapToAccountDto).collect(Collectors.toList());
    }


}
