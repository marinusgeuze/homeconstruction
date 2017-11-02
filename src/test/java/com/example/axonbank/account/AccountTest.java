package com.example.axonbank.account;

import com.example.axonbank.coreapi.AccountCreatedEvent;
import com.example.axonbank.coreapi.CreateAccountCommand;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    private AggregateTestFixture<Account> fixture;

    @Before
    public void setUp() throws Exception {
        fixture = new AggregateTestFixture<>(Account.class);
    }

    @Test
    public void testCreateAccount() throws Exception {
        fixture.givenNoPriorActivity()
                .when(new CreateAccountCommand("1234", 1000))
                .expectEvents(new AccountCreatedEvent("1234", 1000));
    }
}