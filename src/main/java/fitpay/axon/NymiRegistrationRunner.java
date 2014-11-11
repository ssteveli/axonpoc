package fitpay.axon;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fitpay.axon.commands.CreateNymiRegistrationCommand;

public class NymiRegistrationRunner {

    private CommandGateway gw;
    
    private void run() {
        ClassPathXmlApplicationContext ctx = 
                new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
        gw = ctx.getBean(CommandGateway.class);
        
        char[] vkId = new char[]{ '0' };
        char[] vkKey = new char[]{ 'a', 'b', 'c' };
        
        for (int x=0; x<2; x++) {
            gw.send(new CreateNymiRegistrationCommand(vkId, vkKey));
        }
        
        ctx.close();
    }
    
    public static void main(String[] args) throws Exception {
        new NymiRegistrationRunner().run();
    }
}
