package com.example.spring_boot_demo.service;

import com.example.spring_boot_demo.dto.AccountDto;

import java.util.List;

/**
 * AccountService interface
 */
public interface AccountService {

    /**
     * Create Account method
     *
     * @param accountDto
     * @return
     */
    AccountDto createAccount(AccountDto accountDto);

    /**
     * Get by AccountId method
     *
     * @param id
     * @return
     */
    AccountDto getAccountById(Long id);

    /**
     * Deposit balance in Account
     *
     * @param id
     * @param amount
     * @return
     */
    AccountDto deposit(Long id, double amount);

    /**
     * Withdraw from Account
     *
     * @param id
     * @param amount
     * @return
     */
    AccountDto withdraw(Long id, double amount);

    /**
     * Get all Accounts
     *
     * @return
     */
    List<AccountDto> getAllAccounts();
}
