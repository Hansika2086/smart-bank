package com.example.demo.controller;

import com.example.demo.model.Transaction;
import com.example.demo.repository.TransactionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionRepository repo;

    public TransactionController(TransactionRepository repo) {
        this.repo = repo;
    }

    // ✅ Get ALL transactions (existing)
    @GetMapping
    public List<Transaction> getAll() {
        return repo.findAll();
    }

    // ✅ Get transactions for specific account (NEW)
    @GetMapping("/{id}")
    public List<Transaction> getByAccount(@PathVariable Long id) {
        return repo.findByAccountId(id);
    }
}