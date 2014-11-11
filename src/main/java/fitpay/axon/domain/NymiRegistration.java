package fitpay.axon.domain;

import java.util.Collections;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.AbstractEventSourcedAggregateRoot;
import org.axonframework.eventsourcing.EventSourcedEntity;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import fitpay.axon.commands.CreateNymiRegistrationCommand;
import fitpay.axon.events.NymiRegistrationCreatedEvent;

public class NymiRegistration extends AbstractEventSourcedAggregateRoot<String> {
    private static final long serialVersionUID = 5172048164793954016L;

    @AggregateIdentifier
    private String id;
    
    public NymiRegistration() {
    }
    
    @CommandHandler
    public NymiRegistration(CreateNymiRegistrationCommand cmd) {
        apply(new NymiRegistrationCreatedEvent(cmd.getId(), cmd.getVkId(), cmd.getVkKey()));
    }
    
    @EventHandler
    public void on(NymiRegistrationCreatedEvent event) {
        this.id = event.getId();
    }

    public String getIdentifier() {
        return id;
    }

    @Override
    protected Iterable<? extends EventSourcedEntity> getChildEntities() {
        return Collections.emptyList();
    }

    @Override
    protected void handle(@SuppressWarnings("rawtypes") DomainEventMessage msg) {
        System.out.println("msg: " + msg);
        if (NymiRegistrationCreatedEvent.class.isAssignableFrom(msg.getPayloadType())) {
            this.id = ((NymiRegistrationCreatedEvent)msg.getPayload()).getId();
        }
    }
}
