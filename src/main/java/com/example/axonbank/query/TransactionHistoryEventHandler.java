package com.example.axonbank.query;

import com.example.axonbank.coreapi.MoneyDepositedEvent;
import com.example.axonbank.coreapi.MoneyWithdrawnEvent;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@ProcessingGroup("TransactionHistory")
@RestController
public class TransactionHistoryEventHandler {

    private final EntityManager entityManager;

    public TransactionHistoryEventHandler(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @EventHandler
    public void on(MoneyDepositedEvent event) {

        entityManager.persist(new TransactionHistory(event.getAccountId(), event.getTransactionId(), event.getAmount()));
    }

    @EventHandler
    public void on(MoneyWithdrawnEvent event) {

        entityManager.persist(new TransactionHistory(event.getAccountId(), event.getTransactionId(), - event.getAmount()));
    }

    @GetMapping("/history/{accountId}")
    public List<TransactionHistory> findTransaction(@PathVariable String accountId) {
        List<TransactionHistory> history = entityManager.createQuery("SELECT th FROM TransactionHistory th WHERE th.accountId = :accountId ORDER BY th.id")
                .setParameter("accountId", accountId)
                .getResultList();
        return history;
    }
}
