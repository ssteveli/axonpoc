package fitpay.axon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.interceptors.JSR303ViolationException;
import org.axonframework.repository.AggregateNotFoundException;
import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.DefaultUnitOfWork;
import org.axonframework.unitofwork.TransactionManager;
import org.axonframework.unitofwork.UnitOfWork;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fitpay.axon.commands.CreateNymiRegistrationCommand;
import fitpay.axon.commands.UpdateNymiRegistrationCommand;
import fitpay.axon.domain.NymiRegistration;

public class NymiRegistrationContinuousFeeder {

    private CommandGateway gw;
    
    private void run() throws Exception {
        ClassPathXmlApplicationContext ctx = 
                new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
                
        gw = ctx.getBean(CommandGateway.class);
        
        char[] vkId = new char[]{ '0','1','2','3','4','5','6','7','8','9','0','1','2','3','4','5' };
        char[] vkKey = new char[]{ 'a', 'b', 'c' };
        
        Random r = new Random(System.currentTimeMillis());
        List<String> ids = new ArrayList<String>();
        while (true) {
            CreateNymiRegistrationCommand cmd = new CreateNymiRegistrationCommand(vkId, vkKey);
            try {
                gw.send(cmd);
                ids.add(cmd.getId());
                
                if ((r.nextInt() % 3) == 0) {
                    gw.send(new UpdateNymiRegistrationCommand(cmd.getId(), cmd.getVkId(), new char[]{'x','y','z'}));
                }
            } catch (JSR303ViolationException e) {
                e.printStackTrace();
            }
            
            Thread.sleep(r.nextInt(10000));
        }
    }
    
    public static void main(String[] args) throws Exception {
        new NymiRegistrationContinuousFeeder().run();
    }
}
