package fitpay.axon;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.repository.AggregateNotFoundException;
import org.axonframework.repository.Repository;
import org.axonframework.unitofwork.DefaultUnitOfWork;
import org.axonframework.unitofwork.TransactionManager;
import org.axonframework.unitofwork.UnitOfWork;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import fitpay.axon.commands.CreateNymiRegistrationCommand;
import fitpay.axon.commands.UpdateNymiRegistrationCommand;
import fitpay.axon.domain.NymiRegistration;

public class NymiRegistrationRunner {

    private CommandGateway gw;
    
    private void run() {
        ClassPathXmlApplicationContext ctx = 
                new ClassPathXmlApplicationContext("classpath:/applicationContext.xml");
                
        gw = ctx.getBean(CommandGateway.class);
        
        char[] vkId = new char[]{ '0' };
        char[] vkKey = new char[]{ 'a', 'b', 'c' };
        
        Random r = new Random(System.currentTimeMillis());
        List<String> ids = new ArrayList<String>();
        for (int x=0; x<10; x++) {
            CreateNymiRegistrationCommand cmd = new CreateNymiRegistrationCommand(vkId, vkKey);
            gw.send(cmd);
            ids.add(cmd.getId());
            
            if ((r.nextInt() % 3) == 0) {
                gw.send(new UpdateNymiRegistrationCommand(cmd.getId(), cmd.getVkId(), new char[]{'x','y','z'}));
            }
        }
        
        @SuppressWarnings("unchecked")
        Repository<NymiRegistration> repo = ctx.getBean("nymiRegistrationRepository", Repository.class);
        @SuppressWarnings("rawtypes")
        TransactionManager tm = ctx.getBean(TransactionManager.class);
        
        UnitOfWork uow = DefaultUnitOfWork.startAndGet(tm);
        NymiRegistration savedReg = repo.load("2c88bfbd-2081-4dd2-ad14-e5f20dfa6688");
        System.out.println("saved Reg: " + savedReg);

        try {
            repo.load("123");
        } catch (AggregateNotFoundException e) {
            System.err.println("not found: " + e.getMessage());
        }
        
        for (String id : ids) {
            System.out.println("loading aggregate: " + id);
            
            NymiRegistration reg = repo.load(id);
            System.out.println(reg);
            
            if ((r.nextInt() % 5) == 0) {
                gw.sendAndWait(new UpdateNymiRegistrationCommand(id, reg.getVkId(), new char[]{'d','e'}));
            }
        }
        uow.commit();
        
        ctx.close();
    }
    
    public static void main(String[] args) throws Exception {
        new NymiRegistrationRunner().run();
    }
}
