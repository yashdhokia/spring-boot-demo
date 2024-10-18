package com.example.spring_boot_demo.service.impl;

import com.example.spring_boot_demo.dto.AccountDto;
import com.example.spring_boot_demo.entity.Account;
import com.example.spring_boot_demo.mapper.AccountMapper;
import com.example.spring_boot_demo.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@SpringBootTest
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    @Test
    void createAccount() {
        String accountHolderName = "Yash";
        double balance = 40000;
        AccountDto accountDto = new AccountDto(1L, accountHolderName, balance);
        Account account = new Account(1L, accountHolderName, balance);
        MockedStatic<AccountMapper> accountMapperMockedStatic = mockStatic(AccountMapper.class);
        accountMapperMockedStatic.when(() -> AccountMapper.mapToAccount(accountDto)).thenReturn(account);
        accountMapperMockedStatic.when(() -> AccountMapper.mapToAccountDto(account)).thenReturn(accountDto);
        when(accountRepository.save(account)).thenReturn(account);
        AccountDto result = accountServiceImpl.createAccount(accountDto);
        assertEquals(accountHolderName, result.getAccountHolderName());
    }
}