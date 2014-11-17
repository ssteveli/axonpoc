package fitpay.axon.eventhandlers;

import org.axonframework.eventhandling.annotation.EventHandler;

import fitpay.axon.events.NymiRegistrationCreatedEvent;
import fitpay.axon.events.NymiRegistrationUpdatedEvent;

public class NymiRegistrationEventHandler {

    @EventHandler
    public void handle(NymiRegistrationCreatedEvent event) {
        System.out.println("nymi registration created: " + event);
    }
   
    @EventHandler
    public void handle(NymiRegistrationUpdatedEvent event) {
        System.out.println("nymi registration updated: " + event);
    }
}
