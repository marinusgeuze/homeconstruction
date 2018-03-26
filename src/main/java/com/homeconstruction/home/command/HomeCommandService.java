package com.homeconstruction.home.command;

import com.homeconstruction.home.api.DefineHome;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeCommandService {

    //TODO: Change this to async implementation
    private final CommandGateway commandGateway;

    @Autowired
    public HomeCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void defineHome(DefineHome defineHome) {

        commandGateway.send(defineHome, LoggingCallback.INSTANCE);
    }
}
