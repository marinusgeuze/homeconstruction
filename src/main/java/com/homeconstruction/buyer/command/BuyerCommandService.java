package com.homeconstruction.buyer.command;

import com.homeconstruction.buyer.api.AddBuyer;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
public class BuyerCommandService {

    //TODO: Change this to async implementation
    private final CommandGateway commandGateway;

    public BuyerCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void addBuyer(AddBuyer addBuyer) {

        commandGateway.send(addBuyer, LoggingCallback.INSTANCE);
    }
}
