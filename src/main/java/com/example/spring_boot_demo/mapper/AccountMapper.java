package com.example.spring_boot_demo.mapper;

import com.example.spring_boot_demo.dto.AccountDto;
import com.example.spring_boot_demo.entity.Account;

/**
 * Account Mapper class to map from AccountDto to Account and Account to AccountDto
 */
public class AccountMapper {

    /**
     * Convert AccountDto to Account
     *
     * @param accountDto
     * @return
     */
    public static Account mapToAccount(AccountDto accountDto){
        return new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
    }

    /**
     * Convert Account to AccountDto
     *
     * @param account
     * @return
     */
    public static AccountDto mapToAccountDto(Account account){
        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }
}
