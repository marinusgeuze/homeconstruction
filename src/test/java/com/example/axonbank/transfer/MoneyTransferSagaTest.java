package com.example.axonbank.transfer;

import com.example.axonbank.coreapi.*;
import org.axonframework.test.saga.SagaTestFixture;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoneyTransferSagaTest {

    private SagaTestFixture fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new SagaTestFixture<>(MoneyTransferSaga.class);
    }

    @Test
    public void testMoneyTransferRequest() {
        fixture.givenNoPriorActivity()
                .whenPublishingA(new MoneyTransferRequestedEvent(
                        "tf1", "acct1", "acct2", 100))
                .expectActiveSagas(1)
                .expectDispatchedCommands(new WithdrawMoneyCommand("acct1", "tf1", 100));

    }

    @Test
    public void testDepositMoneyAfterWithdrawal() {
        fixture.givenAPublished(new MoneyTransferRequestedEvent("tf1", "acct1", "acct2", 100))
                .whenPublishingA(new MoneyWithdrawnEvent("acct1", "tf1", 100, 500))
                .expectDispatchedCommands(new DepositMoneyCommand("acct2", "tf1",100));
    }

    @Test
    public void testTransferCompletedAfterDeposit() throws Exception {
        fixture.givenAPublished(new MoneyTransferRequestedEvent("tf1", "acct1", "acct2", 100))
                .andThenAPublished(new MoneyWithdrawnEvent("acct1", "tf1", 100, 500))
                .whenPublishingA(new MoneyDepositedEvent("acct2", "tf1", 100, 400))
                .expectDispatchedCommands(new CompleteMoneyTransferCommand("tf1"));
    }

    @Test
    public void testSageEndsAfterTransactionCompleted() throws Exception {
        fixture.givenAPublished(new MoneyTransferRequestedEvent("tf1", "acct1", "acct2", 100))
                .andThenAPublished(new MoneyWithdrawnEvent("acct1", "tf1", 100, 500))
                .andThenAPublished(new MoneyDepositedEvent("acct2", "tf1", 100, 400))
                .whenPublishingA(new MoneyTransferCompletedEvent("tf1"))
                .expectActiveSagas(0)
                .expectNoDispatchedCommands();
    }
}