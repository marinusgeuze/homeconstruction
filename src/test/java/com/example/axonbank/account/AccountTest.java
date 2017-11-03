package com.example.axonbank.account;

import com.example.axonbank.coreapi.*;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    private static final String ACCOUNT_ID = "1234";

    private AggregateTestFixture<Account> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(Account.class);
    }

    @Test
    public void testCreateAccount() throws Exception {
        fixture.givenNoPriorActivity()
                .when(new CreateAccountCommand(ACCOUNT_ID, 1000))
                .expectEvents(new AccountCreatedEvent(ACCOUNT_ID, 1000));
    }

    @Test
    public void testWithdrawReasonableAmount() throws Exception {
        fixture.given(new AccountCreatedEvent(ACCOUNT_ID, 1000))
                .when(new WithdrawMoneyCommand(ACCOUNT_ID, "tx1",500))
                .expectEvents(new MoneyWithdrawnEvent(ACCOUNT_ID, "tx1",500, -500));
    }

    @Test
    public void testWithdrawAbsurdAmount() throws Exception {
        fixture.given(new AccountCreatedEvent(ACCOUNT_ID, 1000))
                .when(new WithdrawMoneyCommand(ACCOUNT_ID, "tx1",1001))
                .expectNoEvents()
                .expectException(OverdraftLimitExceededException.class);
    }

    @Test
    public void testWithdrawTwice() {
        fixture.given(new AccountCreatedEvent(ACCOUNT_ID, 1000),
                new MoneyWithdrawnEvent(ACCOUNT_ID, "tx1",999, -999))
                .when(new WithdrawMoneyCommand(ACCOUNT_ID, "tx1", 2))
                .expectNoEvents()
                .expectException(OverdraftLimitExceededException.class);
    }
}