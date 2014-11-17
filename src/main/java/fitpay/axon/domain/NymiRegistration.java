package fitpay.axon.domain;

import java.util.Arrays;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import fitpay.axon.commands.CreateNymiRegistrationCommand;
import fitpay.axon.events.NymiRegistrationCreatedEvent;
import fitpay.axon.events.NymiRegistrationUpdatedEvent;

public class NymiRegistration extends AbstractAnnotatedAggregateRoot<String> {
    private static final long serialVersionUID = 5172048164793954016L;

    @AggregateIdentifier
    private String id;
    private char[] vkId;
    private char[] vkKey;
    
    public NymiRegistration() {
    }
    
    public NymiRegistration(CreateNymiRegistrationCommand cmd) {
        this.id = cmd.getId();
        this.vkId = cmd.getVkId();
        this.vkKey = cmd.getVkKey();
        
        apply(new NymiRegistrationCreatedEvent(cmd.getId(), cmd.getVkId(), cmd.getVkKey()));
    }

    public void update(final char[] vkId, final char[] vkKey) {
        this.vkId = vkId;
        this.vkKey = vkKey;
        
        apply(new NymiRegistrationUpdatedEvent(id, vkId, vkKey));
    }

    @EventSourcingHandler
    public void handle(final NymiRegistrationCreatedEvent e) {
        this.id = e.getId();
        this.vkId = e.getVkId();
        this.vkKey = e.getVkKey();
    }
    
    @EventSourcingHandler
    public void handle(final NymiRegistrationUpdatedEvent e) {
        this.vkId = e.getVkId();
        this.vkKey = e.getVkKey();
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
