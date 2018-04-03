package com.homeconstruction.home.command;

import com.homeconstruction.home.api.DefineHome;
import com.homeconstruction.home.api.ReserveHome;
import com.homeconstruction.home.api.SellHome;
import com.homeconstruction.home.api.StartHomeConstruction;
import org.axonframework.commandhandling.callbacks.LoggingCallback;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

@Service
public class HomeCommandService {

    //TODO: Change this to async implementation
    private final CommandGateway commandGateway;

    public HomeCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public void defineHome(DefineHome defineHome) {

        commandGateway.send(defineHome, LoggingCallback.INSTANCE);
    }

    public void reserveHome(ReserveHome reserveHome) {

        commandGateway.send(reserveHome, LoggingCallback.INSTANCE);
    }

    public void sellHome(SellHome sellHome) {

        commandGateway.send(sellHome, LoggingCallback.INSTANCE);
    }

    public void startHomeConstruction(StartHomeConstruction startHomeConstruction) {

        commandGateway.send(startHomeConstruction, LoggingCallback.INSTANCE);
    }
}
