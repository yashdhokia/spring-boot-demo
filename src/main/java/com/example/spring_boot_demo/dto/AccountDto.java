package com.example.spring_boot_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Data Transfer Object
 */
@Data
@AllArgsConstructor
public class AccountDto {
    private Long id;
    private String accountHolderName;
    private double balance;
}
