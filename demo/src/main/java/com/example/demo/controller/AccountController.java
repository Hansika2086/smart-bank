package com.example.demo.controller;
import com.example.demo.model.Account;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@CrossOrigin(origins="*")
@RequestMapping("/accounts")
public class AccountController{
    @Autowired
    private AccountService service;
    @PostMapping
    public Account create(@RequestBody Account account){
        return service.createAccount(account);
    }
    @GetMapping
    public List<Account> getAll(){
        return service.getAllAccounts();
    }
    @PutMapping("/deposit/{id}")
    public Account deposit(@PathVariable Long id,@RequestParam double amount){
        return service.deposit(id,amount);
    }
    @PutMapping("/withdraw/{id}")
    public Account withdraw(@PathVariable Long id,@RequestParam double amount){
        return service.withdraw(id,amount);
    }
}