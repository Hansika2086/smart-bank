package com.example.demo.model;
import jakarta.persistence.*;
@Entity
public class Account{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;
    private String holderName;
    private String email;
    private double balance;
    private AccountType accountType;
    public Account(){}
    public Long getId(){return id;}
    public String getAccountNumber(){return accountNumber;}
    public void setAccountNumber(String accountNumber){this.accountNumber=accountNumber;}
    public String getHolderName(){return holderName;}
    public void setHolderName(String holderName){this.holderName=holderName;}
    public String getEmail(){return email;}
    public void setEmail(String email){this.email=email;}
    public double getBalance(){return balance;}
    public void setBalance(double balance){this.balance=balance;}
    public AccountType getAccountType(){return accountType;}
    public void setAccountType(AccountType accountType){this.accountType=accountType;}
}