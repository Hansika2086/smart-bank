package com.example.demo.service;
import com.example.demo.service.EmailService;
import com.example.demo.model.Account;
import com.example.demo.model.Transaction;
import com.example.demo.repository.AccountRepository;
import com.example.demo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.LocalDateTime;

@Service
public class AccountService{
    @Autowired
    private EmailService emailService;
    @Autowired
    private AccountRepository repository;
    @Autowired
    private TransactionRepository transactionRepository;
    public Account createAccount(Account account){
        return repository.save(account);
    }
    public List<Account> getAllAccounts(){
        return repository.findAll();
    }
    public Account deposit(Long id,double amount){
        Account acc=repository.findById(id).orElse(null);
        if(acc!=null){
            acc.setBalance(acc.getBalance()+amount);
            repository.save(acc);

            Transaction t=new Transaction();
            t.setType("DEPOSIT");
            t.setAmount(amount);
            t.setDate(LocalDateTime.now());
            t.setAccount(acc);
            transactionRepository.save(t);
            emailService.sendEmail(
                    acc.getEmail(),
                    "Smart Bank Deposit Alert",
                    "Dear " + acc.getHolderName() + ",\n\n₹" + amount +
                            " has been deposited.\nBalance: ₹" + acc.getBalance() +
                            "\n\n- Smart Bank"
            );

            return acc;
        }
        return null;
    }
    public Account withdraw(Long id,double amount){
        Account acc=repository.findById(id).orElse(null);
        if(acc!=null && acc.getBalance()>=amount){
            acc.setBalance(acc.getBalance()-amount);
            repository.save(acc);

            Transaction t=new Transaction();
            t.setType("WITHDRAW");
            t.setAmount(amount);
            t.setDate(LocalDateTime.now());
            t.setAccount(acc);
            transactionRepository.save(t);

            emailService.sendEmail(
                    acc.getEmail(),
                    "Smart Bank Withdrawal Alert",
                    "Dear " + acc.getHolderName() + ",\n\n₹" + amount +
                            " has been withdrawn.\nBalance: ₹" + acc.getBalance() +
                            "\n\n- SmartBank"
            );

            return acc;
        }
        return null;
    }
}