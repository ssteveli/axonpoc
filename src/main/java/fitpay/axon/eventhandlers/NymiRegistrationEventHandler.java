package fitpay.axon.eventhandlers;

import org.axonframework.eventhandling.annotation.EventHandler;

import fitpay.axon.events.NymiRegistrationCreatedEvent;

public class NymiRegistrationEventHandler {

    @EventHandler
    public void handle(NymiRegistrationCreatedEvent event) {
        System.out.println("nymi registration created: " + event.getId());
    }
    
}
