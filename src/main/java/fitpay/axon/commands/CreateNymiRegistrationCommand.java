package fitpay.axon.commands;

import java.util.UUID;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CreateNymiRegistrationCommand {

    @TargetAggregateIdentifier
    private final String id;
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
