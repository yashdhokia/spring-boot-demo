package com.example.spring_boot_demo.controller;

import com.example.spring_boot_demo.dto.AccountDto;
import com.example.spring_boot_demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Account controller class
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    /**
     * Constructor for to Initialize AccountService
     *
     * @param accountService
     */
    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * Create Account
     *
     * @param accountDto
     * @return
     */
    @PostMapping
    public ResponseEntity<AccountDto> createAccount(@RequestBody AccountDto accountDto) {
        AccountDto newAccountDto = accountService.createAccount(accountDto);
        return ResponseEntity.ok(newAccountDto);
    }

    /**
     * Get Account by Id
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getByAccountId(@PathVariable Long id) {
        AccountDto accountDto = accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);
    }

    /**
     * Deposit balance in Account
     *
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/deposit/{id}")
    public ResponseEntity<AccountDto> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        AccountDto accountDto = accountService.deposit(id, request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    /**
     * Withdraw balance from Account
     *
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/withdraw/{id}")
    public ResponseEntity<AccountDto> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
        AccountDto accountDto = accountService.withdraw(id, request.get("amount"));
        return ResponseEntity.ok(accountDto);
    }

    /**
     * Get all the Accounts in List
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<AccountDto>> getAllAccounts() {
        List<AccountDto> allAccounts = accountService.getAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }
}
