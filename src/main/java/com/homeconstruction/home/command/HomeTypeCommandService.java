package com.homeconstruction.home.command;

import com.homeconstruction.home.api.DefineHomeType;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
public class HomeTypeCommandService {

    //TODO: Change this to async implementation
    private final CommandGateway commandGateway;

    public HomeTypeCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void defineHomeType(DefineHomeType defineHomeType) {

        commandGateway.send(defineHomeType, LoggingCallback.INSTANCE);
    }
}