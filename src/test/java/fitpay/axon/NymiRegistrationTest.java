package fitpay.axon;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.junit.Before;
import org.junit.Test;

import fitpay.axon.commands.CreateNymiRegistrationCommand;
import fitpay.axon.domain.NymiRegistration;
import fitpay.axon.events.NymiRegistrationCreatedEvent;

public class NymiRegistrationTest {

    private FixtureConfiguration<NymiRegistration> fixture;
    
    @Before
    public void setup() throws Exception {
        fixture = Fixtures.newGivenWhenThenFixture(NymiRegistration.class);
    }
    
    @Test
    public void testCreateNymiRegistration() throws Exception {
        char[] vkId = new char[]{ '0' };
        char[] vkKey = new char[]{ 'a', 'b', 'c' };
        
        CreateNymiRegistrationCommand cmd = new CreateNymiRegistrationCommand(vkId, vkKey);
        fixture.given()
            .when(cmd)
            .expectEvents(new NymiRegistrationCreatedEvent(cmd.getId(), vkId, vkKey));
    }
}
