package fitpay.axon.events;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NymiRegistrationUpdatedEvent {

    private final String id;
    private final char[] vkId;
    private final char[] vkKey;
    
    public NymiRegistrationUpdatedEvent(
            @JsonProperty("id") String id, 
            @JsonProperty("vkId") char[] vkId, 
            @JsonProperty("vkKey") char[] vkKey) {
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

    @Override
    public String toString() {
        return "NymiRegistrationUpdatedEvent [id=" + id + ", vkId="
                + Arrays.toString(vkId) + ", vkKey=" + Arrays.toString(vkKey)
                + "]";
    }

}
