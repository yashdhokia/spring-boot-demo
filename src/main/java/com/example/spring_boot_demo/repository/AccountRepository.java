package com.example.spring_boot_demo.repository;

import com.example.spring_boot_demo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
