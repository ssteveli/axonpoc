package fitpay.axon.commandhandlers;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fitpay.axon.commands.CreateNymiRegistrationCommand;
import fitpay.axon.commands.UpdateNymiRegistrationCommand;
import fitpay.axon.domain.NymiRegistration;

@Component
public class NymiRegistrationCommandHandler {

    @Autowired
    private Repository<NymiRegistration> repo;
    
    @CommandHandler
    public void handle(final CreateNymiRegistrationCommand cmd) {
        NymiRegistration reg = new NymiRegistration(cmd);
        repo.add(reg);
    }
    
    @CommandHandler
    public void handle(final UpdateNymiRegistrationCommand cmd) {
        NymiRegistration reg = repo.load(cmd.getId());
        reg.update(cmd.getVkId(), cmd.getVkKey());
    }
}
