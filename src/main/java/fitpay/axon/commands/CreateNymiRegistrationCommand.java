package fitpay.axon.commands;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CreateNymiRegistrationCommand {

    @TargetAggregateIdentifier
    protected final String id;
    
    @NotNull(message = "the vkId is required")
    @Size(min=16, max=16, message="the vkId must have a size from {min} to {max}")
    private final char[] vkId;
    
    private final char[] vkKey;

    public CreateNymiRegistrationCommand(char[] vkId, char[] vkKey) {
        this.id = UUID.randomUUID().toString();
        this.vkId = vkId;
        this.vkKey = vkKey;
    }

    public String getId() {
        return id;
    }

    public char[] getVkId() {
        return vkId;
    }

    public char[] getVkKey() {
        return vkKey;
    }

}
