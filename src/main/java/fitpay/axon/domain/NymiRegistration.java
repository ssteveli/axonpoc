package fitpay.axon.domain;

import java.util.Arrays;
import java.util.Collections;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.domain.DomainEventMessage;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.AbstractEventSourcedAggregateRoot;
import org.axonframework.eventsourcing.EventSourcedEntity;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;

import fitpay.axon.commands.CreateNymiRegistrationCommand;
import fitpay.axon.commands.UpdateNymiRegistrationCommand;
import fitpay.axon.events.NymiRegistrationCreatedEvent;
import fitpay.axon.events.NymiRegistrationUpdatedEvent;

public class NymiRegistration extends AbstractEventSourcedAggregateRoot<String> {
    private static final long serialVersionUID = 5172048164793954016L;

    @AggregateIdentifier
    private String id;
    private char[] vkId;
    private char[] vkKey;
    
    public NymiRegistration() {
    }
    
    @CommandHandler
    public NymiRegistration(CreateNymiRegistrationCommand cmd) {
        apply(new NymiRegistrationCreatedEvent(cmd.getId(), cmd.getVkId(), cmd.getVkKey()));
    }
    
    @EventHandler
    public void on(NymiRegistrationCreatedEvent event) {
        this.id = event.getId();
        this.vkId = event.getVkId();
        this.vkKey = event.getVkKey();
    }

    @EventHandler
    public void on(NymiRegistrationUpdatedEvent event) {
        this.vkId = event.getVkId();
        this.vkKey = event.getVkKey();
        System.out.println("update isLive? " + isLive());
    }
    
    @CommandHandler
    public void update(UpdateNymiRegistrationCommand cmd) {
        this.vkId = cmd.getVkId();
        this.vkKey = cmd.getVkKey();
        apply(new NymiRegistrationUpdatedEvent(id, vkId, vkKey));
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
        System.out.println("msg: " + msg + ", payload: " + msg.getPayload());
        if (NymiRegistrationCreatedEvent.class.isAssignableFrom(msg.getPayloadType())) {
            on((NymiRegistrationCreatedEvent)msg.getPayload());
        } else if (NymiRegistrationUpdatedEvent.class.isAssignableFrom(msg.getPayloadType())) {
            on((NymiRegistrationUpdatedEvent)msg.getPayload());
        }
    }

    @Override
    public String toString() {
        return "NymiRegistration [id=" + id + ", vkId=" + Arrays.toString(vkId)
                + ", vkKey=" + Arrays.toString(vkKey) + "]";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public char[] getVkId() {
        return vkId;
    }

    public void setVkId(char[] vkId) {
        this.vkId = vkId;
    }

    public char[] getVkKey() {
        return vkKey;
    }

    public void setVkKey(char[] vkKey) {
        this.vkKey = vkKey;
    }
}
