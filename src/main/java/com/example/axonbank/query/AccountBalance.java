package com.example.axonbank.query;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AccountBalance {

    @Id
    private String accountId;
    private int balance;

    public AccountBalance() {
    }

    AccountBalance(String accountId, int balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
