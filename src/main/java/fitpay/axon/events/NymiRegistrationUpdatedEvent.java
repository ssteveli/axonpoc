package fitpay.axon.events;

public class NymiRegistrationUpdatedEvent {

    private final String id;
    private final char[] vkId;
    private final char[] vkKey;
    
    public NymiRegistrationUpdatedEvent(String id, char[] vkId, char[] vkKey) {
        this.id = id;
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
