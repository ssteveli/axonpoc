package fitpay.axon.events;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NymiRegistrationCreatedEvent {

    private final String id;
    private final char[] vkId;
    private final char[] vkKey;
        
    public NymiRegistrationCreatedEvent(
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
        return "NymiRegistrationCreatedEvent [id=" + id + ", vkId="
                + Arrays.toString(vkId) + ", vkKey=" + Arrays.toString(vkKey)
                + "]";
    }

}
