package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.dto.AccountDto;

import java.util.List;

public interface AccountService {

    AccountDto createAccount(AccountDto accountDto);

    AccountDto getAccountById(Long id);

    AccountDto deposit(Long id, double amount);

    AccountDto withdraw(Long id, double amount);

    List<AccountDto> getAllAccounts();
}
